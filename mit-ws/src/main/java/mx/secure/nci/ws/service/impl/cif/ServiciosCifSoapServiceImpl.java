package mx.secure.nci.ws.service.impl.cif;

import mx.secure.nci.business.report.IReportesService;
import mx.secure.nci.business.report.beans.DetalleArchivoCifReportBean;
import mx.secure.nci.business.service.IServiciosCifService;
import mx.secure.nci.business.util.enums.CtrlResponseWSEnum;
import mx.secure.nci.business.vo.ActualizaEstatusCifVO;
import mx.secure.nci.business.vo.ArchivoDetalleCifVO;
import mx.secure.nci.business.vo.DetalleMovimientosCifVO;
import mx.secure.nci.business.vo.DevolucionessecureCifVO;
import mx.secure.nci.business.vo.PendientesTimbradoCifVO;
import mx.secure.nci.business.vo.ReporteVO;
import mx.secure.nci.business.vo.ValidacionesVO;
import mx.secure.nci.ws.beans.ReportBean;
import mx.secure.nci.ws.beans.request.ActualizaEstatusCifBeanRequest;
import mx.secure.nci.ws.beans.request.DetalleArchivoCifBeanRequest;
import mx.secure.nci.ws.beans.request.DetalleMovCifBeanRequest;
import mx.secure.nci.ws.beans.request.PendientesTimbradoCifBeanRequest;
import mx.secure.nci.ws.beans.request.ValidarResultLoteCifBeanRequest;
import mx.secure.nci.ws.beans.response.ActualizaEstatusCifBeanResponse;
import mx.secure.nci.ws.beans.response.DetalleArchivoCifBeanResponse;
import mx.secure.nci.ws.beans.response.DetalleMovCifBeanResponse;
import mx.secure.nci.ws.beans.response.PendientesTimbradoCifBeanResponse;
import mx.secure.nci.ws.beans.response.ValidarResultLoteCifBeanResponse;
import mx.secure.nci.ws.exceptions.FaultBeanServiceInfo;
import mx.secure.nci.ws.exceptions.MitWebServiceException;
import mx.secure.nci.ws.service.cif.IServiciosCifSoapService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviciosCifSoapService")
public class ServiciosCifSoapServiceImpl implements IServiciosCifSoapService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiciosCifSoapServiceImpl.class);
	private static final int ZERO = 0;


	@Autowired
	IServiciosCifService iServiciosCifService;
	@Autowired
	IReportesService reportesService;
	
	@Override
	public ActualizaEstatusCifBeanResponse actualizaEstatusCif(ActualizaEstatusCifBeanRequest actualizaCifBeanRequest) {
		ValidacionesVO validacion = null;
		try {
			if( actualizaCifBeanRequest == null ) {
				return new ActualizaEstatusCifBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,"REQUEST necesario"
						);
			}			
			if( !(validacion = actualizaCifBeanRequest.validarRequest()).isEstatus() ) {
				return new ActualizaEstatusCifBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,validacion.getMensaje()
						);
			}		
			Integer rgsAct = iServiciosCifService.actualizaEstatusCif( (ActualizaEstatusCifVO) validacion.getObjetoAux());
			Integer rgsActDatamart = iServiciosCifService.actualizaEstatusCifDatamarts( (ActualizaEstatusCifVO) validacion.getObjetoAux());
			if(rgsAct != null && rgsAct > 0 && rgsActDatamart != null && rgsActDatamart > 0) {
				return new ActualizaEstatusCifBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Actualizacion Exitosa", rgsAct + rgsActDatamart);			
			}else if(rgsAct != null && rgsAct > 0 ) {
				return new ActualizaEstatusCifBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Actualizacion Exitosa", rgsAct);			
			}else if(rgsActDatamart != null && rgsActDatamart > 0 ) {
				return new ActualizaEstatusCifBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Actualizacion Exitosa", rgsActDatamart);			
			}else {
				return new ActualizaEstatusCifBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "No se encontraron registros para actualizar con ese folio");			
			}					
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACION     :" + ServiciosCifSoapServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "actualizaEstatusCif" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION   :" , e );
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet()+", actualizaEstatusIdc");
			throw new MitWebServiceException(e.getMessage(), missing);
		}finally {
			validacion			= null;
		}
	}
	
	
	
	public DetalleArchivoCifBeanResponse obtenerDetalleArchivoCif(DetalleArchivoCifBeanRequest request) {
		ValidacionesVO validacion = null;
		List<ArchivoDetalleCifVO> ltsDetalleCif = null;
		List<DetalleArchivoCifReportBean> ltsDetalleArchivoCifBean = null;
		DetalleArchivoCifReportBean detalleArchivoCifBean = null;
		
		try {
			if (request == null) {
				return new DetalleArchivoCifBeanResponse (
						CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						, CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						, "REQUEST necesario");
			}
			if (!(validacion = request.validarRequest()).isEstatus() ) {
				return new DetalleArchivoCifBeanResponse (
						CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						, CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						, validacion.getMensaje()
						);
			}
			ltsDetalleCif = iServiciosCifService.obtenerListaDetalleCif((ArchivoDetalleCifVO)validacion.getObjetoAux());
			
			if(ltsDetalleCif != null && ltsDetalleCif.size() > ZERO) {
				ltsDetalleArchivoCifBean = new ArrayList<DetalleArchivoCifReportBean>();
				LOGGER.info("*******************************************************************************************************");
				for(ArchivoDetalleCifVO item : ltsDetalleCif) {
					detalleArchivoCifBean = new DetalleArchivoCifReportBean();
					detalleArchivoCifBean.setPortafolio(item.getPortafolio());
					detalleArchivoCifBean.setEntradas(item.getEntradas());
					detalleArchivoCifBean.setNumRegEnt(item.getNumRegEnt());
					detalleArchivoCifBean.setSalidas(item.getSalidas());
					detalleArchivoCifBean.setNumRegSal(item.getNumRegSal());
					detalleArchivoCifBean.setEstatus(item.getEstatus());
					LOGGER.info("ITEM"+item.getPortafolio());
					ltsDetalleArchivoCifBean.add(detalleArchivoCifBean);
				}
				LOGGER.info("*******************************************************************************************************");
			} else {
				return new DetalleArchivoCifBeanResponse(
						CtrlResponseWSEnum.WS_OK.getCodRet()
						, CtrlResponseWSEnum.WS_OK.getMsgRet()
						, "No fue posible obtener la informacion con el detalle de CIF para el folio");
			}
			
			ReporteVO reporteVO = reportesService.generarReporteDetalleCif(ltsDetalleArchivoCifBean);
			if(reporteVO != null) {
				ReportBean reportBean = new ReportBean();
				reportBean.setArchivo(reporteVO.getArchivo());
				reportBean.setNombre(reporteVO.getNombre());
				return new DetalleArchivoCifBeanResponse(
						CtrlResponseWSEnum.WS_OK.getCodRet()
						, CtrlResponseWSEnum.WS_OK.getMsgRet()
						, "Se genero el reporte con el detalle de CIF para el folio"
						, reportBean, ltsDetalleCif);
			} else {
				return new DetalleArchivoCifBeanResponse(
						CtrlResponseWSEnum.WS_OK.getCodRet()
						, CtrlResponseWSEnum.WS_OK.getMsgRet()
						, "No fue posible generar el reporte con el detalle de CIF"
						, ltsDetalleCif);
			}
		}catch(final Exception e) {
			LOGGER.error( "ERROR UBICACION     :" + ServiciosCifSoapServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "obtenerDetalleArchivoCif" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION   :" , e );
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet()+", obtenerDetalleArchivoCif");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			validacion = null;
			ltsDetalleCif = null;
			ltsDetalleArchivoCifBean = null;
			detalleArchivoCifBean = null;
		}
	}
	
	
	public DetalleMovCifBeanResponse getDetalleMovimientosCif(DetalleMovCifBeanRequest request) {
		LOGGER.info("En el metodo getDetalleMovimientosCif");
		ValidacionesVO validacion = null;
		List<DetalleMovimientosCifVO> lstDetalleMovCif = null;
		try {
			if ( request == null ) {
				return new DetalleMovCifBeanResponse(
						CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						, CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						, "REQUEST necesario");
			}
			if ( !(validacion = request.validarRequest()).isEstatus() ) {
				return new DetalleMovCifBeanResponse(
						CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						, CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						, validacion.getMensaje());
			}
			lstDetalleMovCif = iServiciosCifService.obtenerListaDetalleMovCif((DetalleMovimientosCifVO) validacion.getObjetoAux());
			if (lstDetalleMovCif != null && lstDetalleMovCif.size() > 0) {
				return new DetalleMovCifBeanResponse(
						CtrlResponseWSEnum.WS_OK.getCodRet()
						, CtrlResponseWSEnum.WS_OK.getMsgRet()
						, "Se obtuvo el detalle de movimientos Cif"
						, lstDetalleMovCif);
			} else {
				return new DetalleMovCifBeanResponse(
						CtrlResponseWSEnum.WS_OK.getCodRet()
						, CtrlResponseWSEnum.WS_OK.getMsgRet()
						, "No se identificaron detalle de movimientos Cif para el folio");
			}
		} catch(final Exception e) {
			LOGGER.error( "ERROR UBICACION     :" + ServiciosCifSoapServiceImpl.class.getCanonicalName());
			LOGGER.error( "ERROR METODO        :" + "getDetalleMovimientosCif" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION   :" , e );
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet()+", getDetalleMovimientosCif");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			validacion = null;
		}
	}
	

	public PendientesTimbradoCifBeanResponse getListaPendientesTimbradoCif(PendientesTimbradoCifBeanRequest request) {
		ValidacionesVO validacion = null;
		List<PendientesTimbradoCifVO> lstDevolucionessecure = null;
		try {
			if (request == null) {
				return new PendientesTimbradoCifBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "REQUEST necesario");
			}
			if (!(validacion = request.validarRequest()).isEstatus()) {
				return new PendientesTimbradoCifBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), validacion.getMensaje());
			}

			lstDevolucionessecure = iServiciosCifService
					.obtenerDevolucionessecure((PendientesTimbradoCifVO) validacion.getObjetoAux());
			if (!lstDevolucionessecure.isEmpty()) {
				return new PendientesTimbradoCifBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Consulta exitosa", lstDevolucionessecure);
			} else {
				return new PendientesTimbradoCifBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(),
						"No se encontraron registros con los criterios ingresados");
			}
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACION     :" + ServiciosCifSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO        :" + "getListaPendientesTimbradoCif");
			LOGGER.error("ERROR MENSAJE       :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION   :", e);
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", getListaPendientesTimbradoCif");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			validacion = null;
			lstDevolucionessecure = null;
		}
	}
	
	
	public ValidarResultLoteCifBeanResponse validarResultadoLoteCif(ValidarResultLoteCifBeanRequest request) {
		ValidacionesVO validacion = null;
	    List<DevolucionessecureCifVO>lstDevolucionessecure = null;
		try {
			if( request == null ) {
				return new ValidarResultLoteCifBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,"El request es necesario"
						);
			}
			if( !(validacion = request.validarRequest()).isEstatus()) {
				return new ValidarResultLoteCifBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,validacion.getMensaje()
						);
			}
			
			lstDevolucionessecure = iServiciosCifService.obtenerListaDevolucionessecureCif((DevolucionessecureCifVO)validacion.getObjetoAux());
			if(lstDevolucionessecure != null && !lstDevolucionessecure.isEmpty()) {
				return new ValidarResultLoteCifBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Consulta exitosa",lstDevolucionessecure);	
			}else {
				return new ValidarResultLoteCifBeanResponse (CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "No se encontraros registros con los criterios ingresados");
				
			}	
		}catch(Exception e){
			LOGGER.error( "ERROR UBICACION     :" + ServiciosCifSoapServiceImpl.class.getCanonicalName());
			LOGGER.error( "ERROR METODO        :" + "validarResultadoLoteCif" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION   :" , e );
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet()+", validarResultadoLoteCif");
			throw new MitWebServiceException(e.getMessage(), missing);
		}finally {
		   validacion = null;
		   lstDevolucionessecure = null;
		}
	}
}

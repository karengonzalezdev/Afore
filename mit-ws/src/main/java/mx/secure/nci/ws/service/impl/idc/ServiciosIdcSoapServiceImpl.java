package mx.secure.nci.ws.service.impl.idc;

import mx.secure.nci.business.report.IReportesService;
import mx.secure.nci.business.report.beans.DetalleIdcReportBean;
import mx.secure.nci.business.service.IServiciosIdcService;
import mx.secure.nci.business.util.enums.CtrlResponseWSEnum;
import mx.secure.nci.business.vo.ActualizaEstatusIdcVO;
import mx.secure.nci.business.vo.DetalleExcepcionesIdcVO;
import mx.secure.nci.business.vo.ReporteDetalleIdcVO;
import mx.secure.nci.business.vo.ReporteVO;
import mx.secure.nci.business.vo.ResultadoIdcVO;
import mx.secure.nci.business.vo.ValidacionesVO;
import mx.secure.nci.ws.beans.ReportBean;
import mx.secure.nci.ws.beans.request.ActualizaEstatusIdcBeanRequest;
import mx.secure.nci.ws.beans.request.DetalleExcepcionesIdcBeanRequest;
import mx.secure.nci.ws.beans.request.DetalleIdcReporteBeanRequest;
import mx.secure.nci.ws.beans.request.IdcResultadoBeanRequest;
import mx.secure.nci.ws.beans.response.ActualizaEstatusIdcBeanResponse;
import mx.secure.nci.ws.beans.response.DetalleExcepcionesIdcBeanResponse;
import mx.secure.nci.ws.beans.response.DetalleIdcReporteBeanResponse;
import mx.secure.nci.ws.beans.response.IdcResultadoBeanResponse;
import mx.secure.nci.ws.exceptions.FaultBeanServiceInfo;
import mx.secure.nci.ws.exceptions.MitWebServiceException;
import mx.secure.nci.ws.service.idc.IServiciosIdcSoapService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviciosIdcSoapService")
public class ServiciosIdcSoapServiceImpl implements IServiciosIdcSoapService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiciosIdcSoapServiceImpl.class);
	private static final int ZERO = 0;

	@Autowired
	IServiciosIdcService serviciosIdc;
	@Autowired
	IReportesService reportesService;
	
	@Override
	public ActualizaEstatusIdcBeanResponse marcarDescartadosIdc(ActualizaEstatusIdcBeanRequest actualizaEstatusIdcBeanRequest) {
		ValidacionesVO validacion = null;
		try {
			if( actualizaEstatusIdcBeanRequest == null ) {
				return new ActualizaEstatusIdcBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,"REQUEST necesario"
						);
			}			
			if( !(validacion = actualizaEstatusIdcBeanRequest.validarRequest()).isEstatus() ) {
				return new ActualizaEstatusIdcBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,validacion.getMensaje()
						);
			}		
			Integer rgsAct = serviciosIdc.actualizaEstatusIdc( (ActualizaEstatusIdcVO) validacion.getObjetoAux());
			if(rgsAct != null ) {
				return new ActualizaEstatusIdcBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Actualizacion Exitosa", rgsAct);			
			}else {
				return new ActualizaEstatusIdcBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "No se encontraron registros para actualizar con ese folio");			
			}
					
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACION     :" + ServiciosIdcSoapServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "actualizaDevoluciones" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION   :" , e );
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet()+", actualizaEstatusIdc");
			throw new MitWebServiceException(e.getMessage(), missing);
		}finally {
			validacion			= null;
		}
	}
	
	
	public DetalleExcepcionesIdcBeanResponse getDetalleExcepcionesIdc(DetalleExcepcionesIdcBeanRequest request) {
		ValidacionesVO validacion = null;
		List<DetalleExcepcionesIdcVO> ltsDetalleExcepcionesIdc = null;
		
		try {
			if (request == null) {
				return new DetalleExcepcionesIdcBeanResponse (
						CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						, CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						, "REQUEST necesario");
			}
			if ( !(validacion = request.validarRequest()).isEstatus() ) {
				return new DetalleExcepcionesIdcBeanResponse (
						CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						, CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						, validacion.getMensaje()
						);
			}
			ltsDetalleExcepcionesIdc = serviciosIdc.obtenerListaDetalleExcepcionesIdc((DetalleExcepcionesIdcVO) validacion.getObjetoAux());
			
			if (ltsDetalleExcepcionesIdc != null) {
				return new DetalleExcepcionesIdcBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Obtención de datos correcto", ltsDetalleExcepcionesIdc);
			}else {
				return new DetalleExcepcionesIdcBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "No se encontraron registros");
			}		 
		} catch( Exception e ) {
			LOGGER.error( "ERROR UBICACION     :" + ServiciosIdcSoapServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "getDetalleExcepcionesIdc" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION   :" , e );
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet()+", getDetalleExcepcionesIdc");
			throw new MitWebServiceException(e.getMessage(), missing);
		}finally {
			validacion = null;
			ltsDetalleExcepcionesIdc = null;
		}
	}
	
	
	public DetalleIdcReporteBeanResponse generaReporteDetalleIdc (DetalleIdcReporteBeanRequest request) {
		ValidacionesVO validacion = null;
		List<ReporteDetalleIdcVO> ltsDetalleIdc = null;
		List<DetalleIdcReportBean> ltsDetalleIdcBean = null;
		DetalleIdcReportBean detalleIdcBean = null;
		
		try {
			if (request == null) {
				return new DetalleIdcReporteBeanResponse (
						CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						, CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						, "REQUEST necesario");
			}
			if ( !(validacion = request.validarRequest()).isEstatus() ) {
				return new DetalleIdcReporteBeanResponse (
						CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						, CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						, validacion.getMensaje()
						);
			}
			ltsDetalleIdc = serviciosIdc.obtenerListaDetalleIdc((ReporteDetalleIdcVO)validacion.getObjetoAux());
			if(ltsDetalleIdc != null && ltsDetalleIdc.size() > ZERO) {
				ltsDetalleIdcBean = new ArrayList<DetalleIdcReportBean>();
				LOGGER.info("*******************************************************************************************************");
				for(ReporteDetalleIdcVO item : ltsDetalleIdc) {
					detalleIdcBean = new DetalleIdcReportBean();
					detalleIdcBean.setNumConsecutivo(item.getNumConsecutivo());
					detalleIdcBean.setNss(item.getNss());
					detalleIdcBean.setCurp(item.getCurp());
					detalleIdcBean.setNumeroCuenta(item.getNumeroCuenta());
					detalleIdcBean.setNombreCompleto(item.getNombreCompleto());
					detalleIdcBean.setDiagnosticoIdc(item.getDiagnosticoIdc());
					detalleIdcBean.setSubProcesoNoVigencia(item.getSubProcesoNoVigencia());
					detalleIdcBean.setFechaCertificacion(item.getFechaCertificacion());
					//detalleIdcBean.setFolio(item.getFolio());
					LOGGER.info("ITEM"+item.getNumConsecutivo());
					ltsDetalleIdcBean.add(detalleIdcBean);
				}
				LOGGER.info("*******************************************************************************************************");
			} else {
				return new DetalleIdcReporteBeanResponse(
						CtrlResponseWSEnum.WS_OK.getCodRet()
						, CtrlResponseWSEnum.WS_OK.getMsgRet()
						, "No fue posible obtener la informacion con el detalle de IDC para el folio del proceso de devolución de secure");
			}
			

			ReporteVO reporteVO = reportesService.generaReporteDetalleIdc(ltsDetalleIdcBean);
			if (reporteVO != null) {
				/*Se arma la respuesta*/
				ReportBean reportBean = new ReportBean();
				reportBean.setArchivo(reporteVO.getArchivo());
				reportBean.setNombre(reporteVO.getNombre());
				return new DetalleIdcReporteBeanResponse(
						CtrlResponseWSEnum.WS_OK.getCodRet()
						, CtrlResponseWSEnum.WS_OK.getMsgRet()
						, "Se genero el reporte con el detalle de IDC para el folio del proceso de devolución de secure"
						, reportBean, ltsDetalleIdc);
			} else {
				return new DetalleIdcReporteBeanResponse(
						CtrlResponseWSEnum.WS_OK.getCodRet()
						, CtrlResponseWSEnum.WS_OK.getMsgRet()
						, "No fue posible generar el reporte con el detalle de IDC con la informacion recuperada"
						, ltsDetalleIdc);
				
			}
		} catch(final Exception e){
			LOGGER.error( "ERROR UBICACION     :" + ServiciosIdcSoapServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "generaReporteDetalleIdc" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION   :" , e );
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet()+", generaReporteDetalleIdc");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			validacion = null;
			ltsDetalleIdc = null;
			ltsDetalleIdcBean = null;
			detalleIdcBean = null;
		}
	}
	

	public IdcResultadoBeanResponse getResultadoIdc(IdcResultadoBeanRequest request) {
		  ValidacionesVO validacion	= null;
		  ResultadoIdcVO resultadoIdc= null;

		  
			try {
				if( request == null ) {
					return new IdcResultadoBeanResponse(
							   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
							 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
							 ,"El request es necesario");
				}
				if( !(validacion = request.validaRequest()).isEstatus() ) {
					return new IdcResultadoBeanResponse(
							   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
							 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
							 ,validacion.getMensaje());
				}
				
				resultadoIdc = serviciosIdc.consultarResultadoIdc((ResultadoIdcVO) validacion.getObjetoAux());
				
				if (resultadoIdc != null) {
					return new IdcResultadoBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
							CtrlResponseWSEnum.WS_OK.getMsgRet(), "Consulta exitosa",resultadoIdc);	
				}else {
					return new IdcResultadoBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
							CtrlResponseWSEnum.WS_OK.getMsgRet(), "No se encontraros registros con los criterios ingresados");
				
				}
             
			}catch( Exception e ) {
				LOGGER.error( "ERROR UBICACION     :" + ServiciosIdcSoapServiceImpl.class.getCanonicalName() );
				LOGGER.error( "ERROR METODO        :" + "generaReporteDetalleIdc" );
				LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
				LOGGER.error( "ERROR DESCRIPCION   :" , e );
				FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
				missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet()+", getResultadoIdc");
				throw new MitWebServiceException(e.getMessage(), missing);
			}
			finally {
				validacion   = null;
				resultadoIdc = null;
			}
	  }		
}

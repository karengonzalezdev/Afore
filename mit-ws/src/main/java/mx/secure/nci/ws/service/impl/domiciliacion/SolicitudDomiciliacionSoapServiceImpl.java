package mx.secure.nci.ws.service.impl.domiciliacion;

import java.util.List;

import mx.secure.nci.business.exception.ErrorCodeSoap;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.ISolicitudService;
import mx.secure.nci.business.vo.GenericCatalogoVO;
import mx.secure.nci.business.vo.SolicitudVO;
import mx.secure.nci.business.wrapped.SolicitudFilter;
import mx.secure.nci.ws.beans.SolicitudDomiciliacionBean;
import mx.secure.nci.ws.beans.request.SolicitudDomiciliacionActualizarBeanRequest;
import mx.secure.nci.ws.beans.request.SolicitudDomiciliacionConsultarBeanRequest;
import mx.secure.nci.ws.beans.request.SolicitudDomiciliacionConsultarSumBeanRequest;
import mx.secure.nci.ws.beans.request.SolicitudDomiciliacionInsertarBeanRequest;
import mx.secure.nci.ws.beans.response.SolicitudDomiciliacionConsultarBeanResponse;
import mx.secure.nci.ws.beans.response.SolicitudDomiciliacionConsultarSumBeanResponse;
import mx.secure.nci.ws.service.domiciliacion.ISolicitudDomiciliacionSoapService;
import mx.secure.nci.ws.util.UtilMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.ws.service.exception.WebServiceException;

import static mx.secure.nci.business.util.ConstantesCatalogos.ID_ESTATUS_CARGO_PENDIENTE;

@Service("solicitudDomiciliacionSoapService")
public class SolicitudDomiciliacionSoapServiceImpl implements ISolicitudDomiciliacionSoapService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SolicitudDomiciliacionSoapServiceImpl.class);

	@Autowired ISolicitudService solicitudService;
	
	public SolicitudDomiciliacionConsultarBeanResponse consultar(SolicitudDomiciliacionConsultarBeanRequest solicitudDomiciliacionConsultarBeanRequest)
	{
		
		List<SolicitudDomiciliacionBean> listResponse=null;
		try
		{

			SolicitudFilter filter=null;
			
			if(solicitudDomiciliacionConsultarBeanRequest != null)
			{
				filter=new SolicitudFilter();
				
				filter.setNumCuentaIndividual(solicitudDomiciliacionConsultarBeanRequest.getNumCuentaIndividual());
				filter.setFolio(solicitudDomiciliacionConsultarBeanRequest.getFolio());
				filter.setEstatusSolicitudes(solicitudDomiciliacionConsultarBeanRequest.getEstatusSolicitudes());
				filter.setClaveSolicitud(solicitudDomiciliacionConsultarBeanRequest.getClaveSolicitud());
				filter.setFolioProcesar(solicitudDomiciliacionConsultarBeanRequest.getFolioProcesar());
				filter.setCurp(solicitudDomiciliacionConsultarBeanRequest.getCurp());
				filter.setCurpTutor(solicitudDomiciliacionConsultarBeanRequest.getCurpTutor());
				filter.setCuentaBanco(solicitudDomiciliacionConsultarBeanRequest.getCuentaBanco());
			}
			
			listResponse =  UtilMapping.mappingListSolicitudDomiciliacionVO(this.solicitudService.consultar(filter));
			
			
		}
		catch(MitBusinessException ex)
		{
			LOGGER.error(ex.getMessage(),ex);
			throw new WebServiceException(ex.getExceptionDetails());
		}
		catch (Exception ex) 
		{
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService: "+ex.getMessage(),
									new Object[] { "SolicitudDomiciliacionServiceImpl", "consultar(...)" }, ex));
						
			LOGGER.error(webServiceException.getMessage(), ex);
			
			throw webServiceException;
		}
		
		return new SolicitudDomiciliacionConsultarBeanResponse(listResponse);
		
	}

	// MAMC ATENCION A MEJORA 2362
	public SolicitudDomiciliacionConsultarSumBeanResponse consultarMonto(SolicitudDomiciliacionConsultarSumBeanRequest solicitudDomiciliacionConsultarSumBeanRequest)
	{
		
		List<SolicitudDomiciliacionBean> listResponse=null;
		try
		{

			SolicitudFilter filter=null;
			
			if(solicitudDomiciliacionConsultarSumBeanRequest != null)
			{
				filter=new SolicitudFilter();
				
				filter.setNumCuentaIndividual(solicitudDomiciliacionConsultarSumBeanRequest.getNumCuentaIndividual());
				filter.setFolio(solicitudDomiciliacionConsultarSumBeanRequest.getFolio());
				filter.setEstatusSolicitudes(solicitudDomiciliacionConsultarSumBeanRequest.getEstatusSolicitudes());
				filter.setClaveSolicitud(solicitudDomiciliacionConsultarSumBeanRequest.getClaveSolicitud());
				filter.setCuentaBanco(solicitudDomiciliacionConsultarSumBeanRequest.getCuentaBanco());
			}
			
			listResponse =  UtilMapping.mappingListSolicitudDomiciliacionVO(this.solicitudService.consultarMonto(filter));
			
			
		}
		catch(MitBusinessException ex)
		{
			LOGGER.error(ex.getMessage(),ex);
			throw new WebServiceException(ex.getExceptionDetails());
		}
		catch (Exception ex) 
		{
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService: "+ex.getMessage(),
									new Object[] { "SolicitudDomiciliacionServiceImpl", "consultarMonto(...)" }, ex));
						
			LOGGER.error(webServiceException.getMessage(), ex);
			
			throw webServiceException;
		}
		
		return new SolicitudDomiciliacionConsultarSumBeanResponse(listResponse);
		
	}
	
	@Override
	public boolean Insertar(SolicitudDomiciliacionInsertarBeanRequest solicitudDomiciliacionInsertarBeanRequest) {
		try {
			int estatusOperacion = 0;
			if (solicitudDomiciliacionInsertarBeanRequest != null) {
				
				SolicitudVO solicitudDomiciliacionVO = UtilMapping.mappingSolicitudDomiciliacionBean(solicitudDomiciliacionInsertarBeanRequest.getSolicitudDomiciliacion());
				if(solicitudDomiciliacionInsertarBeanRequest.getSolicitudDomiciliacion().getUsuario()!=null){
					solicitudDomiciliacionVO.setUsuarioCreacion(solicitudDomiciliacionInsertarBeanRequest.getSolicitudDomiciliacion().getUsuario());
				}
				if(solicitudDomiciliacionInsertarBeanRequest.getSolicitudDomiciliacion().getUsuarioCreacion()!=null){
					solicitudDomiciliacionVO.setUsuarioCreacion(solicitudDomiciliacionInsertarBeanRequest.getSolicitudDomiciliacion().getUsuarioCreacion());
				}
				
				setDefaultSolicitudData(solicitudDomiciliacionVO);
				estatusOperacion= solicitudService.insertar(solicitudDomiciliacionVO);
				
			}
			return estatusOperacion == 1;
		}
		catch(MitBusinessException ex)
		{
			LOGGER.error(ex.getMessage(),ex);
			throw new WebServiceException(ex.getExceptionDetails());
		}		
		catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "SolicitudDomiciliacionServiceImpl", "insertar(...)" }, ex));
			LOGGER.error(webServiceException.getMessage(), ex);
			
			throw webServiceException;
		}

	}

	private void setDefaultSolicitudData(SolicitudVO solVO) throws MitBusinessException {
		if(solVO.getEstatusCargo()== null || solVO.getEstatusCargo().getId()==null){
			solVO.setEstatusCargo(new GenericCatalogoVO(ID_ESTATUS_CARGO_PENDIENTE));
		}
		if(solVO.getPeriodo()!=null &&  solVO.getPeriodo().getId() !=null ){
			solVO.setFechaProximoCargo(solicitudService.getProximaFechaDeCargo(solVO));
		}
		
	}
	

	
	

	@Override
	public boolean Actualizar(SolicitudDomiciliacionActualizarBeanRequest solicitudDomiciliacionActualizarBeanRequest) {
		try {
			int estatusOperacion = 0;
			if (solicitudDomiciliacionActualizarBeanRequest != null) {
				SolicitudDomiciliacionBean solicitudDomiciliacion = solicitudDomiciliacionActualizarBeanRequest.getSolicitudDomiciliacion();
				SolicitudVO solicitudDomiciliacionVO = UtilMapping.mappingSolicitudDomiciliacionBean(solicitudDomiciliacion);
				if(solicitudDomiciliacion.getUsuario()!=null){
					solicitudDomiciliacionVO.setUsuarioActualizacion(solicitudDomiciliacion.getUsuario());
				}
				if(solicitudDomiciliacion.getUsuarioActualizacion()!=null){
					solicitudDomiciliacionVO.setUsuarioActualizacion(solicitudDomiciliacion.getUsuarioActualizacion());
				}
				if(solicitudDomiciliacionVO.getPeriodo()!=null &&  solicitudDomiciliacionVO.getPeriodo().getId() !=null){
					solicitudDomiciliacionVO.setFechaProximoCargo(solicitudService.getProximaFechaDeCargo(solicitudDomiciliacionVO));
				}
				
//				Long importe = 0L;
//				if(solicitudDomiciliacion.getDiversificaciones()!= null) {
//					if(solicitudDomiciliacion.getDiversificaciones().size()>0) {
//						for (DiversificacionBean diversificacionVO : solicitudDomiciliacion.getDiversificaciones()) {
//							importe = importe + diversificacionVO.getMonto().longValue();
//						}
//					}
//				}
//				
//				if(importe>0) {
//					if(importe != (solicitudDomiciliacion.getImporte()==null?0L:solicitudDomiciliacion.getImporte().longValue())) {
//						throw new Exception("El importe de la domiciliación es diferente al de la diversificación");
//					}						
//				}
				
				estatusOperacion = solicitudService.actualizar(solicitudDomiciliacionVO);
			}
			return estatusOperacion == 1; 
		}
		catch(MitBusinessException ex)
		{
			LOGGER.error(ex.getMessage(),ex);
			throw new WebServiceException(ex.getExceptionDetails());
		}		
		catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "SolicitudDomiciliacionServiceImpl", "actualizar(...)" }, ex));
			LOGGER.error(webServiceException.getMessage(), ex);
			
			throw webServiceException;
		}

	}
}

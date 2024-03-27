package mx.secure.nci.ws.service.impl.conciliacion;

import java.util.ArrayList;
import java.util.List;

import mx.secure.nci.business.exception.ErrorCodeSoap;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.IConciliacionService;
import mx.secure.nci.business.vo.ConciliacionVO;
import mx.secure.nci.business.vo.GenericCatalogoVO;
import mx.secure.nci.business.wrapped.ConciliacionFilter;
import mx.secure.nci.ws.beans.ConciliacionBean;
import mx.secure.nci.ws.beans.request.ConciliacionActualizarBeanRequest;
import mx.secure.nci.ws.beans.request.ConciliacionConsultaFolioBeanRequest;
import mx.secure.nci.ws.beans.request.ConciliacionConsultarBeanRequest;
import mx.secure.nci.ws.beans.request.ConciliacionConsultarTotalesBeanRequest;
import mx.secure.nci.ws.beans.request.ConciliacionInsertarBeanRequest;
import mx.secure.nci.ws.beans.response.ConciliacionConsultaFolioBeanResponse;
import mx.secure.nci.ws.beans.response.ConciliacionConsultarBeanResponse;
import mx.secure.nci.ws.beans.response.ConciliacionConsultarCuantosBeanResponse;
import mx.secure.nci.ws.beans.response.ConciliacionConsultarIdPagoBeanResponse;
import mx.secure.nci.ws.service.conciliacion.IConciliacionSoapService;
import mx.secure.nci.ws.util.UtilMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.ws.service.exception.WebServiceException;

@Service("conciliacionSoapService")
public class ConciliacionSoapServiceImpl implements IConciliacionSoapService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConciliacionSoapServiceImpl.class);

	@Autowired
	IConciliacionService conciliacionService;

	@Override
	public ConciliacionConsultarBeanResponse consultar(ConciliacionConsultarBeanRequest conciliacionConsultarBeanRequest) {
		List<ConciliacionBean> lista = new ArrayList<ConciliacionBean>();
		try {
			ConciliacionFilter conciliacionFilter = convertToFilter(conciliacionConsultarBeanRequest);
			
			List<ConciliacionVO> listaVO = conciliacionService.consultarBasica(conciliacionFilter);
			if (listaVO != null) {
				for (ConciliacionVO conciliacion : listaVO) {
					ConciliacionBean regIns = UtilMapping.ConciliacionBeanVOToBean(conciliacion);
					regIns.setCelular(conciliacion.getCelular()!=null?conciliacion.getCelular().toString():null);
					regIns.setClaveBanco(conciliacion.getClaveBanco()!=null?conciliacion.getClaveBanco().toString():null);
					regIns.setNss(conciliacion.getNss()!=null?conciliacion.getNss().toString():null);
					regIns.setNumeroCuentaIndividual(conciliacion.getNumeroCuentaIndividual()!=null?conciliacion.getNumeroCuentaIndividual().toString():null);
					
					lista.add(regIns);
				}
			}
		} catch (MitBusinessException ex) {
			throw new WebServiceException(ex.getExceptionDetails());
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService:"+ex.getMessage(),
							new Object[] { getClass().getSimpleName(), "consultar(...)" }, ex));
			LOGGER.error(webServiceException.getMessage(), ex);
			throw webServiceException;
		}
		
		ConciliacionConsultarBeanResponse conciliacionConsultarBeanResponse = new ConciliacionConsultarBeanResponse(lista);
		
		return conciliacionConsultarBeanResponse;
	}

	
	@Override
	public ConciliacionConsultarCuantosBeanResponse consultarCuantosRegistros(ConciliacionConsultarBeanRequest conciliacionConsultarBeanRequest) {
		ConciliacionConsultarCuantosBeanResponse response = new ConciliacionConsultarCuantosBeanResponse(); 
		try{
			ConciliacionFilter conciliacionFilter = convertToFilter(conciliacionConsultarBeanRequest);
			response.setNumRegistros(conciliacionService.consultarCuantosRegistros(conciliacionFilter));
		} catch (MitBusinessException ex) {
			throw new WebServiceException(ex.getExceptionDetails());
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService:"+ex.getMessage(),
							new Object[] { getClass().getSimpleName(), "consultarCuantos(...)" }, ex));
			LOGGER.error(webServiceException.getMessage(), ex);
			throw webServiceException;
		}
		return response;
	}

	@Override
	public Boolean insertar(ConciliacionInsertarBeanRequest conciliacionInsertarBeanRequest) 
	{
		try 
		{
			if (conciliacionInsertarBeanRequest.getConciliacion() != null) 
			{
				ConciliacionVO conciliacionVO = UtilMapping.ConciliacionBeanTOVO(conciliacionInsertarBeanRequest.getConciliacion(), "INSERT");
			
				return this.conciliacionService.insertar(conciliacionVO);
			} 
			else 
			{
				return false;
			}

		} catch (MitBusinessException ex) 
		{
			throw new WebServiceException(ex.getExceptionDetails());
		} 
		catch (Exception ex) 
		{
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService"+ex.getMessage(),
							new Object[] { getClass().getSimpleName(), "insertar(...)" }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;
		}
	}

	

	@Override
	public Boolean actualizar(ConciliacionActualizarBeanRequest conciliacionActualizarBeanRequest) 
	{
		try {
			if (conciliacionActualizarBeanRequest.getConciliacion() != null) 
			{
				ConciliacionVO conciliacionVO = UtilMapping.ConciliacionBeanTOVO(conciliacionActualizarBeanRequest.getConciliacion(), "ACTUALIZAR");
				return this.conciliacionService.actualizar(conciliacionVO);
			}
			else
			{
				return false;
			}
		} catch (MitBusinessException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new WebServiceException(ex.getExceptionDetails());
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService:"+ex.getMessage(),
							new Object[] { getClass().getSimpleName(), "actualizar(...)" }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);
			throw webServiceException;
		}
	}



	@Override
	public boolean conciliarTotales(ConciliacionConsultarTotalesBeanRequest request) {
		try {
			if (request != null) {
				return conciliacionService.conciliarTotales(request.getUsuario(),request.getFolioConciliacion());
			} else {
				return false;
			}
		} catch (MitBusinessException ex) {
			throw new WebServiceException(ex.getExceptionDetails());
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService:"+ex.getMessage(),
							new Object[] { getClass().getName(), "conciliarTotales(...)" }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;
		}
	}



	@Override
	public ConciliacionConsultarIdPagoBeanResponse consultaIdPagosecure() {
		ConciliacionConsultarIdPagoBeanResponse response = new ConciliacionConsultarIdPagoBeanResponse();
		try {
			
			response.setIdPagosecure(conciliacionService.getIdPagosecure());
				return response;

		} catch (MitBusinessException ex) {
			throw new WebServiceException(ex.getExceptionDetails());
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService:"+ex.getMessage(),
							new Object[] { getClass().getName(), "conciliarTotales(...)" }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;
		}
	}
	
	public ConciliacionConsultaFolioBeanResponse consultarFolios(ConciliacionConsultaFolioBeanRequest request){
		ConciliacionConsultaFolioBeanResponse response = new ConciliacionConsultaFolioBeanResponse();
		
		try{	
			ConciliacionFilter conciliacionFilter = new ConciliacionFilter();
			conciliacionFilter.setMovimientoGenerado(request.getMovGenerado());
			conciliacionFilter.setRegistroConciliado(request.getRegConciliado());
			conciliacionFilter.setOrigenAportacion(new GenericCatalogoVO(request.getIdOrigenAportacion()));
			conciliacionFilter.setEstatusTramite(new GenericCatalogoVO(request.getIdEstatusTramite()));
			conciliacionFilter.setFolioConciliacion(request.getFolioConciliacion());
			conciliacionFilter.setFolioArch(request.getFolioArch());
			conciliacionFilter.setIdSubEtapa(request.getIdSubEtapa());
			conciliacionFilter.setBandera(request.getBandera());
			
			response.setFolios(conciliacionService.consultarFolios(conciliacionFilter));
		} catch (MitBusinessException ex) {
			throw new WebServiceException(ex.getExceptionDetails());
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService:"+ex.getMessage(),
							new Object[] { getClass().getSimpleName(), "consultarFolios(...)" }, ex));
			LOGGER.error(webServiceException.getMessage(), ex);
			throw webServiceException;
		}
		return response;
	}
	
	private ConciliacionFilter convertToFilter(ConciliacionConsultarBeanRequest conciliacionConsultarBeanRequest){
		ConciliacionFilter conciliacionFilter = new ConciliacionFilter();
		
		conciliacionFilter.setFolio(conciliacionConsultarBeanRequest.getFolio());
		conciliacionFilter.setNumeroCuentaIndividual(conciliacionConsultarBeanRequest.getNumCuentaIndividual());
		conciliacionFilter.setRegistroConciliado(conciliacionConsultarBeanRequest.getRegistroConciliado());
		
		conciliacionFilter.setCurp(conciliacionConsultarBeanRequest.getCurp());
		conciliacionFilter.setTipoAhorrador(conciliacionConsultarBeanRequest.getTipoAhorrador());
		conciliacionFilter.setMovimientoGenerado(conciliacionConsultarBeanRequest.getMovimientoGenerado());
		
		if (conciliacionConsultarBeanRequest.getIdOrigenAportacion() != null)  {
			GenericCatalogoVO origen = new GenericCatalogoVO();
			origen.setId(conciliacionConsultarBeanRequest.getIdOrigenAportacion());
			conciliacionFilter.setOrigenAportacion(origen);
		}
		
		if (conciliacionConsultarBeanRequest.getIdEstatusTramite() != null)  {
			GenericCatalogoVO estatus = new GenericCatalogoVO();
			estatus.setId(conciliacionConsultarBeanRequest.getIdEstatusTramite());
			conciliacionFilter.setEstatusTramite(estatus);
		}
		conciliacionFilter.setClaveSolicitud(conciliacionConsultarBeanRequest.getClaveSolicitud());
		return conciliacionFilter;
	}

}

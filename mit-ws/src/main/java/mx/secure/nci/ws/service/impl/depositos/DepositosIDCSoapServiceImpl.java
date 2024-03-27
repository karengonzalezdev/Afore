package mx.secure.nci.ws.service.impl.depositos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.ws.service.exception.WebServiceException;

import mx.secure.nci.ws.beans.DepositosIDCBean;
import mx.secure.nci.ws.beans.ValidarCuentaBean;
import mx.secure.nci.ws.beans.request.DepositosIDCConsultaBeanRequest;
import mx.secure.nci.ws.beans.request.ValidarCuentaBeanRequest;
import mx.secure.nci.ws.beans.response.DepositosIDCConsultaBeanResponse;
import mx.secure.nci.ws.beans.response.ValidarCuentaBeanResponse;
import mx.secure.nci.ws.service.depositos.IDepositosIDCSoapService;
import mx.secure.nci.ws.util.UtilMapping;
import mx.secure.nci.business.exception.ErrorCodeSoap;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.IDepositosIDCService;
import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.wrapped.DepositosIDCFilter;
import mx.secure.nci.business.wrapped.ValidarCuentaFilter;
import mx.secure.nci.business.vo.DepositosIDCVO;
import mx.secure.nci.business.vo.ValidarCuentaVO;

@Service("depositosIDCSoapService")
public class DepositosIDCSoapServiceImpl implements IDepositosIDCSoapService{

	private static final Logger LOGGER = LoggerFactory.getLogger(DepositosIDCSoapServiceImpl.class);
	
	//Inyección de a la capa de negocio
	@Autowired IDepositosIDCService depositosIDCService;
	
	
	@Override
	public DepositosIDCConsultaBeanResponse consultarDepositos (DepositosIDCConsultaBeanRequest request) {
		
		List<DepositosIDCBean> lista = new ArrayList<DepositosIDCBean>();
		
		try {
			if(request !=null) {
				DepositosIDCFilter depositosFilter = new DepositosIDCFilter();
				Date defaultDate = new SimpleDateFormat("yyyy-MM-dd").parse("0001-01-03");
				
				
				//Llenamos los campos de busqueda
				
				depositosFilter.setFechaInicio((defaultDate.compareTo(request.getFechaInicio())!=0)?request.getFechaInicio():null);
				depositosFilter.setFechaFinal(((defaultDate.compareTo(request.getFechaFin())!=0)?request.getFechaFin():null));
				depositosFilter.setEstatusIDC(request.getEstatusIDC());
				depositosFilter.setBanco(!UtilValidate.isBlankOrNull(request.getBanco()) ? request.getBanco() : null);
				depositosFilter.setReferencia(!UtilValidate.isBlankOrNull(request.getReferencia()) ? request.getReferencia() : null);
				
				for(DepositosIDCVO depositosVo: depositosIDCService.consultar(depositosFilter)) {
					
					DepositosIDCBean depositosBean = new DepositosIDCBean();
					//UtilMapping.mapVoToBean(depositosBean, depositosVo);
					depositosBean.setBanco(depositosVo.getBanco());
					depositosBean.setEstatusDeposito(depositosVo.getEstatusDeposito());
					depositosBean.setFechaDeposito(depositosVo.getFechaDeposito());
					depositosBean.setImporte(depositosVo.getImporte());
					depositosBean.setMedioDeposito(depositosVo.getMedioDeposito());
					depositosBean.setReferencia(depositosVo.getReferencia());
					depositosBean.setIdBanco(depositosVo.getIdBanco());
					depositosBean.setCuentaIndividual(depositosVo.getCuentaIndividual());
					depositosBean.setConcepto(depositosVo.getConcepto());
					depositosBean.setConvenio(depositosVo.getConvenio());
					
					lista.add(depositosBean);
				}
				
				
			}
			
		} catch (MitBusinessException ex) {
			
			LOGGER.error(ex.getMessage(),ex);
			throw new WebServiceException(ex.getExceptionDetails());
			
		}catch (Exception ex) {
			
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService:"+ex.getMessage(),
							new Object[] { "DepositosIDCSoapServiceImpl", "consultarDepositos(...)" }, ex));
			LOGGER.error(webServiceException.getMessage(), ex);
			throw webServiceException;
			
		}
		
		
		
		return new DepositosIDCConsultaBeanResponse(lista);
	}


	@Override
	public ValidarCuentaBeanResponse validarCuenta(ValidarCuentaBeanRequest request) {
		
		ValidarCuentaBean cuentaBean = new ValidarCuentaBean();
		
		
		try {
			
			if(request !=null) {
				
				ValidarCuentaVO cuentaVO = new ValidarCuentaVO();
			
				cuentaVO = depositosIDCService.validarCuenta(request.getCuentaIndividual());
				
				if(cuentaVO!=null) {
					
					cuentaBean.setApellidoMaterno(cuentaVO.getApellidoMaterno());
					cuentaBean.setApellidoPaterno(cuentaVO.getApellidoPaterno());
					cuentaBean.setCorreo(cuentaVO.getCorreo());
					cuentaBean.setCuentaIndividual(cuentaVO.getCuentaIndividual());
					cuentaBean.setEstatus(cuentaVO.getEstatus());
					cuentaBean.setNombre(cuentaVO.getNombre());
					cuentaBean.setTelefono(cuentaVO.getTelefono());
					
					
				}else {
				
					cuentaBean = null;
				}
			}
			
			
			
		} catch (MitBusinessException ex) {
			
			LOGGER.error(ex.getMessage(),ex);
			throw new WebServiceException(ex.getExceptionDetails());
			
		}catch (Exception ex) {
			
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService:"+ex.getMessage(),
							new Object[] { "DepositosIDCSoapServiceImpl", "consultarDepositos(...)" }, ex));
			LOGGER.error(webServiceException.getMessage(), ex);
			throw webServiceException;
			
		}
		
		return new ValidarCuentaBeanResponse(cuentaBean);
	}

	
}

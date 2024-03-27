package mx.secure.nci.ws.service.impl.principal;

import java.util.ArrayList;
import java.util.List;

import mx.secure.nci.business.exception.ErrorCodeSoap;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.IPrincipalService;
import mx.secure.nci.business.vo.PrincipalVO;
import mx.secure.nci.business.wrapped.PrincipalFilter;
import mx.secure.nci.ws.beans.GenericoCatalogoBean;
import mx.secure.nci.ws.beans.PrincipalBean;
import mx.secure.nci.ws.beans.request.PrincipalConsultarBeanRequest;
import mx.secure.nci.ws.beans.response.PrincipalConsultarBeanResponse;
import mx.secure.nci.ws.service.principal.IPrincipalSoapService;
import mx.secure.nci.ws.util.UtilMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.ws.service.exception.WebServiceException;

@Service("principalSoapService")
public class PrincipalSoapServiceImpl implements IPrincipalSoapService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PrincipalSoapServiceImpl.class);
	
	@Autowired IPrincipalService principalService;
	
	@Override
	public PrincipalConsultarBeanResponse consultar(PrincipalConsultarBeanRequest principalConsultarBeanRequest){
		PrincipalConsultarBeanResponse obj = new PrincipalConsultarBeanResponse();
		List<PrincipalBean> lista = new ArrayList<PrincipalBean>();
		try {
			PrincipalFilter filter = new PrincipalFilter();
			filter.setFolios(principalConsultarBeanRequest.getFolios());
			for (PrincipalVO prinVo : principalService.consultar(filter)){
				PrincipalBean regBean = new PrincipalBean();
				UtilMapping.mapVoToBean(regBean, prinVo);
				
				if (prinVo.getOrigenAportacion() != null && prinVo.getOrigenAportacion().getId()!=null){
					GenericoCatalogoBean origen = new GenericoCatalogoBean();
					origen.setId(prinVo.getOrigenAportacion().getId());
					origen.setValor(prinVo.getOrigenAportacion().getValor());
					regBean.setOrigenAportacion(origen);
				}
				if (prinVo.getBanco() != null && prinVo.getBanco().getId()!=null){
					GenericoCatalogoBean banco = new GenericoCatalogoBean();
					banco.setId(prinVo.getBanco().getId());
					banco.setValor(prinVo.getBanco().getValor());
					regBean.setBanco(banco);
				}
				if (prinVo.getSubctasecure() != null && prinVo.getSubctasecure().getId()!=null){
					GenericoCatalogoBean subcta = new GenericoCatalogoBean();
					subcta.setId(prinVo.getSubctasecure().getId());
					subcta.setValor(prinVo.getSubctasecure().getValor());
					regBean.setRefSubctasecure(subcta);
				}
				if (prinVo.getEmpresa() != null && prinVo.getEmpresa().getId()!=null){
					GenericoCatalogoBean empresa = new GenericoCatalogoBean();
					empresa.setId(prinVo.getEmpresa().getId());
					empresa.setValor(prinVo.getEmpresa().getValor());
					regBean.setEmpresa(empresa);
				}
				if (prinVo.getTipoNomina() != null && prinVo.getTipoNomina().getId()!=null){
					GenericoCatalogoBean tipoNomina = new GenericoCatalogoBean();
					tipoNomina.setId(prinVo.getTipoNomina().getId());
					tipoNomina.setValor(prinVo.getTipoNomina().getValor());
					regBean.setTipoNomina(tipoNomina);
				}
				if (prinVo.getClaveRedComercial() != null && prinVo.getClaveRedComercial().getId()!=null){
					GenericoCatalogoBean redComer = new GenericoCatalogoBean();
					redComer.setId(prinVo.getClaveRedComercial().getId());
					redComer.setValor(prinVo.getClaveRedComercial().getValor());
					regBean.setCveRedComercial(redComer);
				}
				lista.add(regBean);
			}
			obj.setListaPrincipal(lista);
		}
		catch(MitBusinessException ex)
		{
			LOGGER.error(ex.getMessage(),ex);
			throw new WebServiceException(ex.getExceptionDetails());
		}		
		catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "PrincipalSoapServiceImpl", "consultar(...)" }, ex));
			LOGGER.error(webServiceException.getMessage(), ex);
			
			throw webServiceException;
		}
		return obj;
	}
}
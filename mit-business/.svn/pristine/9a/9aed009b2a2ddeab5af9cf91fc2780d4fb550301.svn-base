package mx.profuturo.nci.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.ICatalogosService;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.persistence.CatalogoPersistence;
import mx.profuturo.nci.ws.webservice.catalogo.CatalogoBean;
import mx.profuturo.nci.ws.webservice.catalogo.CatalogoConsultarBeanRequest;
import mx.profuturo.nci.ws.webservice.catalogo.CatalogoConsultarBeanResponse;
import mx.profuturo.nci.ws.webservice.catalogo.CatalogoConsultarBeanResponse.Catalogos;
import mx.profuturo.nci.ws.webservice.catalogo.ICatalogoSoapWSConsultarResponse;

@Service("catalogosService")
public class CatalogosServiceImpl implements ICatalogosService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogosServiceImpl.class);

	@Autowired
	CatalogoPersistence catalogoPersistence;

	@Autowired
	WSPortTypeFactory wsPortTypeFactory;

	public List<CatalogoVO> consultarLista(final ParamCatalogoVO paramCatalogoVO) throws MitBusinessException {
		/*List<CatalogoVO> listaCatalogosVO = null;
		try {
			listaCatalogosVO = new ArrayList<CatalogoVO>();
			CatalogoVO catalogoVO = null;
			CatalogoConsultarBeanRequest request = new CatalogoConsultarBeanRequest();
			request.setIdCatalogo(paramCatalogoVO.getIdCatalogo() != null ? paramCatalogoVO.getIdCatalogo() : null);
			request.setIdCatalogoPadre(paramCatalogoVO.getIdCatalogoPadre() != null ? paramCatalogoVO.getIdCatalogoPadre() : null);
			request.setIdLineaNegocio(paramCatalogoVO.getIdLineaNegocio() != null ? paramCatalogoVO.getIdLineaNegocio() : null);
			request.setIdTipoCatalogo(paramCatalogoVO.getIdTipoCatalogo() != null ? paramCatalogoVO.getIdTipoCatalogo() : null);
			ICatalogoSoapWSConsultarResponse response = wsPortTypeFactory.catalogoPortType().consultar(request);
			if(response.getResponse().getCatalogos() != null)
			{
				List<CatalogoBean> listaCatalogos = response.getResponse().getCatalogos().getCatalogo();
				for(CatalogoBean CatalogoBean : listaCatalogos)
				{
					catalogoVO = new CatalogoVO();
					catalogoVO.setIdCatCatalogo(CatalogoBean.getIdCatCatalogo());
					catalogoVO.setValor(CatalogoBean.getValor());
					LOGGER.debug(CatalogoBean.getValor());
					listaCatalogosVO.add(catalogoVO);
				}
			}
			return listaCatalogosVO;
		} catch (BusinessException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Identificar Cliente",
							new Object[] { "IdentificarClienteServiceImpl", "consultarClienteBasico()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}*/
		return catalogoPersistence.select(paramCatalogoVO);
	}

}

package mx.profuturo.nci.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeveris.core.exception.impl.BusinessException;
import com.jeveris.core.persistence.exception.PersistenceException;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IBancosService;
import mx.profuturo.nci.business.service.ICatalogosService;
import mx.profuturo.nci.business.vo.BancosVO;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.business.wrapped.BancosFilter;
import mx.profuturo.nci.persistence.BancoPersistence;

@Service("bancosService")
public class BancosServiceImpl implements IBancosService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BancosServiceImpl.class);
	@Autowired
	BancoPersistence bancoPersistence;

	@Autowired
	ICatalogosService catalogosService;

	public List<BancosVO> consultar(BancosFilter bancosFilter) throws BusinessException {
		try {
			return bancoPersistence.select(bancosFilter);
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar bancos:"+ex.getMessage(),
							new Object[] { "BancosServiceImpl", "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

	public List<BancosVO> consultarMovs(BancosFilter bancosFilter) throws BusinessException {
		try {
			return bancoPersistence.selectMovs(bancosFilter);
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar bancos:"+ex.getMessage(),
							new Object[] { "BancosServiceImpl", "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

	
	public GenericCatalogoVO convertToCatalog(Short idBusqueda) throws MitBusinessException {
		GenericCatalogoVO catalogo = new GenericCatalogoVO();

		ParamCatalogoVO paramCatalogoVO = new ParamCatalogoVO();
		paramCatalogoVO.setIdCatalogo(idBusqueda);
		List<CatalogoVO> listaBusca = new ArrayList<CatalogoVO>();

		listaBusca = catalogosService.consultarLista(paramCatalogoVO);
		catalogo.setId(idBusqueda);
		catalogo.setValor(listaBusca.get(0).getValor());
		return catalogo;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int actualizar(BancosVO bancosVO) throws BusinessException {
		if (bancosVO.getFolio() != null || bancosVO.getIdBanco() != null || bancosVO.getAutBanco() != null) {
			try {
				int estatusOperaion = bancoPersistence.actualizar(bancosVO);
				if(bancosVO.getFolioRelacionado()!= null && !bancosVO.getFolioRelacionado().isEmpty() ) {
					estatusOperaion+=bancoPersistence.insertAllFolioBanco(bancosVO);
				}
				return estatusOperaion;
			} catch (PersistenceException ex) {
				throw new BusinessException(ex.getExceptionDetails());
			} catch (Exception ex) {
				MitBusinessException mitBusinessException = new MitBusinessException(
						GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar bancos:"+ex.getMessage(),
								new Object[] { "BancosServiceImpl", "actualizar()" }, ex));
				
				throw mitBusinessException;
			}
		} else {
			return 0;
		}
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int actualizarConciliado(List<BancosVO> bancosVO) throws BusinessException {
		int estatusOperacion = 0;
		try {
			for(BancosVO bancos : bancosVO){
				estatusOperacion=bancoPersistence.actualizarConciliado(bancos);		
				estatusOperacion+=bancoPersistence.insertAllFolioBanco(bancos);
			}
			return estatusOperacion;
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "actualizarConciliado:"+ex.getMessage(),
							new Object[] { "BancosServiceImpl", "actualizarConciliado()" }, ex));

			throw mitBusinessException;
		}
	}

}

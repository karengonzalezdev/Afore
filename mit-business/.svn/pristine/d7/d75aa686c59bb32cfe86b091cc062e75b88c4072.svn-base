package mx.profuturo.nci.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IArchivoService;
import mx.profuturo.nci.business.vo.ArchivoVO;
import mx.profuturo.nci.business.wrapped.ArchivoFilter;
import mx.profuturo.nci.persistence.ArchivoPersistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;
import com.jeveris.core.persistence.exception.PersistenceException;

@Service("archivoService")
public class ArchivoServiceImpl implements IArchivoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArchivoServiceImpl.class);

	@Autowired
	ArchivoPersistence archivoPersistence;

	public List<ArchivoVO>  consultar(ArchivoFilter filter)throws BusinessException {
		try {

			List<ArchivoVO> listArchivoVO = new ArrayList<ArchivoVO>();
			listArchivoVO = this.archivoPersistence.select(filter);
			if (!listArchivoVO.isEmpty()) {
				return listArchivoVO;
			} else {
				return null;
			}
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(
							ErrorCodeService.EX_EXCEPTION,
							"En consultar Archivo", new Object[] {
									"ArchivoServiceImpl", "consultarLista()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
	

}

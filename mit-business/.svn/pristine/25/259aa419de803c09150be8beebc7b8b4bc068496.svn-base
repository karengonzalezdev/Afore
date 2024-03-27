package mx.profuturo.nci.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.ISumConciliacionService;
import mx.profuturo.nci.business.vo.SumConciliacionVO;
import mx.profuturo.nci.business.wrapped.SumConciliacionFilter;
import mx.profuturo.nci.persistence.SumConciliacionPersistence;

@Service("sumConciliacionService")
public class SumConciliacionServiceImpl implements ISumConciliacionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SumConciliacionServiceImpl.class);

	@Autowired
	SumConciliacionPersistence sumConciliacionPersistence;

	public List<SumConciliacionVO> consultarSumConciliacion(SumConciliacionFilter sumConciliacionFilter)
			throws MitBusinessException {
		try {
			return sumConciliacionPersistence.select(sumConciliacionFilter);
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar sumConciliaciones",
							new Object[] { "SumConciliacionServiceImpl", "consultarSumConciliacion()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;

		}
	}
	public List<SumConciliacionVO> consultarSumConciliacionAConciliar(SumConciliacionFilter sumConciliacionFilter)
			throws MitBusinessException {
		try {
			return sumConciliacionPersistence.selectAconciliar(sumConciliacionFilter);
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar sumConciliaciones",
							new Object[] { "SumConciliacionServiceImpl", "consultarSumConciliacion()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;

		}
	}

	public int actualizarSumConciliacion(SumConciliacionVO sumConciliacionVO) throws MitBusinessException {
		try {
			return sumConciliacionPersistence.actualizar(sumConciliacionVO);
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al actualizar sumConciliaciones",
							new Object[] { "SumConciliacionServiceImpl", "actualizarSumConciliacion()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;

		}
	}

	public int actualizarSumConciliacionConciliado(List<SumConciliacionVO> sumConciliacionVO)
			throws MitBusinessException {
		try {
			for (SumConciliacionVO sumConcilia : sumConciliacionVO) {
				sumConciliacionPersistence.actualizarConciliado(sumConcilia);
			}
			return 1;
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al actualizar sumConciliaciones",
							new Object[] { "SumConciliacionServiceImpl", "actualizarSumConciliacion()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;

		}
	}

}

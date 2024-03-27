package mx.profuturo.nci.business.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.service.ICifrasLiquidacionService;
import mx.profuturo.nci.business.vo.DetalleFondosVO;
import mx.profuturo.nci.business.vo.DetalleSieforesVO;
import mx.profuturo.nci.business.vo.CifrasLiquidacionConsultaSolVO;
import mx.profuturo.nci.business.vo.CifrasLiquidacionSolVO;
import mx.profuturo.nci.business.vo.CifrasLiquidacionVO;
import mx.profuturo.nci.business.wrapped.CifrasLiquidacionFilter;
import mx.profuturo.nci.persistence.CifrasLiquidacionPersistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("CifrasLiquidacionService")
public class CifrasLiquidacionServiceImpl implements ICifrasLiquidacionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CifrasLiquidacionServiceImpl.class);

	@Autowired
	CifrasLiquidacionPersistence cifrasLiquidacionPersistence;

	public CifrasLiquidacionVO consultarCifrasLiquidacion(CifrasLiquidacionFilter filter) throws Exception {
		try {
			CifrasLiquidacionVO cifrasLiquidacionVO = new CifrasLiquidacionVO();
			List<DetalleSieforesVO> detalleSieforeVO = new ArrayList<DetalleSieforesVO>();
			
			cifrasLiquidacionVO.setFechaFin(filter.getFechaFin());
			cifrasLiquidacionVO.setFechaInicio(filter.getFechaInicio());
			cifrasLiquidacionVO.setUsuario(filter.getUsuario());
		
			detalleSieforeVO = cifrasLiquidacionPersistence.cifrasSolicitudes(filter);
			cifrasLiquidacionVO.setDetalleSieforeVO(detalleSieforeVO);
			
			return cifrasLiquidacionVO;

		} catch (Exception ex) {
			LOGGER.error("ERROR UBICACION      : " + CifrasLiquidacionServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO         : " + "consultarCifrasLiquidacion");
			LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
			LOGGER.error("ERROR DESCRIPCION    : ", ex);
			throw ex;
		} 
	}

	public List<CifrasLiquidacionSolVO> consultaSolicitudes(CifrasLiquidacionFilter filter)
			throws Exception {
		try {
			return cifrasLiquidacionPersistence.consultaSolicitudes(filter);
		} catch (Exception ex) {
			LOGGER.error("ERROR UBICACION      : " + CifrasLiquidacionServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO         : " + "consultaSolicitudes");
			LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
			LOGGER.error("ERROR DESCRIPCION    : ", ex);
			throw ex;
		}
	}

	public Integer registrarCifrasLiquidacion(CifrasLiquidacionFilter filter)
			throws Exception {
		try {
			return cifrasLiquidacionPersistence.registrarCifrasLiquidacion(filter);
		} catch (Exception ex) {
			LOGGER.error("ERROR UBICACION      : " + CifrasLiquidacionServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO         : " + "registrarCifrasLiquidacion");
			LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
			LOGGER.error("ERROR DESCRIPCION    : ", ex);
			throw ex;
		}
	}

	public Integer excluirSolCifrasLiquidacion(CifrasLiquidacionFilter filter) throws Exception {
		// TODO Auto-generated method stub
		try {
			return cifrasLiquidacionPersistence.excluirSolCifrasLiquidacion(filter);
		} catch (Exception ex) {
			LOGGER.error("ERROR UBICACION      : " + CifrasLiquidacionServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO         : " + "excluirSolCifrasLiquidacion");
			LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
			LOGGER.error("ERROR DESCRIPCION    : ", ex);
			throw ex;
		}
	}

	public int eliminarRegistrosCifrasLiquidacion() throws Exception {
		try {
			return cifrasLiquidacionPersistence.eliminarRegistrosCifrasLiquidacion();
		} catch (Exception ex) {
			LOGGER.error("ERROR UBICACION      : " + CifrasLiquidacionServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO         : " + "registrarCifrasLiquidacion");
			LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
			LOGGER.error("ERROR DESCRIPCION    : ", ex);
			throw ex;
		}
		
	}

	public List<CifrasLiquidacionSolVO> consultaSolicitudesExcuidas() throws Exception {
		try {
			return cifrasLiquidacionPersistence.consultaSolicitudesExcuidas();
		} catch (Exception ex) {
			LOGGER.error("ERROR UBICACION      : " + CifrasLiquidacionServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO         : " + "consultaSolicitudesExcuidas");
			LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
			LOGGER.error("ERROR DESCRIPCION    : ", ex);
			throw ex;
		}
	}

}
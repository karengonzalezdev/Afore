package mx.profuturo.nci.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;
import com.jeveris.core.persistence.exception.PersistenceException;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.report.ICifrasControlReportService;
import mx.profuturo.nci.business.service.ICifrasGenerales;
import mx.profuturo.nci.business.vo.CifrasControlReporteVO;
import mx.profuturo.nci.business.vo.CifrasGeneralesReporteVO;
import mx.profuturo.nci.business.vo.CifrasGeneralesTipoVO;
import mx.profuturo.nci.business.vo.CifrasGeneralesVO;
import mx.profuturo.nci.business.vo.ElementoVO;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.vo.SeccionReporteVO;
import mx.profuturo.nci.business.vo.SeccionVO;
import mx.profuturo.nci.business.wrapped.CifrasGeneralesFilter;
import mx.profuturo.nci.persistence.CifrasGeneralesPersistence;

@Service("cifrasGeneralesService")
public class CifrasGeneralesImpl implements ICifrasGenerales {
	@Autowired
	CifrasGeneralesPersistence cifrasGeneralesPersistence;

	@Autowired
	ICifrasControlReportService cifrasControlReportService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CifrasGeneralesImpl.class);
	private static final String REPORTE_SBC = "POR INDICADOR SALDO BUEN COBRO";
	private static final String REPORTE_BANCO = "POR BANCO";
	private static final String REPORTE_TIPO = "POR TIPO";
	private static final String REPORTE_ORIGEN = "POR ORIGEN";

	public CifrasGeneralesTipoVO consultarPorTipoReporte(CifrasGeneralesFilter cifrasGeneralesFilter)
			throws BusinessException {
		try {
			CifrasGeneralesTipoVO cifrasGenerales = new CifrasGeneralesTipoVO();
			CifrasGeneralesTipoVO lp = this.cifrasGeneralesPersistence.selectPorTipoLPReporte(cifrasGeneralesFilter);
			CifrasGeneralesTipoVO cp = this.cifrasGeneralesPersistence.selectPorTipoCPReporte(cifrasGeneralesFilter);
			cifrasGenerales.setSeccionesLargoPlazo(lp != null ? lp.getSeccionesLargoPlazo() : null);
			cifrasGenerales.setSeccionesCortoPlazo(cp != null ? cp.getSeccionesCortoPlazo() : null);
			
			if(lp != null && cp != null){
				if (lp.getTotalGeneralNoConciliado() != null && cp.getTotalGeneralNoConciliado() != null) {
					ElementoVO generalNo = new ElementoVO();
					generalNo.setRegistros(lp.getTotalGeneralNoConciliado().getRegistros().intValue() + cp.getTotalGeneralNoConciliado().getRegistros().intValue());
					cifrasGenerales.setTotalGeneralNoConciliado(generalNo);
				}
				
				if (lp.getTotalGeneralConciliado() != null && cp.getTotalGeneralConciliado() != null) {
					ElementoVO generalNo = new ElementoVO();
					generalNo.setRegistros(lp.getTotalGeneralConciliado().getRegistros().intValue() + cp.getTotalGeneralConciliado().getRegistros().intValue());
					cifrasGenerales.setTotalGeneralConciliado(generalNo);
				}
			}
			else if(lp != null && cp == null){
				if (lp.getTotalGeneralNoConciliado() != null) {
					ElementoVO generalNo = new ElementoVO();
					generalNo.setRegistros(lp.getTotalGeneralNoConciliado().getRegistros().intValue());
					cifrasGenerales.setTotalGeneralNoConciliado(generalNo);
				}
				
				if (lp.getTotalGeneralConciliado() != null) {
					ElementoVO generalNo = new ElementoVO();
					generalNo.setRegistros(lp.getTotalGeneralConciliado().getRegistros().intValue());
					cifrasGenerales.setTotalGeneralConciliado(generalNo);
				}
			}
			else if(lp == null && cp != null){
				if (cp.getTotalGeneralNoConciliado() != null) {
					ElementoVO generalNo = new ElementoVO();
					generalNo.setRegistros(cp.getTotalGeneralNoConciliado().getRegistros().intValue());
					cifrasGenerales.setTotalGeneralNoConciliado(generalNo);
				}
				
				if (cp.getTotalGeneralConciliado() != null) {
					ElementoVO generalNo = new ElementoVO();
					generalNo.setRegistros(cp.getTotalGeneralConciliado().getRegistros().intValue());
					cifrasGenerales.setTotalGeneralConciliado(generalNo);
				}
			}
			return cifrasGenerales;
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar cifras",
							new Object[] { "CifrasGeneralesImpl", "consultarPorTipo()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
	
	public CifrasGeneralesTipoVO consultarPorTipo(CifrasGeneralesFilter cifrasGeneralesFilter)
			throws BusinessException {
		try {
			CifrasGeneralesTipoVO cifrasGenerales = new CifrasGeneralesTipoVO();
//			CifrasGeneralesTipoVO cifrasGeneralesResult = 
			CifrasGeneralesTipoVO lp = this.cifrasGeneralesPersistence.selectPorTipoLP(cifrasGeneralesFilter);
			CifrasGeneralesTipoVO cp = this.cifrasGeneralesPersistence.selectPorTipoCP(cifrasGeneralesFilter);
			cifrasGenerales.setSeccionesLargoPlazo(lp != null ? lp.getSeccionesLargoPlazo() : null);
			cifrasGenerales.setSeccionesCortoPlazo(cp != null ? cp.getSeccionesCortoPlazo() : null);
			
//			for(CifrasGeneralesTipoVO lpTemp : lp){
//				finalList.add(lpTemp);
//				}
//			for(CifrasGeneralesTipoVO cpTemp : cp){
//				finalList.add(cpTemp);
//				}
//			cifrasGenerales.setSeccionesLargoPlazo(cifrasGeneralesResult.getSeccionesLargoPlazo() != null ? cifrasGeneralesResult.getSeccionesLargoPlazo() : null);
//			cifrasGenerales.setNombreSeccion(cifrasGeneralesResult.getNombreSeccion() != null ? cifrasGeneralesResult.getNombreSeccion() : null);
//			cifrasGeneralesResult = this.cifrasGeneralesPersistence.selectPorTipoCP(cifrasGeneralesFilter);
//			if (cifrasGeneralesResult != null && cifrasGeneralesResult.getSeccionesCortoPlazo() != null) {
//				cifrasGenerales.setSeccionesCortoPlazo(cifrasGeneralesResult.getSeccionesCortoPlazo() != null ? cifrasGeneralesResult.getSeccionesCortoPlazo() : null);
//				cifrasGenerales.setNombreSeccion(cifrasGeneralesResult.getNombreSeccion() != null ? cifrasGeneralesResult.getNombreSeccion() : null);
//			}
//			CifrasGeneralesVO totales = this.cifrasGeneralesPersistence.selectTotales(cifrasGeneralesFilter);
			if(lp != null && cp != null){
				if (lp.getTotalGeneralNoConciliado() != null && cp.getTotalGeneralNoConciliado() != null) {
					ElementoVO generalNo = new ElementoVO();
					generalNo.setRegistros(lp.getTotalGeneralNoConciliado().getRegistros().intValue() + cp.getTotalGeneralNoConciliado().getRegistros().intValue());
					cifrasGenerales.setTotalGeneralNoConciliado(generalNo);
				}
				
				if (lp.getTotalGeneralConciliado() != null && cp.getTotalGeneralConciliado() != null) {
					ElementoVO generalNo = new ElementoVO();
					generalNo.setRegistros(lp.getTotalGeneralConciliado().getRegistros().intValue() + cp.getTotalGeneralConciliado().getRegistros().intValue());
					cifrasGenerales.setTotalGeneralConciliado(generalNo);
				}
			}
			else if(lp != null && cp == null){
				if (lp.getTotalGeneralNoConciliado() != null) {
					ElementoVO generalNo = new ElementoVO();
					generalNo.setRegistros(lp.getTotalGeneralNoConciliado().getRegistros().intValue());
					cifrasGenerales.setTotalGeneralNoConciliado(generalNo);
				}
				
				if (lp.getTotalGeneralConciliado() != null) {
					ElementoVO generalNo = new ElementoVO();
					generalNo.setRegistros(lp.getTotalGeneralConciliado().getRegistros().intValue());
					cifrasGenerales.setTotalGeneralConciliado(generalNo);
				}
			}
			else if(lp == null && cp != null){
				if (cp.getTotalGeneralNoConciliado() != null) {
					ElementoVO generalNo = new ElementoVO();
					generalNo.setRegistros(cp.getTotalGeneralNoConciliado().getRegistros().intValue());
					cifrasGenerales.setTotalGeneralNoConciliado(generalNo);
				}
				
				if (cp.getTotalGeneralConciliado() != null) {
					ElementoVO generalNo = new ElementoVO();
					generalNo.setRegistros(cp.getTotalGeneralConciliado().getRegistros().intValue());
					cifrasGenerales.setTotalGeneralConciliado(generalNo);
				}
			}
			return cifrasGenerales;
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar cifras",
							new Object[] { "CifrasGeneralesImpl", "consultarPorTipo()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

	public List<CifrasGeneralesVO> consultarPorBanco(CifrasGeneralesFilter cifrasGeneralesFilter)
			throws BusinessException {
		try {
			return cifrasGeneralesPersistence.selectPorBanco(cifrasGeneralesFilter);
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar cifras",
							new Object[] { "CifrasGeneralesImpl", "consultarPorBanco()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

	public List<CifrasGeneralesVO> consultarPorSBC(CifrasGeneralesFilter cifrasGeneralesFilter)
			throws BusinessException {
		try {
			return cifrasGeneralesPersistence.selectPorSBC(cifrasGeneralesFilter);
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar cifras",
							new Object[] { "CifrasGeneralesImpl", "consultarPorSBC()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

	public List<CifrasGeneralesVO> consultarPorOrigen(CifrasGeneralesFilter cifrasGeneralesFilter)
			throws BusinessException {
		try {
			return cifrasGeneralesPersistence.selectPorOrigen(cifrasGeneralesFilter);
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar cifras",
							new Object[] { "CifrasGeneralesImpl", "consultarPorTipo()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

	public ReporteVO generarReport(CifrasGeneralesFilter cifrasGeneralesFilter) throws BusinessException {

		try {
			List<CifrasGeneralesVO> cifras = cifrasGeneralesPersistence.selectPorBanco(cifrasGeneralesFilter);
			List listaCifras = new ArrayList();
			CifrasGeneralesReporteVO CifrasGeneralesReporteVOPorBanco = new CifrasGeneralesReporteVO();
			CifrasGeneralesReporteVOPorBanco.setReporte("Por banco");
			CifrasGeneralesReporteVOPorBanco.setListaCifras(CifrasGeneralesVOTOCifrasGeneralesReporteVO(cifras, REPORTE_BANCO));
			listaCifras.add(CifrasGeneralesReporteVOPorBanco);
			//Extraer por origen
			cifras.clear();
			cifras = cifrasGeneralesPersistence.selectPorOrigen(cifrasGeneralesFilter);
			CifrasGeneralesReporteVO CifrasGeneralesReporteVOPorOrigen = new CifrasGeneralesReporteVO();
			CifrasGeneralesReporteVOPorOrigen.setReporte("Por Origen");
			CifrasGeneralesReporteVOPorOrigen.setListaCifras(CifrasGeneralesVOTOCifrasGeneralesReporteVO(cifras, REPORTE_ORIGEN));
			listaCifras.add(CifrasGeneralesReporteVOPorOrigen);
			//extraer por SBC
			cifras = cifrasGeneralesPersistence.selectPorSBC(cifrasGeneralesFilter);
			CifrasGeneralesReporteVO CifrasGeneralesReporteVOPorSBC = new CifrasGeneralesReporteVO();
			CifrasGeneralesReporteVOPorSBC.setReporte("Por Saldo Buen Cobro");
			CifrasGeneralesReporteVOPorSBC.setListaCifras(CifrasGeneralesVOTOCifrasGeneralesReporteVO(cifras, REPORTE_SBC));
			listaCifras.add(CifrasGeneralesReporteVOPorSBC);
			cifrasControlReportService.generarArchivoCifrasGenerales(listaCifras);
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar cifras",
							new Object[] { "CifrasGeneralesImpl", "consultarPorTipo()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
		return null;
	}

	private List CifrasGeneralesVOTOCifrasGeneralesReporteVO(List<CifrasGeneralesVO> cifrasVO, String tipoReporte) {
		List cifrasGrales = new ArrayList();
		for (CifrasGeneralesVO cifras : cifrasVO) {
			CifrasControlReporteVO cifrasControlReporteVO = null;
			List<SeccionVO> secciones = cifras.getSecciones();
			for (SeccionVO seccion : secciones) {
				cifrasControlReporteVO = new CifrasControlReporteVO();
				cifrasControlReporteVO.setTipoReporte(tipoReporte.equalsIgnoreCase(REPORTE_SBC) ? 20 : 0);
//				cifrasControlReporteVO.setNombre(seccion.getNombreSeccion() != null ? seccion.getNombreSeccion() : "");
				List seccionesReport = new ArrayList();
				for (ElementoVO elemento : seccion.getElementos()) {
					SeccionReporteVO seccionReporteVO = new SeccionReporteVO();
//					seccionReporteVO.setNombreSeccion(seccion.getNombreSeccion() != null ? seccion.getNombreSeccion() : "");
					seccionReporteVO.setNombreElemento(elemento.getNombreElemento());
					seccionReporteVO.setImporte(elemento.getImporte() != null ? elemento.getImporte() : null);
					seccionReporteVO.setRegistros(elemento.getRegistros() != null ? elemento.getRegistros() : null);
					//LOGGER.debug(seccion.getTotalConciliado().getRegistros());
					seccionReporteVO.setEstatus(seccion.getTotalConciliado().getRegistros() == null ? null : ((int) seccion.getTotalConciliado().getRegistros() == 0 ? "NO CONCILIADO":"CONCILIADO"));
					seccionReporteVO
							.setArchivoOrigen(elemento.getArchivoOrigen() != null ? elemento.getArchivoOrigen() : "");
					seccionesReport.add(seccionReporteVO);
				}
				cifrasControlReporteVO.setSecciones(seccionesReport);
				cifrasGrales.add(cifrasControlReporteVO);
			}

		}
		return cifrasGrales;
	}
}

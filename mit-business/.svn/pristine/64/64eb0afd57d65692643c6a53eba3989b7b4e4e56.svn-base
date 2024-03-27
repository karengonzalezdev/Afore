package mx.profuturo.nci.business.service.cifctrlreclasificacion;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.report.impl.CifrasGeneralesReportServiceImpl;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.vo.cifctrlreclasificacion.CCOrigDestReclasificacionVO;
import mx.profuturo.nci.business.vo.cifctrlreclasificacion.CCTotalReclasificacionVO;
import mx.profuturo.nci.business.vo.cifctrlreclasificacion.CCTotalSubcuentaReclasificacionVO;
import mx.profuturo.nci.business.wrapped.cifctrlreclasificacion.CifCtrlReclasificacionFilter;

/**
 * Clase de acceso a la tabla del esquema ETL  TTSISGRAL_ETL_CC_RECLASIFICACION
 * 
 * @author UC998101
 *
 */
public interface ICifCtrlReclasificacionService {
	 
	
	/**
	 * Consulta el total de solicitudes aceptadas y rechazadas.
	 * @param filter Folio de reclasificacion.
	 * @return Toles de acptadas, rechazadas y ambas
	 * @throws BusinessException
	 */
	public CCTotalReclasificacionVO consultaTotalReclasificacion(CifCtrlReclasificacionFilter filter)throws BusinessException;
	/**
	 * Consulta el numero de solicitudes de cargos y abonos por siefore y los clasifica en Origen/Destino
	 * @param filter Folio de reclasificacion.
	 * @return Listado de Origen/Destino de numero de cargos/abono por siefores.
	 * @throws BusinessException
	 */
	public List<CCOrigDestReclasificacionVO> consultaOrigDestReclasificacion(CifCtrlReclasificacionFilter filter)throws BusinessException;
	/**
	 * Consulta total de pesos por subcuenta y por siefores. Resta cargos menos abonos.
	 * @param filter Folio de reclasificacion.
	 * @return Lista por subcuenta los totales (cargos - abonos) clasificados por siefores
	 * @throws BusinessException
	 */
	public List<CCTotalSubcuentaReclasificacionVO> consultaTotalPorSubcuentas(CifCtrlReclasificacionFilter filter)throws BusinessException;
	/**
	 * Consulta total de pesos por subcuenta y por siefores de Cargos|Abonos
	 * @param filter Folio de reclasificacion, Tipo de movimiento: 180-CARGOS, 181-ABONOS 
	 * @return Lista por subcuenta los totales (cargos o abonos) clasificados por siefores
	 * @throws BusinessException
	 */
	public List<CCTotalSubcuentaReclasificacionVO> consultaCargAboPorSubcuentas(CifCtrlReclasificacionFilter filter)throws BusinessException;
	
	/**
	 * Se exporta archivo que se miestra en panatalla el cual.
	 * @param los lsitados que estan en panatalla para que se muestren en reporte
	 * @return se muestra el reporte generado por estos resultados 
	 * @throws BusinessException
	 * */
	//public ReporteVO exportaArchivoCifCtl() throws BusinessException;
	
}

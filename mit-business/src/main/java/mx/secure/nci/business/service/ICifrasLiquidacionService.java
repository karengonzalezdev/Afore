package mx.secure.nci.business.service;

import java.util.Date;
import java.util.List;

import mx.secure.nci.business.vo.CifrasLiquidacionConsultaSolVO;
import mx.secure.nci.business.vo.CifrasLiquidacionSolVO;
import mx.secure.nci.business.vo.CifrasLiquidacionSolicitudVO;
import mx.secure.nci.business.vo.CifrasLiquidacionVO;
import mx.secure.nci.business.wrapped.CifrasLiquidacionFilter;

public interface ICifrasLiquidacionService {

	/**
     * Consulta las cifras liquidacion para la pantalla de BPM
     * @param  CifrasLiquidacionFilter
     * @author NO997418
     * @return SolicitudCifrasLiquidacionVO con la info para la pantalla de bpm
     */
	CifrasLiquidacionVO consultarCifrasLiquidacion(CifrasLiquidacionFilter filter) throws Exception;
	
	/**
	 * Consulta las cifras liquidacion 
	 * 
	 * @param SolCifrasLiquidacionBeanRequest
	 * @author NO997418
	 * @date 07/12/2021
	 * @return SolicitudCifrasLiquidacionBeanResponse con la info para la
	 *         pantalla de bpm de Cifras Liquidación Traspaso seccion posponer solicitudes
	 */
	List<CifrasLiquidacionSolVO> consultaSolicitudes(CifrasLiquidacionFilter filter) throws Exception;
	
	
	
	
    /**
     * Consultar las solicitudes seleccionadas por un rango de fecha en la pantalla cifras liquidacion 
     *
     * @author NO997418
     * @category Carta de mantenimiento - 06-Enero-2022
     * @param CifrasLiquidacionFilter filter
     * @return Devuelve true si se inserto de manera correcta
     * @throws Exception 
     */
	Integer registrarCifrasLiquidacion(CifrasLiquidacionFilter filter ) throws Exception;
	
	
	
    /**
     * Consultar las solicitudes seleccionadas por un rango de fecha en la pantalla cifras liquidacion 
     *
     * @author NO997418
     * @category Carta de mantenimiento - 06-Enero-2022
     * @param CifrasLiquidacionFilter filter
     * @return Devuelve true si se inserto de manera correcta
     * @throws Exception 
     */
	Integer excluirSolCifrasLiquidacion(CifrasLiquidacionFilter filter ) throws Exception;
	
	
	int eliminarRegistrosCifrasLiquidacion() throws Exception;
	/**
	 * Consulta las cifras liquidacion 
	 * 
	 * @param SolCifrasLiquidacionBeanRequest
	 * @author NO997418
	 * @date 07/12/2021
	 * @return SolicitudCifrasLiquidacionBeanResponse con la info para la
	 *         pantalla de bpm de Cifras Liquidación Traspaso seccion posponer solicitudes
	 */
	List<CifrasLiquidacionSolVO> consultaSolicitudesExcuidas() throws Exception;
}
package mx.profuturo.nci.persistence;

import java.util.List;
import mx.profuturo.nci.business.vo.CifrasLiquidacionSolVO;
import mx.profuturo.nci.business.vo.DetalleSieforesVO;
import mx.profuturo.nci.business.wrapped.CifrasLiquidacionFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface CifrasLiquidacionPersistence {
	
    /**
     * Consultar las solicitudes   
     *
     * @author NO997418
     * @category Carta de mantenimiento - 06-Enero-2022
     * @param CifrasLiquidacionFilter filter
     * @return Devuelve una lista por siefore de las cifras liquidacion
     */
	List<DetalleSieforesVO> consultarPorSieforeCifrasLiquidacion(CifrasLiquidacionFilter solicitudFilter);
	
    /**
     * Consultar las solicitudes seleccionadas por un rango de fecha en la pantalla cifras liquidacion 
     *
     * @author NO997418
     * @category Carta de mantenimiento - 06-Enero-2022
     * @param CifrasLiquidacionFilter filter
     * @return Devuelve true si se inserto de manera correcta
     */
	Integer registrarCifrasLiquidacion(CifrasLiquidacionFilter filter );
	
	
	
    /**
     * Consultar las solicitudes seleccionadas por un rango de fecha en la pantalla cifras liquidacion 
     *
     * @author NO997418
     * @category Carta de mantenimiento - 06-Enero-2022
     * @param CifrasLiquidacionFilter filter
     * @return Devuelve true si se inserto de manera correcta
     */
	Integer excluirSolCifrasLiquidacion(CifrasLiquidacionFilter filter );
	
	
	/**
	 * Consulta las cifras liquidacion 
	 * 
	 * @param CifrasLiquidacionBeanRequest
	 * @author NO997418
	 * @date 07/12/2021
	 * @return SolicitudCifrasLiquidacionBeanResponse con la info para la
	 *         pantalla de bpm de Cifras Liquidación Traspaso seccion posponer solicitudes
	 */
	public List<CifrasLiquidacionSolVO> consultaSolicitudes(CifrasLiquidacionFilter solicitudFilter);
	
	
	public List<DetalleSieforesVO> cifrasSolicitudes(CifrasLiquidacionFilter solicitudFilter);
	
	public int eliminarRegistrosCifrasLiquidacion();
	
	public List<CifrasLiquidacionSolVO> consultaSolicitudesExcuidas();


}
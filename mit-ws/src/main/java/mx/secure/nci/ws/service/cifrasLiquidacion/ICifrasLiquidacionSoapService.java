package mx.secure.nci.ws.service.cifrasLiquidacion;

import mx.secure.nci.ws.beans.response.CifrasLiquidacionBeanResponse;
import mx.secure.nci.ws.beans.response.CifrasLiquidacionConsultaSolBeanResponse;
import mx.secure.nci.ws.beans.response.CifrasLiquidacionExcluirSolBeanResponce;
import mx.secure.nci.ws.beans.response.CifrasLiquidacionExportableBeanResponse;
import mx.secure.nci.ws.beans.response.ReporteBeanResponse;
import mx.secure.nci.ws.beans.request.CifrasLiquidacionBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasLiquidacionConsultaSolBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasLiquidacionExcluirSolBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasLiquidacionExportableBeanRequest;

public interface ICifrasLiquidacionSoapService{

	/**
	 * Consulta las cifras liquidacion 
	 * 
	 * @param CifrasLiquidacionBeanRequest
	 * @author NO997418
	 * @date 01/12/2021
	 * @return SolicitudCifrasLiquidacionBeanResponse con la info para la
	 *         pantalla de bpm de Cifras Liquidación Traspaso seccion consulta cifras
	 */
	public CifrasLiquidacionBeanResponse consultaCifrasLiquidacion(
			CifrasLiquidacionBeanRequest solicitudCifrasLiquidacionBeanRequest);
	
	/**
	 * Consulta las cifras liquidacion 
	 * 
	 * @param CifrasLiquidacionBeanRequest
	 * @author NO997418
	 * @date 07/12/2021
	 * @return SolicitudCifrasLiquidacionBeanResponse con la info para la
	 *         pantalla de bpm de Cifras Liquidación Traspaso seccion posponer solicitudes
	 */
	public CifrasLiquidacionConsultaSolBeanResponse consultaSolicitudes(
			CifrasLiquidacionConsultaSolBeanRequest solicitudCifrasLiquidacionBeanRequest);
	
	/**
	 * Excluye las solicitudes de la pantallas cifras liquidacion 
	 * 
	 * @param CifrasLiquidacionBeanRequest
	 * @author NO997418
	 * @date 07/12/2021
	 * @return CifrasLiquidacionExcluirSolBeanResponse devuelve true si fue exitosa la exclusion
	 */
	public CifrasLiquidacionExcluirSolBeanResponce excluirSolCifrasLiquidacion(
			CifrasLiquidacionExcluirSolBeanRequest solicitudCifrasLiquidacionBeanRequest);
	
	/**
     * Descarga las cifras de la pantalla de Cifras de liquidacion y las solicitudes excluidas
     * @param  FolioRelacionadoBeanRequest
     * @author NO997418
     * @date 05/01/2022
     * @return ReporteBeanResponse con la info para el exportable
     */
	ReporteBeanResponse exportableCifrasLiquidacion(CifrasLiquidacionExportableBeanRequest request);
	
	
	public CifrasLiquidacionExportableBeanResponse consultaCifrasLiquidacionExportable(
			CifrasLiquidacionExportableBeanRequest solicitudCifrasLiquidacionBeanRequest);

}

package mx.secure.nci.ws.service.cifctrlreclasificacion;


import mx.secure.nci.business.vo.cifctrlreclasificacion.CCTotalReclasificacionVO;
import mx.secure.nci.ws.beans.request.CifCtrlCargoAbonoReclasifBeanRequest;
import mx.secure.nci.ws.beans.request.CifCtrlTotalReclasificacionBeanRequest;
import mx.secure.nci.ws.beans.response.CifCtrlOrigDestReclasificacionBeanResponse;
import mx.secure.nci.ws.beans.response.CifCtrlReclasificacionBeanResponse;

/**
 * Servicio para acceso a los datos en el esquema ETL de la tabla TTSISGRAL_ETL_CC_RECLASIFICACION
 * 
 * @author UC998101
 *
 */
public interface ICifCtrlReclasificacionSoapService {
	
	public CCTotalReclasificacionVO consultaTotalReclasificacion(CifCtrlTotalReclasificacionBeanRequest request);
	public CifCtrlOrigDestReclasificacionBeanResponse consultaOrigDestReclasificacion(CifCtrlTotalReclasificacionBeanRequest request);
	public CifCtrlReclasificacionBeanResponse consultaTotalPorSubcuentas(CifCtrlTotalReclasificacionBeanRequest request);
	public CifCtrlReclasificacionBeanResponse consultaCargAboPorSubcuentas(CifCtrlCargoAbonoReclasifBeanRequest request);

}

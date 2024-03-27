package mx.secure.nci.web.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.ReporteVO;
import mx.secure.nci.web.beans.ConsultaAforeMovilBean;

public interface IMonitoreoAforeMovilWebService {
	
	public List<ConsultaAforeMovilBean> consultarMovimientosAforeMovil(String filtroVal, String filtro, 
			Date fecInicio, Date fecFin) throws MitBusinessException;
	
	public ReporteVO generaReporteDepositosAforeMovil(List<ConsultaAforeMovilBean> lstAforeMovil) throws MitBusinessException;
	
	public String obtenerParamCatConfig(Short idConfig) throws MitBusinessException;
	
	public boolean rechazarMovimientos(List<ConsultaAforeMovilBean> beans,String usuario) throws MitBusinessException;
	
	public String obtenerFolioNuevoAforeMovil(Integer proceso, Integer subProceso, Integer canal, Short estatus, 
                                              String usuario) throws MitBusinessException;
	
	public int insertaRegistroConciliacion(String folio, Short origen, Long numCtaInd, BigDecimal importe, String nombre,
                                           String apPaterno, String apMaterno, String curp, String rfc, Long nss, String correo,
                                           Long celular, String usuario, BigInteger subctasecure) throws MitBusinessException;
	
	public boolean invocaServicioLiberar(List<String> lstCurp, String usuario) throws MitBusinessException;
	
	public int actualizaStatusConciliacion(Integer idConciliacion, Short status, String usuario) throws MitBusinessException;
}
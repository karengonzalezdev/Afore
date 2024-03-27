package mx.profuturo.nci.business.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.DetalleMesDomiTraspasoVO;
import mx.profuturo.nci.business.vo.GenericCatalogoIntegerVO;
import mx.profuturo.nci.business.vo.InversionDomiTraspasoVO;
import mx.profuturo.nci.business.vo.MonitorCargosDomiTraspasoVO;
import mx.profuturo.nci.business.vo.MonitorSolicitudesVO;

public interface IDomiTraspasosService {

	public List<MonitorCargosDomiTraspasoVO> monitorCargosDomiTraspaso() throws MitBusinessException;
	
	public List<InversionDomiTraspasoVO> inversionDomiTrasPorAcreditar() throws MitBusinessException;
	
	public List<InversionDomiTraspasoVO> inversionDomiTrasPendientesTraspaso() throws MitBusinessException;
	
	public List<InversionDomiTraspasoVO> inversionDomiTrasDevolucion() throws MitBusinessException;
	
	public List<InversionDomiTraspasoVO> inversionDomiTrasAcreditados() throws MitBusinessException;
	
	public List<GenericCatalogoIntegerVO> obtenerLstMesDetalleMesDomiTras() throws MitBusinessException;
	
	public List<DetalleMesDomiTraspasoVO> obtenerLstDetalleMesDomiTras(Integer mesBusqueda) throws MitBusinessException;
	//FOP 27/09/2018 | Se agrega Domi Registro C/S Liquidaciones
	public List<DetalleMesDomiTraspasoVO> obtenerLstDetalleMesDomiTrasCL(Integer mesBusqueda) throws MitBusinessException;
	public List<DetalleMesDomiTraspasoVO> obtenerLstDetalleMesDomiTrasSL(Integer mesBusqueda) throws MitBusinessException;
	
	public List<MonitorSolicitudesVO> obtenerLstMonitorSolicitud(String numCuenta, String nss, String curp, List<Short> origenSol) throws MitBusinessException;
	
	public List<MonitorSolicitudesVO> obtenerLstMonitorSolicitud(String numCuenta, String nss, String curp) throws MitBusinessException;
	
	public Date actualizaDomiSol(String cveSol, Short periodicidad, Short frecIni, Short frecFin, BigDecimal importe, String usuario) throws MitBusinessException;
	
	public int actualizaDomiDiversificacion(String cveSol, Short fondoApovol, BigDecimal monto)throws MitBusinessException;
	
	public int insertarDomiDiversificacion(String cveSol, Short fondo, BigDecimal monto, Integer porcentaje, String usuario, Integer vigencia) throws MitBusinessException;
	
	public int actualizaDomiDiversificacionVigencia(String cveSol, Integer vigencia, String usuario) throws MitBusinessException;
	
	public int actualizaEstatusDomiSol(String cveSol,Short idEstatus,String usuario) throws MitBusinessException;

	public List<MonitorSolicitudesVO> obtenerLstHistSolicitud(String cveSol, Short idOrigen);
}
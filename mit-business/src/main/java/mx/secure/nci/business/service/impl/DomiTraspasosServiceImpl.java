package mx.secure.nci.business.service.impl;

import static mx.secure.nci.business.util.ConstantesCatalogos.ID_ESTATUS_SOLICITUD_CANCELADO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.secure.nci.business.exception.ErrorCodeService;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.IDomiTraspasosService;
import mx.secure.nci.business.service.ISolicitudService;
import mx.secure.nci.business.vo.DetalleMesDomiTraspasoVO;
import mx.secure.nci.business.vo.GenericCatalogoIntegerVO;
import mx.secure.nci.business.vo.InversionDomiTraspasoVO;
import mx.secure.nci.business.vo.MonitorCargosDomiTraspasoVO;
import mx.secure.nci.business.vo.MonitorSolicitudesVO;
import mx.secure.nci.business.wrapped.secureFilter;
import mx.secure.nci.business.wrapped.DetalleMesDomiTraspasoFilter;
import mx.secure.nci.business.wrapped.MonitorDomiTraspasoFilter;
import mx.secure.nci.business.wrapped.SolicitudFilter;
import mx.secure.nci.persistence.SolicitudPersistence;

@Service("domiTraspasoService")
public class DomiTraspasosServiceImpl implements IDomiTraspasosService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DomiTraspasosServiceImpl.class);

	@Autowired
	SolicitudPersistence solicitudPersistence;
	
	@Autowired ISolicitudService solicitudService;

	public List<MonitorCargosDomiTraspasoVO> monitorCargosDomiTraspaso() throws MitBusinessException{
		try{
			return this.solicitudPersistence.monitorCargosDomiTraspaso(new SolicitudFilter());
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Al Consultar Solicitudes ",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
	
	public List<InversionDomiTraspasoVO> inversionDomiTrasPorAcreditar() throws MitBusinessException{
		try{
			return this.solicitudPersistence.inversionDomiTrasPorAcreditar(new SolicitudFilter());
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Al Consultar Solicitudes ",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
		
	public List<InversionDomiTraspasoVO> inversionDomiTrasPendientesTraspaso() throws MitBusinessException{
		try{
			return this.solicitudPersistence.inversionDomiTrasPendientesTraspaso(new SolicitudFilter());
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Al Consultar Solicitudes ",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
		
	public List<InversionDomiTraspasoVO> inversionDomiTrasDevolucion() throws MitBusinessException{
		try{
			return this.solicitudPersistence.inversionDomiTrasDevolucion(new SolicitudFilter());
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Al Consultar Solicitudes ",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
		
	public List<InversionDomiTraspasoVO> inversionDomiTrasAcreditados() throws MitBusinessException{
		try{
			return this.solicitudPersistence.inversionDomiTrasAcreditados(new SolicitudFilter());
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Al Consultar Solicitudes ",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
	
	public List<GenericCatalogoIntegerVO> obtenerLstMesDetalleMesDomiTras() throws MitBusinessException{
		try{
			return this.solicitudPersistence.detalleMesDomiTraspaso(new SolicitudFilter());
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Al Consultar el listado de Meses para detalleMesDomiTras ",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}		
	}
	
	public List<DetalleMesDomiTraspasoVO> obtenerLstDetalleMesDomiTras(Integer mesBusqueda) throws MitBusinessException{
		try{
			DetalleMesDomiTraspasoFilter filter = new DetalleMesDomiTraspasoFilter();
			filter.setAnio(mesBusqueda.toString().substring(0,4));
			filter.setMes(mesBusqueda.toString().substring(4,6));
			return this.solicitudPersistence.lstDetalleDomiTraspaso(filter);
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Al Consultar el listado sobre Detalle de Mes ",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
	
	public List<DetalleMesDomiTraspasoVO> obtenerLstDetalleMesDomiTrasCL(Integer mesBusqueda) throws MitBusinessException{
		try{
			DetalleMesDomiTraspasoFilter filter = new DetalleMesDomiTraspasoFilter();
			filter.setAnio(mesBusqueda.toString().substring(0,4));
			filter.setMes(mesBusqueda.toString().substring(4,6));
			return this.solicitudPersistence.lstDetalleDomiTraspasoCL(filter);
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Al Consultar el listado sobre Detalle Motor DCL de Mes ",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
	
	public List<DetalleMesDomiTraspasoVO> obtenerLstDetalleMesDomiTrasSL(Integer mesBusqueda) throws MitBusinessException{
		try{
			DetalleMesDomiTraspasoFilter filter = new DetalleMesDomiTraspasoFilter();
			filter.setAnio(mesBusqueda.toString().substring(0,4));
			filter.setMes(mesBusqueda.toString().substring(4,6));
			return this.solicitudPersistence.lstDetalleDomiTraspasoSL(filter);
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Al Consultar el listado sobre Detalle Motor DSL de Mes ",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}	
	
	public List<MonitorSolicitudesVO> obtenerLstHistSolicitud(String cveSol, Short idOrigen){
		MonitorDomiTraspasoFilter filter = new MonitorDomiTraspasoFilter();
		filter.setCveSol(cveSol);
		filter.setIdOrigenSol(idOrigen);
		
		return this.solicitudPersistence.lstHistDomiTraspaso(filter);
	}

	public List<MonitorSolicitudesVO> obtenerLstMonitorSolicitud(String numCuenta, String nss, String curp, List<Short> origenSol) throws MitBusinessException{
		try{
			MonitorDomiTraspasoFilter filter = new MonitorDomiTraspasoFilter();
			if(numCuenta!=null && !numCuenta.isEmpty()){
				filter.setCuentaInd(numCuenta);
			}else if(nss!=null && !nss.isEmpty()){
				filter.setNss(nss);
			}else if (curp!=null && !curp.isEmpty()){
				filter.setCurp(curp);
			}
			filter.setOrigenSol(origenSol);
			
			return this.solicitudPersistence.lstMonitorDomiTraspaso(filter);
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Al Consultar el listado del Monitor de DomiTraspaso ",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

	
	public List<MonitorSolicitudesVO> obtenerLstMonitorSolicitud(String numCuenta, String nss, String curp) throws MitBusinessException{
		try{
			MonitorDomiTraspasoFilter filter = new MonitorDomiTraspasoFilter();
			if(numCuenta!=null && !numCuenta.isEmpty()){
				filter.setCuentaInd(numCuenta);
			}else if(nss!=null && !nss.isEmpty()){
				filter.setNss(nss);
			}else if (curp!=null && !curp.isEmpty()){
				filter.setCurp(curp);
			}
			
			List<Short> lstOrigen = new ArrayList<Short>();
			lstOrigen.add(new Short("845"));
			filter.setOrigenSol(lstOrigen);
			return this.solicitudPersistence.lstMonitorDomiTraspaso(filter);
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Al Consultar el listado del Monitor de DomiTraspaso ",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

	
	public Date actualizaDomiSol(String cveSol, Short periodicidad, Short frecIni, Short frecFin,BigDecimal importe, String usuario)throws MitBusinessException{
		secureFilter filter = new secureFilter(); 
		filter.setCveSol(cveSol);
		filter.setPeriodicidad(periodicidad);
		
		if(frecIni!=null && frecIni>0){
			filter.setFrecIni(frecIni);
		}
		if(frecFin!=null && frecFin>0){
			filter.setFrecFin(frecFin);
		}
		
		filter.setMonto(importe);
		filter.setUsuario(usuario);
		
//		filter.setIdEstatus(new Short("514"));
		
		try{
			filter.setFechaProximoCargo(solicitudService.getProximaFechaDeCargo(filter.getSolicitud()));
			 this.solicitudPersistence.actualizaDomiTraspaso(filter);
			 return filter.getFechaProximoCargo();
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Error al actualizar domi traspaso",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}		
	}
	
	public int actualizaEstatusDomiSol(String cveSol,Short idEstatus,String usuario) throws MitBusinessException{
		try{
			secureFilter filter = new secureFilter(); 
			filter.setCveSol(cveSol);
			filter.setUsuario(usuario);
			filter.setIdEstatus(idEstatus);
			return this.solicitudPersistence.actualizaEstatusDomiTraspaso(filter);
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
				ErrorCodeService.EX_EXCEPTION, "Error al cancelar domi traspaso:"+ex.getMessage(),
				new Object[] { getClass().getSimpleName(), "cancelaDomiSol("+cveSol+")" }, ex));
			throw mitBusinessException;
		}		
	}
	
	public int actualizaDomiDiversificacion(String cveSol, Short fondosecure, BigDecimal monto)throws MitBusinessException{
		secureFilter filter = new secureFilter();
		filter.setCveSol(cveSol);
		filter.setFondosecure(fondosecure);
		filter.setMonto(monto);
		
		try{
			return this.solicitudPersistence.actualizaDomiTraspasosecure(filter);
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Error al actualizar domi traspaso secure ",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
	
	public int actualizaDomiDiversificacionVigencia(String cveSol, Integer vigencia, String usuario)throws MitBusinessException{
		secureFilter filter = new secureFilter();
		filter.setCveSol(cveSol);
		filter.setVigencia(vigencia);
		filter.setUsuario(usuario);

		try{
			return this.solicitudPersistence.actualizaDomiVigencia(filter);
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Error al actualizar domi Viencia ",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
	
	public int insertarDomiDiversificacion(String cveSol, Short fondo, BigDecimal monto, Integer porcentaje, String usuario, Integer vigencia) throws MitBusinessException{
		secureFilter filter = new secureFilter();
		filter.setCveSol(cveSol);
		filter.setFondo(fondo.intValue());
		filter.setMonto(monto);
		filter.setPorcentaje(porcentaje);
		filter.setUsuario(usuario);
		filter.setVigencia(vigencia);
		try{
			return this.solicitudPersistence.insertaDomiDiversificacion(filter);
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "Error al actualizar domi vigencia",
			new Object[] { getClass().getSimpleName(), "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	} 
}
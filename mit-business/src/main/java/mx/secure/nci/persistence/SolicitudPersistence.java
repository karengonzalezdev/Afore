package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.DetalleMesDomiTraspasoVO;
import mx.secure.nci.business.vo.DiversificacionVO;
import mx.secure.nci.business.vo.GenericCatalogoIntegerVO;
import mx.secure.nci.business.vo.InversionDomiTraspasoVO;
import mx.secure.nci.business.vo.MonitorCargosDomiTraspasoVO;
import mx.secure.nci.business.vo.MonitorSolicitudesVO;
import mx.secure.nci.business.vo.SolicitudCargoVO;
import mx.secure.nci.business.vo.SolicitudSumTotalPorBancoVO;
import mx.secure.nci.business.vo.SolicitudSumTotalVO;
import mx.secure.nci.business.vo.SolicitudVO;
import mx.secure.nci.business.vo.SpeiDetallesecureVO;
import mx.secure.nci.business.vo.ValidacionVO;
import mx.secure.nci.business.wrapped.secureFilter;
import mx.secure.nci.business.wrapped.DetalleMesDomiTraspasoFilter;
import mx.secure.nci.business.wrapped.MonitorDomiTraspasoFilter;
import mx.secure.nci.business.wrapped.SolicitudFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface SolicitudPersistence {
	List<SolicitudVO> selectSolicitudDomiciliacion(SolicitudFilter solicitudFilter);

	List<SolicitudVO> selectSolicitudSumDomiciliacion(SolicitudFilter solicitudFilter);
	
	int insertarSolicitudDomiciliacion(SolicitudVO solicitudVO);

	int actualizarSolicitudDomiciliacion(SolicitudVO solicitudVO);

	List<SolicitudVO> selectDomiRecords(SolicitudFilter solicitudFilter);
	
	List<SolicitudVO> selectIdsDomiRecords(SolicitudFilter solicitudFilter);
	
	List<SolicitudSumTotalVO> selectDomiResumenTotal(SolicitudFilter solicitudFilter);
	
	List<SolicitudSumTotalPorBancoVO> selectDomiResumenPorBanco(SolicitudFilter solicitudFilter);
	
	List<MonitorCargosDomiTraspasoVO> monitorCargosDomiTraspaso(SolicitudFilter solicitudFilter);
	
	List<InversionDomiTraspasoVO> inversionDomiTrasPorAcreditar(SolicitudFilter solicitudFilter);
	
	List<InversionDomiTraspasoVO> inversionDomiTrasPendientesTraspaso(SolicitudFilter solicitudFilter);
	
	List<InversionDomiTraspasoVO> inversionDomiTrasDevolucion(SolicitudFilter solicitudFilter);
	
	List<InversionDomiTraspasoVO> inversionDomiTrasAcreditados(SolicitudFilter solicitudFilter);
	
	List<GenericCatalogoIntegerVO> detalleMesDomiTraspaso(SolicitudFilter solicitudFilter);
	
	List<DetalleMesDomiTraspasoVO> lstDetalleDomiTraspaso(DetalleMesDomiTraspasoFilter solicitudFilter);
	//FOP 27/09/2018 | Se agrega Domi Registro C/S Liquidaciones
	List<DetalleMesDomiTraspasoVO> lstDetalleDomiTraspasoCL(DetalleMesDomiTraspasoFilter solicitudFilter);
	List<DetalleMesDomiTraspasoVO> lstDetalleDomiTraspasoSL(DetalleMesDomiTraspasoFilter solicitudFilter);
	
	List<MonitorSolicitudesVO> lstMonitorDomiTraspaso(MonitorDomiTraspasoFilter solicitudFilter);
	
	int actualizaDomiTraspaso(secureFilter solicitudFilter);
	
	int actualizaEstatusDomiTraspaso(secureFilter solicitudFilter);
	
	int actualizaDomiTraspasosecure(secureFilter solicitudFilter);
	
	int actualizaDomiVigencia(secureFilter solicitudFilter);
	
	int insertaDomiDiversificacion(secureFilter solicitudFilter);
	
	int cancelaSolicitud(SolicitudVO solicitudVO);
		
	List<SolicitudVO> consultarSolicitudDomiciliacion(SolicitudFilter solicitudFilter);
	
	List<SolicitudVO> consultarHistSolicitud(SolicitudFilter claveSolicitud);
	
//	va a la tabla de Cargos 		
	List<SolicitudVO> consultarSolicitudCargo(SolicitudFilter claveSolicitud);
	
	List<SolicitudVO> consultarCargosConciliacion(SolicitudFilter claveSolicitud);
	
	List<SolicitudVO> consultarHistSolicitudNoDomi(SolicitudFilter claveSolicitud);
	
	List<SolicitudVO> consultarCuentasSolicitud(SolicitudFilter claveSolicitud);
	
	List<SolicitudVO> consultarSolicitudDomiciliacionCorta(SolicitudFilter solicitudFilter);
	
	List<DiversificacionVO> consultarDiversificacionesSolicitud(SolicitudFilter solicitudFilter);

	List<DiversificacionVO> consultarDiversificacionesPreSolicitud(SolicitudFilter solicitudFilter);
	
	List<DiversificacionVO> consultarDiversificacionesConciliacion(SolicitudFilter solicitudFilter);
	
	List<ValidacionVO> consultarValidacionesSolicitud(SolicitudFilter solicitudFilter);
	
	List<MonitorSolicitudesVO> lstHistDomiTraspaso(MonitorDomiTraspasoFilter solFilter);
	
//	va a la tabla de Cargos 		
	List<MonitorSolicitudesVO> lstHistDomiTraspasoTablaCargos(MonitorDomiTraspasoFilter solFilter);
	
	List<SpeiDetallesecureVO> consultarDetalleSpei(SolicitudFilter solFilter);
	
	List<SpeiDetallesecureVO> consultarDetalleNomina(SolicitudFilter solFilter);
	
	List<SolicitudVO> consultarCuentasSolicitudSolici(SolicitudFilter claveSolicitud);
	
	List<SolicitudVO> consultarCuentasSolicitudConcil(SolicitudFilter claveSolicitud);
	
	List<SolicitudVO> consultarCuentasSolicitudBanco(SolicitudFilter claveSolicitud);
	
	List<SolicitudVO> consultarCuentasSolicitudSpei(SolicitudFilter claveSolicitud);
	
	List<SolicitudVO> consultarCuentasSolicitudTodos(SolicitudFilter claveSolicitud);
	
	
	List<SolicitudVO> consultarSolicitudesSolicitud(SolicitudFilter claveSolicitud);
	
	List<SolicitudVO> consultarSolicitudesConciliacion(SolicitudFilter claveSolicitud);
	
	List<SolicitudVO> consultarSolicitudesBancos(SolicitudFilter claveSolicitud);
	
	List<SolicitudVO> consultarSolicitudesSpei(SolicitudFilter claveSolicitud);
	
	List<SolicitudVO> consultarSolicitudesTodos(SolicitudFilter claveSolicitud);
//*
	List<SolicitudCargoVO> consultarCargoSolicitudSolici(SolicitudFilter solicitudFilter);

	int actualizarFechaProximoCargo(secureFilter filter);
	//FOP | 2314 | 12/03/2018
	int reactivarDomicializacionUpdate(SolicitudVO solicitudVO);
	//FOP | 2315 | 26/03/2018
	Integer updateEstatusTramiteAportaciones(SolicitudVO solicitudVO);	
	
	List<SolicitudVO> selectDomiRecordsParcialidades(SolicitudFilter solicitudFilter);
}
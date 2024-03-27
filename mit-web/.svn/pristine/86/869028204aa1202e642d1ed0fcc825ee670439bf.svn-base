package mx.profuturo.nci.web.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.util.HashIndexed3DMatrix;
import mx.profuturo.nci.business.vo.SolicitudVO;
import mx.profuturo.nci.web.beans.ArchivoDomiViewParamBean;
import mx.profuturo.nci.web.beans.CifrasPorBancoBean;
import mx.profuturo.nci.web.beans.CifrasTotalesBean;
import mx.profuturo.nci.web.beans.CuentaIndividualBean;
import mx.profuturo.nci.web.beans.GeneracionArchivoDomiBean;
import mx.profuturo.nci.web.beans.GenericCatalogoBean;
import mx.profuturo.nci.web.beans.RangoFechaParamBean;
import mx.profuturo.nci.web.beans.traspasos.DetalleMesBean;
import mx.profuturo.nci.web.beans.traspasos.InversionDomiTraspasosBean;
import mx.profuturo.nci.web.beans.traspasos.MonitoreoCargosDomiBean;
import mx.profuturo.nci.web.beans.traspasos.MonitoreoSolicitudesDetalleBean;

public interface IArchivoDomiciliacionWebService {
	HashIndexed3DMatrix<RangoFechaParamBean> getRangosUltimoGenerado(Short idOrigen) throws MitBusinessException;
	List<GeneracionArchivoDomiBean> generarArchivoDomi(ArchivoDomiViewParamBean paramBean,List<SolicitudVO> noConviven) throws MitBusinessException;
	List<CifrasTotalesBean> generaCifrasTotales(ArchivoDomiViewParamBean paramBean) throws MitBusinessException;
	List<CifrasPorBancoBean> generarCifrasPorBanco(ArchivoDomiViewParamBean paramBean) throws MitBusinessException;
	List<SolicitudVO> getNoConviven(ArchivoDomiViewParamBean paramBean) throws MitBusinessException;
	List<GenericCatalogoBean> getTiposOrigen() throws MitBusinessException;
	
	List<MonitoreoCargosDomiBean> generaMonitoreoCargosDomiTraspaso() throws MitBusinessException;
	
	InversionDomiTraspasosBean generaInvDomiTrasPorAcreditar() throws MitBusinessException;
	InversionDomiTraspasosBean generaInvDomiTrasPendientesTraspaso() throws MitBusinessException;
	InversionDomiTraspasosBean generaInvDomiTrasDevolucion() throws MitBusinessException;
	InversionDomiTraspasosBean generaInvDomiTrasAcreditados() throws MitBusinessException;
	
	List<SelectItem> obtenerLstMesDetalleMesDomiTras() throws MitBusinessException;
	DetalleMesBean obtenerLstDetalleMesDomiTras(Integer mesBusqueda) throws MitBusinessException;
	//FOP 27/09/2018 | Se agrega Domi Registro C/S Liquidaciones
	List<DetalleMesBean> obtenerLtsDetalleMesDomiTrasAll(Integer mesBusqueda) throws MitBusinessException;
	
	List<MonitoreoSolicitudesDetalleBean> obtenerMonitoreoSolPorValor(String numCuenta, String nss, String curp, List<Short> origenSol) throws MitBusinessException;
	
	List<MonitoreoSolicitudesDetalleBean> obtenerMonitoreoSolPorValor(String numCuenta, String nss, String curp) throws MitBusinessException;
	
	List<GenericCatalogoBean> obtenerPeriodicidad() throws MitBusinessException;
	List<GenericCatalogoBean> obtenerTiposFrecSemanal() throws MitBusinessException;
	
	List<GenericCatalogoBean> obtenerTiposFrecQuincenalIni() throws MitBusinessException;
	
	List<GenericCatalogoBean> obtenerTiposFrecQuincenalFin(Integer frecIni) throws MitBusinessException;
	
	List<GenericCatalogoBean> obtenerTiposFrecMensual() throws MitBusinessException;
	
	int actualizaDomiTraspaso(MonitoreoSolicitudesDetalleBean bean) throws MitBusinessException;
	int actualizaEstatusSolicitudTraspaso(String cveSol,String numCta,Short idEstatus,String usuario) throws MitBusinessException;
	int actualizaEstatusSolicitud(MonitoreoSolicitudesDetalleBean sol,Short idEstatus,String usuario) throws MitBusinessException;
	
	CuentaIndividualBean validarCuentaInd(Long cuenta) throws MitBusinessException;
	
	boolean cancelarCuentasNoConviven(List<SolicitudVO> noConviven) throws MitBusinessException;
	
	Date actualizaSolicitud(String cveSolicitud, Short periodicidad, Short frecIni, Short frecFin, BigDecimal impTotal, String usuario) throws MitBusinessException;
	
	boolean actualizaDiversificacion(String cveSolicitud, BigDecimal cp, BigDecimal lp, BigDecimal avpilp, BigDecimal acr, BigDecimal totalDiver, String usuario) throws MitBusinessException;
	
	List<MonitoreoSolicitudesDetalleBean> historicocargoSol(MonitoreoSolicitudesDetalleBean selectedSol);
}
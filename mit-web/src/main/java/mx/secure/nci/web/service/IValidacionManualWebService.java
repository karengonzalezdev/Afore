package mx.secure.nci.web.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.web.beans.ArchivosBean;
import mx.secure.nci.web.beans.BancosBean;
import mx.secure.nci.web.beans.BancosFilterBean;
import mx.secure.nci.web.beans.CuentaIndividualBean;
import mx.secure.nci.web.beans.GenericCatalogoBean;

public interface IValidacionManualWebService {
	public List<GenericCatalogoBean> obtenerBancos() throws MitBusinessException;
	public List<GenericCatalogoBean> obtenerEmpresa() throws MitBusinessException;
	public List<GenericCatalogoBean> obtenerOrigen() throws MitBusinessException;
	public List<GenericCatalogoBean> obtenersecure() throws MitBusinessException;
	public CuentaIndividualBean validarCuentaInd(Long cuenta) throws MitBusinessException;
	public List<BancosBean> consultar(BancosFilterBean bancosFilter) throws MitBusinessException;
	public String guardarInfo(BancosBean bancosBean, CuentaIndividualBean cuenta, Short idEmpresa, List<ArchivosBean> listaArchivos, String usuario) throws MitBusinessException;
	public Boolean guardarInfoConciliacion(BancosBean bancosBean, CuentaIndividualBean cuenta, Short idEmpresa, String usuario) throws MitBusinessException;
	public void guardarArchivos(List<ArchivosBean> listaArchivos, BancosBean bancosBean) throws MitBusinessException;
}

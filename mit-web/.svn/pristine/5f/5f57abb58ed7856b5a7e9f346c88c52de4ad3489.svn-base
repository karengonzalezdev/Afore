package mx.profuturo.nci.web.views;

import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_BANAMEX_NCI;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_BANCOMER_NCI;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_CUENTA_CHEQUERA;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_CUENTA_CLABE;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_CUENTA_DEBITO;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_OTROS_BANCOS_DOMI_FILE;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ORIGEN_DOMICILIACION_TRASPASO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.util.HashIndexed3DMatrix;
import mx.profuturo.nci.business.util.UtilMethod;
import mx.profuturo.nci.business.vo.SolicitudVO;
import mx.profuturo.nci.web.beans.ArchivoDomiParamRegistry;
import mx.profuturo.nci.web.beans.ArchivoDomiViewParamBean;
import mx.profuturo.nci.web.beans.CifrasPorBancoBean;
import mx.profuturo.nci.web.beans.CifrasTotalesBean;
import mx.profuturo.nci.web.beans.GeneracionArchivoDomiBean;
import mx.profuturo.nci.web.beans.GenericCatalogoBean;
import mx.profuturo.nci.web.beans.RangoFechaParamBean;
import mx.profuturo.nci.web.beans.TipoCuentaArchivoDomiBean;
import mx.profuturo.nci.web.service.IArchivoDomiciliacionWebService;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "archivoDomiciliacionView")
@ViewScoped
public class ArchivoDomiciliacionView implements Serializable{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArchivoDomiciliacionView.class);
	
	@ManagedProperty(value="#{archivoDomiciliacionService}")
	IArchivoDomiciliacionWebService archivoDomiciliacionWebService;

	private static final long serialVersionUID = 1L;
	
	private ArchivoDomiViewParamBean paramBean;
	private HashIndexed3DMatrix<RangoFechaParamBean> rangosUltimoGenerado;
	
	private List<TipoCuentaArchivoDomiBean> listaFinal;
	private List<GeneracionArchivoDomiBean> listaResultado;
	private List<CifrasTotalesBean> cifrasTotales;
	private List<CifrasPorBancoBean> cifrasBanco;
	private List<SolicitudVO> cuentasNOConviven;
	private List<GenericCatalogoBean> tiposOrigen;
	private String fechaInicioMax;
	private String fechaFinMax;
	private String fechamin;

//	
	 @PostConstruct
	 public void init() {
		 if(paramBean==null){
			 paramBean = new ArchivoDomiViewParamBean();
		 }
	 }
	
	 public void test(){

		 ArchivoDomiParamRegistry[] valor = paramBean.getRegistros();
		 
		 List<String> banco = paramBean.getRegistros()[0].getIdBanco();
		 if(banco== null || banco.size()==0 || banco.get(0)==null){
			 
			 paramBean.getRegistros()[0].setTipoArchivo(new Short("0"));

			 List<String> idsTiposCuenta = new ArrayList<String>();
			 paramBean.getRegistros()[0].setIdsTiposCuenta(idsTiposCuenta);
			 
			 paramBean.getRegistros()[0].setArchivoUnico(false);
		 }
		 	 
		 LOGGER.info("TEST");
	 }

	 public void selectTipoArchivo(){
		 try{
			 if(paramBean.getIdOrigenDomiciliacion()!=null){
				 rangosUltimoGenerado=archivoDomiciliacionWebService.getRangosUltimoGenerado(paramBean.getIdOrigenDomiciliacion());
			 }
		 }catch (MitBusinessException e) {
			 LOGGER.error(e.getMessage(),e);
		 }
	 }
	 
	 public void changeOrigen(){
		 LOGGER.info("CMABIO DE ORIGEN");
		 ArchivoDomiParamRegistry[] regs = new ArchivoDomiParamRegistry[3];
		 regs[0] = new ArchivoDomiParamRegistry();
		 regs[1] = new ArchivoDomiParamRegistry();
		 regs[2] = new ArchivoDomiParamRegistry();
		 
		 paramBean.setRegistros(regs);
	 }
	 
	 public void testBancomer(){
		 ArchivoDomiParamRegistry[] valor = paramBean.getRegistros();
		 
		 List<String> banco = paramBean.getRegistros()[1].getIdBanco();
		 if(banco== null || banco.size()==0 || banco.get(0)==null){
			 
			 paramBean.getRegistros()[1].setTipoArchivo(new Short("0"));

			 List<String> idsTiposCuenta = new ArrayList<String>();
			 paramBean.getRegistros()[1].setIdsTiposCuenta(idsTiposCuenta);
			 
			 paramBean.getRegistros()[1].setArchivoUnico(false);
		 }
		 	 
		 LOGGER.info("TEST BANCOMER");
	 }

	 public void testOtro(){
		 ArchivoDomiParamRegistry[] valor = paramBean.getRegistros();
		 
		 List<String> banco = paramBean.getRegistros()[2].getIdBanco();
		 if(banco== null || banco.size()==0 || banco.get(0)==null){
			 
			 paramBean.getRegistros()[2].setTipoArchivo(new Short("0"));

			 List<String> idsTiposCuenta = new ArrayList<String>();
			 paramBean.getRegistros()[2].setIdsTiposCuenta(idsTiposCuenta);
			 
			 paramBean.getRegistros()[2].setArchivoUnico(false);
		}
		 LOGGER.info("TEST OTRO");
	 }
	 
	 public void limpiaForm(){
		 if(paramBean==null){
				paramBean= new ArchivoDomiViewParamBean();
			}
			 try {
				 FacesContext facesContext = FacesContext.getCurrentInstance();
				 paramBean.setUsuario(facesContext.getExternalContext().getRemoteUser());
				 paramBean.setFechaInicio(Calendar.getInstance().getTime());
//				rangosUltimoGenerado=archivoDomiciliacionWebService.getRangosUltimoGenerado();
				if(getTiposOrigen()==null || getTiposOrigen().size()>0){
					tiposOrigen = archivoDomiciliacionWebService.getTiposOrigen();
				}
			} catch (MitBusinessException e) {
				LOGGER.error("Error al cargar fechas de ultimo archivo generado",e);
			} 
	 }
	 
	public void resetForm(){
		LOGGER.info("");
		
		 ArchivoDomiParamRegistry[] regs = new ArchivoDomiParamRegistry[3];
		 regs[0] = new ArchivoDomiParamRegistry();
		 regs[1] = new ArchivoDomiParamRegistry();
		 regs[2] = new ArchivoDomiParamRegistry();
		 
		 paramBean.setRegistros(regs);
	}

	public void clickGenerarArchivo()  {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			paramBean.setUsuario(facesContext.getExternalContext().getRemoteUser());
			listaResultado = this.archivoDomiciliacionWebService.generarArchivoDomi(paramBean,cuentasNOConviven);
			rangosUltimoGenerado=archivoDomiciliacionWebService.getRangosUltimoGenerado(paramBean.getIdOrigenDomiciliacion());
		} catch (MitBusinessException e) {
			LOGGER.error(e.getMessage(),e);
		}
		LOGGER.debug("Lista archivos VIEW "+listaResultado.size());
	}

	
	private List<TipoCuentaArchivoDomiBean> getFinalFilters() {
		if(listaFinal == null || !listaFinal.isEmpty()){
			listaFinal = new ArrayList<TipoCuentaArchivoDomiBean>();
		}
		for(ArchivoDomiParamRegistry reg:paramBean.getRegistros()){
			
			for(String idTipoCuenta:reg.getIdsTiposCuenta()){
				TipoCuentaArchivoDomiBean bean = new TipoCuentaArchivoDomiBean();
				if(idTipoCuenta!=null){
					bean.setFechaInicio(paramBean.getFechaInicio());
					bean.setFechaFin(paramBean.getFechaFin());
					bean.setArchivoGenerar(reg.getTipoArchivo());
					bean.setUnArchivo(reg.getArchivoUnico());
					bean.setIdBanco(Short.valueOf(reg.getIdBanco().get(0)));
					bean.setTipoCuenta(new GenericCatalogoBean(Short.valueOf(idTipoCuenta)));
					listaFinal.add(bean);
				}
			}
		}
		
		return listaFinal;
	}

	public void clickGenerarCifras(){		
		LOGGER.debug("Entrando a generar cifras");
		listaFinal = getFinalFilters();
		try {
			if(!ID_ORIGEN_DOMICILIACION_TRASPASO.equals(paramBean.getIdOrigenDomiciliacion())){
				cuentasNOConviven = archivoDomiciliacionWebService.getNoConviven(paramBean);
				archivoDomiciliacionWebService.cancelarCuentasNoConviven(cuentasNOConviven);
			}
			
			cifrasTotales = archivoDomiciliacionWebService.generaCifrasTotales(paramBean);
			cifrasBanco = archivoDomiciliacionWebService.generarCifrasPorBanco(paramBean);
		} catch (MitBusinessException e) {
			LOGGER.error(e.getMessage(),e);
		}
		LOGGER.debug("OK");
	}


	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public String getFechamin() {
		Date fechaAct = new Date(System.currentTimeMillis());
		fechaAct=UtilMethod.getDateWithDayOffset(fechaAct,-5);
		this.fechamin = UtilMethod.dateFormatter(fechaAct,"dd/MM/yyyy");
		return fechamin;
	}

	public void setFechamin(String fechamin) {
		this.fechamin = fechamin;
	}

	public String getFechaInicioMax() {
		Date fechaAct = new Date(System.currentTimeMillis());
		fechaInicioMax = UtilMethod.dateFormatter(fechaAct,"dd/MM/yyyy");
		return fechaInicioMax;
	}

	public void setFechaInicioMax(String fechaInicioMax) {
		this.fechaInicioMax = fechaInicioMax;
	}

	public String getFechaFinMax() {
		Date fechaAct = new Date(System.currentTimeMillis());
		fechaFinMax= UtilMethod.dateFormatter(fechaAct,"dd/MM/yyyy");
		return fechaFinMax;
	}


	public void setFechaFinMax(String fechaFinMax) {
		this.fechaFinMax = fechaFinMax;
	}

	public List<TipoCuentaArchivoDomiBean> getListaFinal() {
		return listaFinal;
	}

	public void setListaFinal(List<TipoCuentaArchivoDomiBean> listaFinal) {
		this.listaFinal = listaFinal;
	}


	public List<GeneracionArchivoDomiBean> getListaResultado() {
		return listaResultado;
	}

	public void setListaResultado(List<GeneracionArchivoDomiBean> listaResultado) {
		this.listaResultado = listaResultado;
	}

	public List<CifrasTotalesBean> getCifrasTotales() {
		return cifrasTotales;
	}

	public void setCifrasTotales(List<CifrasTotalesBean> cifrasTotales) {
		this.cifrasTotales = cifrasTotales;
	}

	public List<CifrasPorBancoBean> getCifrasBanco() {
		return cifrasBanco;
	}

	public void setCifrasBanco(List<CifrasPorBancoBean> cifrasBanco) {
		this.cifrasBanco = cifrasBanco;
	}

	public ArchivoDomiViewParamBean getParamBean() {
		return paramBean;
	}
	
	public Short getIdCuentaDebito(){
		return ID_CUENTA_DEBITO;
	}
	public Short getIdCuentaClabe(){
		return ID_CUENTA_CLABE;
	}
	public Short getIdCuentaChequera(){
		return ID_CUENTA_CHEQUERA;
	}
	public Short getIdBanamex(){
		return ID_BANAMEX_NCI;
	}
	public Short getIdBancomer(){
		return ID_BANCOMER_NCI;
	}
	public Short getIdOtrosBancos(){
		return ID_OTROS_BANCOS_DOMI_FILE;
	}
	
	public HashIndexed3DMatrix<RangoFechaParamBean> getRangosUltimoGenerado() {
		return rangosUltimoGenerado;
	}
	public List<GenericCatalogoBean> getTiposOrigen() {
		return tiposOrigen;
	}
	
	public void setTiposOrigen(List<GenericCatalogoBean> tiposOrigen) {
		tiposOrigen = this.tiposOrigen;
	}
	
	public IArchivoDomiciliacionWebService getArchivoDomiciliacionWebService() {
		return archivoDomiciliacionWebService;
	}
	public void setArchivoDomiciliacionWebService(
			IArchivoDomiciliacionWebService archivoDomiciliacionWebService) {
		this.archivoDomiciliacionWebService = archivoDomiciliacionWebService;
	}
}

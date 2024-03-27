package mx.profuturo.nci.web.views.traspasos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.web.beans.traspasos.DetalleMesBean;
import mx.profuturo.nci.web.service.IArchivoDomiciliacionWebService;

@ManagedBean(name = "detalleMesView")
@ViewScoped
public class DetalleMesView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DetalleMesView.class);

	@ManagedProperty(value="#{archivoDomiciliacionService}")
	IArchivoDomiciliacionWebService archivoDomiciliacionWebService;
	
	private Integer mesBusqueda;
	
	private String mesBusquedaTxt;
	private boolean busquedaValida;
	private DetalleMesBean solicitudes;
	//FOP 27/09/2018 | Se agrega Domi Registro C/S Liquidaciones
	private DetalleMesBean solicitudesDSL;
	private DetalleMesBean solicitudesDCL;
	private List<DetalleMesBean> ltsSolicitudes;
	private boolean busquedaValidaDCL;
	private boolean busquedaValidaDSL;
	//****************************************************
	private List<SelectItem> mesesBusqueda;
	private boolean muestraBoton;
	
	public String init() {
		return "detalleMes";
	}	
	
	public void test(){
		LOGGER.info("TEST");
	}
	
	public void obtenerMonitoreo(){
		if(mesBusqueda==null || mesBusqueda.toString().isEmpty() || mesBusqueda==0){
			setBusquedaValida(false);
		}else{
			setBusquedaValida(true);
			setBusquedaValidaDCL(true);
			setBusquedaValidaDSL(true);
			try{
				ltsSolicitudes = archivoDomiciliacionWebService.obtenerLtsDetalleMesDomiTrasAll(mesBusqueda);
				for(int i=0; i<3; i++){
					if(i == 0) {
						solicitudes = ltsSolicitudes.get(i);
					}else if (i == 1) {
						solicitudesDSL = ltsSolicitudes.get(i);
					}else if (i == 2) {
						solicitudesDCL = ltsSolicitudes.get(i);
					}
				}
				//DetalleMesBean regs = archivoDomiciliacionWebService.obtenerLstDetalleMesDomiTras(mesBusqueda);
				//setSolicitudes(regs);
			} catch (MitBusinessException e) {
				LOGGER.error("Error al obtener el monitoreo de cargos domiciliacion traspasos",e);
			}	
			
		}
	}
	
	public boolean isBusquedaValida() {
		return busquedaValida;
	}
	
	public void setBusquedaValida(boolean busquedaValida) {
		this.busquedaValida = busquedaValida;
	}
	
	public DetalleMesBean getSolicitudes() {	
		return solicitudes;
	}
	
	public void setSolicitudes(DetalleMesBean solicitudes) {
		this.solicitudes = solicitudes;
	}
	
	public Integer getMesBusqueda() {
		return mesBusqueda;
	}
	
	public void setMesBusqueda(Integer mesBusqueda) {
		this.mesBusqueda = mesBusqueda;
	}

	public List<SelectItem> getMesesBusqueda() {
		if(mesesBusqueda==null || mesesBusqueda.isEmpty()){
			try{
				List<SelectItem> regs = archivoDomiciliacionWebService.obtenerLstMesDetalleMesDomiTras();
				setMesesBusqueda(regs);
			} catch (MitBusinessException e) {
				LOGGER.error("Error al obtener el listado de meses en detalle Mes", e);
			}
		}
		
		return mesesBusqueda;
	}

	public void setMesesBusqueda(List<SelectItem> mesesBusqueda) {
		this.mesesBusqueda = mesesBusqueda;
	}

	public String getMesBusquedaTxt() {
		for(SelectItem opcion : mesesBusqueda){
			if(opcion.getValue().toString().equals(mesBusqueda.toString())){
				mesBusquedaTxt = opcion.getLabel();
				break;
			}
		}	
		return mesBusquedaTxt;
	}

	public void setMesBusquedaTxt(String mesBusquedaTxt) {
		this.mesBusquedaTxt = mesBusquedaTxt;
	}
	
	public void seleccionMenu(final AjaxBehaviorEvent event){
		if(mesBusqueda==null || mesBusqueda.toString().isEmpty() || mesBusqueda==0){
			setMuestraBoton(false);
			setBusquedaValida(false);
		}else{
			setMuestraBoton(true);
		}
	}
	
	public boolean isMuestraBoton() {
		return muestraBoton;
	}
	
	public void setMuestraBoton(boolean muestraBoton) {
		this.muestraBoton = muestraBoton;
	}
	
	public IArchivoDomiciliacionWebService getArchivoDomiciliacionWebService() {
		return archivoDomiciliacionWebService;
	}
	
	public void setArchivoDomiciliacionWebService(IArchivoDomiciliacionWebService archivoDomiciliacionWebService) {
		this.archivoDomiciliacionWebService = archivoDomiciliacionWebService;
	}

	public boolean isBusquedaValidaDCL() {
		return busquedaValidaDCL;
	}

	public void setBusquedaValidaDCL(boolean busquedaValidaDCL) {
		this.busquedaValidaDCL = busquedaValidaDCL;
	}

	public boolean isBusquedaValidaDSL() {
		return busquedaValidaDSL;
	}

	public void setBusquedaValidaDSL(boolean busquedaValidaDSL) {
		this.busquedaValidaDSL = busquedaValidaDSL;
	}

	public DetalleMesBean getSolicitudesDSL() {
		return solicitudesDSL;
	}

	public void setSolicitudesDSL(DetalleMesBean solicitudesDSL) {
		this.solicitudesDSL = solicitudesDSL;
	}

	public DetalleMesBean getSolicitudesDCL() {
		return solicitudesDCL;
	}

	public void setSolicitudesDCL(DetalleMesBean solicitudesDCL) {
		this.solicitudesDCL = solicitudesDCL;
	}

	public List<DetalleMesBean> getLtsSolicitudes() {
		return ltsSolicitudes;
	}

	public void setLtsSolicitudes(List<DetalleMesBean> ltsSolicitudes) {
		this.ltsSolicitudes = ltsSolicitudes;
	}
	
	
}
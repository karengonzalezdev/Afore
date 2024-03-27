package mx.profuturo.nci.web.views.traspasos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.util.Constantes;
import mx.profuturo.nci.business.util.UtilMethod;
import mx.profuturo.nci.web.beans.traspasos.MonitoreoCargosDomiBean;
import mx.profuturo.nci.web.beans.traspasos.MonitoreoCargosDomiDetalleBean;
import mx.profuturo.nci.web.service.IArchivoDomiciliacionWebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "monitoreoCargosView")
@ViewScoped
public class MonitoreoCargosView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitoreoCargosView.class);
	
	@ManagedProperty(value="#{archivoDomiciliacionService}")
	IArchivoDomiciliacionWebService archivoDomiciliacionWebService;
	
	private String fechaConsulta;
	private List<MonitoreoCargosDomiBean> lstCargos;
	private BigDecimal[] impTotales;
	
	public void test(){
		 LOGGER.info("TEST");
	}
	 
	public void obtenerValoresTabla(){
		try{
			List<MonitoreoCargosDomiBean> regs = archivoDomiciliacionWebService.generaMonitoreoCargosDomiTraspaso();
			setLstCargos(regs);
		} catch (MitBusinessException e) {
			LOGGER.error("Error al obtener el monitoreo de cargos domiciliacion traspasos",e);
		}		
	}
	
	public List<MonitoreoCargosDomiBean> getLstCargos() {
		if(lstCargos==null || lstCargos.isEmpty()){
			obtenerValoresTabla();
		}
		return lstCargos;
	}
	
	public void setLstCargos(List<MonitoreoCargosDomiBean> lstCargos) {
		this.lstCargos = lstCargos;
	}
	
	public BigDecimal[] getImpTotales() {
		impTotales = new BigDecimal[9];
		for(int i=0;i<9;i++){
			impTotales[i] = new BigDecimal("0");
		}
		
		for (MonitoreoCargosDomiBean temp : getLstCargos()){
			impTotales[3] = impTotales[3].add(new BigDecimal(temp.getCargos()));
			for(MonitoreoCargosDomiDetalleBean tempDos : temp.getLstRecurrencias()){
				impTotales[0] = impTotales[0].add(new BigDecimal(tempDos.getNoSolicitudes())); 
				impTotales[1] = impTotales[1].add(new BigDecimal(tempDos.getTraspasoLiq())); 
				impTotales[2] = impTotales[2].add(new BigDecimal(tempDos.getTraspasoPend()));
				impTotales[4] = impTotales[4].add(tempDos.getMontoEsperado());
				impTotales[5] = impTotales[5].add(new BigDecimal(tempDos.getCargosAcep()));
				impTotales[6] = impTotales[6].add(new BigDecimal(tempDos.getCargosRech()));
				impTotales[7] = impTotales[7].add(tempDos.getMontoConfirmado());
				impTotales[8] = impTotales[8].add(tempDos.getMontoRech());
			}
		}
		return impTotales;
	}
	
	public void setImpTotales(BigDecimal[] impTotales) {
		this.impTotales = impTotales;
	}
	
	public String getFechaConsulta() {
		Date fechaAct = new Date(System.currentTimeMillis());
		return UtilMethod.dateFormatter(fechaAct,Constantes.DOMI_CARGOS_DATE_FORMAT);
	}
	
	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
	
	public IArchivoDomiciliacionWebService getArchivoDomiciliacionWebService() {
		return archivoDomiciliacionWebService;
	}
	
	public void setArchivoDomiciliacionWebService(IArchivoDomiciliacionWebService archivoDomiciliacionWebService) {
		this.archivoDomiciliacionWebService = archivoDomiciliacionWebService;
	}
}
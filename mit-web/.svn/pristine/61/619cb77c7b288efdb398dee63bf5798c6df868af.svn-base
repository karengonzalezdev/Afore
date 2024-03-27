package mx.profuturo.nci.web.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.util.Constantes;
import mx.profuturo.nci.business.vo.CatalogoConfiguracionVO;
import mx.profuturo.nci.business.wrapped.CatalogoConfiguracionFilter;
import mx.profuturo.nci.web.service.ICatalogoConfiguracionWebService;

@ManagedBean(name = "configAfiliadoIndependienteView")
@ViewScoped
public class ConfigAfiliadoIndependienteView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigAfiliadoIndependienteView.class);
	
	@ManagedProperty(value="#{catalogoConfiguracionWebService}")
	ICatalogoConfiguracionWebService catalogoConfiguracionWebService;
	
	private List<SelectItem> lstTipoUsuario;
	private Short tipoUsuario;
	private boolean mostrarValores;
	private Integer cantidad;
	private String errorMessage;
	
	@PostConstruct
	public void init(){
		setMostrarValores(false);
		setErrorMessage("");
	}
	
	public void actualizaTipo(){
		restaurandoMinimos();
	}
	
	public void actualizarMinimos(){
		CatalogoConfiguracionFilter filter=new CatalogoConfiguracionFilter();
		filter.setIdCatalogoConfiguracion(Short.valueOf(tipoUsuario));
		filter.setValor(String.valueOf(cantidad));
		FacesContext facesContext = FacesContext.getCurrentInstance();
		filter.setUsuarioActualizacion(facesContext.getExternalContext().getRemoteUser());
		int numUpdate;
		boolean result;
		try {
			RequestContext context = RequestContext.getCurrentInstance();
			
			
			if(cantidad<=0){
				setErrorMessage("El valor de monto mínimo debe ser mayor a 0");
				context.execute("PF('dlgErrorMessage').show()");
				return;
			}
			
			numUpdate = this.catalogoConfiguracionWebService.actualizaPorIdCatalogo(filter);
			if(numUpdate > 0){
				setErrorMessage("Se actualizó correctamente el parámetro");
				context.execute("PF('dlgErrorMessage').show()");
			}else{
				setErrorMessage("Ocurrió un error al actualizar el parámetro");
				context.execute("PF('dlgErrorMessage').show()");
			}
		} catch (MitBusinessException e) {
			LOGGER.error(e.getMessage(),e);
		}
	
	}
	
	public void restaurandoMinimos(){
		if(tipoUsuario!=null && tipoUsuario>0){
			setMostrarValores(true);
			if(tipoUsuario>0){
				try {
					CatalogoConfiguracionVO configuracionVO;
					configuracionVO = catalogoConfiguracionWebService.consultarPorIdConfig(tipoUsuario);
					if(configuracionVO != null && configuracionVO.getValor() != null){
						setCantidad(Integer.parseInt(configuracionVO.getValor().trim()));
					}else{
						setMostrarValores(false);
					}
				}catch (MitBusinessException e) {
					setMostrarValores(false);
					LOGGER.error(e.getMessage(),e);
				}
			}
		}else{
			setMostrarValores(false);
		}
	}
	
	public List<SelectItem> getLstTipoUsuario(){
		lstTipoUsuario = new ArrayList<SelectItem>();
		lstTipoUsuario.add(new SelectItem(Constantes.ID_CAT_CONFIG_TIPO_AFILIADO,"ASIGNADO"));
		lstTipoUsuario.add(new SelectItem(Constantes.ID_CAT_CONFIG_TIPO_INDEPENDIENTE,"INDEPENDIENTE"));
		return lstTipoUsuario;
	}
	
	public void setLstTipoUsuario(List<SelectItem> lstTipoUsuario){
		this.lstTipoUsuario = lstTipoUsuario;
	}
	
	public Short getTipoUsuario(){
		return tipoUsuario;
	}
	
	public void setTipoUsuario(Short tipoUsuario){
		this.tipoUsuario = tipoUsuario;
	}
	
	public boolean isMostrarValores(){
		return mostrarValores;
	}
	
	public void setMostrarValores(boolean mostrarValores){
		this.mostrarValores = mostrarValores;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public ICatalogoConfiguracionWebService getCatalogoConfiguracionWebService() {
		return catalogoConfiguracionWebService;
	}
	
	public void setCatalogoConfiguracionWebService(ICatalogoConfiguracionWebService catalogoConfiguracionWebService) {
		this.catalogoConfiguracionWebService = catalogoConfiguracionWebService;
	}	
}
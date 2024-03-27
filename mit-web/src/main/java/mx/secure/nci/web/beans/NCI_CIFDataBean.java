package mx.secure.nci.web.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.BeanUtils;

public class NCI_CIFDataBean implements Serializable{
	private static final long serialVersionUID = -6460608526412689161L;
	//Datos tabla de paso
	private	Long	idConciliacion;
	private	String	seleccion;
	private	Date	fechaCreacion;
	private	String	usuarioCreacion;
	private	Date	fechaActualizacion;
	private	String	usuarioActualizacion;
	private	Date	fechaEnvioCIF;
	private	String	usuarioEnvioCIF;
	private String origenAportacion;
	private String origenAportacionDesc;
	
	private Short idBanco;
	private Integer consecutivo;
	private Date fechaDepositoBancario;
	private String referencia;
	private String referenciaAmpliada;
	
	//DatosCIF secure
	private String idItem;
	private String idItemNew;
	private String unidadDeNegocio;
	private BigDecimal importe;
	private String idCliente;
	private Date fechaItem;
	private Date fechaContable;
	private Date fechaIntroduccion;
	private String metodoCobro;
	private String condicionCobro;
	private String tipo;
	private String motivo;
	private String codigoMoneda;
	private String banco;
	private String cuenta;
	private String tipoProceso;
	private String estatus;
	private Long secuencia;
	private String folioConciliacion;
	
	public NCI_CIFDataBean() {	}
	
	public NCI_CIFDataBean(String idItem) {this.idItem=idItem;}
	
	public Long getIdConciliacion() {
		return idConciliacion;
	}

	public void setIdConciliacion(Long idConciliacion) {
		this.idConciliacion = idConciliacion;
	}

	public String getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public Date getFechaEnvioCIF() {
		return fechaEnvioCIF;
	}

	public void setFechaEnvioCIF(Date fechaEnvioCIF) {
		this.fechaEnvioCIF = fechaEnvioCIF;
	}

	public String getUsuarioEnvioCIF() {
		return usuarioEnvioCIF;
	}

	public void setUsuarioEnvioCIF(String usuarioEnvioCIF) {
		this.usuarioEnvioCIF = usuarioEnvioCIF;
	}

	public String getIdItem() {
		return idItem;
	}
	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}
	
	public String getIdItemNew() {
		return idItemNew;
	}
	public void setIdItemNew(String idItemNew) {
		this.idItemNew = idItemNew;
	}

	public String getUnidadDeNegocio() {
		return unidadDeNegocio;
	}
	public void setUnidadDeNegocio(String unidadDeNegocio) {
		this.unidadDeNegocio = unidadDeNegocio;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public Date getFechaItem() {
		return fechaItem;
	}
	public void setFechaItem(Date fechaItem) {
		this.fechaItem = fechaItem;
	}
	public Date getFechaContable() {
		return fechaContable;
	}
	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}
	public Date getFechaIntroduccion() {
		return fechaIntroduccion;
	}
	public void setFechaIntroduccion(Date fechaIntroduccion) {
		this.fechaIntroduccion = fechaIntroduccion;
	}
	public String getMetodoCobro() {
		return metodoCobro;
	}
	public void setMetodoCobro(String metodoCobro) {
		this.metodoCobro = metodoCobro;
	}
	public String getCondicionCobro() {
		return condicionCobro;
	}
	public void setCondicionCobro(String condicionCobro) {
		this.condicionCobro = condicionCobro;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getCodigoMoneda() {
		return codigoMoneda;
	}
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getTipoProceso() {
		return tipoProceso;
	}
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Long getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(Long secuencia) {
		this.secuencia = secuencia;
	}

	public Short getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(Short idBanco) {
		this.idBanco = idBanco;
	}

	public Integer getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Date getFechaDepositoBancario() {
		return fechaDepositoBancario;
	}
	public void setFechaDepositoBancario(Date fechaDepositoBancario) {
		this.fechaDepositoBancario = fechaDepositoBancario;
	}

	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getReferenciaAmpliada() {
		return referenciaAmpliada;
	}
	public void setReferenciaAmpliada(String referenciaAmpliada) {
		this.referenciaAmpliada = referenciaAmpliada;
	}

	public String getOrigenAportacion() {
		return origenAportacion;
	}

	public void setOrigenAportacion(String origenAportacion) {
		this.origenAportacion = origenAportacion;
	}
	
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getFolioConciliacion() {
		return folioConciliacion;
	}

	public void setFolioConciliacion(String folioConciliacion) {
		this.folioConciliacion = folioConciliacion;
	}

	public String getOrigenAportacionDesc() {
		return origenAportacionDesc;
	}

	public void setOrigenAportacionDesc(String origenAportacionDesc) {
		this.origenAportacionDesc = origenAportacionDesc;
	}

	public NCI_CIFDataBean clone() {
		NCI_CIFDataBean cloned = new  NCI_CIFDataBean();
		BeanUtils.copyProperties(this, cloned);
		return cloned;
	}
	
	@Override
	public boolean equals(Object obj) {
		return 	obj != null 
				&& obj instanceof NCI_CIFDataBean 
				&& idItem != null 
				&& idItem.equals(((NCI_CIFDataBean)obj).getIdItem());
	}
	
}

package mx.profuturo.nci.business.file.generator.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;

@Record(minOccurs=0, maxOccurs=-1)
@Fields({
    @Field(at=0, name="recordType", rid=true,literal="02")
    
})

public class BancomerDomiRecordRecordBeanPar {
	private static final byte ZERO = (byte)'0';
	private static final String REF_LEYENDA_EMISOR="DOMICILIACION";
	
	private Integer idCargo;
	private Integer numParcialidad;
	
	@Field(at=2,length=7,padding=ZERO, align=Align.RIGHT)
	private Integer	numSecuencia;
	
	@Field(at=9,length=2,padding=ZERO, defaultValue="30", align=Align.RIGHT)
	private Integer codigoOperacion;
	
	@Field(at=11,length=2,padding=ZERO,defaultValue="01", align=Align.RIGHT)
	private Integer codigoDivisa;
	
	@Field(at=13,length=15,padding=ZERO,handlerName="currencyTypeHandler", align=Align.RIGHT)
	private BigDecimal importeOperacion;
	
	@Field(at=28,length=8,format="yyyyMMdd", align=Align.RIGHT)
	private Date fechaLiquidacion;
	
	@Field(at=36,length=24, align=Align.LEFT)
	private String usoFuturo1;
	
	@Field(at=60,length=2,padding=ZERO,defaultValue="51", align=Align.RIGHT)
	private Integer tipoOperacion;
	
	@Field(at=62,length=8,format="yyyyMMdd", align=Align.RIGHT)
	private Date fechaVencimiento;
	
	@Field(at=70,length=3,padding=ZERO,defaultValue="0", align=Align.RIGHT)
	private Integer bancoReceptorClienteUsuario;
	
	@Field(at=73,length=2,padding=ZERO,defaultValue="0", align=Align.RIGHT)
	private Integer tipoCuentaClienteUsuario;
	
	@Field(at=75,length=20,padding=ZERO,defaultValue="0", align=Align.RIGHT)
	private BigInteger numCuentaClienteUsuario;
	
	@Field(at=95,length=40,defaultValue=" ", align=Align.LEFT)
	private String nombreClienteUsuario;
	
	//---------CAMBIO QUE NO ESTA EN EL EON, NI EN EL LAYOUT PERO LO NECESITA ETL-----------
	@Field(at=135,length=10,padding=ZERO,defaultValue="0", align=Align.RIGHT)
	private String referenciaServicioEmisor;
	
	@Field(at=145,length=7,padding=ZERO,defaultValue="0", align=Align.RIGHT)
	private Integer numDomiciliacion;
	
	@Field(at=152,length=18,align=Align.LEFT)
	private String claveSolicitud;
	
	@Field(at=170,length=5,padding=ZERO,defaultValue="00000",align=Align.LEFT)
	private String usoFuturo3;
	//---------------------------------------------------------------------------------------
	
	@Field(at=175,length=40, align=Align.LEFT)
	private String nombreTitularServicio;
	
	@Field(at=215,length=15,padding=ZERO,handlerName="currencyTypeHandler", align=Align.RIGHT)
	private BigDecimal importeIVAOperacion;
	
	@Field(at=230,length=7,padding=ZERO,defaultValue="1", align=Align.RIGHT)
	private Integer referenciaNumericaEmisor;
	
	@Field(at=237,length=40,defaultValue=REF_LEYENDA_EMISOR,align=Align.LEFT)
	private String referenciaLeyendaEmisor;
	
	@Field(at=277,length=2,padding=ZERO,defaultValue="00", align=Align.RIGHT)
	private Integer motivoDevolucion;
	
	@Field(at=279,length=21, align=Align.LEFT)
	private String usoFuturo2;
	
	// ---------------------------------------------------------------------------------
	//Se comentan los SETTERS en los valores constantes para reducir el numero de metodos
	// ---------------------------------------------------------------------------------
	public Integer getNumSecuencia() {
		return numSecuencia;
	}

	public void setNumSecuencia(Integer numSecuencia) {
		this.numSecuencia = numSecuencia;
	}

	public Integer getCodigoOperacion() {
		return codigoOperacion;
	}

//	public void setCodigoOperacion(Integer codigoOperacion) {
//		this.codigoOperacion = codigoOperacion;
//	}

	public Integer getCodigoDivisa() {
		return codigoDivisa;
	}

//	public void setCodigoDivisa(Integer codigoDivisa) {
//		this.codigoDivisa = codigoDivisa;
//	}

	
	public String getUsoFuturo1() {
		return usoFuturo1;
	}

//	public void setUsoFuturo1(String usoFuturo1) {
//		this.usoFuturo1 = usoFuturo1;
//	}

	public Integer getTipoOperacion() {
		return tipoOperacion;
	}
	
	public Integer getBancoReceptorClienteUsuario() {
		return bancoReceptorClienteUsuario;
	}

	public void setBancoReceptorClienteUsuario(Integer bancoReceptorClienteUsuario) {
		this.bancoReceptorClienteUsuario = bancoReceptorClienteUsuario;
	}

	public Integer getTipoCuentaClienteUsuario() {
		return tipoCuentaClienteUsuario;
	}

	public void setTipoCuentaClienteUsuario(Integer tipoCuentaClienteUsuario) {
		this.tipoCuentaClienteUsuario = tipoCuentaClienteUsuario;
	}

	public BigInteger getNumCuentaClienteUsuario() {
		return numCuentaClienteUsuario;
	}

	public void setNumCuentaClienteUsuario(BigInteger numCuentaClienteUsuario) {
		this.numCuentaClienteUsuario = numCuentaClienteUsuario;
	}

	public String getNombreClienteUsuario() {
		return nombreClienteUsuario;
	}

	public void setNombreClienteUsuario(String nombreClienteUsuario) {
		this.nombreClienteUsuario = nombreClienteUsuario;
	}

	public String getReferenciaServicioEmisor() {
		return referenciaServicioEmisor;
	}

	public void setReferenciaServicioEmisor(String referenciaServicioEmisor) {
		this.referenciaServicioEmisor = referenciaServicioEmisor;
	}

	public String getNombreTitularServicio() {
		return nombreTitularServicio;
	}

	public void setNombreTitularServicio(String nombreTitularServicio) {
		this.nombreTitularServicio = nombreTitularServicio;
	}

	public BigDecimal getImporteIVAOperacion() {
		return importeIVAOperacion;
	}

	public void setImporteIVAOperacion(BigDecimal importeIVAOperacion) {
		this.importeIVAOperacion = importeIVAOperacion;
	}

	public Integer getReferenciaNumericaEmisor() {
		return referenciaNumericaEmisor;
	}

	public void setReferenciaNumericaEmisor(Integer referenciaNumericaEmisor) {
		this.referenciaNumericaEmisor = referenciaNumericaEmisor;
	}

	public Integer getMotivoDevolucion() {
		return motivoDevolucion;
	}

	public String getUsoFuturo2() {
		return usoFuturo2;
	}

//	public void setUsoFuturo2(String usoFuturo2) {
//		this.usoFuturo2 = usoFuturo2;
//	}

	public BigDecimal getImporteOperacion() {
		return importeOperacion;
	}

	public void setImporteOperacion(BigDecimal importeOperacion) {
		this.importeOperacion = importeOperacion;
	}

	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getReferenciaLeyendaEmisor() {
		return referenciaLeyendaEmisor;
	}

	public Integer getNumDomiciliacion() {
		return numDomiciliacion;
	}

	public void setNumDomiciliacion(Integer numDomiciliacion) {
		this.numDomiciliacion = numDomiciliacion;
	}

	public String getClaveSolicitud() {
		return claveSolicitud;
	}

	public void setClaveSolicitud(String claveSolicitud) {
		this.claveSolicitud = claveSolicitud;
	}

//	public void setReferenciaLeyendaEmisor(String referenciaLeyendaEmisor) {
//		this.referenciaLeyendaEmisor = referenciaLeyendaEmisor;
//	}

	public String getUsoFuturo3() {
		return usoFuturo3;
	}

	public Integer getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
	}

	public Integer getNumParcialidad() {
		return numParcialidad;
	}

	public void setNumParcialidad(Integer numParcialidad) {
		this.numParcialidad = numParcialidad;
	}
	
}

package mx.profuturo.nci.business.file.generator.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;


@Record(minOccurs=1, maxOccurs=-1)
@Fields({
    @Field(at=0, name="recordType", rid=true,literal="02")
    
})

public class BanamexDomiRecordRecordBeanPar {
	private static final byte ZERO = (byte)'0';
	private static final String REF_LEYENDA_EMISOR="DOMICILIACION";
	
	private Integer idCargo;
	private Integer numParcialidad;
	
	@Field(at=2,length=7,padding=ZERO, align=Align.RIGHT)
	private Integer	numSecuencia;
	
	@Field(at=9,length=2,padding=ZERO,defaultValue="30",  align=Align.RIGHT)
	private Integer codigoOperacion;
	
	@Field(at=11,length=2,padding=ZERO,defaultValue="01", align=Align.RIGHT)
	private Integer codigoDivisa;
	
	@Field(at=13,length=15,padding=ZERO,handlerName="currencyTypeHandler", align=Align.RIGHT)
	private BigDecimal importeOperacion;
	
	@Field(at=28,length=8,format="yyyyMMdd", align=Align.RIGHT)
	private Date fechaLiquidacion;
	
	@Field(at=36,length=6,padding=ZERO, align=Align.RIGHT)
	private Integer contadorReintento;
	
	@Field(at=42,length=18, align=Align.LEFT)
	private String usoFuturo1;
	
	@Field(at=60,length=2,padding=ZERO,defaultValue="51", align=Align.RIGHT)
	private Integer tipoOperacion;
	
	@Field(at=62,length=8,format="yyyyMMdd", align=Align.RIGHT)
	private Date fechaVencimiento;
	
	@Field(at=70,length=3,padding=ZERO,defaultValue="999", align=Align.RIGHT)
	private Integer bancoReceptorClienteUsuario;
	
	@Field(at=73,length=2,padding=ZERO,defaultValue="99", align=Align.RIGHT)
	private Integer tipoCuentaClienteUsuario;
	
	@Field(at=75,length=20,padding=ZERO, align=Align.RIGHT)
	private BigInteger numCuentaClienteUsuario;
	
	@Field(at=95,length=40, defaultValue=" ", align=Align.LEFT)
	private String nombreClienteUsuario;
	
	public Integer getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(Integer tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	//---------CAMBIO QUE NO ESTA EN EL EON, NI EN EL LAYOUT PERO LO NECESITA ETL-----------
	@Field(at=135,length=10,padding=ZERO,defaultValue="0", align=Align.RIGHT)
	private String referenciaServicioEmisor;
	
	@Field(at=145,length=7,padding=ZERO,defaultValue="0", align=Align.RIGHT)
	private Integer numDomiciliacion;
	
	@Field(at=152,length=18,align=Align.LEFT)
	private String claveSolicitud;
	
	@Field(at=170,length=5,padding=ZERO,defaultValue="00000",align=Align.LEFT)
	private String usoFuturo4;
	//---------------------------------------------------------------------------------------
	
	@Field(at=175,length=40, align=Align.LEFT)
	private String nombreTitularServicio;
	
	@Field(at=215,length=15,padding=ZERO,handlerName="currencyTypeHandler", defaultValue="0",align=Align.RIGHT)
	private BigDecimal importeIVAOperacion;
	
	@Field(at=230,length=7,padding=ZERO,defaultValue="0", align=Align.RIGHT)
	private Integer referenciaNumericaEmisor;
	
	@Field(at=237,length=40,defaultValue=REF_LEYENDA_EMISOR, align=Align.LEFT)
	private String referenciaLeyendaEmisor;
	
	@Field(at=277,length=2,padding=ZERO,defaultValue="00", align=Align.RIGHT)
	private Integer motivoDevolucion;
		
	@Field(at=279,length=21, align=Align.LEFT)
	private String usoFuturo2;

	@Field(at=300,length=2, align=Align.RIGHT)
	private Integer digitoVerificador;
	
	@Field(at=302,length=2, align=Align.RIGHT)
	private Integer numCorte;
	
	@Field(at=304,length=1, align=Align.RIGHT)	
	private Integer indicadorPago;
	
	@Field(at=305,length=6, align=Align.RIGHT)
	private Integer autorizacionBanco;
	
	@Field(at=311,length=8, align=Align.RIGHT)
	private Integer fechaAplicacion;
	
	@Field(at=319,length=2, align=Align.LEFT)
	private String secuenciaArchivoOriginal;
	
	@Field(at=321,length=20, align=Align.LEFT)
	private String referencia1;
	
	@Field(at=341,length=20, align=Align.LEFT)
	private String referencia2;
	
	@Field(at=361,length=20, align=Align.LEFT)
	private String referencia3;
	
	@Field(at=381,length=19, align=Align.LEFT)
	private String usoFuturo3;

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

	public Integer getContadorReintento() {
		return contadorReintento;
	}

//	public void setContadorReintento(Integer contadorReintento) {
//		this.contadorReintento = contadorReintento;
//	}

	public String getUsoFuturo1() {
		return usoFuturo1;
	}

//	public void setUsoFuturo1(String usoFuturo1) {
//		this.usoFuturo1 = usoFuturo1;
//	}


//	public void setTipoOperación(Integer tipoOperación) {
//		this.tipoOperación = tipoOperación;
//	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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

	public String getReferenciaLeyendaEmisor() {
		return referenciaLeyendaEmisor;
	}

//	public void setReferenciaLeyendaEmisor(Integer referenciaLeyendaEmisor) {
//		ReferenciaLeyendaEmisor = referenciaLeyendaEmisor;
//	}

	public Integer getMotivoDevolucion() {
		return motivoDevolucion;
	}

//	public void setMotivoDevolucion(Integer motivoDevolucion) {
//		this.motivoDevolucion = motivoDevolucion;
//	}

	public String getUsoFuturo2() {
		return usoFuturo2;
	}

//	public void setUsoFuturo2(String usoFuturo2) {
//		this.usoFuturo2 = usoFuturo2;
//	}

	public Integer getDigitoVerificador() {
		return digitoVerificador;
	}

//	public void setDigitoVerificador(Integer digitoVerificador) {
//		this.digitoVerificador = digitoVerificador;
//	}

	public Integer getNumCorte() {
		return numCorte;
	}

//	public void setNumCorte(Integer numCorte) {
//		this.numCorte = numCorte;
//	}

	public Integer getIndicadorPago() {
		return indicadorPago;
	}

//	public void setIndicadorPago(Integer indicadorPago) {
//		this.indicadorPago = indicadorPago;
//	}

	public Integer getAutorizacionBanco() {
		return autorizacionBanco;
	}

//	public void setAutorizacionBanco(Integer autorizacionBanco) {
//		this.autorizacionBanco = autorizacionBanco;
//	}

	public Integer getFechaAplicacion() {
		return fechaAplicacion;
	}

//	public void setFechaAplicacion(Integer fechaAplicacion) {
//		this.fechaAplicacion = fechaAplicacion;
//	}

	public String getSecuenciaArchivoOriginal() {
		return secuenciaArchivoOriginal;
	}

//	public void setSecuenciaArchivoOriginal(String secuenciaArchivoOriginal) {
//		this.secuenciaArchivoOriginal = secuenciaArchivoOriginal;
//	}

	public String getReferencia1() {
		return referencia1;
	}

//	public void setReferencia1(String referencia1) {
//		this.referencia1 = referencia1;
//	}

	public String getReferencia2() {
		return referencia2;
	}

//	public void setReferencia2(String referencia2) {
//		this.referencia2 = referencia2;
//	}

	public String getReferencia3() {
		return referencia3;
	}

//	public void setReferencia3(String referencia3) {
//		this.referencia3 = referencia3;
//	}

	public String getUsoFuturo3() {
		return usoFuturo3;
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

//	public void setUsoFuturo3(String usoFuturo3) {
//		this.usoFuturo3 = usoFuturo3;
//	}
	
	public String getUsoFuturo4() {
		return usoFuturo4;
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

package mx.profuturo.nci.business.file.generator.bean;

import java.util.Date;

import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;

@Record(minOccurs=1, maxOccurs=1)
@Fields({
    @Field(at=0, name="recordType", rid=true,literal="01")
})
public class BancomerDomiHeaderRecordBean {
	private static final byte ZERO = (byte)'0';
	private static final String RAZON_SOCIAL_PROFUTURO = "PROFUTURO AFORE SA DE CV";
	private static final String RFC_EMISOR="PGA 961220PB4";
	
	@Field(at=2,length=7,padding=ZERO,defaultValue="9999999", align=Align.RIGHT)
	private Integer	numSecuencia;
	
	@Field(at=9,length=2,padding=ZERO,defaultValue="30",align=Align.RIGHT)
	private Integer	codigoOperacion;
	
	@Field(at=11,length=3,padding=ZERO,defaultValue="012",align=Align.RIGHT) 
	private Integer	bancoParticipante;
	
	@Field(at=14,length=1,defaultValue="E",align=Align.LEFT)
	private String	sentido;
	
	@Field(at=15,length=1,padding=ZERO,defaultValue="2",align=Align.RIGHT)
	private Integer	servicio;
	
//	@Field(at=16,length=7,padding=ZERO,align=Align.RIGHT)
//	private Integer	numBloque;
	
	@Field(at=16,length=2,padding=ZERO,align=Align.RIGHT)
	private Integer diaMes;
	
	@Field(at=18,length=5,padding=ZERO,align=Align.RIGHT)
	private Integer consecutivo;
	
	@Field(at=23,length=8,format="yyyyMMdd",align=Align.RIGHT)
	private Date fechaPresentacion;
	
	@Field(at=31,length=2,padding=ZERO,defaultValue="01",align=Align.RIGHT)
	private Integer	codigoDivisas;
	
	@Field(at=33,length=2,padding=ZERO,defaultValue="00",align=Align.RIGHT)
	private Integer	causaRechazoBloque;
	
	@Field(at=35,length=25,align=Align.RIGHT)
	private String	usoFuturoBancomer1;
	
	@Field(at=60,length=40,defaultValue=RAZON_SOCIAL_PROFUTURO,align=Align.LEFT)
	private String	razonSocialEmisor;
	
	@Field(at=100,length=18,defaultValue=RFC_EMISOR,align=Align.LEFT)
	private String	rfcEmisor;
	
	@Field(at=118,length=8,padding=ZERO,align=Align.RIGHT)
	private Integer idArchivo;
	
	@Field(at=126,length=174,align=Align.LEFT)
	private String	usoFuturoBancomer2;
	
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
	public Integer getBancoParticipante() {
		return bancoParticipante;
	}
//	public void setBancoParticipante(Integer bancoParticipante) {
//		this.bancoParticipante = bancoParticipante;
//	}
	public String getSentido() {
		return sentido;
	}
//	public void setSentido(String sentido) {
//		this.sentido = sentido;
//	}
	public Integer getServicio() {
		return servicio;
	}
//	public void setServicio(Integer servicio) {
//		this.servicio = servicio;
//	}
	public Date getFechaPresentacion() {
		return fechaPresentacion;
	}
	public Integer getDiaMes() {
		return diaMes;
	}
	public void setDiaMes(Integer diaMes) {
		this.diaMes = diaMes;
	}
	public Integer getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}
	public void setFechaPresentacion(Date fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}
	public Integer getCodigoDivisas() {
		return codigoDivisas;
	}
//	public void setCodigoDivisas(Integer codigoDivisas) {
//		this.codigoDivisas = codigoDivisas;
//	}
	public Integer getCausaRechazoBloque() {
		return causaRechazoBloque;
	}
//	public void setCausaRechazoBloque(Integer causaRechazoBloque) {
//		this.causaRechazoBloque = causaRechazoBloque;
//	}
	public String getUsoFuturoBancomer1() {
		return usoFuturoBancomer1;
	}
//	public void setUsoFuturoBancomer1(String usoFuturoBancomer1) {
//		this.usoFuturoBancomer1 = usoFuturoBancomer1;
//	}
	public String getRazonSocialEmisor() {
		return razonSocialEmisor;
	}
//	public void setRazonSocialEmisor(String razonSocialEmisor) {
//		this.razonSocialEmisor = razonSocialEmisor;
//	}
	public String getRfcEmisor() {
		return rfcEmisor;
	}
//	public void setRfcEmisor(String rfcEmisor) {
//		this.rfcEmisor = rfcEmisor;
//	}
	public String getUsoFuturoBancomer2() {
		return usoFuturoBancomer2;
	}
//	public void setUsoFuturoBancomer2(String usoFuturoBancomer2) {
//		this.usoFuturoBancomer2 = usoFuturoBancomer2;
//	}
	public Integer getIdArchivo() {
		return idArchivo;
	}
	public void setIdArchivo(Integer idArchivo) {
		this.idArchivo = idArchivo;
	}
	
	
}

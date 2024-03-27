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
public class BanamexDomiHeaderRecordBean {
	private static final byte ZERO = (byte)'0';
	private static final String RAZON_SOCIAL_PROFUTURO = "PROFUTURO AFORE SA DE CV";
	private static final String RFC_EMISOR="PGA961220PB4";
	private static final String NUM_CLIENTE_PROFUTURO_BANAMEX = "000106886951";
	
	@Field(at=2,length=7,padding=ZERO,defaultValue="1", align=Align.RIGHT)
	private Integer	numSecuencia;
	
	@Field(at=9,length=2,padding=ZERO,defaultValue="30",align=Align.RIGHT)
	private Integer	codigoOperacion;
	
	@Field(at=11,length=3,padding=ZERO,defaultValue="002",align=Align.RIGHT) 
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
	private Date	fechaPresentacion;
	
	@Field(at=31,length=2,padding=ZERO,defaultValue="01",align=Align.RIGHT)
	private Integer	codigoDivisas;
	
	@Field(at=33,length=2,padding=ZERO,defaultValue="00",align=Align.RIGHT)
	private Integer	causaRechazoBloque;
	
	@Field(at=35,length=25,align=Align.RIGHT)
	private String	usoFuturo1;
	
	@Field(at=60,length=40,defaultValue=RAZON_SOCIAL_PROFUTURO,align=Align.LEFT)
	private String	razonSocialEmisor;
	
	@Field(at=100,length=18,defaultValue=RFC_EMISOR,align=Align.LEFT)
	private String	rfcEmisor;
	
	@Field(at=118,length=182,align=Align.LEFT)
	private String	usoFuturo2;
	
	@Field(at=300,length=12,padding=ZERO,defaultValue=NUM_CLIENTE_PROFUTURO_BANAMEX,align=Align.RIGHT)
	private String numCliente;
	
	@Field(at=312,length=2,align=Align.RIGHT)
	private Integer secuencialArchivo;

	@Field(at=314,length=86,align=Align.LEFT)
	private String	usoFuturo3;
	
	
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
	public String getUsoFuturo1() {
		return usoFuturo1;
	}
//	public void setUsoFuturo1(String usoFuturo1) {
//		this.usoFuturo1 = usoFuturo1;
//	}
	public String getUsoFuturo2() {
		return usoFuturo2;
	}
//	public void setUsoFuturo2(String usoFuturo2) {
//		this.usoFuturo2 = usoFuturo2;
//	}
	public String getNumCliente() {
		return numCliente;
	}
//	public void setNumCliente(String numCliente) {
//		this.numCliente = numCliente;
//	}
	public Integer getSecuencialArchivo() {
		return secuencialArchivo;
	}
	public void setSecuencialArchivo(Integer secuencialArchivo) {
		this.secuencialArchivo = secuencialArchivo;
	}
	public String getUsoFuturo3() {
		return usoFuturo3;
	}
//	public void setUsoFuturo3(String usoFuturo3) {
//		this.usoFuturo3 = usoFuturo3;
//	}
	
	
}

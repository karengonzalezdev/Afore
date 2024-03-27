package mx.profuturo.nci.business.file.generator.bean;

import java.math.BigDecimal;

import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;

@Record(minOccurs=1, maxOccurs=-1)
@Fields({
    @Field(at=0, name="recordType", rid=true,literal="09")
})
public class BancomerDomiFooterRecordBean {
	private static final byte ZERO = (byte)'0';
	
	@Field(at=2,length=7,padding=ZERO, align=Align.RIGHT)
	private Integer numeroSecuencia;
	
	@Field(at=9,length=2,padding=ZERO,defaultValue="30", align=Align.RIGHT)
	private Integer codigoOperacion;
	
//	@Field(at=11,length=7,padding=ZERO,defaultValue="9999999", align=Align.RIGHT)
//	private Integer numeroBloque;

	@Field(at=11,length=2,padding=ZERO,defaultValue="99", align=Align.RIGHT)
	private Integer diaMes;
	
	@Field(at=13,length=5,padding=ZERO,defaultValue="99999", align=Align.RIGHT)
	private Integer consecutivo;
	
	@Field(at=18,length=7,padding=ZERO,defaultValue="99999999", align=Align.RIGHT)
	private Integer numeroOperaciones;
	
	@Field(at=25,length=18,padding=ZERO,handlerName="currencyTypeHandler", align=Align.RIGHT)
	private BigDecimal importeTotalOperaciones;
	
	@Field(at=43,length=17,align=Align.RIGHT)
	private String usoFuturoBancomer1;
	
	@Field(at=60,length=240,align=Align.RIGHT)
	private String usoFuturoBancomer2;
	
	// ---------------------------------------------------------------------------------
	//Se comentan los SETTERS en los valores constantes para reducir el numero de metodos
	// ---------------------------------------------------------------------------------
	public Integer getNumeroSecuencia() {
		return numeroSecuencia;
	}
	public void setNumeroSecuencia(Integer numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}
	public Integer getCodigoOperacion() {
		return codigoOperacion;
	}
//	public void setCodigoOperacion(Integer codigoOperacion) {
//		this.codigoOperacion = codigoOperacion;
//	}

	public Integer getNumeroOperaciones() {
		return numeroOperaciones;
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
	public void setNumeroOperaciones(Integer numeroOperaciones) {
		this.numeroOperaciones = numeroOperaciones;
	}
	public BigDecimal getImporteTotalOperaciones() {
		return importeTotalOperaciones;
	}
	public void setImporteTotalOperaciones(BigDecimal importeTotalOperaciones) {
		this.importeTotalOperaciones = importeTotalOperaciones;
	}
	public String getUsoFuturoBancomer1() {
		return usoFuturoBancomer1;
	}
//	public void setUsoFuturoBancomer1(String usoFuturoBancomer1) {
//		this.usoFuturoBancomer1 = usoFuturoBancomer1;
//	}
	public String getUsoFuturoBancomer2() {
		return usoFuturoBancomer2;
	}
//	public void setUsoFuturoBancomer2(String usoFuturoBancomer2) {
//		this.usoFuturoBancomer2 = usoFuturoBancomer2;
//	}

	
	
}

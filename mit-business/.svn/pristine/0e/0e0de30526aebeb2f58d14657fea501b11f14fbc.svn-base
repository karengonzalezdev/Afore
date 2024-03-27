package mx.profuturo.nci.business.vo.cif;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DevolucionesCabeceraCifVO {
	
	private String businessUnit;
	private String origin;
	private String pfTranId; // idItemDev DevolucionesCIFVO
	private String desCr60;
	private Date scheduledPayDt;
	private String voucherId;
	private String vendorId;
	private String invoiceId;
	private String pfStatusCif;
	private String pymntIdRef;
	private Date dueDt;
	private String pfDirBen;
	private String pfNomBen;	//nombreBeneficiario DevolucionesCIFVO
	private String bankCd;		//idBancoDestino DevolucionesCIFVO
	private String ctaBeneficiario;	//idBancoDestino DevolucionesCIFVO
	private String checkDigit;		//digitoVerificador DevolucionesCIFVO
	private Date pymntDt;
	private String remitVendor;		//numCtaIndividual DevolucionesCIFVO
	private Double pymntAmt;		//importe DevolucionesCIFVO
	private String currencyCd;
	private String pfTipoCuenta;	//tipoCuenta DevolucionesCIFVO
	private String pfCogTipoPago;	//tipoPago DevolucionesCIFVO
	private Date accountingDt;		
	private Date enteredDt;
	private Date opStartTime;
	private String opPridEnteredBy;		// usuCreacion DevolucionesCIFVO
	private String opPridReviewedBy;	// usuReviso DevolucionesCIFVO
	private String  opPridApprovedBy;	// usuAutorizo DevolucionesCIFVO
	private String pfDummyField1;
	private String pfDummyField2;
	private String pfDummyField3;
	private String pfDummyField4;
	private String pfDummyField5;
	private String pfDummyField6;
	private String pfDummyField7;
	private String pfDummyField8;
	private String pfDummyField9;
	private String pfDummyField10;

	
	
	public DevolucionesCabeceraCifVO() {
	}

	public DevolucionesCabeceraCifVO(String idItemDev, String nombreBeneficiario, String idBancoDestino, String ctaBeneficiario, String digitoVerificador, String numCtaIndividual,
										Double importe, String tipoCuenta, String tipoPago, String usuCreacion, String usuReviso, String  usuAutorizo) {
	    
		Date date = this.formatStrictDate();
		this.pfTranId = idItemDev;
		this.pfNomBen = nombreBeneficiario;
		this.bankCd = idBancoDestino;
		this.ctaBeneficiario = ctaBeneficiario;
		this.checkDigit = digitoVerificador;
		this.remitVendor = numCtaIndividual;
		this.pymntAmt = importe;
		this.pfTipoCuenta = tipoCuenta;
		this.pfCogTipoPago = tipoPago;
		this.opPridEnteredBy = usuCreacion;
		this.opPridReviewedBy = usuReviso;
		this.opPridApprovedBy = usuAutorizo;
		this.businessUnit = "PAF01";
		this.origin = "DEV";
		this.desCr60 = " ";
		this.scheduledPayDt = date;
		this.voucherId = " ";
		this.vendorId = " ";
		this.invoiceId = " ";
		this.pfStatusCif = "PE";
		this.pymntIdRef = " ";
		this.dueDt = null;
		this.pfDirBen = " ";
		this.pymntDt = date;
		this.currencyCd = "MXN";
		this.accountingDt = null;
		this.enteredDt = date;
		this.opStartTime = date;
		this.pfDummyField1 = " ";
		this.pfDummyField2 = " ";
		this.pfDummyField3 = " ";
		this.pfDummyField4 = " ";
		this.pfDummyField5 = " ";
		this.pfDummyField6 = " ";
		this.pfDummyField7 = " ";
		this.pfDummyField8 = " ";
		this.pfDummyField9 = " ";
		this.pfDummyField10 = " ";
	}
	
	private Date formatStrictDate() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTime();
	}	

	public String getPfDummyField1() {
		return pfDummyField1;
	}

	public void setPfDummyField1(String pfDummyField1) {
		this.pfDummyField1 = pfDummyField1;
	}



	public String getPfDummyField2() {
		return pfDummyField2;
	}



	public void setPfDummyField2(String pfDummyField2) {
		this.pfDummyField2 = pfDummyField2;
	}



	public String getPfDummyField3() {
		return pfDummyField3;
	}



	public void setPfDummyField3(String pfDummyField3) {
		this.pfDummyField3 = pfDummyField3;
	}



	public String getPfDummyField4() {
		return pfDummyField4;
	}



	public void setPfDummyField4(String pfDummyField4) {
		this.pfDummyField4 = pfDummyField4;
	}



	public String getPfDummyField5() {
		return pfDummyField5;
	}



	public void setPfDummyField5(String pfDummyField5) {
		this.pfDummyField5 = pfDummyField5;
	}



	public String getPfDummyField6() {
		return pfDummyField6;
	}



	public void setPfDummyField6(String pfDummyField6) {
		this.pfDummyField6 = pfDummyField6;
	}



	public String getPfDummyField7() {
		return pfDummyField7;
	}



	public void setPfDummyField7(String pfDummyField7) {
		this.pfDummyField7 = pfDummyField7;
	}



	public String getPfDummyField8() {
		return pfDummyField8;
	}



	public void setPfDummyField8(String pfDummyField8) {
		this.pfDummyField8 = pfDummyField8;
	}



	public String getPfDummyField9() {
		return pfDummyField9;
	}



	public void setPfDummyField9(String pfDummyField9) {
		this.pfDummyField9 = pfDummyField9;
	}



	public String getPfDummyField10() {
		return pfDummyField10;
	}



	public void setPfDummyField10(String pfDummyField10) {
		this.pfDummyField10 = pfDummyField10;
	}



	public String getOpPridApprovedBy() {
		return opPridApprovedBy;
	}



	public void setOpPridApprovedBy(String opPridApprovedBy) {
		this.opPridApprovedBy = opPridApprovedBy;
	}



	public String getOpPridReviewedBy() {
		return opPridReviewedBy;
	}



	public void setOpPridReviewedBy(String opPridReviewedBy) {
		this.opPridReviewedBy = opPridReviewedBy;
	}



	public String getOpPridEnteredBy() {
		return opPridEnteredBy;
	}


	public void setOpPridEnteredBy(String opPridEnteredBy) {
		this.opPridEnteredBy = opPridEnteredBy;
	}


	public Date getEnteredDt() {
		return enteredDt;
	}



	public void setEnteredDt(Date enteredDt) {
		this.enteredDt = enteredDt;
	}



	public Date getOpStartTime() {
		return opStartTime;
	}



	public void setOpStartTime(Date opStartTime) {
		this.opStartTime = opStartTime;
	}



	public Date getAccountingDt() {
		return accountingDt;
	}



	public void setAccountingDt(Date accountingDt) {
		this.accountingDt = accountingDt;
	}



	public String getPfTipoCuenta() {
		return pfTipoCuenta;
	}



	public void setPfTipoCuenta(String pfTipoCuenta) {
		this.pfTipoCuenta = pfTipoCuenta;
	}



	public String getPfCogTipoPago() {
		return pfCogTipoPago;
	}



	public void setPfCogTipoPago(String pfCogTipoPago) {
		this.pfCogTipoPago = pfCogTipoPago;
	}



	public String getCurrencyCd() {
		return currencyCd;
	}



	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}



	public Double getPymntAmt() {
		return pymntAmt;
	}



	public void setPymntAmt(Double pymntAmt) {
		this.pymntAmt = pymntAmt;
	}



	public String getRemitVendor() {
		return remitVendor;
	}



	public void setRemitVendor(String remitVendor) {
		this.remitVendor = remitVendor;
	}



	public Date getPymntDt() {
		return pymntDt;
	}



	public void setPymntDt(Date pymntDt) {
		this.pymntDt = pymntDt;
	}



	public String getCheckDigit() {
		return checkDigit;
	}



	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}



	public String getCtaBeneficiario() {
		return ctaBeneficiario;
	}



	public void setCtaBeneficiario(String ctaBeneficiario) {
		this.ctaBeneficiario = ctaBeneficiario;
	}



	public String getBankCd() {
		return bankCd;
	}



	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}



	public String getPfNomBen() {
		return pfNomBen;
	}



	public void setPfNomBen(String pfNomBen) {
		this.pfNomBen = pfNomBen;
	}



	public String getPfDirBen() {
		return pfDirBen;
	}



	public void setPfDirBen(String pfDirBen) {
		this.pfDirBen = pfDirBen;
	}



	public Date getDueDt() {
		return dueDt;
	}



	public void setDueDt(Date dueDt) {
		this.dueDt = dueDt;
	}



	public String getPymntIdRef() {
		return pymntIdRef;
	}



	public void setPymntIdRef(String pymntIdRef) {
		this.pymntIdRef = pymntIdRef;
	}



	public String getPfStatusCif() {
		return pfStatusCif;
	}



	public void setPfStatusCif(String pfStatusCif) {
		this.pfStatusCif = pfStatusCif;
	}



	public String getInvoiceId() {
		return invoiceId;
	}



	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}



	public String getVendorId() {
		return vendorId;
	}


	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}


	public String getVoucherId() {
		return voucherId;
	}


	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}


	public Date getScheduledPayDt() {
		return scheduledPayDt;
	}


	public void setScheduledPayDt(Date scheduledPayDt) {
		this.scheduledPayDt = scheduledPayDt;
	}


	public String getDesCr60() {
		return desCr60;
	}


	public void setDesCr60(String desCr60) {
		this.desCr60 = desCr60;
	}


	public String getPfTranId() {
		return pfTranId;
	}


	public void setPfTranId(String pfTranId) {
		this.pfTranId = pfTranId;
	}


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

}

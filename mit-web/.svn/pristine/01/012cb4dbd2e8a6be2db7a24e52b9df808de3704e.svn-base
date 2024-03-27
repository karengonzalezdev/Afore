package mx.profuturo.nci.web.beans;

import java.util.List;

public class CifrasPorBancoBean {
	private GenericCatalogoBean tipocuenta;
	private List<CifrasBancoBean> listaBancos;
	

	public List<CifrasBancoBean> getListaBancos() {
		return listaBancos;
	}
	public void setListaBancos(List<CifrasBancoBean> listaBancos) {
		this.listaBancos = listaBancos;
	}
	public GenericCatalogoBean getTipocuenta() {
		return tipocuenta;
	}
	public void setTipocuenta(GenericCatalogoBean tipocuenta) {
		this.tipocuenta = tipocuenta;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj!=null 
				&& obj instanceof CifrasPorBancoBean 
				&& tipocuenta!=null 
				&& tipocuenta.getId()!=null
				&& tipocuenta.equals(((CifrasPorBancoBean)obj).getTipocuenta());
	}
	
}

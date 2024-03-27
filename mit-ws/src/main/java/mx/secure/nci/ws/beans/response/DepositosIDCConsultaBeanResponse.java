package mx.secure.nci.ws.beans.response;


import java.util.List;
import mx.secure.nci.ws.beans.DepositosIDCBean;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;



public class DepositosIDCConsultaBeanResponse {
	
	private  List<DepositosIDCBean> listaDepositosReintegro;
	
	public DepositosIDCConsultaBeanResponse() {
		
	}
	 
	public DepositosIDCConsultaBeanResponse (List<DepositosIDCBean> listaDepositosReintegro) {
		super();
		this.listaDepositosReintegro = listaDepositosReintegro;
	}
	
	

	@XmlElementWrapper(name = "depositos")
	@XmlElement(name = "deposito")
	public List<DepositosIDCBean> getListaDepositosReintegro() {
		return listaDepositosReintegro;
	}

	public void setListaDepositosReintegro(List<DepositosIDCBean> listaDepositosReintegro) {
		this.listaDepositosReintegro = listaDepositosReintegro;
	}
	
	

}

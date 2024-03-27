package mx.secure.nci.business.wrapped;

import java.util.List;

import mx.secure.nci.business.vo.OrdenesVO;

public class OrdenesFilter  extends OrdenesVO
{

	private List<Short> estatusOrdenes;
	
	
	

	public List<Short> getEstatusOrdenes() {
		return estatusOrdenes;
	}

	public void setEstatusOrdenes(List<Short> estatusOrdenes) {
		this.estatusOrdenes = estatusOrdenes;
	}
	
	
	
	
	
}

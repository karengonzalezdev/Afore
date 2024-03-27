package idcWS;

import java.util.List;

import idcBusiness.ValidacionesVO;

public interface IDCpersistence {

	List<ValidacionesVO> getListaIDC(ValidacionesVO param);
	
}

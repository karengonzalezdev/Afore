package idcWS;

import java.util.List;

import idcBusiness.ValidacionesVO;

public interface IDCService {

	public List<ValidacionesVO> obtenerListaIDC(ValidacionesVO param) throws Exception;
	
}

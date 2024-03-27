package idcWS;

import java.util.ArrayList;
import java.util.List;

import idcBusiness.ValidacionesVO;

public class IDCServiceImpl implements IDCService{
	
	@Autowired
	IDCpersistence idcPersistence;

	@Override
	public List<ValidacionesVO> obtenerListaIDC(ValidacionesVO param) throws Exception {
		
		return idcPersistence.getListaIDC(param);
		
	}	
	
}

package mx.secure.nci.business.service;

import mx.secure.nci.business.vo.DictamenRechazadaRespuestaVO;
import mx.secure.nci.business.vo.ResultadoDictamenRechazadoVO;

public interface IClientesService {
	
	DictamenRechazadaRespuestaVO aplicaDictamenRechazado( ResultadoDictamenRechazadoVO request );
	

}

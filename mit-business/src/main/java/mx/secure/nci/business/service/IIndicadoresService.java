package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.ws.webservice.indicadores.IIndicadoresSoapWSConsultaBasicaResponse;

public interface IIndicadoresService {
	
	public IIndicadoresSoapWSConsultaBasicaResponse consultar(List<Integer> listaIndicadores, List<Long> numerosCuenta) throws MitBusinessException;
}

package mx.profuturo.nci.business.service;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.util.IdentificarClienteEnum;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.ConsultarClienteBasicoResp;

public interface IIdentificarClienteService {
	/**
	 * Metodo Que Consume El Servicio Web De Indentificación De Cliente Basico.
	 * 
	 * @param identificador
	 * @param valorIdentificador
	 * @return
	 * @throws MitBusinessException
	 */
	public ConsultarClienteBasicoResp consultarClienteBasico(IdentificarClienteEnum identificador,
			String valorIdentificador) throws MitBusinessException;

	/**
	 * Metodo Que Consume El Servicio Web De Indentificación De Cliente Basico.
	 * 
	 * @param identificador
	 * @param valorIdentificador
	 * @return
	 * @throws MitBusinessException
	 */
	public ConsultarClienteBasicoResp consultarClienteBasicoFiltro(IdentificarClienteEnum identificador,
			String valorIdentificador) throws MitBusinessException;

}

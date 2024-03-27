/**
 * 
 */
package mx.profuturo.nci.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.ICatalogosService;
import mx.profuturo.nci.business.service.IConciliacionService;
import mx.profuturo.nci.business.service.IIdentificarClienteService;
import mx.profuturo.nci.business.util.Constantes;
import mx.profuturo.nci.business.util.ConstantesCatalogos;
import mx.profuturo.nci.business.util.IdentificarClienteEnum;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ConciliacionVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.business.wrapped.ConciliacionFilter;
import mx.profuturo.nci.web.beans.ClienteOrdenSpeiBean;
import mx.profuturo.nci.web.beans.FondoBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.ConsultarClienteBasicoResp;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.InformacionCliente;

/**
 * @author ribarrgo
 *
 */
@Controller
public class IdentificarClienteController implements Serializable{
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(IdentificarClienteController.class);

	@Autowired
	IIdentificarClienteService identificarClienteService;

	@Autowired
	ICatalogosService catalogosService;
	
	@Autowired
	IConciliacionService conciliacionService;
	
	/**
	 * 
	 */
	public IdentificarClienteController() {
	}

	/**
	 * 
	 * @param valor
	 * @param filtro
	 * @return
	 */
	public List<ClienteOrdenSpeiBean> identificarCliente(String valor,
			String filtro) {
		List<ClienteOrdenSpeiBean> lstCliente = null;
		LOGGER.info("metodo: identificarCliente: " + " Valor: " + valor
				+ " filtro: " + filtro);
		if (valor.equalsIgnoreCase(Constantes.CUENTA)) {
			try {
				ConsultarClienteBasicoResp clienteBasico = identificarClienteService
						.consultarClienteBasicoFiltro(
								IdentificarClienteEnum.IDENTIFICADOR_NUM_CUENTA,
								filtro);
				lstCliente = clienteBasicoToClienteSpei(clienteBasico);
			} catch (MitBusinessException e) {
				LOGGER.error("ERROR UBICACION",e);
			}
		} else if (valor.equalsIgnoreCase(Constantes.LBL_CURP)) {
			try {
				ConsultarClienteBasicoResp clienteBasico = identificarClienteService
						.consultarClienteBasicoFiltro(
								IdentificarClienteEnum.IDENTIFICADOR_CURP,
								filtro);
				lstCliente = clienteBasicoToClienteSpei(clienteBasico);
			} catch (MitBusinessException e) {
				LOGGER.error("ERROR UBICACION",e);
			}
		} else if (valor.equalsIgnoreCase(Constantes.LBL_NSS)) {
			try {
				ConsultarClienteBasicoResp clienteBasico = identificarClienteService
						.consultarClienteBasicoFiltro(
								IdentificarClienteEnum.IDENTIFICADOR_NSS,
								filtro);
				lstCliente = clienteBasicoToClienteSpei(clienteBasico);
			} catch (MitBusinessException e) {
				LOGGER.error("ERROR UBICACION",e);
			}
		}
		return lstCliente;
	}

	private List<ClienteOrdenSpeiBean> clienteBasicoToClienteSpei(
			ConsultarClienteBasicoResp consultarClienteBasicoResp) {
		List<ClienteOrdenSpeiBean> lstCliente = new ArrayList<ClienteOrdenSpeiBean>();
		ClienteOrdenSpeiBean cliente;
		List<InformacionCliente> lstClientes = consultarClienteBasicoResp
				.getClientes().getInformacionCliente();
		for (InformacionCliente informacionCliente : lstClientes) {
			cliente = new ClienteOrdenSpeiBean();
			cliente.setNumeroCuenta(informacionCliente.getInformacionBasica()
					.getCliente().getNumeroCuenta());
			cliente.setRfc(informacionCliente.getInformacionBasica()
					.getCliente().getRfc());
			cliente.setNss(informacionCliente.getInformacionBasica()
					.getCliente().getNss());
			cliente.setCurp(informacionCliente.getInformacionBasica()
					.getCliente().getCurp());
			cliente.setEstadoCuenta(informacionCliente.getInformacionBasica()
					.getCliente().getEstatus());// Duda
			cliente.setClabe(informacionCliente.getInformacionBasica()
					.getCliente().getClabeUnica());
			cliente.setNombre(informacionCliente.getInformacionBasica()
					.getPersona().getNombre()
					+ " "
					+ informacionCliente.getInformacionBasica().getPersona()
							.getApellidoPaterno()
					+ " "
					+ informacionCliente.getInformacionBasica().getPersona()
							.getApellidoMaterno());
			
			if(informacionCliente.getInformacionDemografica() != null && informacionCliente.getInformacionDemografica().getCorreos() != null && informacionCliente.getInformacionDemografica().getCorreos().getCorreo() != null)
				cliente.setCorreoElectronico(informacionCliente.getInformacionDemografica().getCorreos().getCorreo().get(0).getEmail()); //Modificar
			if(informacionCliente.getInformacionDemografica() != null && informacionCliente.getInformacionDemografica().getDomicilios() != null && informacionCliente.getInformacionDemografica().getDomicilios().getDomicilio() != null)
				cliente.setDireccion(informacionCliente.getInformacionDemografica().getDomicilios().getDomicilio().get(0).getCalle().concat(", ").concat(informacionCliente.getInformacionDemografica().getDomicilios().getDomicilio().get(0).getColonia()).concat(", ").concat(informacionCliente.getInformacionDemografica().getDomicilios().getDomicilio().get(0).getCiudad())); //Modificar
//			if(informacionCliente.getInformacionFinanciera() != null && informacionCliente.getInformacionFinanciera().getCuentaBancaria() != null && informacionCliente.getInformacionFinanciera().getCuentaBancaria().getNumeroCuentaCLABE() != null)
//				cliente.setClabe(informacionCliente.getInformacionFinanciera().getCuentaBancaria().getNumeroCuentaCLABE());
			lstCliente.add(cliente);
		}
		return lstCliente;
	}

	/**
	 * Metodo para obtener el catalogo de Fondos de Ahorro Autor: EJLF
	 * 
	 * @return
	 * @throws MitBusinessException
	 */
	public List<FondoBean> obtenerCatalogo() throws MitBusinessException {
		
		List<FondoBean> lstFondoAhorro = new ArrayList<FondoBean>();
		List<CatalogoVO> lstCatalogo = this.catalogosService.consultarLista(new ParamCatalogoVO(ConstantesCatalogos.ID_CATALOGO_FONDOS));
		for(CatalogoVO cat : lstCatalogo){
			FondoBean fondo = new FondoBean();
			fondo.setId(cat.getIdCatCatalogo());
			fondo.setDescripcion(cat.getValor());
			lstFondoAhorro.add(fondo);
		}

		return lstFondoAhorro;
	}
	
	/**
	 * Metodo para consultar la tabla de conciliaciones
	 * 
	 * @param conciliacionFilter
	 * @return
	 * @throws MitBusinessException
	 */
	public List<ConciliacionVO> consultarConciliaciones(ConciliacionFilter conciliacionFilter) throws MitBusinessException{
		return this.conciliacionService.consultar(conciliacionFilter);
	}

}
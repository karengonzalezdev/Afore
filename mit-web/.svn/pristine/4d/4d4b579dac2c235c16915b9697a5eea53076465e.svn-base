package mx.profuturo.nci.web.views;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.profuturo.nci.business.service.impl.GeneracionArchivoDomiServiceImpl;
import mx.profuturo.nci.web.beans.ClienteOrdenSpeiBean;
import mx.profuturo.nci.web.beans.DiversificacionOrdenSpeiBean;
import mx.profuturo.nci.web.beans.FondoBean;
import mx.profuturo.nci.web.beans.OrdenSpeiBean;
import mx.profuturo.nci.web.controller.OrdenSpeiController;

@ManagedBean(name = "ordenSpeiView")
@ViewScoped
@Component
public class OrdenSpeiView implements Serializable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GeneracionArchivoDomiServiceImpl.class);
	
	@Autowired
	OrdenSpeiController ordenSpeiController;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Varable que permite realizar el manejo de informaci&oacute;n de la
	 * pantalla
	 **/
	private ClienteOrdenSpeiBean clienteSpei;
	private OrdenSpeiBean orden = new OrdenSpeiBean();
	private DiversificacionOrdenSpeiBean diversificacion = new DiversificacionOrdenSpeiBean();
	private Double importe;
	private ArrayList<FondoBean> diversificacionLst;
	public String valor = "monto";
	private ClienteOrdenSpeiBean clienteSelected;
	// private OrdenSpeiServiceImpl ordenesCtrl = new OrdenSpeiServiceImpl();

	@PostConstruct
	public void init() {
		clienteSpei = new ClienteOrdenSpeiBean();
		clienteSpei.setNombre("Rabelt Ibarra Godinez");
		clienteSpei.setCurp("IAGR8303141MA");
		clienteSpei.setRfc("IAGR8303141MA");
		clienteSpei.setEstadoCuenta("6969");
		clienteSpei.setDireccion("Cerrada de las palomas");
		clienteSpei.setCorreoElectronico("rabelt@gmail.com");
		clienteSpei.setNumeroCuenta("9696");
		clienteSpei.setNss("IAGRDJJ458789669");
		clienteSpei.setClabe("12345678945614554");
		orden = new OrdenSpeiBean();
		diversificacion = new DiversificacionOrdenSpeiBean();
		FondoBean fondo1 = new FondoBean();
		fondo1.setId(Short.valueOf("1"));
		fondo1.setDescripcion("DESC");
		fondo1.setSelecion(true);
		FondoBean fondo2 = new FondoBean();
		fondo2.setId(Short.valueOf("2"));
		fondo2.setDescripcion("DESC");
		fondo2.setSelecion(false);
		diversificacionLst = new ArrayList<FondoBean>();
		diversificacionLst.add(fondo1);
		diversificacionLst.add(fondo2);
	}

	/**
	 * M&eacute;todo que permite realizar la operaci&oacute;n para una orden
	 * SPEI.
	 */
	public void generarOrdenSpei(ActionEvent actionEvent) {
		LOGGER.info("Valor:" + valor);
		orden.setImporte(importe);
		clienteSpei.setOrdenSpei(orden);
		clienteSpei.getOrdenSpei().setDiversificacionSpei(diversificacionLst);
		ordenSpeiController.guardarOrdenSpei(clienteSpei, valor != null
				&& valor.isEmpty() && valor.equalsIgnoreCase("Monto"));
	}

	

}

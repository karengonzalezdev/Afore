package mx.profuturo.nci.business.service.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IIdentificarClienteService;
import mx.profuturo.nci.business.service.impl.WSPortTypeFactory;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.ClientePortType;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.ClienteService;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.ConsultarClienteBasicoReq;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.ConsultarClienteBasicoResp;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.Identificadores;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/integration-core-config.xml",
		"classpath:/spring/app-nci-business-beans-test.xml", "classpath:/spring/app-nci-persistence-db-test.xml",
		"classpath:/spring/app-nci-persistence-mybatis-test.xml" })
public class ClienteServiceIT2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteServiceIT2.class);

	private static final String LOCALPART_CURP = "curp";
	private static final String LOCALPART_NSS = "nss";
	private static final String LOCALPART_NUM_CUENTA = "numeroCuenta";
	 
	@Autowired
	WSPortTypeFactory wsPortTypeFactory;
	
	@Autowired
	IIdentificarClienteService identificarClienteService;
	
	private static ClientePortType getPortType(){
		ClientePortType port = null;
		ClienteService service = new  ClienteService();
		port = service.getClientePort();
		BindingProvider bp = (BindingProvider)port;
		Map<String, Object> requestContext = bp.getRequestContext();
		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://172.16.60.69:7800/iib/nci/IdentificacionCliente/ClienteService/v1");
		return port;
	}
	
	@Test
	public void consultarIT() {

		try {
			
			String numCta ="612000";
			ConsultarClienteBasicoReq 	requesWs=null;
			Identificadores             identificadores=null;
			List<JAXBElement<String>>   listIdentificadores=null;
			
			requesWs=new ConsultarClienteBasicoReq();
			identificadores=new Identificadores();
			
			numCta = Long.valueOf(numCta).toString();
			listIdentificadores=new ArrayList<JAXBElement<String>>();
			
			listIdentificadores.add(new JAXBElement<String>(new QName("numeroCuenta"), String.class, numCta));
			identificadores.setNssOrRfcOrCurp(listIdentificadores);
			requesWs.setIdentificadores(identificadores);

			ConsultarClienteBasicoResp resp = getPortType().consultarClienteBasico(requesWs);
			LOGGER.info(resp.getClientes().toString());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
	}
}

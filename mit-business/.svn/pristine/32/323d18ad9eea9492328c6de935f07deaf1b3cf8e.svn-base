package mx.profuturo.nci.business.service.impl;


import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jeveris.core.exception.impl.BusinessException;

import mx.com.profuturo_gnp.comun.catalogo.CatalogoComun;
import mx.com.profuturo_gnp.comun.catalogo.CatalogoComunService;
import mx.com.profuturo_gnp.ws.sso.controlacceso.ControlAccesoSSOPortType;
import mx.com.profuturo_gnp.ws.sso.controlacceso.ControlAccesoSSOService;
import mx.profuturo.ldap.operacionesldap.OperacionesLdapService;
import mx.profuturo.ldap.operacionesldap.OperacionesLdapServices;
import mx.profuturo.nci.business.util.NameSpaceBindingProvider;
import mx.profuturo.nci.business.util.WebSphereNamespace;
import mx.profuturo.nci.ws.webservice.catalogo.impl.ICatalogoSoapWS;
import mx.profuturo.nci.ws.webservice.catalogo.impl.ICatalogoSoapWSService;
import mx.profuturo.nci.ws.webservice.indicadores.impl.IIndicadoresSoapWS;
import mx.profuturo.nci.ws.webservice.indicadores.impl.IIndicadoresSoapWSService;
import profuturo.mx.iib.apovol.conciliacion.conciliacionservice.v1.ConciliacionPortType;
import profuturo.mx.iib.apovol.conciliacion.conciliacionservice.v1.ConciliacionService;
import profuturo.mx.iib.apovol.domiciliaciones.domiciliacionservice.v1.DomiciliacionPortType;
import profuturo.mx.iib.apovol.domiciliaciones.domiciliacionservice.v1.DomiciliacionService;
import profuturo.mx.iib.certif.certificaciones.cuentascertificadasservice.v1.CuentasCertificadasPortType;
import profuturo.mx.iib.certif.certificaciones.cuentascertificadasservice.v1.CuentasCertificadasService;
import profuturo.mx.iib.nci.bitacoraprocesos.folioservice.v1.FolioPortType;
import profuturo.mx.iib.nci.bitacoraprocesos.folioservice.v1.FolioService;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.ClientePortType;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.ClienteService;
import profuturo.mx.iib.nci.matrizconvivencia.matrizconvivenciaservice.v1.MatrizConvivenciaPortType;
import profuturo.mx.iib.nci.matrizconvivencia.matrizconvivenciaservice.v1.MatrizConvivenciaService;

@Configuration("portType")
public class WSPortTypeFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(WSPortTypeFactory.class);
	private static final String CONNECT_TIMEOUT = "javax.xml.ws.client.connectionTimeout";
	private static final String REQUEST_TIMEOUT="javax.xml.ws.client.receiveTimeout";

	@Bean
	public ClientePortType clientePortType() throws BusinessException{
		ClientePortType port = null;
		ClienteService service = new  ClienteService();
		port = service.getClientePort();
		BindingProvider bp = (BindingProvider)port;
		Map<String, Object> requestContext = bp.getRequestContext();
//		LOGGER.debug("Variable cliente "+WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_CONSULTA_CLIENTE);
		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, NameSpaceBindingProvider.getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_CONSULTA_CLIENTE));
		requestContext.put(CONNECT_TIMEOUT, 5*1000); // Timeout in millis
		requestContext.put(REQUEST_TIMEOUT, 3*60*1000); // Timeout in millis
		
		
		return port;
	}
	
	@Bean
	public ControlAccesoSSOPortType controlAccesoSSOPortType() throws BusinessException{
		ControlAccesoSSOPortType port = null;
		ControlAccesoSSOService service = new ControlAccesoSSOService();
		port = service.getControlAccesoSSOPortType();
		
		BindingProvider bp = (BindingProvider)port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, NameSpaceBindingProvider.getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_AUTH));
		
		return port;
	}
	
	@Bean
	public CuentasCertificadasPortType cuentasCertificadasPortType() throws BusinessException{
		CuentasCertificadasPortType port = null;
		CuentasCertificadasService service = new CuentasCertificadasService();
		port = service.getCuentasCertificadasPort();
		
		BindingProvider bp = (BindingProvider)port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, NameSpaceBindingProvider.getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_CUENTA_CERTIFICADA));
		
		return port;
	}
	
	@Bean
	public FolioPortType folioPortType() throws BusinessException{
		FolioPortType port = null;
		FolioService service = new FolioService();
		port = service.getFolioPort();
		
		BindingProvider bp = (BindingProvider)port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, NameSpaceBindingProvider.getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_FOLIO));
		
		return port;
	}
	
	
	@Bean
	public CatalogoComun catalogoComun() throws BusinessException {
		CatalogoComun port= null;
		CatalogoComunService service = new CatalogoComunService();
		port = service.getCatalogoComunServicePort();
		
		BindingProvider bp = (BindingProvider)port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, NameSpaceBindingProvider.getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_CATALOGO_COMUN));
		
		return port;
	}
	
	@Bean
	public OperacionesLdapServices operacionesLdapServices() throws BusinessException{
		OperacionesLdapServices port= null;
		OperacionesLdapService service = new OperacionesLdapService();
		port = service.getOperacionesLdapPort();
		
		BindingProvider bp = (BindingProvider)port;
		Map<String, Object> requestContext = bp.getRequestContext();
		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, NameSpaceBindingProvider.getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_OPERACIONES_LDAP));
		
		return port;
	}
	
	
	@Bean
	public ICatalogoSoapWS catalogoPortType() throws BusinessException{
		ICatalogoSoapWS port = null;
		ICatalogoSoapWSService catalogo = new ICatalogoSoapWSService();
		port = catalogo.getICatalogoSoapWSPort();
		BindingProvider bp = (BindingProvider)port;
		Map<String, Object> requestContext = bp.getRequestContext();
		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, NameSpaceBindingProvider.getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_NCI_CATALOGO));
//		requestContext.put(CONNECT_TIMEOUT, 5*1000); // Timeout in millis
//		requestContext.put(REQUEST_TIMEOUT, 5*1000); // Timeout in millis
		
		//"
		return port;
	}
	
//	@Bean
//	public FileNetPortType fileNetPortType() throws BusinessException{
//		FileNetPortType port = null;
//		FileNetService filenetService = new FileNetService();
//		port = filenetService.getFileNetPortType();
//		BindingProvider bp = (BindingProvider)port;
//		Map<String, Object> requestContext = bp.getRequestContext();
//		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, NameSpaceBindingProvider.getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_FILENET));
//		requestContext.put(CONNECT_TIMEOUT, 5*1000); // Timeout in millis
//		requestContext.put(REQUEST_TIMEOUT, 3*60*1000); // Timeout in millis
//		
//		
//		return port;
//	}
	
	@Bean
	public IIndicadoresSoapWS indicadoresPortType() throws BusinessException{
		IIndicadoresSoapWS port = null;
		IIndicadoresSoapWSService indicadoresService = new IIndicadoresSoapWSService();
		port = indicadoresService.getIIndicadoresSoapWSPort();
		BindingProvider bp = (BindingProvider)port;
		Map<String, Object> requestContext = bp.getRequestContext();
		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, NameSpaceBindingProvider.getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_INDICADORES));
//		requestContext.put(CONNECT_TIMEOUT, 5*1000); // Timeout in millis
//		requestContext.put(REQUEST_TIMEOUT, 5*1000); // Timeout in millis
//		
		
		return port;
	}
	
	@Bean
	public MatrizConvivenciaPortType matrizConvivenciaPortType() throws BusinessException{
		MatrizConvivenciaPortType port = null;
		MatrizConvivenciaService service = new MatrizConvivenciaService();
		port = service.getMatrizConvivenciaPort();
		BindingProvider bp = (BindingProvider)port;
		Map<String, Object> requestContext = bp.getRequestContext();
		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, NameSpaceBindingProvider.getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_MATRIZ));
//		requestContext.put(CONNECT_TIMEOUT, 5*1000); // Timeout in millis
//		requestContext.put(REQUEST_TIMEOUT, 5*1000); // Timeout in millis
		
		
		return port;
	}
	
	@Bean
	public ConciliacionPortType conciliacionPortType() throws BusinessException{
		ConciliacionPortType port = null;
		ConciliacionService service = new ConciliacionService();
		port = service.getConciliacionPort();
		BindingProvider bp = (BindingProvider)port;
		Map<String, Object> requestContext = bp.getRequestContext();
		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, NameSpaceBindingProvider.getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_CONCILIACION));
//		requestContext.put(CONNECT_TIMEOUT, 5*1000); // Timeout in millis
//		requestContext.put(REQUEST_TIMEOUT, 5*1000); // Timeout in millis
		
		
		return port;
	}
	
	@Bean
	public DomiciliacionPortType domiciliacionPortType() throws BusinessException{
		DomiciliacionPortType port = null;
		DomiciliacionService service = new DomiciliacionService();
		port = service.getDomiciliacionPort();
		BindingProvider bp = (BindingProvider)port;
		Map<String, Object> requestContext = bp.getRequestContext();
		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, NameSpaceBindingProvider.getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_DOMICILIACION));
//		requestContext.put(CONNECT_TIMEOUT, 5*1000); // Timeout in millis
//		requestContext.put(REQUEST_TIMEOUT, 5*1000); // Timeout in millis
		
		
		return port;
	}
}

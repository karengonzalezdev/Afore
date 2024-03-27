package mx.profuturo.nci.business.util;


import javax.net.ssl.SSLContext;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ConsumeWS {
	
	private static Logger LOGGER				= LoggerFactory.getLogger( ConsumeWS.class );
	private String rutaTemplate					= "../template";
	private Boolean wsSeguridadHTTPS			= false;
	private Boolean wsTemplate					= false;
	private String wsContentType				= null;
	private String wsEncoding					= null;
	private String wsTLSV						= null;
	
	private String plantilla					= null;
	private String mensaje						= null;


    
    private BufferedReader rd 					= null;
    private String line 						= "";
    private String xmlResponse 					= "";
    private String respuesta 					= "";
    
    public String obtenerValor(String variableInicio, String variableFin, String xml){
        
        try{
            return ( (xml.indexOf(variableInicio) != -1) && (xml.indexOf( variableFin ) != -1 ) )? xml.substring( xml.indexOf(variableInicio) + variableInicio.length() ,xml.indexOf( variableFin )  ) :"";
        }catch(Exception e){
        	LOGGER.error("ERROR Persona         :" + e.getMessage());
        	LOGGER.error("ERROR descripcion    :" , e);
            return "";
        }
    }
	
	//SI wsSeguridadHTTPS = FALSE REQUEST POR PROTOCOLO HTTP
	//SI wsSeguridadHTTPS = TRUE  REQUEST POR PROTOCOLO HTTPS

	//SI wsSeguridadHTTPS = TRUE Y wsTLSV == NULL OCUPARA CERTIFICADO TLSv1
	//SI wsSeguridadHTTPS = TRUE y wsTLSC =! null OCUPARA EL CONTENIDO DE LA VARIABLE wsTLSV
    
    //SI wsTemplate       = TRUE  OCUAPARA UN TEMPLATE DE UN ARCHIVO GENERADO, EN EL PAQUETE mx.profuturo.nci.business.template
    //SI wsTemplate       = FALSE OCUPARA UN TEMPLATE CARGADO EN LA VARIABLE "MENSAJE"
    
    //SI wsContentType	  != NULL  OCUPARA EL CONTENTTYPE ASIGNADO EN DICHA VARIABLE
    //SI wsContentType	  == NULL  OCUPARA CONTENT TYPE POR DEFAULT TEXT/XML;CHARSET=UTF-8
    
    //SI mensaje			  == NULL OCUPARA plantilla como envelope
    //SI mensaje 			  != NULL OCUPARA plantilla y llenara plantilla con contenido de mensaje
	public Object consumeWS( String mensaje, String plantilla, String URL ){
		
		CloseableHttpClient httpclient		= null;
		String xmlRequest					= null;
		HttpPost postRequest 				= null;
		StringEntity strEntity 				= null;
		HttpResponse response 				= null;
		try {
			
			postRequest 	= new HttpPost( URL );
			this.mensaje	= mensaje;
			this.plantilla	= plantilla;
            httpclient 		= generarHtppCliet();
            xmlRequest 		= (mensaje != null)?generarRequest():plantilla;
            
            
            strEntity 		= new StringEntity(xmlRequest);
            strEntity.setContentType( (wsContentType == null)?"text/xml;charset=UTF-8":wsContentType);
           
            postRequest.setEntity(strEntity);
            response 		= httpclient.execute(postRequest);
            
            if (response.getStatusLine().getStatusCode() != 200) {
                respuesta = "OCURRIO UN ERROR [" + response.getStatusLine().getStatusCode();
            } else {

                rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                while ((line = rd.readLine()) != null) {
                    if (xmlResponse.equals("")) {
                        xmlResponse += line;
                    } else {
                        xmlResponse += " " + line.trim();
                    }
                }
                respuesta = xmlResponse;
            }
            return respuesta;
		}catch( Exception e ){
			LOGGER.info( "ERROR UBICACION   :" + ConsumeWS.class.getCanonicalName() );
			LOGGER.info( "ERROR METODO      :" + "consumeWS" );
			LOGGER.info( "ERROR MENSAJE     :" + e.getMessage() );
			LOGGER.info( "ERROR DESCRIPCION :" , e);
			return null;
		}finally {
			
		}
	}
	
	private CloseableHttpClient generarHtppCliet( ){
		
		SSLContext context					= null;
		SSLConnectionSocketFactory sslsf	= null;
		try {
			if( wsSeguridadHTTPS ){
				context	= SSLContext.getInstance("TLS");
				context.init(new KeyManager[0],
							new TrustManager[]{new ConsumeWS.DefaultTrustManager()},
							new SecureRandom());
	            
				SSLContext.setDefault(context);
	            
	            sslsf = new SSLConnectionSocketFactory(	context,
	            										new String[]{ (wsTLSV == null)?"TLSv1":wsTLSV },
	            										null,
	            										SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	            
	            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
			}else {
				return HttpClients.custom().build();
			}
		}catch( Exception e ) {
			LOGGER.info( "ERROR UBICACION    : " + ConsumeWS.class.getCanonicalName() );
			LOGGER.info( "ERROR METODO       : "  + "iniciar");
			return null;
		}finally {
			context		= null;
			sslsf		= null;
		}
	}
	
	private String generarRequest(  ){
		Template template 					= null;
		Map<String, Object> data 			= null;
		StringWriter sw 					= null;
		Configuration cfg 					= null;
		try {
			if( wsTemplate ) {
				cfg 	= new Configuration();
	            cfg.setClassForTemplateLoading(this.getClass(), this.rutaTemplate );
	            
	            template = cfg.getTemplate( this.plantilla );
	            
	            data = new HashMap<String, Object>();
	            data.put("msg", this.mensaje );
	            
	            sw = new StringWriter();
	            template.process(data, sw);
	            return sw.getBuffer().toString();
			}else {
				return this.mensaje;
			}
		}catch( Exception e ) {
			LOGGER.info( "ERROR UBICACION   :" + ConsumeWS.class.getCanonicalName() );
			LOGGER.info( "ERROR METODO      :" + "generarRequest" );
			LOGGER.info( "ERROR MENSAJE     :" + e.getMessage() );
			LOGGER.info( "ERROR DESCRIPCION :" , e );
			return null;
		}
	}
	
	public Boolean getSeguridadHTTPS() {
		return wsSeguridadHTTPS;
	}

	public void setSeguridadHTTPS(Boolean seguridadHTTPS) {
		this.wsSeguridadHTTPS = seguridadHTTPS;
	}
	
	public String getWsTLSV() {
		return wsTLSV;
	}

	public void setWsTLSV(String wsTLSV) {
		this.wsTLSV = wsTLSV;
	}
	
	public String getWsContentType() {
		return wsContentType;
	}

	public void setWsContentType(String wsContentType) {
		this.wsContentType = wsContentType;
	}


	
    private static class DefaultTrustManager implements X509TrustManager {

        
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

}

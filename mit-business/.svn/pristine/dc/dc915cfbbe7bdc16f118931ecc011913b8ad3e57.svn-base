package mx.profuturo.nci.business.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.envelop.ClientesEnvelope;
import mx.profuturo.nci.business.service.IClientesService;
import mx.profuturo.nci.business.util.ConsumeWS;
import mx.profuturo.nci.business.util.NameSpaceBindingProvider;
import mx.profuturo.nci.business.util.WebSphereNamespace;
import mx.profuturo.nci.business.vo.DictamenRechazadaRespuestaVO;
import mx.profuturo.nci.business.vo.ResultadoDictamenRechazadoVO;

@Service("clientesServiceDos")
public class ClientesServiceImpl implements IClientesService{

	private static final Logger LOGGER	= Logger.getLogger( ClientesServiceImpl.class );
	
	public DictamenRechazadaRespuestaVO aplicaDictamenRechazado(ResultadoDictamenRechazadoVO request) {
		
		ConsumeWS ws							= null;
		String respuesta						= null;
		DictamenRechazadaRespuestaVO response	= null;
		try{
			response	= new DictamenRechazadaRespuestaVO();
			ws	= new ConsumeWS();
			ws.setSeguridadHTTPS(false);
			if( (respuesta	= (String) ws.consumeWS(null, new ClientesEnvelope().envelopeAP_ResultadoDictamenRechazada(request),
												NameSpaceBindingProvider.getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_DICTAMEN_RECHAZADA))) != null  ) {
				
				if( ws.obtenerValor("<ActualizarEstatusSolicitudResponse>", "</ActualizarEstatusSolicitudResponse>", respuesta).length() > 0 ) {
					response.setCodRet( "000" );
					response.setMsgRet( "Consulta Correcta" );
					response.setActualizaEstatusSuccess( ws.obtenerValor("<success>", "</success>", ws.obtenerValor("<ActualizarEstatusSolicitudResponse>", "</ActualizarEstatusSolicitudResponse>", respuesta) ) );
					response.setActualizaEstatusResponse( ws.obtenerValor("<response>", "</response>", ws.obtenerValor("<ActualizarEstatusSolicitudResponse>", "</ActualizarEstatusSolicitudResponse>", respuesta) ) );
					
					response.setProcesoDesmarcaSuccess( ws.obtenerValor("<success>", "</success>", ws.obtenerValor("<ProcesoDesmarcaResponse>", "</ProcesoDesmarcaResponse>", respuesta) ) );
					response.setProcesoDesmarcaSuccess( ws.obtenerValor("<response>", "</response>", ws.obtenerValor("<ProcesoDesmarcaResponse>", "</ProcesoDesmarcaResponse>", respuesta) ) );
					response.setFinalizaFolioSuccess( ws.obtenerValor("<success>", "</success>", ws.obtenerValor("<FinalizaFolioResponse>", "</FinalizaFolioResponse>", respuesta) ) );
				}else {
					response.setCodRet( "999" );
					response.setMsgRet( respuesta );
				}				
			}else {
				response.setCodRet( "2000" );
				response.setMsgRet( "Error al consumir el servicio" );
			}
			
			return response;
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACION   :" + ClientesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO      :" + "aplicaDictamenRechazado" );
			LOGGER.error( "ERROR MENSAJE     :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION :" , e );
			return null;
		}
	}
	
}

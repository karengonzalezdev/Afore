package mx.profuturo.nci.business.envelop;

import org.apache.log4j.Logger;
import mx.profuturo.nci.business.vo.ResultadoDictamenRechazadoVO;

public class ClientesEnvelope {

	private static Logger LOGGER	= Logger.getLogger( ClientesEnvelope.class );
	
	public String envelopeAP_ResultadoDictamenRechazada(ResultadoDictamenRechazadoVO dictamen) {

		try {

			return "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:v1='http://profuturo.mx/recaudacion/reclasificacionApoVol/ValidarTramite/AP_ResultadoDictamenRechazada/v1'>"+
						"<soapenv:Header/>"+
							"<soapenv:Body>"+
								"<v1:ResultadoDictamenRechazadaRequest>"+
									"<ResultadoDictamenRechazadaRequest>"+
										"<ActualizarEstatusSolicitud>"+
											"<cveSolicitud>"+dictamen.getEstatusSolicitud().getCveSolicitud() +"</cveSolicitud>"+
											"<idEstatusSolicitud>"+dictamen.getEstatusSolicitud().getIdEstatusSolicitud()+"</idEstatusSolicitud>"+
											"<idMotivoRechazo>" + dictamen.getEstatusSolicitud().getIdMotivoRechazo() + "</idMotivoRechazo>"+
											"<usuActualiza>" + dictamen.getEstatusSolicitud().getUsuActualiza() + "</usuActualiza>"+
										"</ActualizarEstatusSolicitud>" +

										"<desmarcarCuentaMatrizConvivencia>"+
											"<idProceso>"+dictamen.getDesmarcarCuentaMatrizConvivencia().getIdProceso() + "</idProceso>"+
											"<idSubProceso>"+ dictamen.getDesmarcarCuentaMatrizConvivencia().getIdSubProceso() + "</idSubProceso>"+
											"<numCuentaIndividual>" + dictamen.getDesmarcarCuentaMatrizConvivencia().getNumCuentaIndividual()+ "</numCuentaIndividual>"+
											"<folio>" + dictamen.getDesmarcarCuentaMatrizConvivencia().getFolio()+ "</folio>"+
											"<idSubetapa>" + dictamen.getDesmarcarCuentaMatrizConvivencia().getIdSubetapa()+ "</idSubetapa>"+
											"<usuario>" + dictamen.getDesmarcarCuentaMatrizConvivencia().getUsuario()+"</usuario>"+
										"</desmarcarCuentaMatrizConvivencia>" +

										"<FinalizaFolio>"+
											"<folio>"+ dictamen.getFinalizaFolio().getFolio() + "</folio>"+
											"<idEstatusProceso>" + dictamen.getFinalizaFolio().getIdEstatusProceso() + "</idEstatusProceso>"+
											"<usuAct>" + dictamen.getFinalizaFolio().getUsuAct() + "</usuAct>"+
										"</FinalizaFolio>"+
									"</ResultadoDictamenRechazadaRequest>"+
								"</v1:ResultadoDictamenRechazadaRequest>"+
							"</soapenv:Body>"+
					"</soapenv:Envelope>";

		} catch (Exception e) {
			LOGGER.error( "ERROR UBICACION   :" + ClientesEnvelope.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO      :" + "envelopeAP_ResultadoDictamenRechazada" );
			LOGGER.error( "ERROR MENSAJE     :" + e.getMessage() ); 
			LOGGER.error( "ERROR DESCRIPCION :" , e );
			return null;
		} finally {

		}

	}
	
	public String envelopeContratoArchivosDomi (String idContrato) {
		try {
			
			return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cat=\"http://catalogo.webservice.ws.nci.profuturo.mx/\">\r\n" + 
					"   <soapenv:Header/>\r\n" + 
					"   <soapenv:Body>\r\n" + 
					"      <cat:consultar>\r\n" + 
					"         <request>\r\n" + 
					"            <idCatalogo>" + idContrato + "</idCatalogo>\r\n" + 
					"         </request>\r\n" + 
					"      </cat:consultar>\r\n" + 
					"   </soapenv:Body>\r\n" + 
					"</soapenv:Envelope>";
			
		} catch (Exception e) {
			LOGGER.error( "ERROR UBICACION   :" + ClientesEnvelope.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO      :" + "envelopeContratoArchivosDomi" );
			LOGGER.error( "ERROR MENSAJE     :" + e.getMessage() ); 
			LOGGER.error( "ERROR DESCRIPCION :" , e );
			return "";
		}
	}
	
	
	public String envelopeContratoFinalizacionArchDomi(String folio, String estatus) {
		
		try {
			return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:dom=\"http://profuturo.mx/iib/nci/retiros/domiciliacion\">\r\n" + 
					"   <soapenv:Header/>\r\n" + 
					"   <soapenv:Body>\r\n" + 
					"      <dom:EstatusdelaGeneracioArchivosJavaRequest>\r\n" + 
					"          <folio>" + folio + "</folio>\r\n" + 
					"         <successjava>" + estatus + "</successjava>\r\n" + 
					"      </dom:EstatusdelaGeneracioArchivosJavaRequest>\r\n" + 
					"   </soapenv:Body>\r\n" + 
					"</soapenv:Envelope>";
		} catch (Exception e) {
			LOGGER.error( "ERROR UBICACION   :" + ClientesEnvelope.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO      :" + "envelopeContratoFinalizacionArchDomi" );
			LOGGER.error( "ERROR MENSAJE     :" + e.getMessage() ); 
			LOGGER.error( "ERROR DESCRIPCION :" , e );
			return "";
		}
	}

}

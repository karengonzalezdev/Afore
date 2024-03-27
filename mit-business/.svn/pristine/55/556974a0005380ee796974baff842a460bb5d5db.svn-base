package mx.profuturo.nci.business.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeveris.core.exception.impl.BusinessException;


public final class Constantes {
	private static final Logger LOGGER = LoggerFactory.getLogger(Constantes.class); 
	
	static{
		PATH_ACHIVO_DOMI_BANCOMER = getServerValue(WebSphereNamespace.MIT_NCI_AV_GEN_ARCHIVO_DOMI_BANCOMER_PATH);
		PATH_ACHIVO_DOMI_BANAMEX =  getServerValue(WebSphereNamespace.MIT_NCI_AV_GEN_ARCHIVO_DOMI_BANAMEX_PATH);
		PROFUTURO_REPOSITORIO_TESORERIA_DOMAIN = getServerValue(WebSphereNamespace.MIT_NCI_AV_PROFUTURO_REPOSITORIO_TESORERIA_DOMAIN);
		PROFUTURO_REPOSITORIO_TESORERIA_USERNAME = getServerValue(WebSphereNamespace.MIT_NCI_AV_PROFUTURO_REPOSITORIO_TESORERIA_USERNAME);
		PROFUTURO_REPOSITORIO_TESORERIA_PASSWORD = getServerValue(WebSphereNamespace.MIT_NCI_AV_PROFUTURO_REPOSITORIO_TESORERIA_PASSWORD);
		ID_APLICACION = getServerValue(WebSphereNamespace.MIT_NCI_AV_WEB_ID_APP_AUTH_NAME);
	}
	
	public static final String ID_APLICACION;
	
	public static final short ORIGENES_DOMICILIACION = 291;
	public static final short ORIGENES_DOMICILIACION_ESAR = 844;
	public static final short ORIGENES_DOMICILIACION_TRASPASOS = 845;
	/*
	 * FOP 23/09/2020
	 * Nuevo Origen ChatBot
	 */
	public static final short ORIGENES_DOMICILIACION_CHATBOT = 7271;
	
	public static final short CAT_ORIGENES_DOMICILIACION = 48;
	public static final short CAT_ORIGENES = 40;
	public static final short CAT_ESTATUS = 55;
	public static final short CAT_ORIGENES_DISTINTOS = 62;
	
	public static final Integer REGISTRO_VIGENTE = 1;
	public static final Integer REGISTRO_NO_VIGENTE = 0;
	public static final Integer SELECT_ALL = 1;
	
	public static final Integer REGISTRO_NUMERICO_VACIO = -1;
	
	public static final String STRING_TRUE = "1";
	public static final String STRING_FALSE = "0";
	
	public static final String CURP = "CURP";
	public static final String NSS = "NSS";
	public static final String NO_CUENTA = "Cuenta";
	
	//Tablas Movimientos 
	public static final String TABLA_MOV_RCV = "MOV_RCV";	
	public static final String TABLA_MOV_GOB = "MOV_GOB";
	public static final String TABLA_MOV_SAR = "MOV_SAR";
	public static final String TABLA_MOV_VIV = "MOV_VIV";
	public static final String TABLA_MOV_AVOL = "MOV_AVOL";
	public static final String TABLA_MOV_COMP = "MOV_COMP";
	
	
	public static final Short STATUS_DEPOSITO_ARCHIVO_NO_VALIDO = 1;
	public static final Short STATUS_DEPOSITO_ARCHIVO_PENDIENTE_DEPOSITO = 2;
	public static final Short STATUS_DEPOSITO_ARCHIVO_DEPOSITADO = 3;
	public static final Short STATUS_DEPOSITO_ARCHIVO_ERROR_DEPOSITAR = 4;


	public static final HashMap<Short,String> DES_STATUS_DEPOSITO_ARCHIVO=new HashMap<Short, String>()
			{
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					
					put(STATUS_DEPOSITO_ARCHIVO_NO_VALIDO, "No Válido");
					put(STATUS_DEPOSITO_ARCHIVO_PENDIENTE_DEPOSITO, "Pendiente de Depositar");
					put(STATUS_DEPOSITO_ARCHIVO_DEPOSITADO, "Depositado");
					put(STATUS_DEPOSITO_ARCHIVO_ERROR_DEPOSITAR, "Error al depositar");
					
				}
			};

	public static final Short ID_CAT_CONFIG_TIPO_AFILIADO = 13;
	public static final Short ID_CAT_CONFIG_TIPO_INDEPENDIENTE = 14;
	
	public static final Short ID_ESTATUS_ARCHIVO_DOMI_GENERADO=1;
	public static final Short ID_ESTATUS_ARCHIVO_DOMI_CANCELADO=0;
	public static final Short ID_ESTATUS_ARCHIVO_DOMI_EN_PROCESO=-1;
	
	public static final String ID_BANCOMER_DOMICILIACIONES = "BANCOMER_";
	public static final String ID_BANAMEX_DOMICILIACIONES = "DCB";
	public static final String FILE_NAME_PART_DOMICILIACIONES_BANCOMER="_";
	public static final String FILE_NAME_PART_DOMICILIACIONES_BANAMEX="000106886951";
	public static final String FILE_NAME_EXT_DOMICILIACIONES_BANCOMER=".TXT";
	public static final String FILE_NAME_EXT_DOMICILIACIONES_BANAMEX=".DOM";
	public static final String DOMI_FILES_DATE_FORMAT_BANCOMER = "yyyyMMdd";
	public static final String DOMI_FILES_DATE_FORMAT_BANAMEX = "yyMMdd";
	public static final String DOMI_CARGOS_DATE_FORMAT ="dd/MM/yyyy";
	public static final Short BANCOMER_CONSECUTIVE_PADDING_POSITIONS = 3;
	public static final Short BANAMEX_CONSECUTIVE_PADDING_POSITIONS = 2;
	
	public static BigDecimal DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO = new BigDecimal("50000");
	public static final String USO_PRIORIDAD_CATALOGO_DIVERSIFICACION_SOLICITUD="DIVERSIFICACION SOLICITUD";
	public static final String ID_CAT_CATALOGO_MONTO_MAXIMO_ENVIO_BANCO = "3859";
	
	public static final Integer FILE_NAME_OFFSET_DOMICILIACIONES_TRASPASO=29;
//	public static final String FILE_NAME_PART_DOMICILIACIONES_TRASPASO="AFORE";
//	public static final String FILE_NAME_PART_DOMICILIACIONES_ACRONIMO_TRASPASO="TRP";
//	public static final String DOMI_FILES_DATE_FORMAT_TRASPASO = "ddMMyyyy";
	
	public static final Short ID_UNICA_PARCIALIDAD = 0;
	public static final Short ID_MAS_DE_UNA_PARCIALIDAD = 1;

	//------------------
	
	//-------------------MOVER A NAMESPACES DE WAS
	public static final String PATH_ACHIVO_DOMI_BANAMEX;// = "smb://172.16.57.38/EGRESOS_ENVIO_BCOS/DOMICILIACION/PAF01/BANAMEX/";
	public static final String PATH_ACHIVO_DOMI_BANCOMER; //"smb://172.16.57.38/EGRESOS_ENVIO_BCOS/DOMICILIACION/PAF01/BBVA/"
	
	
	
    public static final String PROFUTURO_REPOSITORIO_TESORERIA_DOMAIN;//="PROFUTURO-GNP";
    public static final String PROFUTURO_REPOSITORIO_TESORERIA_USERNAME;// = "RS998586";
    public static final String PROFUTURO_REPOSITORIO_TESORERIA_PASSWORD;// = "PROLAT05**";
    //-------------------------------------------------------------

    
    private static String getServerValue(WebSphereNamespace key){
    	String value=null;
		try {
			value= NameSpaceBindingProvider.getValue(key);
		} catch (BusinessException e) {
			LOGGER.error("No se pudo obtener el valor del servidor para "+key.getValue());
		}
		return value;
    }

	// ---------------------------PORTALES-------------------
	public static final String LBL_CUENTA_INDIVIDUAL = "Cuenta Individual";
	public static final String LBL_NSS = "NSS";
	public static final String LBL_CURP = "CURP";
	public static final String NUMERO_CUENTA = "numeroCuenta";
	public static final String _NSS = "nss";
	public static final String _CURP = "curp";
	
	public static final String CUENTA = "Cuenta";
	
	public static final Map<String, String> SPECIAL_CHARACTERS_REPLACERS = 
			new HashMap<String, String>(){
				private static final long serialVersionUID = 427840258944551562L;
				{
					put("ñ", "n");
					put("Ñ", "N");
				}
	};
	
	public static final Short MONTO_MINIMO_AFILIADO = 13;
	public static final Short MONTO_MINIMO_INDEPENDENDIENTE = 14;
	
	//FOP | 2314 12/03/2018
	public static final String ESTATUS_VIGENTE = "VIGENTE";
	public static final String ESTATUS_SOLICITUD_ACEPTADO = "ACEPTADO";
	public static final String ESTATUS_SOLICITUD_SUSPENDIDA = "SUSPENDIDA";
	public static final Short ESTATUS_SOLICITUD_SUSPENDIDA_ID = 516;
	
	public static final String ERROR_AL_LEER_DATOS_DE_ENTRADA = "Ha ocurrido un error al leer datos de entrada.";
	public static final String NULL_ITEM_REQ = "NULL_ITEM_REQ";
	
	public static final Short CAT_CARGO = 180;
	public static final Short CAT_ABONO = 181;
	//Devoluciones | FOP | 14/04/2021
	public static final String ESTATUS_ENVIADO = "1";
	public static final String ESTATUS_ELIMINADO = "2";
	public static final String ESTATUS_AUTORIZADO = "3";
	
    public static final String OPERACION_FOLIO_WS_REQUEST_CIFRAR_DEVOLUCIONES_CIF = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cip=\"http://profuturo.mx/iib/nci/retirosApovol/cipherRA/\">\r\n" + 
    		"   <soapenv:Header/>\r\n" + 
    		"   <soapenv:Body>\r\n" + 
    		"      <cip:cifrar>\r\n" + 
    		"         <fecha>${fechaEjec}</fecha>\r\n" + 
    		"      </cip:cifrar>\r\n" + 
    		"   </soapenv:Body>\r\n" + 
    		"</soapenv:Envelope>";		
	
}

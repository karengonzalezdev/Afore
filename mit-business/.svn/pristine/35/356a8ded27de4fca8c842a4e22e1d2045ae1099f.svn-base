package mx.profuturo.nci.business.util.enums;


/**
 * 
 * @author 		LR997982
 * @version		1.0
 * @category	JAVA GENERAL
 * @desc		Variables para el control de respuesta de los servicios.
 * 
 */
public enum CtrlResponseWSEnum {
	
	WS_OK( 			"000","Respuesta Correcta" ),
	WS_ERROR(		"999","Error en el servicio"),
	WS_BD_ERROR(	"888","Error en BD"),
	WS_CLIENT_ERROR("777","Error al consumir servicio externo"),
	WS_INPUT_ERROR(	"666","Parametros entrada incorrectos"),
	WS_NO_RECORD(	"001","Sin Registros");
	
	private String codRet;
	private String msgRet;
	
	private CtrlResponseWSEnum( String codRet, String msgRet ) {
		this.codRet	= codRet;
		this.msgRet	= msgRet;
	}

	public String getCodRet() {
		return codRet;
	}

	
	public String getMsgRet() {
		return msgRet;
	}

}

package mx.secure.nci.ws.util.enums;

public enum PresolicitudEnum 
{
	MAPPING_TO_INSERTAR("MAPPING TO INSERTAR"),
	MAPPING_TO_ACTUALIZAR("MAPPING TO ACTUALIZAR"),
	;
	
	private String value;
	
	PresolicitudEnum(String value)
	{
		this.value=value;
	}
	
	public String getValue()
	{
		return this.value;
	}
	

}

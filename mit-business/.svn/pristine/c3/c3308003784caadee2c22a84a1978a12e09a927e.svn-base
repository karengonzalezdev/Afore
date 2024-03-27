package mx.profuturo.aportaciones.ws.utils;

import org.apache.log4j.Logger;

public class UtilsParseXML {

	private static final Logger LOG = Logger.getLogger(UtilsParseXML.class);

	public String obtenerValor(String variableInicio, String variableFin, String xml) {
		try {
			return ((xml.indexOf(variableInicio) != -1) && (xml.indexOf(variableFin) != -1))
					? xml.substring(xml.indexOf(variableInicio) + variableInicio.length(), xml.indexOf(variableFin))
					: "";
		} catch (Exception e) {
			LOG.error("ERROR Persona :" + e.getMessage());
			LOG.error("ERROR descripcion :", e);
			return "";
		}

	}

	public String recortarCadena(String xml, String variable) {
		return xml.substring(xml.indexOf(variable) + variable.length());
	}

}
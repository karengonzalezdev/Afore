package mx.profuturo.nci.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="convertNumberInput")
public class NumericConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		return valor.replaceAll("\\D+","");
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		return valor.toString();
	}

}

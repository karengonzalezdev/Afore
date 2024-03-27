package mx.profuturo.nci.business.util;

import java.util.Calendar;

public enum DiasSemanaEnum {
		LUNES(Calendar.MONDAY,834),
		MARTES(Calendar.TUESDAY,835),
		MIERCOLES(Calendar.WEDNESDAY,836),
		JUEVES(Calendar.THURSDAY,837),
		VIERNES(Calendar.FRIDAY,838)
		;
		
	private Integer calendarValue;
	private Short catalogValue;
	
	private DiasSemanaEnum(Integer calendaValue, Integer valorCatalogo) {
		this.calendarValue= calendaValue;
		this.catalogValue = valorCatalogo.shortValue();
	}

	public static DiasSemanaEnum getEnumFromCalendarValue(Integer calendarValue){
		for(DiasSemanaEnum dia:values()){
			if(dia.getCalendarValue().equals(calendarValue)){
				return dia;
			}
		}
		return null;
	}
	public static DiasSemanaEnum getEnumFromcatalogValue(Short catalogValue){
		for(DiasSemanaEnum dia:values()){
			if(dia.getCatalogValue().equals(catalogValue)){
				return dia;
			}
		}
		return null;
	}

	public Integer getCalendarValue() {
		return calendarValue;
	}

	public void setCalendarValue(Integer calendarValue) {
		this.calendarValue = calendarValue;
	}

	public Short getCatalogValue() {
		return catalogValue;
	}

	public void setCatalogValue(Short catalogValue) {
		this.catalogValue = catalogValue;
	}

	
	
}

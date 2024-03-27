package mx.secure.nci.persistence;

import java.util.Date;

import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface CalendarioPersistence {
	Date selectNextLaborDay(Date fecha);
	Short isDiaHabil(Date fecha);
}

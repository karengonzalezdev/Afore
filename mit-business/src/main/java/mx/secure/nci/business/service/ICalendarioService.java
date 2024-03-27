package mx.secure.nci.business.service;

import java.util.Date;

import mx.secure.nci.business.exception.MitBusinessException;

public interface ICalendarioService {
	Date consultaSiguienteDiaHabil(Date fecha) throws MitBusinessException;
	Boolean esDiaHabil(Date fecha) throws MitBusinessException;
}

package mx.secure.nci.business.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.secure.nci.business.exception.ErrorCodeService;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.ICalendarioService;
import mx.secure.nci.persistence.CalendarioPersistence;

@Service("calendarioService")
public class CalendarioServiceImpl implements ICalendarioService {
	
	@Autowired CalendarioPersistence calendarioPersistence; 

	public Date consultaSiguienteDiaHabil(Date fecha) throws MitBusinessException {
		try{
			return calendarioPersistence.selectNextLaborDay(fecha);
		} catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al consultar Calendario "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "consultaSiguienteDiaHabil()" }, ex));
			throw mitBusinessException;			
		}
	}

	public Boolean esDiaHabil(Date fecha) throws MitBusinessException {
		try{
			Short esDiaHabil=calendarioPersistence.isDiaHabil(fecha);
			if(esDiaHabil==null){
				throw new Exception("No se encuentra el registro de dia habil para la fecha: "+fecha);
			}
			return esDiaHabil > 0;
		} catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al consultar Calendario "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "esDiaHabil()" }, ex));
			throw mitBusinessException;			
		}
	}

}

package mx.profuturo.nci.business.exception;

import com.jeveris.core.exception.impl.BusinessException;
import com.jeveris.core.exception.impl.ExceptionDetails;

public class MitBusinessException extends BusinessException 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MitBusinessException(ExceptionDetails exceptionDetails) 
	{
		super(exceptionDetails);
	}
	
	
	 public String getMessage()
	 {
		return super.getMessage() +": "+ super.getExceptionDetails().getCauseMessage();
	 }
	

}

package mx.profuturo.nci.business.exception;

import com.jeveris.core.exception.impl.ExceptionDetails;

public final class GenerateExceptionDetails 
{

	
	public static ExceptionDetails generate(ErrorCodeRepository errorCode, String causeMessage,Object[] errorMessageArguments,Throwable baseException)
	{
		return generateExceptionDetails(errorCode.getErrorCode(), causeMessage, errorMessageArguments, baseException);
	}
	

	public static ExceptionDetails generate(ErrorCodeService errorCode, String causeMessage,Object[] errorMessageArguments,Throwable baseException)
	{
		return generateExceptionDetails(errorCode.getErrorCode(), causeMessage, errorMessageArguments, baseException);
	}

	public static ExceptionDetails generate(ErrorCodeSoap errorCode, String causeMessage,Object[] errorMessageArguments,Throwable baseException)
	{
		return generateExceptionDetails(errorCode.getErrorCode(), causeMessage, errorMessageArguments, baseException);
	}	
	
	private static ExceptionDetails generateExceptionDetails(String errorCode, String causeMessage,Object[] errorMessageArguments,Throwable baseException)
	{
		return new ExceptionDetails(errorCode, causeMessage, errorMessageArguments, baseException);
	}

}

package mx.profuturo.nci.business.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jeveris.core.exception.impl.BusinessException;
import com.jeveris.core.exception.impl.ExceptionDetails;

public class NameSpaceBindingProvider 
{
		
	public static String getValue(WebSphereNamespace nameSpace)throws BusinessException
	{
		boolean isWebSphere=false;
		
		try
		{
			try{
				return getValueWebSphere(nameSpace);
				//return "";
			}
			catch(Exception e)
			{
				return getValueTomcat(nameSpace);
			}
		}
		catch(BusinessException ex)
		{
			throw new BusinessException(ex.getExceptionDetails());			
		}
	}
	
	private static String getValueTomcat(WebSphereNamespace nameSpace)throws BusinessException
	{
		
		InitialContext initialContext=null;
		String value=null;
		try 
		{
			initialContext = new InitialContext();
			
			Context environmentContext = (Context) initialContext.lookup("java:/comp/env");
			
			Object  valueNameSpace = environmentContext.lookup(nameSpace.getValue());
						
			value = valueNameSpace != null ? (String) valueNameSpace : null;

		} 
		catch (NamingException ex) 
		{
			String errorCode="2000",
					   causeMessage="NamingException En getValueTomcat";				
				Throwable baseException =  ex;	
				
				ExceptionDetails exceptionDetails = new ExceptionDetails(errorCode,causeMessage);
				exceptionDetails.setBaseException(baseException);
			
				throw new BusinessException(exceptionDetails);		
		}
		catch (Exception ex) 
		{
			String errorCode="2000",
					   causeMessage="Exception En getValueTomcat";				
				Throwable baseException =  ex;	
				
				ExceptionDetails exceptionDetails = new ExceptionDetails(errorCode,causeMessage);
				exceptionDetails.setBaseException(baseException);
			
				throw new BusinessException(exceptionDetails);		
		}
		
		
		return value;
				
	}
	
	private static String getValueWebSphere(WebSphereNamespace nameSpace) throws BusinessException
	{
		
		Context initialContext=null;
		String value=null;
		try 
		{
			initialContext = new InitialContext();

			Object  valueNameSpace = initialContext.lookup(nameSpace.getValue());
			value = valueNameSpace != null ? (String) valueNameSpace : null;

		} 
		catch (NamingException ex) 
		{
			String errorCode="2000",
					   causeMessage="NamingException En getValueWebSphere";				
				Throwable baseException =  ex;	
				
				ExceptionDetails exceptionDetails = new ExceptionDetails(errorCode,causeMessage);
				exceptionDetails.setBaseException(baseException);
			
				throw new BusinessException(exceptionDetails);		
		}
		catch (Exception ex) 
		{
			String errorCode="2000",
					   causeMessage="Exception En getValueWebSphere";				
				Throwable baseException =  ex;	
				
				ExceptionDetails exceptionDetails = new ExceptionDetails(errorCode,causeMessage);
				exceptionDetails.setBaseException(baseException);
			
				throw new BusinessException(exceptionDetails);		
		}
		
		return value;
				
	}	
	

}

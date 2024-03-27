package mx.secure.nci.ws.service;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.secure.nci.business.exception.ErrorCodeSoap;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.service.impl.GeneracionArchivoDomiServiceImpl;

import com.jeveris.core.ws.service.AbstractGenericWebServiceInvoker;
import com.jeveris.core.ws.service.exception.WebServiceException;
import com.jeveris.core.ws.service.response.wrapper.AbstractWebServiceResponse;

public abstract class AbstractWebServiceInvoker<T extends AbstractWebServiceResponse> extends AbstractGenericWebServiceInvoker<T>
{	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractWebServiceInvoker.class);
	@Override
	public T invokeService() 
	{
		T response;
		
        block2 : {
            		response = super.createResponse();
		            try 
		            {
		                this.invokeServiceMethod(response);
		                
		                response.setSuccess(true);
		            }
		            catch (WebServiceException ex) 
		            {
		                response.setSuccess(false);
		                this.handleException(response, ex);
		                if (!super.enableTrace()) break block2;		                		               
		                		             		                
		                this.writeStackTrace(response,ex);
		            }
		            catch(Exception ex)
		            {
		            	response.setSuccess(false);
		            	
		    			WebServiceException webServiceException = new WebServiceException(
		    					GenerateExceptionDetails
		    							.generate(ErrorCodeSoap.EX_EXCEPTION,
		    									"Al invokeService",
		    									new Object[] { "AbstractWebServiceInvoker",
		    											"invokeService()" }, ex));

		                
		                this.handleException(response, webServiceException);
		                
		                if (!super.enableTrace()) break block2;
		                
		                this.writeStackTrace(response,webServiceException);		                		             		                		                
		            	
		            }
        		 }
        return response;
	}

    
		
	protected void handleException(T response, WebServiceException e) 
	{
		response.setErrorCode(e.getExceptionDetails().getErrorCode());
		response.setErrorMessage(e.getMessage());
	}


	private void writeStackTrace(T response, WebServiceException e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        LOGGER.info("",pw);
        response.setErrorTrace(e.getExceptionDetails().getBaseException()+", "+sw.toString());
    }

	
	
	public abstract void invokeServiceMethod(T var1) throws WebServiceException;
	
}

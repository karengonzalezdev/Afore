package mx.secure.nci.ws.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "FaultBeanServiceInfo", targetNamespace = "http://www.secure-gnp.com.mx/web/exception")
public class MitWebServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FaultBeanServiceInfo faultInfo;

	public MitWebServiceException(String message, FaultBeanServiceInfo faultInfo) {
		super(message);
        this.faultInfo = faultInfo;
	}

	public MitWebServiceException(String message, FaultBeanServiceInfo faultInfo, 
	     Throwable cause) {
		super(message, cause);
        this.faultInfo = faultInfo;
	}

	public FaultBeanServiceInfo getFaultInfo() {
		return faultInfo;
	}
}

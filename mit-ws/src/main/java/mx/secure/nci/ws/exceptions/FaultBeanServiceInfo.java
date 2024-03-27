package mx.secure.nci.ws.exceptions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "FaultEx", namespace = "http://busdox.org/2010/02/channel/fault")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MissingField", propOrder = { "faultcode",
        "faultstring",
        "detail"})
public class FaultBeanServiceInfo {
	
	@XmlElement(namespace = "")
	private String faultstring;
	@XmlElement(namespace = "")
	private String detail;
	@XmlElement(namespace = "")
	private String faultcode;


	public String getFaultstring() {
		return faultstring;
	}

	public void setFaultstring(String faultstring) {
		this.faultstring = faultstring;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getFaultcode() {
		return faultcode;
	}

	public void setFaultcode(String faultcode) {
		this.faultcode = faultcode;
	}


}

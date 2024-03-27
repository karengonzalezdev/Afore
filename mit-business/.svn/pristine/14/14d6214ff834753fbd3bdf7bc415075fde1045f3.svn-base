
package profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Identificadores complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Identificadores">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="4">
 *         &lt;element name="nss" type="{http://mx.profuturo/nci/modelo}nss"/>
 *         &lt;element name="rfc" type="{http://mx.profuturo/nci/modelo}rfcPersonaFisica"/>
 *         &lt;element name="curp" type="{http://mx.profuturo/nci/modelo}curp"/>
 *         &lt;element name="numeroCuenta" type="{http://mx.profuturo/nci/modelo}numeroCuenta"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Identificadores", propOrder = {
    "nssOrRfcOrCurp"
})
public class Identificadores {

    @XmlElementRefs({
        @XmlElementRef(name = "curp", type = JAXBElement.class),
        @XmlElementRef(name = "rfc", type = JAXBElement.class),
        @XmlElementRef(name = "nss", type = JAXBElement.class),
        @XmlElementRef(name = "numeroCuenta", type = JAXBElement.class)
    })
    protected List<JAXBElement<String>> nssOrRfcOrCurp;

    /**
     * Gets the value of the nssOrRfcOrCurp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nssOrRfcOrCurp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNssOrRfcOrCurp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<String>> getNssOrRfcOrCurp() {
        if (nssOrRfcOrCurp == null) {
            nssOrRfcOrCurp = new ArrayList<JAXBElement<String>>();
        }
        return this.nssOrRfcOrCurp;
    }

	public void setNssOrRfcOrCurp(List<JAXBElement<String>> nssOrRfcOrCurp) {
		this.nssOrRfcOrCurp = nssOrRfcOrCurp;
	}
    
    

}

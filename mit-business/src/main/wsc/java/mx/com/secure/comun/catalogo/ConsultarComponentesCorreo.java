
package mx.com.secure_gnp.comun.catalogo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarComponentesCorreo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarComponentesCorreo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsultarComponentesCorreoIn" type="{http://www.secure-gnp.com.mx/comun/Catalogo}consultarComponentesCorreoIn" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarComponentesCorreo", propOrder = {
    "consultarComponentesCorreoIn"
})
public class ConsultarComponentesCorreo {

    @XmlElement(name = "ConsultarComponentesCorreoIn")
    protected ConsultarComponentesCorreoIn consultarComponentesCorreoIn;

    /**
     * Obtiene el valor de la propiedad consultarComponentesCorreoIn.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarComponentesCorreoIn }
     *     
     */
    public ConsultarComponentesCorreoIn getConsultarComponentesCorreoIn() {
        return consultarComponentesCorreoIn;
    }

    /**
     * Define el valor de la propiedad consultarComponentesCorreoIn.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarComponentesCorreoIn }
     *     
     */
    public void setConsultarComponentesCorreoIn(ConsultarComponentesCorreoIn value) {
        this.consultarComponentesCorreoIn = value;
    }

}

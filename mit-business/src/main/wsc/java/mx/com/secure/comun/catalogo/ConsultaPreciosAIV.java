
package mx.com.secure_gnp.comun.catalogo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultaPreciosAIV complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaPreciosAIV">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consultarPreciosAccionIn" type="{http://www.secure-gnp.com.mx/comun/Catalogo}consultarPreciosAccionIn" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaPreciosAIV", propOrder = {
    "consultarPreciosAccionIn"
})
public class ConsultaPreciosAIV {

    protected ConsultarPreciosAccionIn consultarPreciosAccionIn;

    /**
     * Obtiene el valor de la propiedad consultarPreciosAccionIn.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarPreciosAccionIn }
     *     
     */
    public ConsultarPreciosAccionIn getConsultarPreciosAccionIn() {
        return consultarPreciosAccionIn;
    }

    /**
     * Define el valor de la propiedad consultarPreciosAccionIn.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarPreciosAccionIn }
     *     
     */
    public void setConsultarPreciosAccionIn(ConsultarPreciosAccionIn value) {
        this.consultarPreciosAccionIn = value;
    }

}

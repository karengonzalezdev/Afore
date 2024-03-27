
package mx.com.secure_gnp.comun.catalogo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarElementoIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarElementoIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="claveAlfanumerica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="claveNumerica" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCatalogo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarElementoIn", propOrder = {
    "claveAlfanumerica",
    "claveNumerica",
    "descripcion",
    "idCatalogo"
})
public class ConsultarElementoIn {

    protected String claveAlfanumerica;
    protected Long claveNumerica;
    protected String descripcion;
    protected Long idCatalogo;

    /**
     * Obtiene el valor de la propiedad claveAlfanumerica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveAlfanumerica() {
        return claveAlfanumerica;
    }

    /**
     * Define el valor de la propiedad claveAlfanumerica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveAlfanumerica(String value) {
        this.claveAlfanumerica = value;
    }

    /**
     * Obtiene el valor de la propiedad claveNumerica.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getClaveNumerica() {
        return claveNumerica;
    }

    /**
     * Define el valor de la propiedad claveNumerica.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setClaveNumerica(Long value) {
        this.claveNumerica = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad idCatalogo.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdCatalogo() {
        return idCatalogo;
    }

    /**
     * Define el valor de la propiedad idCatalogo.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdCatalogo(Long value) {
        this.idCatalogo = value;
    }

}

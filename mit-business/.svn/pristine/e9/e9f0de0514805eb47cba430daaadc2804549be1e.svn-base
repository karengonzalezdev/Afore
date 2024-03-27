
package profuturo.mx.iib.nci.notificaciones.enviocorreoservice.v1.types;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datosCorreo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datosCorreo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="para" type="{http://mx.profuturo/iib/nci/Notificaciones/EnvioCorreoService/v1/types}listaCorreos"/>
 *         &lt;element name="cc" type="{http://mx.profuturo/iib/nci/Notificaciones/EnvioCorreoService/v1/types}listaCorreos" minOccurs="0"/>
 *         &lt;element name="de" type="{http://mx.profuturo/iib/nci/Notificaciones/EnvioCorreoService/v1/types}listaCorreos" minOccurs="0"/>
 *         &lt;element name="asunto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="adjunto" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosCorreo", propOrder = {
    "para",
    "cc",
    "de",
    "asunto",
    "mensaje",
    "adjunto"
})
public class DatosCorreo {

    @XmlElement(required = true)
    protected ListaCorreos para;
    protected ListaCorreos cc;
    protected ListaCorreos de;
    @XmlElement(required = true)
    protected String asunto;
    @XmlElement(required = true)
    protected String mensaje;
    @XmlMimeType("*/*")
    protected DataHandler adjunto;

    /**
     * Obtiene el valor de la propiedad para.
     * 
     * @return
     *     possible object is
     *     {@link ListaCorreos }
     *     
     */
    public ListaCorreos getPara() {
        return para;
    }

    /**
     * Define el valor de la propiedad para.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaCorreos }
     *     
     */
    public void setPara(ListaCorreos value) {
        this.para = value;
    }

    /**
     * Obtiene el valor de la propiedad cc.
     * 
     * @return
     *     possible object is
     *     {@link ListaCorreos }
     *     
     */
    public ListaCorreos getCc() {
        return cc;
    }

    /**
     * Define el valor de la propiedad cc.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaCorreos }
     *     
     */
    public void setCc(ListaCorreos value) {
        this.cc = value;
    }

    /**
     * Obtiene el valor de la propiedad de.
     * 
     * @return
     *     possible object is
     *     {@link ListaCorreos }
     *     
     */
    public ListaCorreos getDe() {
        return de;
    }

    /**
     * Define el valor de la propiedad de.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaCorreos }
     *     
     */
    public void setDe(ListaCorreos value) {
        this.de = value;
    }

    /**
     * Obtiene el valor de la propiedad asunto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Define el valor de la propiedad asunto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsunto(String value) {
        this.asunto = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad adjunto.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getAdjunto() {
        return adjunto;
    }

    /**
     * Define el valor de la propiedad adjunto.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setAdjunto(DataHandler value) {
        this.adjunto = value;
    }

}

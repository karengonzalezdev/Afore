
package secure.mx.nci.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import secure.mx.iib.secure.domiciliaciones.domiciliacionservice.v1.types.ActualizarPreSolicitudResp;
import secure.mx.iib.secure.domiciliaciones.domiciliacionservice.v1.types.ActualizarSolicitudResp;
import secure.mx.iib.secure.domiciliaciones.domiciliacionservice.v1.types.ActualizarTraspasoResp;
import secure.mx.iib.secure.domiciliaciones.domiciliacionservice.v1.types.ConsultarPagoTraspasoResp;
import secure.mx.iib.secure.domiciliaciones.domiciliacionservice.v1.types.ConsultarPreSolicitudResp;
import secure.mx.iib.secure.domiciliaciones.domiciliacionservice.v1.types.ConsultarSolicitudResp;
import secure.mx.iib.secure.domiciliaciones.domiciliacionservice.v1.types.GuardarSolicitudResp;
import secure.mx.iib.secure.domiciliaciones.domiciliacionservice.v1.types.RechazarPendientesPorFolioResp;


/**
 * <p>Clase Java para ResponseBase complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResponseBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensajes" type="{http://mx.secure/nci/modelo}ListaMensaje" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseBase", propOrder = {
    "mensajes"
})
@XmlSeeAlso({
    ActualizarSolicitudResp.class,
    ConsultarPreSolicitudResp.class,
    ActualizarTraspasoResp.class,
    ActualizarPreSolicitudResp.class,
    ConsultarSolicitudResp.class,
    GuardarSolicitudResp.class,
    ConsultarPagoTraspasoResp.class,
    RechazarPendientesPorFolioResp.class
})
public class ResponseBase {

    protected ListaMensaje mensajes;

    /**
     * Obtiene el valor de la propiedad mensajes.
     * 
     * @return
     *     possible object is
     *     {@link ListaMensaje }
     *     
     */
    public ListaMensaje getMensajes() {
        return mensajes;
    }

    /**
     * Define el valor de la propiedad mensajes.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaMensaje }
     *     
     */
    public void setMensajes(ListaMensaje value) {
        this.mensajes = value;
    }

}

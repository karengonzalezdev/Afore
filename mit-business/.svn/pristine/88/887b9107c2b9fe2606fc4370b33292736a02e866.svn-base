
package profuturo.mx.iib.certif.certificaciones.cuentascertificadasservice.v1.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ListaCuentasCertificacionDomi complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ListaCuentasCertificacionDomi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroCuenta" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cuentaCertificada" type="{http://mx.profuturo/iib/certif/Certificaciones/CuentasCertificadasService/v1/types}CuentaCertificada"/>
 *         &lt;element name="domiciliaciones" type="{http://mx.profuturo/iib/certif/Certificaciones/CuentasCertificadasService/v1/types}ListaDomiciliacionesCuentaCertificada" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListaCuentasCertificacionDomi", propOrder = {
    "numeroCuenta",
    "cuentaCertificada",
    "domiciliaciones"
})
public class ListaCuentasCertificacionDomi {

    protected long numeroCuenta;
    @XmlElement(required = true)
    protected CuentaCertificada cuentaCertificada;
    protected List<ListaDomiciliacionesCuentaCertificada> domiciliaciones;

    /**
     * Obtiene el valor de la propiedad numeroCuenta.
     * 
     */
    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Define el valor de la propiedad numeroCuenta.
     * 
     */
    public void setNumeroCuenta(long value) {
        this.numeroCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentaCertificada.
     * 
     * @return
     *     possible object is
     *     {@link CuentaCertificada }
     *     
     */
    public CuentaCertificada getCuentaCertificada() {
        return cuentaCertificada;
    }

    /**
     * Define el valor de la propiedad cuentaCertificada.
     * 
     * @param value
     *     allowed object is
     *     {@link CuentaCertificada }
     *     
     */
    public void setCuentaCertificada(CuentaCertificada value) {
        this.cuentaCertificada = value;
    }

    /**
     * Gets the value of the domiciliaciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the domiciliaciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDomiciliaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListaDomiciliacionesCuentaCertificada }
     * 
     * 
     */
    public List<ListaDomiciliacionesCuentaCertificada> getDomiciliaciones() {
        if (domiciliaciones == null) {
            domiciliaciones = new ArrayList<ListaDomiciliacionesCuentaCertificada>();
        }
        return this.domiciliaciones;
    }

}

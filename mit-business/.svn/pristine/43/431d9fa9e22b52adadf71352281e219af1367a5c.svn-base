
package profuturo.mx.iib.apovol.conciliacion.conciliacionservice.v1.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import profuturo.mx.nci.modelo.ResponseBase;


/**
 * <p>Clase Java para consultarFoliosPorConciliarResp complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarFoliosPorConciliarResp">
 *   &lt;complexContent>
 *     &lt;extension base="{http://mx.profuturo/nci/modelo}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="folios" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="folio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarFoliosPorConciliarResp", propOrder = {
    "folios"
})
public class ConsultarFoliosPorConciliarResp
    extends ResponseBase
{

    protected ConsultarFoliosPorConciliarResp.Folios folios;

    /**
     * Obtiene el valor de la propiedad folios.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarFoliosPorConciliarResp.Folios }
     *     
     */
    public ConsultarFoliosPorConciliarResp.Folios getFolios() {
        return folios;
    }

    /**
     * Define el valor de la propiedad folios.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarFoliosPorConciliarResp.Folios }
     *     
     */
    public void setFolios(ConsultarFoliosPorConciliarResp.Folios value) {
        this.folios = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="folio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "folio"
    })
    public static class Folios {

        protected List<String> folio;

        /**
         * Gets the value of the folio property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the folio property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFolio().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getFolio() {
            if (folio == null) {
                folio = new ArrayList<String>();
            }
            return this.folio;
        }

    }

}

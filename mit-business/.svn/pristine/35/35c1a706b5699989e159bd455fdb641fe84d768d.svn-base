
package mx.profuturo.nci.ws.webservice.catalogo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para catalogoConsultarPorRolesBeanResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="catalogoConsultarPorRolesBeanResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="catalogos" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="catalogo" type="{http://catalogo.webservice.ws.nci.profuturo.mx/}catalogoBean" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "catalogoConsultarPorRolesBeanResponse", propOrder = {
    "catalogos"
})
public class CatalogoConsultarPorRolesBeanResponse {

    protected CatalogoConsultarPorRolesBeanResponse.Catalogos catalogos;

    /**
     * Obtiene el valor de la propiedad catalogos.
     * 
     * @return
     *     possible object is
     *     {@link CatalogoConsultarPorRolesBeanResponse.Catalogos }
     *     
     */
    public CatalogoConsultarPorRolesBeanResponse.Catalogos getCatalogos() {
        return catalogos;
    }

    /**
     * Define el valor de la propiedad catalogos.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogoConsultarPorRolesBeanResponse.Catalogos }
     *     
     */
    public void setCatalogos(CatalogoConsultarPorRolesBeanResponse.Catalogos value) {
        this.catalogos = value;
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
     *         &lt;element name="catalogo" type="{http://catalogo.webservice.ws.nci.profuturo.mx/}catalogoBean" maxOccurs="unbounded" minOccurs="0"/>
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
        "catalogo"
    })
    public static class Catalogos {

        protected List<CatalogoBean> catalogo;

        /**
         * Gets the value of the catalogo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the catalogo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCatalogo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CatalogoBean }
         * 
         * 
         */
        public List<CatalogoBean> getCatalogo() {
            if (catalogo == null) {
                catalogo = new ArrayList<CatalogoBean>();
            }
            return this.catalogo;
        }

    }

}

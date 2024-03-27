
package mx.profuturo.nci.ws.webservice.indicadores;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultaIndicadoresBeanResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaIndicadoresBeanResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listaCuentasIndividuales" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="cuentaIndividual" type="{http://indicadores.webservice.ws.nci.profuturo.mx/}cuentaIndividualVO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "consultaIndicadoresBeanResponse", propOrder = {
    "listaCuentasIndividuales"
})
public class ConsultaIndicadoresBeanResponse {

    protected ConsultaIndicadoresBeanResponse.ListaCuentasIndividuales listaCuentasIndividuales;

    /**
     * Obtiene el valor de la propiedad listaCuentasIndividuales.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaIndicadoresBeanResponse.ListaCuentasIndividuales }
     *     
     */
    public ConsultaIndicadoresBeanResponse.ListaCuentasIndividuales getListaCuentasIndividuales() {
        return listaCuentasIndividuales;
    }

    /**
     * Define el valor de la propiedad listaCuentasIndividuales.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaIndicadoresBeanResponse.ListaCuentasIndividuales }
     *     
     */
    public void setListaCuentasIndividuales(ConsultaIndicadoresBeanResponse.ListaCuentasIndividuales value) {
        this.listaCuentasIndividuales = value;
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
     *         &lt;element name="cuentaIndividual" type="{http://indicadores.webservice.ws.nci.profuturo.mx/}cuentaIndividualVO" maxOccurs="unbounded" minOccurs="0"/>
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
        "cuentaIndividual"
    })
    public static class ListaCuentasIndividuales {

        protected List<CuentaIndividualVO> cuentaIndividual;

        /**
         * Gets the value of the cuentaIndividual property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cuentaIndividual property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCuentaIndividual().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CuentaIndividualVO }
         * 
         * 
         */
        public List<CuentaIndividualVO> getCuentaIndividual() {
            if (cuentaIndividual == null) {
                cuentaIndividual = new ArrayList<CuentaIndividualVO>();
            }
            return this.cuentaIndividual;
        }

    }

}

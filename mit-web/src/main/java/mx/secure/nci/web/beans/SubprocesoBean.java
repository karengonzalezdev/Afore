package mx.secure.nci.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SubprocesoBean implements Serializable {

  /**
   * Generated by Eclipse.
   */
  private static final long serialVersionUID = 2423693697092104045L;

  private Short                  idSubproceso;
  private String                 descSubproceso;
  private Double                 totals;
  private Map<String, Double>    stepsTotals;

  /**
   * Default constructor
   */
  public SubprocesoBean() {
    this.stepsTotals = new HashMap<String, Double>();
  }

  public Short getIdSubproceso() {
    return idSubproceso;
  }

  public SubprocesoBean setIdSubproceso(final Short idSubproceso) {
    this.idSubproceso = idSubproceso;

    return this;
  }

  public String getDescSubproceso() {
    return descSubproceso;
  }

  public SubprocesoBean setDescSubproceso(final String descSubproceso) {
    this.descSubproceso = descSubproceso;

    return this;
  }

  public Double getTotals() {
    return totals;
  }

  public SubprocesoBean setTotals(final Double totals) {
    this.totals = totals;

    return this;
  }

  public Map<String, Double> getStepsTotals() {
    return stepsTotals;
  }

  public SubprocesoBean setStepsTotals(final Map<String, Double> stepsTotals) {
    this.stepsTotals = stepsTotals;

    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).appendSuper(super.toString())
        .append("idSubproceso", idSubproceso).append("descSubproceso", descSubproceso).append("totals", totals)
        .append("stepsTotals", stepsTotals).toString();
  }

}
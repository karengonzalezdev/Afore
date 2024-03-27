package mx.profuturo.nci.business.events.report.transaccion;

import mx.profuturo.nci.business.events.ResponseReadEvent;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CifrasGeneralesReportResponseReadEvent extends ResponseReadEvent {

  private String fileName;
	private byte[] byteArray;

	public CifrasGeneralesReportResponseReadEvent() {
	  super();
  }

  public CifrasGeneralesReportResponseReadEvent(final boolean domainFound) {
    super(domainFound);
  }

  public String getFileName() {
    return fileName;
  }

  public CifrasGeneralesReportResponseReadEvent setFileName(final String fileName) {
    this.fileName = fileName;

    return this;
  }

  public byte[] getByteArray() {
    return byteArray;
  }

  public CifrasGeneralesReportResponseReadEvent setByteArray(final byte[] byteArray) {
    this.byteArray = byteArray;

    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).appendSuper(super.toString())
        .append("fileName", fileName).append("byteArray", byteArray).toString();
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof CifrasGeneralesReportResponseReadEvent)) {
      return false;
    }
    final CifrasGeneralesReportResponseReadEvent castOther = (CifrasGeneralesReportResponseReadEvent) other;
    return new EqualsBuilder().appendSuper(super.equals(other)).append(fileName, castOther.fileName)
        .append(byteArray, castOther.byteArray).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(fileName).append(byteArray).toHashCode();
  }

}

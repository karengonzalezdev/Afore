package mx.profuturo.nci.business.events;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class ResponseReadEvent {

  protected boolean domainFound;

  public ResponseReadEvent() {
    this.domainFound = false;
  }

  public ResponseReadEvent(final boolean domainFound) {
    this.domainFound = domainFound;
  }

  public boolean isDomainFound() {
    return domainFound;
  }

  @SuppressWarnings("unchecked")
  public <T extends ResponseReadEvent> T setDomainFound(final boolean domainFound) {
    this.domainFound = domainFound;

    return (T )this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).appendSuper(super.toString())
        .append("domainFound", domainFound).toString();
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof ResponseReadEvent)) {
      return false;
    }
    final ResponseReadEvent castOther = (ResponseReadEvent) other;
    return new EqualsBuilder().append(domainFound, castOther.domainFound).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(domainFound).toHashCode();
  }

}

package mx.profuturo.nci.web.beans;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GenericCatalogoBean 
implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Short id;
	private String valor;
	
	
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", id).append("valor", valor).toString();
	}



	public GenericCatalogoBean(){}
	
	

	public GenericCatalogoBean(Short idCatalogo) {
		super();
		this.id = idCatalogo;
	}

	public GenericCatalogoBean(Short idCatalogo, String valor) 
	{
		super();
		this.id = idCatalogo;
		this.valor = valor;	
	}



	public Short getId() {
		return id;
	}



	public void setId(Short id) {
		this.id = id;
	}



	public String getValor() {
		return valor;
	}



	public void setValor(String valor) {
		this.valor = valor;
	}


	@Override
	public boolean equals(Object obj) {
		return obj!=null 
				&& obj instanceof GenericCatalogoBean 
				&& id!=null
				&& id.equals(((GenericCatalogoBean)obj).getId());
	}

}

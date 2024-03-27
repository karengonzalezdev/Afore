package mx.profuturo.nci.business.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GenericCatalogoIntegerVO 
{
	
	private Integer id;
	private String valor;
	
	public GenericCatalogoIntegerVO(){}
		

	public GenericCatalogoIntegerVO(Integer idCatalogo) {
		super();
		this.id = idCatalogo;
	}

	public GenericCatalogoIntegerVO(Integer idCatalogo, String valor) 
	{
		super();
		this.id = idCatalogo;
		this.valor = valor;	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", id).append("valor", valor).toString();
	}	
}
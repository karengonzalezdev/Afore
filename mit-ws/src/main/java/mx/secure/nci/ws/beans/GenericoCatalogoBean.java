package mx.secure.nci.ws.beans;

public class GenericoCatalogoBean 
{
	
	private Short id;
	private String valor;
	
	public GenericoCatalogoBean(){}
	
	public GenericoCatalogoBean(Short id, String valor) 
	{	
		this.id = id;
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
	
	

	
	
	
	
	

}

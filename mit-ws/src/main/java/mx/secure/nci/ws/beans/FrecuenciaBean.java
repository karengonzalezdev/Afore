package mx.secure.nci.ws.beans;

public class FrecuenciaBean {
	private GenericoCatalogoBean diaSemana;
	private Short dia;

	
	public FrecuenciaBean(){}
	
	

	public FrecuenciaBean(GenericoCatalogoBean diaSemana, Short dia) {
		super();
		this.diaSemana = diaSemana;
		this.dia = dia;
	}



	public GenericoCatalogoBean getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(GenericoCatalogoBean diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Short getDia() {
		return dia;
	}

	public void setDia(Short dia) {
		this.dia = dia;
	}

}

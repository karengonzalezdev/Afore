package mx.secure.nci.ws.service.bancos;

import mx.secure.nci.ws.beans.request.MovBancoConsultarBeanRequest;
import mx.secure.nci.ws.beans.request.MovBancoActualizarBeanRequest;
import mx.secure.nci.ws.beans.response.MovBancoConsultarBeanResponse;

public interface IBancosSoapService {
	public MovBancoConsultarBeanResponse consultar(MovBancoConsultarBeanRequest movBancoConsultarBeanRequest);
	public int actualizar(MovBancoActualizarBeanRequest movBancoInsertarBeanRequest);
}

package mx.secure.nci.ws.service.sumconcilia;

import mx.secure.nci.ws.beans.request.SumConciliaActualizarBeanRequest;
import mx.secure.nci.ws.beans.request.SumConciliaConsultarBeanRequest;
import mx.secure.nci.ws.beans.response.SumConciliaConsultarBeanResponse;

public interface ISumConciliaSoapService {
	public SumConciliaConsultarBeanResponse consultar(SumConciliaConsultarBeanRequest sumConciliaConsultarBeanRequest);
	public int actualizar(SumConciliaActualizarBeanRequest sumConciliaActualizarBeanRequest);
}

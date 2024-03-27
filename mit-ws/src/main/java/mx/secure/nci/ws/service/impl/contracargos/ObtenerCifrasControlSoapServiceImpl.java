package mx.secure.nci.ws.service.impl.contracargos;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.secure.nci.business.service.IObtenerCifrasControlService;
import mx.secure.nci.business.util.enums.CtrlResponseWSEnum;
import mx.secure.nci.business.vo.ObtenerCifrasControlVO;
import mx.secure.nci.business.vo.ValidacionesVO;
import mx.secure.nci.ws.beans.request.ObtenerCifrasControlBeanRequest;
import mx.secure.nci.ws.beans.response.ObtenerCifrasControlBeanResponse;
import mx.secure.nci.ws.exceptions.FaultBeanServiceInfo;
import mx.secure.nci.ws.exceptions.MitWebServiceException;
import mx.secure.nci.ws.service.contracargos.IObtenerCifrasControlSoapService;

@Service("obtenerCifrasControlSoapService")
public class ObtenerCifrasControlSoapServiceImpl implements IObtenerCifrasControlSoapService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ObtenerCifrasControlSoapServiceImpl.class);
	
	@Autowired
	IObtenerCifrasControlService iObtenerCifrasControlService;
	
	public ObtenerCifrasControlBeanResponse getListaCifrasControl(ObtenerCifrasControlBeanRequest request) {
		ValidacionesVO validacion = null;
		List<ObtenerCifrasControlVO> lstCifrasMovimientosCif = null;
		List<ObtenerCifrasControlVO> lstBancosyCuentasDestino = null;
		List<ObtenerCifrasControlVO> lstTotalDevsecure = null;
		
		try {
			if (request == null) {
				return new ObtenerCifrasControlBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "REQUEST necesario");
			}
			if (!(validacion = request.validarRequest()).isEstatus()) {
				return new ObtenerCifrasControlBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), validacion.getMensaje());
			}
			
			lstCifrasMovimientosCif = iObtenerCifrasControlService.obtenerListaCifrasMovimientosCif((ObtenerCifrasControlVO) validacion.getObjetoAux());
			lstBancosyCuentasDestino = iObtenerCifrasControlService.obtenerListaBancosyCuentasDestino((ObtenerCifrasControlVO) validacion.getObjetoAux());
			lstTotalDevsecure = iObtenerCifrasControlService.obtenerListaTotalDevsecure((ObtenerCifrasControlVO) validacion.getObjetoAux());
			if (!lstCifrasMovimientosCif.isEmpty() && !lstBancosyCuentasDestino.isEmpty() && !lstTotalDevsecure.isEmpty()) {
				return new ObtenerCifrasControlBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Consulta exitosa", lstCifrasMovimientosCif, lstBancosyCuentasDestino, lstTotalDevsecure);
			} else {
				return new ObtenerCifrasControlBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(),
						"No se encontraron registros con los criterios ingresados");
			}
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACION     :" + ObtenerCifrasControlSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO        :" + "getListaCifrasControl");
			LOGGER.error("ERROR MENSAJE       :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION   :", e);
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", getListaCifrasControl");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			validacion = null;
			lstCifrasMovimientosCif = null;
			lstBancosyCuentasDestino = null;
			lstTotalDevsecure = null;
		}
	}
	
}

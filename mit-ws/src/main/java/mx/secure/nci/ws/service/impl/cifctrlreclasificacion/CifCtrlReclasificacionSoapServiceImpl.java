package mx.secure.nci.ws.service.impl.cifctrlreclasificacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;

import mx.secure.nci.business.service.cifctrlreclasificacion.ICifCtrlReclasificacionService;
import mx.secure.nci.business.vo.cifctrlreclasificacion.CCOrigDestReclasificacionVO;
import mx.secure.nci.business.vo.cifctrlreclasificacion.CCTotalReclasificacionVO;
import mx.secure.nci.business.vo.cifctrlreclasificacion.CCTotalSubcuentaReclasificacionVO;
import mx.secure.nci.business.wrapped.cifctrlreclasificacion.CCReclasificacionFilterBuild;
import mx.secure.nci.ws.beans.request.CifCtrlCargoAbonoReclasifBeanRequest;
import mx.secure.nci.ws.beans.request.CifCtrlTotalReclasificacionBeanRequest;
import mx.secure.nci.ws.beans.response.CifCtrlOrigDestReclasificacionBeanResponse;
import mx.secure.nci.ws.beans.response.CifCtrlReclasificacionBeanResponse;
import mx.secure.nci.ws.exceptions.FaultBeanServiceInfo;
import mx.secure.nci.ws.exceptions.MitWebServiceException;
import mx.secure.nci.ws.service.cifctrlreclasificacion.ICifCtrlReclasificacionSoapService;

@Service
public class CifCtrlReclasificacionSoapServiceImpl implements ICifCtrlReclasificacionSoapService {

	private static final Integer TIPO_MOV_ABONO = 180;
	private static final Integer TIPO_MOV_CARGO = 181;
	@Autowired
	ICifCtrlReclasificacionService ccReclasificacionService;

	@Override
	public CCTotalReclasificacionVO consultaTotalReclasificacion(CifCtrlTotalReclasificacionBeanRequest request) {
		CCTotalReclasificacionVO response = null;
		try {
			response = ccReclasificacionService.consultaTotalReclasificacion(
					new CCReclasificacionFilterBuild().setFolioCifCtrl(request.getFolioReclasificacion()).build());
		} catch (BusinessException e) {
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring("Ocurrio un error al consultar total de solicitudes de reclasicicacion.");
			throw new MitWebServiceException(e.getMessage(), missing);
		}
		return response;
	}

	@Override
	public CifCtrlOrigDestReclasificacionBeanResponse consultaOrigDestReclasificacion(
			CifCtrlTotalReclasificacionBeanRequest request) {
		CifCtrlOrigDestReclasificacionBeanResponse response = new CifCtrlOrigDestReclasificacionBeanResponse();
		List<CCOrigDestReclasificacionVO> listaMovimientos = null;
		try {
			listaMovimientos = ccReclasificacionService.consultaOrigDestReclasificacion(
					new CCReclasificacionFilterBuild().setFolioCifCtrl(request.getFolioReclasificacion()).build());
			
			response.setListaMovimientos(listaMovimientos);
		} catch (BusinessException e) {
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring("Ocurrio un error al consultar total de solicitudes origen/destino por siefores.");
			throw new MitWebServiceException(e.getMessage(), missing);
		}
		return response;
	}

	@Override
	public CifCtrlReclasificacionBeanResponse consultaTotalPorSubcuentas(
			CifCtrlTotalReclasificacionBeanRequest request) {
		
		CifCtrlReclasificacionBeanResponse response = new CifCtrlReclasificacionBeanResponse();
		List<CCTotalSubcuentaReclasificacionVO> listSubcuentas = null;
		try {
			listSubcuentas = ccReclasificacionService.consultaTotalPorSubcuentas(
					new CCReclasificacionFilterBuild().setFolioCifCtrl(request.getFolioReclasificacion()).build());
			
			response.setListaTotalSubcuentas(listSubcuentas);
		} catch (BusinessException e) {
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring("Ocurrio un error al consultar montos totales de reclasificacion por subcuentas.");
			throw new MitWebServiceException(e.getMessage(), missing);
		}
		return response;
	}

	@Override
	public CifCtrlReclasificacionBeanResponse consultaCargAboPorSubcuentas(
			CifCtrlCargoAbonoReclasifBeanRequest request) {
		CifCtrlReclasificacionBeanResponse response = new CifCtrlReclasificacionBeanResponse();
		List<CCTotalSubcuentaReclasificacionVO> listSubcuentas = null;
		if(request.getTipoMov() < TIPO_MOV_ABONO || request.getTipoMov() > TIPO_MOV_CARGO){
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring("Ocurrio un error al consultar montos cargos/abonos de reclasificacion por subcuentas.");
			throw new MitWebServiceException("El tipo de movimiento es obligatorio y debe ser 180-Cargos o 181-Abonos", missing);
		}
		try {
			listSubcuentas = ccReclasificacionService.consultaCargAboPorSubcuentas(new CCReclasificacionFilterBuild()
					.setFolioCifCtrl(request.getFolioReclasificacion()).setTipoMov(request.getTipoMov()).build());
			
			response.setListaTotalSubcuentas(listSubcuentas);
		} catch (BusinessException e) {
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(
					"Ocurrio un error al consultar montos cargos/abonos de reclasificacion por subcuentas.");
			throw new MitWebServiceException(e.getMessage(), missing);
		}
		return response;
	}

}

package mx.secure.nci.ws.service.impl.devoluciones;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import mx.secure.nci.business.service.IDevolucionesService;
import mx.secure.nci.business.util.enums.CtrlResponseWSEnum;
import mx.secure.nci.business.vo.DevolucionesVO;
import mx.secure.nci.business.vo.LoteControlAluxVO;
import mx.secure.nci.business.vo.LoteControlNciVO;
import mx.secure.nci.business.vo.ValidacionesVO;
import mx.secure.nci.ws.beans.request.DevolucionesActualizaBeanRequest;
import mx.secure.nci.ws.beans.request.DevolucionesConsultaListaBeanRequest;
import mx.secure.nci.ws.beans.request.DevolucionesEnvioCIFBeanRequest;
import mx.secure.nci.ws.beans.request.DevolucionesListaEstatusBeanRequest;
import mx.secure.nci.ws.beans.response.DevolucionesActualizaBeanResponse;
import mx.secure.nci.ws.beans.response.DevolucionesConsultaListaBeanResponse;
import mx.secure.nci.ws.beans.response.DevolucionesEnvioCIFBeanResponse;
import mx.secure.nci.ws.beans.response.DevolucionesListaEstatusBeanResponse;
import mx.secure.nci.ws.exceptions.FaultBeanServiceInfo;
import mx.secure.nci.ws.exceptions.MitWebServiceException;
import mx.secure.nci.ws.service.devoluciones.IDevolucionesSoapService;

@Service("devolucionesSoapService")
public class DevolucionesSoapServiceImpl implements IDevolucionesSoapService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DevolucionesSoapServiceImpl.class);
	
	@Autowired
	IDevolucionesService iDevoluciones;
	
	@Override
	public DevolucionesEnvioCIFBeanResponse envioDevolucionesCIF(DevolucionesEnvioCIFBeanRequest request) {
		
		ValidacionesVO validacion			 = null;
		try {
			if( request == null ) {
				return new DevolucionesEnvioCIFBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,"REQUEST necesario"
						);
			}	
			if( !(validacion = request.validarRequest()).isEstatus() ) {
				return new DevolucionesEnvioCIFBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,validacion.getMensaje()
						);
			}	
			Integer devCif = iDevoluciones.enviaDevolucionesCIF(request.getFolios());
			if(devCif > 0) {
				return new DevolucionesEnvioCIFBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Se enviaron las devoluciones al CIF",devCif);
			}else {
				return new DevolucionesEnvioCIFBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "No se identificaron registros para enviar al CIF");
			}			
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACION     :" + DevolucionesSoapServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "envioDevolucionesCIF" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION   :" , e );
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet()+", envioDevolucionesCIF");
			throw new MitWebServiceException(e.getMessage(), missing);
		}finally {
			validacion			= null;
		}		
		
	}
	
	@Override
	public DevolucionesActualizaBeanResponse actualizaDevoluciones(DevolucionesActualizaBeanRequest request) {
		ValidacionesVO validacion					= null;
		try {
			if( request == null ) {
				return new DevolucionesActualizaBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,"REQUEST necesario"
						);
			}			
			if( !(validacion = request.validarRequest()).isEstatus() ) {
				return new DevolucionesActualizaBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,validacion.getMensaje()
						);
			}		
			Integer rgsAct = iDevoluciones.actualizaDevolucion((DevolucionesVO) validacion.getObjetoAux());
			if(rgsAct > 0) {
				return new DevolucionesActualizaBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Actualizacion Exitosa",rgsAct.toString());			
			}else {
				return new DevolucionesActualizaBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "No se encontraron registros para actualizar con ese folio","0");			
			}
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACION     :" + DevolucionesSoapServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "actualizaDevoluciones" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION   :" , e );
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet()+", actualizaDevoluciones");
			throw new MitWebServiceException(e.getMessage(), missing);
		}finally {
			validacion			= null;
		}
	
	}	

	@Override
	public DevolucionesConsultaListaBeanResponse consultaDevoluciones(DevolucionesConsultaListaBeanRequest request) {
		ValidacionesVO validacion					= null;
		List<DevolucionesVO> ltsDevoluciones		= null;
		try {
			if( request == null ) {
				return new DevolucionesConsultaListaBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,"REQUEST necesario"
						);
			}
			if( !(validacion = request.validarRequest()).isEstatus() ) {
				return new DevolucionesConsultaListaBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,validacion.getMensaje()
						);
			}			
			ltsDevoluciones = iDevoluciones.obtenerListaDevoluciones((DevolucionesVO) validacion.getObjetoAux());
			if(!ltsDevoluciones.isEmpty()) {
				DevolucionesConsultaListaBeanResponse rgs = new DevolucionesConsultaListaBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Se enlistan los registros encontrados",ltsDevoluciones);
				rgs.setStrLtsDevoluciones(new Gson().toJson(rgs));
				return rgs;
			}else {
				return new DevolucionesConsultaListaBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "No se encontraros registros con los criterios ingresados");
			}
			
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACION     :" + DevolucionesSoapServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "consultaDevoluciones" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION   :" , e );
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet()+", consultaDevoluciones");
			throw new MitWebServiceException(e.getMessage(), missing);
		}finally {
			validacion			= null;
			ltsDevoluciones		= null;
		}		
	}	
	
	@Override
	public DevolucionesListaEstatusBeanResponse consultaDevolucionesByEstatus(DevolucionesListaEstatusBeanRequest request) {
		ValidacionesVO validacion					= null;
		List<LoteControlAluxVO> lotesAlux			= null;
		List<LoteControlNciVO> lotesNci 			= null;
		try {
			if( request == null ) {
				return new DevolucionesListaEstatusBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,"REQUEST necesario"
						);
			}			
			if( !(validacion = request.validarRequest()).isEstatus() ) {
				return new DevolucionesListaEstatusBeanResponse(
						   CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet()
						 , CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet()
						 ,validacion.getMensaje()
						);
			}
			if(request.getEstatus() != null) {
				if("0".equals(request.getEstatus())) {
					lotesAlux = iDevoluciones.obtenerLotesAlux((LoteControlAluxVO) validacion.getObjetoAux());
					lotesNci = iDevoluciones.obtenerLotesNCI(lotesAlux, request.getEstatus());
				}else {
					LoteControlAluxVO aluxVO = (LoteControlAluxVO)validacion.getObjetoAux();
					aluxVO.setEstatus(new Short(request.getEstatus()));
					lotesNci = iDevoluciones.obtenerLotesNCI(aluxVO);
				}
				
			}else {
				lotesAlux = iDevoluciones.obtenerLotesAlux((LoteControlAluxVO) validacion.getObjetoAux());
				lotesNci = iDevoluciones.obtenerLotesNCI(lotesAlux, request.getEstatus());
			}
			if(!lotesNci.isEmpty()) {
				return new DevolucionesListaEstatusBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Se enlistan los registros encontrados",lotesNci);
			}else {
				return new DevolucionesListaEstatusBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "No se identificaron registros con los criterios ingresados");
			}
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACION     :" + DevolucionesSoapServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "consultaDevolucionesByEstatus" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION   :" , e );
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet()+", consultaDevolucionesByEstatus");
			throw new MitWebServiceException(e.getMessage(), missing);
		}finally {
			validacion		= null;
			lotesAlux		= null;
			lotesNci		= null;
		}
	}


}

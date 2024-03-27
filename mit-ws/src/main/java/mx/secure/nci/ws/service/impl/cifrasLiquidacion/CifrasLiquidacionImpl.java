package mx.secure.nci.ws.service.impl.cifrasLiquidacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import mx.secure.nci.business.service.ICifrasLiquidacionService;
import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.util.enums.CtrlResponseWSEnum;
import mx.secure.nci.business.vo.DetalleFondosVO;
import mx.secure.nci.business.vo.DetalleSieforesVO;
import mx.secure.nci.business.vo.CifrasLiquidacionSolVO;
import mx.secure.nci.business.vo.CifrasLiquidacionVO;
import mx.secure.nci.business.vo.ValidacionesVO;
import mx.secure.nci.business.wrapped.CifrasLiquidacionFilter;
import mx.secure.nci.ws.beans.DetalleFondosBean;
import mx.secure.nci.ws.beans.DetalleSieforesBean;
import mx.secure.nci.ws.beans.request.CifrasLiquidacionBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasLiquidacionConsultaSolBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasLiquidacionExcluirSolBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasLiquidacionExportableBeanRequest;
import mx.secure.nci.ws.beans.response.CifrasLiquidacionBeanResponse;
import mx.secure.nci.ws.beans.response.CifrasLiquidacionConsultaSolBeanResponse;
import mx.secure.nci.ws.beans.response.CifrasLiquidacionExcluirBean;
import mx.secure.nci.ws.beans.response.CifrasLiquidacionExcluirSolBeanResponce;
import mx.secure.nci.ws.beans.response.CifrasLiquidacionExportableBeanResponse;
import mx.secure.nci.ws.beans.response.CifrasLiquidacionSolicitudBean;
import mx.secure.nci.ws.beans.response.ReporteBeanResponse;
import mx.secure.nci.ws.exceptions.FaultBeanServiceInfo;
import mx.secure.nci.ws.exceptions.MitWebServiceException;
import mx.secure.nci.ws.service.cifrasLiquidacion.ICifrasLiquidacionSoapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

@Service("CifrasLiquidacionImplSoapService")
public class CifrasLiquidacionImpl implements ICifrasLiquidacionSoapService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CifrasLiquidacionImpl.class);

	@Autowired 
	ICifrasLiquidacionService cifrasLiquidacionService;
	
	@Override
	public CifrasLiquidacionBeanResponse consultaCifrasLiquidacion(
			CifrasLiquidacionBeanRequest cifrasLiquidacionBeanRequest) {
		ValidacionesVO validaciones;
		CifrasLiquidacionVO cifrasLiquidacionVO;
		CifrasLiquidacionBeanResponse response = null;
		CifrasLiquidacionFilter filter= new CifrasLiquidacionFilter();
		
        try {
            if (cifrasLiquidacionBeanRequest == null) {
                return new CifrasLiquidacionBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
                        CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(),
                        "request requerido.");
            }

            if (!(validaciones = cifrasLiquidacionBeanRequest.validaRequest()).isEstatus()) {
                return new CifrasLiquidacionBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
                        										  CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(),
                        										  validaciones.getMensaje());
            }
            filter.setFechaInicio(cifrasLiquidacionBeanRequest.getFechaInicio());
            filter.setFechaFin(cifrasLiquidacionBeanRequest.getFechaFin());
            filter.setUsuario(cifrasLiquidacionBeanRequest.getUsuario());
            
            int eliminar = cifrasLiquidacionService.eliminarRegistrosCifrasLiquidacion();
            int registrar = cifrasLiquidacionService.registrarCifrasLiquidacion(filter);
           	cifrasLiquidacionVO = cifrasLiquidacionService.consultarCifrasLiquidacion(filter);
 

            response = new CifrasLiquidacionBeanResponse(
                    UtilValidate.isNull(cifrasLiquidacionVO) ? CtrlResponseWSEnum.WS_NO_RECORD.getCodRet() : CtrlResponseWSEnum.WS_OK.getCodRet(),
                    UtilValidate.isNull(cifrasLiquidacionVO) ? CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet() : CtrlResponseWSEnum.WS_OK.getMsgRet(),
                    UtilValidate.isNull(cifrasLiquidacionVO) ? "Consulta no contiene registros con esos parametros" : "Consulta correcta");
           
            if(!UtilValidate.isNull(cifrasLiquidacionVO)) {
            	response.setFechaInicio(cifrasLiquidacionVO.getFechaInicio());
            	response.setFechaFin(cifrasLiquidacionVO.getFechaFin());
            	
            	List<DetalleSieforesBean> detalleSieforeBean = new ArrayList<DetalleSieforesBean>();
                for (DetalleSieforesVO detalleSieforesVO : cifrasLiquidacionVO.getDetalleSieforeVO()) {

                	List<DetalleFondosBean> detalleFondoBean = new ArrayList<DetalleFondosBean>();
                    for (DetalleFondosVO detalleFondoVO : detalleSieforesVO.getDetalleFondosVO()) {
						detalleFondoBean
								.add(new Gson().fromJson(new Gson().toJson(detalleFondoVO), DetalleFondosBean.class));
                    }
                    detalleSieforeBean.add(new DetalleSieforesBean(detalleSieforesVO.getIdSiefore(), detalleSieforesVO.getSiefore(), detalleFondoBean));
                }
            	
                response.setDetalleSieforeBean(detalleSieforeBean);
                
            }
            
            return response;

        } catch (Exception e) {
            LOGGER.error("ERROR UBICACION   :" + CifrasLiquidacionImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO      :" + "consultaCifrasLiquidacion");
            LOGGER.error("ERROR MENSAJE     :" + e.getMessage());
            LOGGER.error("ERROR DESCRIPCION :" , e);

            FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
            missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", consultaCifrasLiquidacion");
            throw new MitWebServiceException(e.getMessage(), missing);
        } finally {
            validaciones = null;
            cifrasLiquidacionVO = null;
            response = null;
        }
	}

	@Override
	public CifrasLiquidacionConsultaSolBeanResponse consultaSolicitudes(
			CifrasLiquidacionConsultaSolBeanRequest cifrasLiquidacionBeanRequest) {
		
		ValidacionesVO validaciones;
		CifrasLiquidacionConsultaSolBeanResponse response = null;
		CifrasLiquidacionFilter filter= new CifrasLiquidacionFilter();
        try {
            if (cifrasLiquidacionBeanRequest == null) {
                return new CifrasLiquidacionConsultaSolBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
                        CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(),
                        "request requerido.");
            }

            if (!(validaciones = cifrasLiquidacionBeanRequest.validaRequest()).isEstatus()) {
                return new CifrasLiquidacionConsultaSolBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
                        										  CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(),
                        										  validaciones.getMensaje());
            }
            
            if(cifrasLiquidacionBeanRequest.getCuenta() != null) {
            	filter.setCuenta(cifrasLiquidacionBeanRequest.getCuenta());
            }
            if(cifrasLiquidacionBeanRequest.getCurp() != null) {
            	filter.setCurp(cifrasLiquidacionBeanRequest.getCurp());
            }
            if(cifrasLiquidacionBeanRequest.getNss() != null) {
            	filter.setNss(cifrasLiquidacionBeanRequest.getNss());
            }
            
            List<CifrasLiquidacionSolVO>  consultaSolicitudes = cifrasLiquidacionService.consultaSolicitudes(filter);
            

            response = new CifrasLiquidacionConsultaSolBeanResponse(
                    UtilValidate.isNull(consultaSolicitudes) ? CtrlResponseWSEnum.WS_NO_RECORD.getCodRet() : CtrlResponseWSEnum.WS_OK.getCodRet(),
                    UtilValidate.isNull(consultaSolicitudes) ? CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet() : CtrlResponseWSEnum.WS_OK.getMsgRet(),
                    UtilValidate.isNull(consultaSolicitudes) ? "Consulta no contiene registros con esos parametros" : "Consulta correcta");
           
            if(!UtilValidate.isNull(consultaSolicitudes)) {

            	
            	List<CifrasLiquidacionSolicitudBean> detalleSolicitudBean = new ArrayList<CifrasLiquidacionSolicitudBean>();
                for (CifrasLiquidacionSolVO detalleSolicitudVO : consultaSolicitudes) {
                	detalleSolicitudBean.add(new Gson().fromJson(new Gson().toJson(detalleSolicitudVO), CifrasLiquidacionSolicitudBean.class));
                }
                response.setDetalleSolicitudes(detalleSolicitudBean);   
            }
            
            return response;

        } catch (Exception e) {
            LOGGER.error("ERROR UBICACION   :" + CifrasLiquidacionImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO      :" + "consultaSolicitudes");
            LOGGER.error("ERROR MENSAJE     :" + e.getMessage());
            LOGGER.error("ERROR DESCRIPCION :" , e);

            FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
            missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", consultaSolicitudes");
            throw new MitWebServiceException(e.getMessage(), missing);
        } finally {
            validaciones = null;
            response = null;
        }
	}

	@Override
	public CifrasLiquidacionExcluirSolBeanResponce excluirSolCifrasLiquidacion(
			CifrasLiquidacionExcluirSolBeanRequest excluirCifrasLiquidacionBeanRequest) {
		// TODO Auto-generated method stub
		ValidacionesVO validaciones;
		CifrasLiquidacionFilter filter= new CifrasLiquidacionFilter();
	
		CifrasLiquidacionExcluirSolBeanResponce response = null;
		
		
		
        try {
            if (excluirCifrasLiquidacionBeanRequest == null) {
                return new CifrasLiquidacionExcluirSolBeanResponce(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
                        CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(),
                        "request requerido.");
            }

            if (!(validaciones = excluirCifrasLiquidacionBeanRequest.validaRequest()).isEstatus()) {
                return new CifrasLiquidacionExcluirSolBeanResponce(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
                        										  CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(),
                        										  validaciones.getMensaje());
            }
            
            for (CifrasLiquidacionExcluirBean elemento : excluirCifrasLiquidacionBeanRequest.getListCuentas()) {
            	filter.setCuenta(elemento.getCuenta());
            	filter.setNoCargo(elemento.getNoCargo());
            	cifrasLiquidacionService.excluirSolCifrasLiquidacion(filter);
            }
            
            response = new CifrasLiquidacionExcluirSolBeanResponce(CtrlResponseWSEnum.WS_OK.getCodRet(),
                    											   CtrlResponseWSEnum.WS_OK.getMsgRet(),
                    											   "Consulta correcta");
           
            response.setSolicitudesExcluidas(true);
            response.setUsuario(excluirCifrasLiquidacionBeanRequest.getUsuario());
            
            return response;

        } catch (Exception e) {
            LOGGER.error("ERROR UBICACION   :" + CifrasLiquidacionImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO      :" + "excluirSolCifrasLiquidacion");
            LOGGER.error("ERROR MENSAJE     :" + e.getMessage());
            LOGGER.error("ERROR DESCRIPCION :" , e);

            FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
            missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", excluirSolCifrasLiquidacion");
            throw new MitWebServiceException(e.getMessage(), missing);
        } finally {
            validaciones = null;
            response = null;
        }
	}

	@Override
	public ReporteBeanResponse exportableCifrasLiquidacion(CifrasLiquidacionExportableBeanRequest request) {

		try {

			return new ReporteBeanResponse(
	                CtrlResponseWSEnum.WS_OK.getCodRet(),
	                CtrlResponseWSEnum.WS_OK.getMsgRet(),
	                new Gson().toJson(this.consultaCifrasLiquidacionExportable(request)));
	    } catch (Exception e) {
	        LOGGER.error("ERROR UBICACION   :" + CifrasLiquidacionImpl.class.getCanonicalName());
	        LOGGER.error("ERROR METODO      :" + "exportableCifrasLiquidacion");
	        LOGGER.error("ERROR MENSAJE     :" + e.getMessage());
	        LOGGER.error("ERROR DESCRIPCION :" , e);
	
	        FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
	        missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", exportableCifrasLiquidacion");
	        throw new MitWebServiceException(e.getMessage(), missing);
	    } finally {
	    	
	    }
	}

	@Override
	public CifrasLiquidacionExportableBeanResponse consultaCifrasLiquidacionExportable(
			CifrasLiquidacionExportableBeanRequest solicitudCifrasLiquidacionBeanRequest) {
		ValidacionesVO validaciones;
		CifrasLiquidacionVO cifrasLiquidacionVO;
		List<CifrasLiquidacionSolVO> solicitudesEx;
		CifrasLiquidacionExportableBeanResponse response = null;
		CifrasLiquidacionFilter filter= new CifrasLiquidacionFilter();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 

		
        try {
            if (solicitudCifrasLiquidacionBeanRequest == null) {
                return new CifrasLiquidacionExportableBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
                        CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(),
                        "request requerido.");
            }

            if (!(validaciones = solicitudCifrasLiquidacionBeanRequest.validaRequest()).isEstatus()) {
                return new CifrasLiquidacionExportableBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
                        										  CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(),
                        										  validaciones.getMensaje());
            }
           	cifrasLiquidacionVO = cifrasLiquidacionService.consultarCifrasLiquidacion(filter);
           	solicitudesEx = cifrasLiquidacionService.consultaSolicitudesExcuidas();
           	
            response = new CifrasLiquidacionExportableBeanResponse(
                    UtilValidate.isNull(cifrasLiquidacionVO) ? CtrlResponseWSEnum.WS_NO_RECORD.getCodRet() : CtrlResponseWSEnum.WS_OK.getCodRet(),
                    UtilValidate.isNull(cifrasLiquidacionVO) ? CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet() : CtrlResponseWSEnum.WS_OK.getMsgRet(),
                    UtilValidate.isNull(cifrasLiquidacionVO) ? "Consulta no contiene registros con esos parametros" : "Consulta correcta");
           
            if(!UtilValidate.isNull(cifrasLiquidacionVO)) {
            	
            	List<DetalleSieforesBean> detalleSieforeBean = new ArrayList<DetalleSieforesBean>();
                for (DetalleSieforesVO detalleSieforesVO : cifrasLiquidacionVO.getDetalleSieforeVO()) {

                	List<DetalleFondosBean> detalleFondoBean = new ArrayList<DetalleFondosBean>();
                    for (DetalleFondosVO detalleFondoVO : detalleSieforesVO.getDetalleFondosVO()) {
						detalleFondoBean
								.add(new Gson().fromJson(new Gson().toJson(detalleFondoVO), DetalleFondosBean.class));
                    }
                    detalleSieforeBean.add(new DetalleSieforesBean(detalleSieforesVO.getIdSiefore(), detalleSieforesVO.getSiefore(), detalleFondoBean));
                }
                
                List<CifrasLiquidacionSolicitudBean> cargosExcluidos = new ArrayList<CifrasLiquidacionSolicitudBean>();
                CifrasLiquidacionSolicitudBean cifrasLiqExcluidas = new CifrasLiquidacionSolicitudBean();
                for (CifrasLiquidacionSolVO cargosExcluido: solicitudesEx) {
                	cifrasLiqExcluidas.setCuenta(cargosExcluido.getCuenta());
                	cifrasLiqExcluidas.setNss(cargosExcluido.getNss());
                	cifrasLiqExcluidas.setImpote(cargosExcluido.getImporte());
                	cifrasLiqExcluidas.setNoCargo(cargosExcluido.getNoCargo());
                	cifrasLiqExcluidas.setFechaCarga(cargosExcluido.getFechaCarga());
                	cifrasLiqExcluidas.setExcluir(cargosExcluido.getExcluir());
                	cargosExcluidos.add(cifrasLiqExcluidas);
                }
            	response.setFechaFin(solicitudCifrasLiquidacionBeanRequest.getFechaFin());
            	response.setFechaInicio(solicitudCifrasLiquidacionBeanRequest.getFechaInicio());
                response.setDetalleSieforeBean(detalleSieforeBean);
                response.setDetalleSolicitudes(cargosExcluidos);
                
                
            }
            
            return response;

        } catch (Exception e) {
            LOGGER.error("ERROR UBICACION   :" + CifrasLiquidacionImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO      :" + "consultaCifrasLiquidacionExportable");
            LOGGER.error("ERROR MENSAJE     :" + e.getMessage());
            LOGGER.error("ERROR DESCRIPCION :" , e);

            FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
            missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", consultaCifrasLiquidacionExportable");
            throw new MitWebServiceException(e.getMessage(), missing);
        } finally {
            validaciones = null;
            cifrasLiquidacionVO = null;
            solicitudesEx = null;
            response = null;

        }
	}
	
	
}

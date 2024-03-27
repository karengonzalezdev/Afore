package mx.profuturo.nci.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.report.IBasicReportService;
import mx.profuturo.nci.business.report.beans.BasicXLSParamReportBean;
import mx.profuturo.nci.business.service.ICifrasControlClienteService;
import mx.profuturo.nci.business.service.IClientesService;
import mx.profuturo.nci.business.service.impl.WSPortTypeFactory;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.DictamenRechazadaRespuestaVO;
import mx.profuturo.nci.business.vo.ReporteCifrasControlVO;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.vo.ResultadoDictamenRechazadoVO;
import mx.profuturo.nci.business.vo.SolicitudReclasificacionVO;
import mx.profuturo.nci.web.beans.CifrasControlClienteFilterBean;
import mx.profuturo.nci.web.service.ICifrasControlClienteWebService;


@Service("cifrasControlClienteWebService")
public class CifrasControlClienteWebServiceImpl implements ICifrasControlClienteWebService {

	@Autowired
	ICifrasControlClienteService cifrasControlClienteService;
	
	@Autowired
	IBasicReportService basicReportService;
	
	@Autowired
	WSPortTypeFactory wsPortTypeFactory;

	@Autowired
	IClientesService clientesService;
	
	
	
	
	public List<CatalogoVO> consultarOrigen() throws MitBusinessException {
		
		return cifrasControlClienteService.consultaOrigen();
	}

	public List<CatalogoVO> consultarEstatus() throws MitBusinessException {
		
		return cifrasControlClienteService.consultaEstatus();
	}
	
	public List<CatalogoVO> consultarMotivos() throws MitBusinessException {
		
		return cifrasControlClienteService.consultaMotivos();
	}

	public List<SolicitudReclasificacionVO> consultaSolicitudes(CifrasControlClienteFilterBean filtro) throws MitBusinessException {
		
		SolicitudReclasificacionVO parametros= new SolicitudReclasificacionVO();
		
		
		long cuenta = (filtro.getCuenta() != null) ? (Long.valueOf(filtro.getCuenta())): (0);
		parametros.setCuenta((cuenta != 0) ? (cuenta) : (null));
		

		parametros.setNss(filtro.getNss());
		parametros.setCurp(filtro.getCurp());
		parametros.setFechaInicio(filtro.getFechaInc());
		parametros.setFechaFin(filtro.getFechaFin());
		
		
		int origen = (filtro.getOrigen() != null) ? ( Integer.parseInt(filtro.getOrigen()) ): (0);
		parametros.setOrigen((origen != 0) ? (origen) : (0));
		
		int estatus = (filtro.getEstatus() != null) ? ( Integer.parseInt(filtro.getEstatus()) ): (0);
		parametros.setEstatus((estatus != 0) ? (estatus) : (0));
		
		return cifrasControlClienteService.consultaSolicitudes(parametros);
	}

	public boolean actializarEstatus(String clave, int estatus) throws MitBusinessException {
		
		boolean response=false;
		
		try {
			
			return response = cifrasControlClienteService.actializarEstatus(clave, estatus);
			
		}catch (Exception ex) {

			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al actualizar cifras control cliente: "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "actializarEstatus()" }, ex));
			throw mitBusinessException;	
		}
		
	}

	public List<SolicitudReclasificacionVO> consultaSolicitudesDetalle(SolicitudReclasificacionVO filtro)
			throws MitBusinessException {

		return cifrasControlClienteService.consultaSolicitudesDetalle(filtro);
	}
	
	public List<SolicitudReclasificacionVO> consultaSolicitudesDetalleCargo(SolicitudReclasificacionVO filtro)
			throws MitBusinessException {

		return cifrasControlClienteService.consultaSolicitudesDetalleCargo(filtro);
	}

	public List<ReporteCifrasControlVO> consultaReporte(SolicitudReclasificacionVO filtro) throws MitBusinessException {
		
		try{
			
			List<ReporteCifrasControlVO> datos = cifrasControlClienteService.consultaReporte(filtro);
			return datos;
		
		} catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al consultar datos para reporte cifras control cliente: "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "consultaReporte()" }, ex));
			throw mitBusinessException;			
		}

		
	}
	
	public ReporteVO generarReporte(List<ReporteCifrasControlVO> datos) throws MitBusinessException {
		ReporteVO repoBean = null;
		
		try{
			
			BasicXLSParamReportBean<ReporteCifrasControlVO> paramBean = new BasicXLSParamReportBean<ReporteCifrasControlVO>();
			paramBean.setDataSource(datos);
			paramBean.setJrxmlReportPath("reports/reporteCifrasControlCliente.jrxml");
			paramBean.setOutputReportFileName("CifrasControlCliente");
			repoBean = 	basicReportService.crearReporteXls(paramBean);
			
			 return repoBean;
			 
		} catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar reporte Envio a CIF: "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "generaReporte()" }, ex));
			throw mitBusinessException;			
		}

		
	}

	public DictamenRechazadaRespuestaVO dictamenRechazada( ResultadoDictamenRechazadoVO datos ) throws MitBusinessException {
		try {
			return clientesService.aplicaDictamenRechazado(datos);
		}catch( Exception ex ){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar reporte Envio a CIF: "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "generaReporte()" }, ex));
			throw mitBusinessException;		
		}
	}

	
	public String totalDiversifica(SolicitudReclasificacionVO filtro) throws MitBusinessException {

		return cifrasControlClienteService.totalDiversifica(filtro);
	}

	public String totalCargo(SolicitudReclasificacionVO filtro) throws MitBusinessException {

		return cifrasControlClienteService.totalCargo(filtro);
	}
	

}

package mx.profuturo.nci.business.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.ICifrasControlClienteService;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ReporteCifrasControlAbonoVO;
import mx.profuturo.nci.business.vo.ReporteCifrasControlCargoVO;
import mx.profuturo.nci.business.vo.ReporteCifrasControlVO;
import mx.profuturo.nci.business.vo.SolicitudReclasificacionVO;
import mx.profuturo.nci.persistence.CifrasControlClientePersistence;

@Service("cifrasControlClienteService")
public class CifrasControlClienteImpl implements ICifrasControlClienteService {

	
	@Autowired
	CifrasControlClientePersistence cifrasControlClientePersistence;


	public List<SolicitudReclasificacionVO> consultaSolicitudes(SolicitudReclasificacionVO filtro) throws MitBusinessException {
			
		List<SolicitudReclasificacionVO> resultados =  cifrasControlClientePersistence.consultaSolicitudes(filtro);
		List<SolicitudReclasificacionVO> response =  new ArrayList<SolicitudReclasificacionVO>();
		
				
		for(SolicitudReclasificacionVO nuevos: resultados) {
			
		String importe = NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(nuevos.getImporte());
		String monto=NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(nuevos.getMonto());
		nuevos.setImporteString(importe);
		nuevos.setMontoString(monto);
		response.add(nuevos);
		
		}
		
			return response;
	}

	public List<CatalogoVO> consultaEstatus() throws MitBusinessException {
		return cifrasControlClientePersistence.consultaEstatus();
	}
	
	public List<CatalogoVO> consultaMotivos() throws MitBusinessException {
		return cifrasControlClientePersistence.consultaMotivos();
	}
	
	public boolean actializarEstatus(String clave, int estatus) throws MitBusinessException {
		
		boolean response=false; 
		SolicitudReclasificacionVO parametrso = new SolicitudReclasificacionVO();
		
		try {
			parametrso.setClave(clave);
			parametrso.setEstatus(estatus);
			
				if(estatus == 512 || estatus == 516) {
					return response = cifrasControlClientePersistence.actualizarEstatus(parametrso);
				}
				
		}catch (Exception e) {
			
		}
		return response;
		
			
	}

	public List<CatalogoVO> consultaOrigen() throws MitBusinessException {
		return cifrasControlClientePersistence.consultaOrigen();
	}

	public List<SolicitudReclasificacionVO> consultaSolicitudesDetalle(SolicitudReclasificacionVO filtro)
			throws MitBusinessException {
		
		List<SolicitudReclasificacionVO> resultadosDetalle =  cifrasControlClientePersistence.consultaSolicitudesDetalle(filtro.getClave());
		List<SolicitudReclasificacionVO> responseDetalle =  new ArrayList<SolicitudReclasificacionVO>();
		
		
		for(SolicitudReclasificacionVO nuevos: resultadosDetalle) {
			
		String importeDetalle = NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(nuevos.getPesos());
		nuevos.setPesosString(importeDetalle);
		
		
		responseDetalle.add(nuevos);
		
		}
	
			return responseDetalle;
		
		
	}
	
	public List<SolicitudReclasificacionVO> consultaSolicitudesDetalleCargo(SolicitudReclasificacionVO filtro)
			throws MitBusinessException {
		
		List<SolicitudReclasificacionVO> resultadosDetalle =  cifrasControlClientePersistence.consultaSolicitudesDetalleCargo(filtro.getClave());
		List<SolicitudReclasificacionVO> responseDetalle =  new ArrayList<SolicitudReclasificacionVO>();
		
		
		for(SolicitudReclasificacionVO nuevos: resultadosDetalle) {
			
		String importeDetalle = NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(nuevos.getPesos());
		nuevos.setPesosString(importeDetalle);
		
		
		responseDetalle.add(nuevos);
		
		}
	
			return responseDetalle;
		
		
	}

	public List<ReporteCifrasControlVO> consultaReporte(SolicitudReclasificacionVO filtro) throws MitBusinessException {
		
		List<ReporteCifrasControlVO> response = new ArrayList<ReporteCifrasControlVO>();
		List<ReporteCifrasControlVO> responseConsulta =cifrasControlClientePersistence.consultaReporte(filtro);
		List<SolicitudReclasificacionVO> responseConsultaCargos=null;
		List<SolicitudReclasificacionVO> responseConsultaAbonos=null;
		ReporteCifrasControlVO addList=null;
		Integer numeracion = 0;
		
		try {
			

			
			for(ReporteCifrasControlVO solicitudes : responseConsulta) {
				
				
				numeracion = numeracion+1;
				
				addList = new ReporteCifrasControlVO();
				responseConsultaCargos = cifrasControlClientePersistence.consultaSolicitudesDetalleCargo(solicitudes.getClaveSolicitud());
				responseConsultaAbonos = cifrasControlClientePersistence.consultaSolicitudesDetalle(solicitudes.getClaveSolicitud());	
				List<ReporteCifrasControlAbonoVO> listAbonos = new ArrayList<ReporteCifrasControlAbonoVO>();
				List<ReporteCifrasControlCargoVO> listCargos = new ArrayList<ReporteCifrasControlCargoVO>();
				
				for(SolicitudReclasificacionVO resultadosAbono : responseConsultaAbonos) {
					
					ReporteCifrasControlAbonoVO abono = new ReporteCifrasControlAbonoVO();
					
					abono.setFondoEntrada(resultadosAbono.getSubCuenta());
					abono.setImporteAbono(resultadosAbono.getPesos());
					abono.setPorcentajeString((resultadosAbono.getPorcentaje().toString())+"%");
					listAbonos.add(abono);
					
				}
				
				for(SolicitudReclasificacionVO resultadosCargo : responseConsultaCargos) {
					
					ReporteCifrasControlCargoVO cargo = new ReporteCifrasControlCargoVO();
					
					cargo.setFondoSalida(resultadosCargo.getSubCuenta());
					cargo.setImporteCargo(resultadosCargo.getPesos());
					cargo.setPorcentajeString((resultadosCargo.getPorcentaje().toString())+"%");
					listCargos.add(cargo);
					
				}
				
				addList.setNumeracion(numeracion);
				addList.setFechaCaptura(formatearFecha(solicitudes.getFechaCaptura()));
				addList.setFechaLiquidacion(formatearFecha(solicitudes.getFechaLiquidacion()));
				addList.setFolio(solicitudes.getFolio());
				addList.setClaveSolicitud(solicitudes.getClaveSolicitud());
				addList.setEstatusSolicitud(solicitudes.getEstatusSolicitud());
				addList.setFechaUltimoEstatus(solicitudes.getFechaUltimoEstatus());
				addList.setUsuarioActualizacion(solicitudes.getUsuarioActualizacion());
				addList.setNumeroCuenta(solicitudes.getNumeroCuenta());
				addList.setCurp(solicitudes.getCurp());
				addList.setNss(solicitudes.getNss());
				addList.setRfc(solicitudes.getNss());
				addList.setNombre(solicitudes.getNombre());
				addList.setOrigenCaptura(solicitudes.getOrigenCaptura());
				addList.setUsuarioCaptura(solicitudes.getUsuarioCaptura());
				addList.setTipoMonto(solicitudes.getTipoMonto());
				addList.setListabono(listAbonos);
				addList.setListCargo(listCargos);
				response.add(addList);
				
			}
		
		return response;
			
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al consultar datos para reporte cifras control cliente: "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "consultaReporte()" }, ex));
			throw mitBusinessException;	
		}
		finally {
			responseConsulta =null;
			responseConsultaCargos=null;
			responseConsultaAbonos= null;
			addList = null;
			numeracion = null;
		}
		
		
		
	}
	
	
	public String formatearFecha(String fechaEntrada) throws MitBusinessException{
		String[] fecha= null;
		String[] fechaHora= null;
		
		String nuevaFecha = null;
		
		try {
			if(fechaEntrada != null) {
				fecha= fechaEntrada.split("-");
				fechaHora= fecha[2].split(" ");
				
				nuevaFecha = fechaHora[0]+"/"+fecha[1]+"/"+fecha[0]+" "+fechaHora[1];
			}
			 
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al formatear fecha: "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "formatearFecha()" }, ex));
			throw mitBusinessException;	
		}

		finally {
			fecha= null;
			fechaHora= null;
		}
	
		return nuevaFecha;		
		
		
	}

	public String totalDiversifica(SolicitudReclasificacionVO filtro) throws MitBusinessException {
		
		List<SolicitudReclasificacionVO> resultadosDetalle =  cifrasControlClientePersistence.consultaSolicitudesDetalle(filtro.getClave());
		
		BigDecimal totalReclasificacion = new BigDecimal("0");
		String importeDetalleTotal = null;
		
		for(SolicitudReclasificacionVO nuevos: resultadosDetalle) {
			

			totalReclasificacion = totalReclasificacion.add(nuevos.getPesos());
	
		
		}
			
		return importeDetalleTotal = NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(totalReclasificacion);
	}
	
	public String totalCargo(SolicitudReclasificacionVO filtro) throws MitBusinessException {
		
		List<SolicitudReclasificacionVO> resultadosDetalle =  cifrasControlClientePersistence.consultaSolicitudesDetalleCargo(filtro.getClave());
		
		BigDecimal totalReclasificacion = new BigDecimal("0");
		String importeDetalleTotal = null;
		
		for(SolicitudReclasificacionVO nuevos: resultadosDetalle) {
			

			totalReclasificacion = totalReclasificacion.add(nuevos.getPesos());
		
		}
			
		return importeDetalleTotal = NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(totalReclasificacion);
	}





}

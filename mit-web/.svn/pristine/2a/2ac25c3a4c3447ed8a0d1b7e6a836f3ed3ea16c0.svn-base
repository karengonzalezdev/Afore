package mx.profuturo.nci.web.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.report.IReportesService;
import mx.profuturo.nci.business.report.beans.ConsultaAforeMovilDataReportBean;
import mx.profuturo.nci.business.service.IAforeMovilService;
import mx.profuturo.nci.business.service.IConciliacionService;
import mx.profuturo.nci.business.service.IGeneraFolioService;
import mx.profuturo.nci.business.service.IParamCatConfigService;
import mx.profuturo.nci.business.vo.ConciliacionVO;
import mx.profuturo.nci.business.vo.ParamCataConfigVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.wrapped.AforeMovilFilter;
import mx.profuturo.nci.web.beans.ConsultaAforeMovilBean;
import mx.profuturo.nci.web.beans.ConsultaDetalleAforeMovilBean;
import mx.profuturo.nci.web.beans.GenericCatalogoBean;
import mx.profuturo.nci.web.service.IMonitoreoAforeMovilWebService;


@Service("monitoreoAforeMovilWebService")
public class MonitoreoAforeMovilWebServiceImpl implements
IMonitoreoAforeMovilWebService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitoreoAforeMovilWebServiceImpl.class);
	
	@Autowired IAforeMovilService aforeMovilService;
	
	@Autowired IReportesService reporteService;
	
	@Autowired IParamCatConfigService paramCatConfigService;

	@Autowired IGeneraFolioService generaFolioService;
	
	@Autowired IConciliacionService conciliacionService;
	
	public List<ConsultaAforeMovilBean> consultarMovimientosAforeMovil(String filtroVal, String filtro, 
			Date fecInicio, Date fecFin) throws MitBusinessException{
		List<ConsultaAforeMovilBean> reg = new ArrayList<ConsultaAforeMovilBean>();
		
		AforeMovilFilter filter = new AforeMovilFilter();
		if(filtroVal.equals("NSS")){
			filter.setNss(filtro);
		}			
		if(filtroVal.equals("CURP")){
			filter.setCurp(filtro);
		}
		filter.setFechaInicio(fecInicio != null ? fecInicio : null);
		filter.setFechaFin(fecFin != null ? fecFin : null);
		
		List<ConciliacionVO> lstRegistros = aforeMovilService.consultar(filter);
		Map<String,ConsultaAforeMovilBean> regs = new HashMap<String,ConsultaAforeMovilBean>();
		
		for(ConciliacionVO concilVo : lstRegistros){
			ConsultaAforeMovilBean beanPrincipal;
			 List<ConsultaDetalleAforeMovilBean> movsPrincipal = new ArrayList<ConsultaDetalleAforeMovilBean>();
			 
			ConsultaAforeMovilBean consulBean = new ConsultaAforeMovilBean();
			
			consulBean.setIdConciliacion(concilVo.getIdConciliacion() != null ? concilVo.getIdConciliacion() : null);
			consulBean.setCurp(concilVo.getCurp() != null ? concilVo.getCurp() : null);
			consulBean.setNombre((concilVo.getNombreCliente() != null ? concilVo.getNombreCliente() : "")+" "+
								 (concilVo.getApellidoClientePaterno() != null ? concilVo.getApellidoClientePaterno() : "")+" "+
					             (concilVo.getApellidoClienteMaterno() != null ? concilVo.getApellidoClienteMaterno() : ""));
			consulBean.setNombreCli(concilVo.getNombreCliente());
			consulBean.setApPaternoCli(concilVo.getApellidoClientePaterno());
			consulBean.setApMaternoCli(concilVo.getApellidoClienteMaterno());
			consulBean.setTipoCliente(concilVo.getCorreoElectronico() != null ? concilVo.getCorreoElectronico() : null);
			consulBean.setImporteAcumulado(concilVo.getImporte() != null ? concilVo.getImporte() : (new BigDecimal("0")));
			consulBean.setPrimerAportacion(concilVo.getFechaPagoApovol() != null ? concilVo.getFechaPagoApovol() : null);
			consulBean.setDiasPrimerAportacion(concilVo.getNumeroEmpleado() != null ? concilVo.getNumeroEmpleado() : null);
			consulBean.setCuenta(concilVo.getNumeroCuentaIndividual() != null ? concilVo.getNumeroCuentaIndividual().toString() : "");
			consulBean.setNumCtaInvdual(concilVo.getNumeroCuentaIndividual() !=null ? concilVo.getNumeroCuentaIndividual().toString() : "");
			
			consulBean.setRfc(concilVo.getRfc() != null ? concilVo.getRfc() : "" );
			consulBean.setNss(concilVo.getNss() != null ? concilVo.getNss().toString() : "");
			consulBean.setCorreo(concilVo.getCorreoElectronico() != null ? concilVo.getCorreoElectronico() : "");
			consulBean.setCelular(concilVo.getCelular() != null ? concilVo.getCelular().toString() : "");
			
			
			if(concilVo.getEstatusTramite()!=null){
				consulBean.setEstatus(new GenericCatalogoBean(concilVo.getEstatusTramite().getId(),concilVo.getEstatusTramite().getValor()));
			}else{
				consulBean.setEstatus(new GenericCatalogoBean(new Short("0"),""));
			}
			consulBean.setSelect(false);
			
			String key = consulBean.getCurp()+consulBean.getNombre()+consulBean.getEstatus().getValor();
			if(regs.containsKey(key)){
				beanPrincipal = regs.get(key);
				beanPrincipal.setImporteAcumulado(beanPrincipal.getImporteAcumulado().add(consulBean.getImporteAcumulado()));
				movsPrincipal = beanPrincipal.getMovimientos();
				
				if(beanPrincipal.getPrimerAportacion()!=null){
					if(consulBean.getPrimerAportacion()!=null){
						if(beanPrincipal.getDiasPrimerAportacion()<consulBean.getDiasPrimerAportacion()){
							beanPrincipal.setPrimerAportacion(consulBean.getPrimerAportacion());
							beanPrincipal.setDiasPrimerAportacion(consulBean.getDiasPrimerAportacion());
						}
					}
				}else{
					beanPrincipal.setPrimerAportacion(consulBean.getPrimerAportacion());
					beanPrincipal.setDiasPrimerAportacion(consulBean.getDiasPrimerAportacion());
				}
			}else{
				beanPrincipal = consulBean;
			}
			
			ConsultaDetalleAforeMovilBean det = new ConsultaDetalleAforeMovilBean();
			det.setImporte(concilVo.getImporte() != null ? concilVo.getImporte() : null);
			det.setFechaDeposito(concilVo.getFechaCargaArchivo() !=null ? concilVo.getFechaCargaArchivo() : null);
			det.setFechaConciliacion(concilVo.getFechaValorApovol() != null ? concilVo.getFechaValorApovol() : null);
			det.setIdConciliacion(concilVo.getIdConciliacion() != null ? concilVo.getIdConciliacion() : null);
			
			movsPrincipal.add(det);
			beanPrincipal.setMovimientos(movsPrincipal);
			
			regs.put(key, beanPrincipal);
		}
		if(regs.size()>0){
			Map<String,ConsultaAforeMovilBean> treeMap = new TreeMap<String,ConsultaAforeMovilBean>(regs);
			
			Set<Entry<String,ConsultaAforeMovilBean>> s = treeMap.entrySet();
			Iterator<Entry<String,ConsultaAforeMovilBean>> it = s.iterator();
			int registro = 1;
			while ( it.hasNext() ) {
				Map.Entry entry = (Map.Entry) it.next();
				
				ConsultaAforeMovilBean rego = (ConsultaAforeMovilBean) entry.getValue();
				rego.setRowId(registro);
				reg.add(rego);
				
				registro++;
			}			
		}
		return reg;
	}
	
	public ReporteVO generaReporteDepositosAforeMovil(List<ConsultaAforeMovilBean> lstAforeMovil) throws MitBusinessException{
		ReporteVO reporte = new ReporteVO();
		
		List<ConsultaAforeMovilDataReportBean> dataReportBean = convert(lstAforeMovil);
		reporte = reporteService.generaReporteDepositosAforeMovil(dataReportBean);
		
		return reporte;
	}
	
	private List<ConsultaAforeMovilDataReportBean> convert(List<ConsultaAforeMovilBean> vBeans) {
		
		List<ConsultaAforeMovilDataReportBean> reporteBeans= new ArrayList<ConsultaAforeMovilDataReportBean>();
		Integer consecutivo=1;
		
		for(ConsultaAforeMovilBean vBean:vBeans){
			if(vBean.getEstatus().getId()==968){
			List<ConsultaDetalleAforeMovilBean> regs = vBean.getMovimientos();
			for(int i=0;i<regs.size();i++){
				ConsultaDetalleAforeMovilBean movs = regs.get(i);
				
				ConsultaAforeMovilDataReportBean rB = new ConsultaAforeMovilDataReportBean();
				rB.setConsecutivo(consecutivo);
				rB.setCurp(vBean.getCurp());
				rB.setNombre(vBean.getNombre());
				rB.setCuenta(vBean.getCuenta());
				rB.setTipoCliente(vBean.getTipoCliente());

				if(i==0){
					rB.setPrimerAportacion(vBean.getPrimerAportacion());
				}
				rB.setFechaAportacion(movs.getFechaDeposito());

				rB.setFechaConciliacion(movs.getFechaConciliacion());
				if(movs.getFechaDeposito()!=null){
					Calendar fecAct = Calendar.getInstance();
					fecAct.set(Calendar.HOUR_OF_DAY, 0);
					fecAct.set(Calendar.MINUTE, 0);
					fecAct.set(Calendar.SECOND, 0);
					fecAct.set(Calendar.MILLISECOND, 0);
					
					Calendar fecAnt = Calendar.getInstance();
					fecAnt.setTime(movs.getFechaDeposito());
					fecAnt.set(Calendar.HOUR_OF_DAY, 0);
					fecAnt.set(Calendar.MINUTE, 0);
					fecAnt.set(Calendar.SECOND, 0);
					fecAnt.set(Calendar.MILLISECOND, 0);
  			        Short plazoRes = (short) ((fecAct.getTime().getTime() - fecAnt.getTime().getTime())  / (1000 * 60 * 60 * 24));
					rB.setDiasPrimerAportacion(plazoRes.intValue());
				}else{
					rB.setDiasPrimerAportacion(0);
				}
				rB.setEstatus(vBean.getEstatus().getValor());
				rB.setImporte(movs.getImporte());
				
				if(i==(regs.size()-1)){
					rB.setImporteAcumulado(vBean.getImporteAcumulado());
				}
				reporteBeans.add(rB);
				consecutivo++;
				}
			}
		}
		return reporteBeans;
	}
	
	public boolean rechazarMovimientos(List<ConsultaAforeMovilBean> beans, String usuario) 
			throws MitBusinessException{
		Integer regs = 0;
		for(ConsultaAforeMovilBean bean : beans){
			AforeMovilFilter filter = new AforeMovilFilter();
			filter.setNombre(bean.getNombreCli());
			filter.setApPaterno(bean.getApPaternoCli());
			filter.setApMaterno(bean.getApMaternoCli());
			filter.setCurp(bean.getCurp());
			filter.setUsuarioAct(usuario);
			if(bean.getEstatus()!=null && bean.getEstatus().getId()>0){
				filter.setEstatus(bean.getEstatus().getId());
			}
			
			regs = regs + aforeMovilService.rechazarAforeConciliacion(filter);
		}
		
		if(regs>0){
			return true;
		}
		else
			return false;
	}
	
	public String obtenerParamCatConfig(Short idConfig) throws MitBusinessException{
		String reg = "";
		ParamCatalogoVO param = new ParamCatalogoVO();
		param.setIdCatalogo(idConfig);
		
		List<ParamCataConfigVO> resConsulta = paramCatConfigService.consultar(param);
		for(ParamCataConfigVO vo : resConsulta){
			reg = vo.getValor();
		}
		
		return reg;
	}
	
	public String obtenerFolioNuevoAforeMovil(Integer proceso, Integer subProceso, Integer canal, Short estatus, 
			                                  String usuario) throws MitBusinessException{
		return generaFolioService.generaFolioAforeMovil(proceso, subProceso, canal, estatus, usuario);
	}
	
	public int insertaRegistroConciliacion(String folio, Short origen, Long numCtaInd, BigDecimal importe, String nombre,
                                           String apPaterno, String apMaterno, String curp, String rfc, Long nss, String correo,
                                           Long celular, String usuario, BigInteger subctaApovol) throws MitBusinessException{
		return generaFolioService.generaRegistroConciliacion(folio, origen, numCtaInd, importe, nombre, apPaterno, apMaterno, curp, 
				                                             rfc, nss, correo, celular, usuario, subctaApovol);
	}
	
	public boolean invocaServicioLiberar(List<String> lstCurp, String usuario) throws MitBusinessException{
		return generaFolioService.invocaServicioLiberar(lstCurp, usuario);
			
	}
	
	public int actualizaStatusConciliacion(Integer idConciliacion, Short estatus, String usuario) throws MitBusinessException{
		return conciliacionService.actualizarEstatusConciliacion(idConciliacion, estatus, usuario) ;
	}
}
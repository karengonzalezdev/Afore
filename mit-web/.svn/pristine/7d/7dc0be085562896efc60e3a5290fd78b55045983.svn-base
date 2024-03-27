/**
 * 
 */
package mx.profuturo.nci.web.controller;

import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ESTATUS_FINALIZADO;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ETAPA_NCI_OPERACION;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_RESULTADO_SIN_HALLAZGOS;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_SUBETAPA_CONCILIACION;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_SUBPROCESO_APORTACION_VOLUNTARIA;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IBitacoraProcesoService;
import mx.profuturo.nci.business.service.ICatalogosService;
import mx.profuturo.nci.business.service.IConciliacionService;
import mx.profuturo.nci.business.service.IOrdenesService;
import mx.profuturo.nci.business.service.impl.GeneracionArchivoDomiServiceImpl;
import mx.profuturo.nci.business.vo.BitacoraProcesoVO;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ConciliacionVO;
import mx.profuturo.nci.business.vo.DiversificacionOrdenesVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.IndicadorVO;
import mx.profuturo.nci.business.vo.OrdenesVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.business.wrapped.OrdenesFilter;
import mx.profuturo.nci.web.beans.ClienteOrdenSpeiBean;
import mx.profuturo.nci.web.beans.FondoBean;
import mx.profuturo.nci.ws.webservice.catalogo.CatalogoBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author ribarrgo
 *
 */
@Controller
public class OrdenSpeiController implements Serializable{
	private static final Logger LOGGER = LoggerFactory.getLogger(OrdenSpeiController.class);
	
	private static final long serialVersionUID = 1L;

	private List<DiversificacionOrdenesVO> lstDiversificacion;
	
	private static Short STATUS_NUEVA_ORDEN = 754;
	private static Short STATUS_NUEVA_ORDEN_CONCILIADA = 755;
	
	@Autowired
	IOrdenesService ordenesService;

	@Autowired
	IConciliacionService conciliacionService;
	
	@Autowired
	ICatalogosService catalogoService;
	
	@Autowired IBitacoraProcesoService bitacoraProcesoService;
	
	/**
	 * 
	 */
	public OrdenSpeiController() {
	}

	public Boolean guardarOrdenSpei(ClienteOrdenSpeiBean clienteSpei, boolean monto) {
		OrdenesVO ordenes = new OrdenesVO();
		ordenes.setImporte(BigDecimal.valueOf(clienteSpei.getOrdenSpei()
				.getImporte()));
		ordenes.setNumCuentaIndividual(new Long(clienteSpei.getNumeroCuenta()));
		if(clienteSpei.getOrdenSpei().getConciliacionVo() != null){
			clienteSpei.getOrdenSpei().getConciliacionVo().setDiversificaciones(null);
		}
		ordenes.setConciliacionVO(clienteSpei.getOrdenSpei().getConciliacionVo());
		ordenes.setUsuarioCreacion(clienteSpei.getOrdenSpei().getUsuarioCreacion());
		
		GenericCatalogoVO cat = new GenericCatalogoVO();
		cat.setId(ordenes.getConciliacionVO() == null ? STATUS_NUEVA_ORDEN : STATUS_NUEVA_ORDEN_CONCILIADA);
		ordenes.setEstatus(cat);
		//ordenes.setNumeroOrden(clienteSpei.getOrdenSpei().getOrden());
		// ordenes.setUsuarioCreacion();
		lstDiversificacion = new ArrayList<DiversificacionOrdenesVO>();
		DiversificacionOrdenesVO div;
		GenericCatalogoVO fondoApovol;
		for (FondoBean fondo : clienteSpei.getOrdenSpei().getDiversificacionSpei()) {
			if(fondo.isSelecion()){
				fondoApovol = new GenericCatalogoVO();
				div = new DiversificacionOrdenesVO();
				fondoApovol.setId(fondo.getId());
				fondoApovol.setValor(fondo.getValor().replaceAll("\\$","").replaceAll("\\%","").replaceAll("\\,", ""));
				div.setFondoApovol(fondoApovol);
				div.setUsuarioCreacion(clienteSpei.getOrdenSpei().getUsuarioCreacion());
				if(monto){
					div.setMonto(new BigDecimal(fondo.getValor().replaceAll("\\$","").replaceAll("\\%","").replaceAll("\\,", "")));
				}else{
					div.setPorcentaje(new Short(new DecimalFormat("#").format(new Double(fondo.getValor().replaceAll("\\$","").replaceAll("\\%","").replaceAll("\\,", "")))));
				}
				lstDiversificacion.add(div);
			}
		}
		ordenes.setDiversificacionesOrdenes(lstDiversificacion);
		try {
			boolean isOk = ordenesService.guardar(ordenes);
			clienteSpei.getOrdenSpei().setOrden(ordenes.getNumeroOrden());
			if(isOk){
				if(ordenes.getConciliacionVO()!=null){
					bitacoraProcesoService.insertar(createBitacoraProcesoVO(ordenes.getConciliacionVO()));
				}
			}
			return isOk;
		} catch (MitBusinessException e) {
			LOGGER.error("ERROR UBICACION",e);
			return false;
		}
	}

	/**
	 * Metodo para consultar las ordenes SPEI
	 * 
	 * @param ordenesFilter
	 * @return
	 * @throws MitBusinessException
	 */
	public List<OrdenesVO> consultarOrdenes(OrdenesFilter ordenesFilter) throws MitBusinessException{
		return this.ordenesService.consultaSelectiva(ordenesFilter);
	}
	
	/**
	 * Metodo para guardan la relacion entre la conciliacion y la orden
	 * 
	 * @param conciliacion
	 * @param orden
	 * @throws MitBusinessException
	 */
	public void guardarConciliciacion(ConciliacionVO conciliacion, OrdenesVO orden) throws MitBusinessException{
		
		conciliacion.setRegistroConciliado(new Short("1"));
		orden.setEstatus(new GenericCatalogoVO(STATUS_NUEVA_ORDEN_CONCILIADA));
		
		conciliacion.setNumeroOrden(orden.getNumeroOrden().toString());
		
		conciliacionService.conciliarOrdenSpei(conciliacion, orden);
		if(conciliacion!=null){
			bitacoraProcesoService.insertar(createBitacoraProcesoVO(conciliacion));
		}
	}
	
	private BitacoraProcesoVO createBitacoraProcesoVO(ConciliacionVO c){
		BitacoraProcesoVO b = new BitacoraProcesoVO();
		b.setFolio(c.getFolio());
		b.setUsuReg(c.getUsuarioActualizacion());
		b.setIdSubproceso(ID_SUBPROCESO_APORTACION_VOLUNTARIA);
		b.setIdResultado(ID_RESULTADO_SIN_HALLAZGOS);
		b.setIdEtapa(ID_ETAPA_NCI_OPERACION);
		b.setIdEstatus(ID_ESTATUS_FINALIZADO);
		b.setIdSubetapa(ID_SUBETAPA_CONCILIACION);
		
		return b;
	}
	/**
	 * Metodo para obtener el catalogo de banco por id
	 * @param idBanco
	 * @return
	 */
	public CatalogoBean consultaBancoById(String idBanco) throws MitBusinessException{
		CatalogoBean bancoBean = new CatalogoBean();
		ParamCatalogoVO param = new ParamCatalogoVO();
		param.setIdCatalogo(new Short(idBanco));
		
		List<CatalogoVO> lstCatalogo = this.catalogoService.consultarLista(param);
		if(lstCatalogo.size() > 0){
			bancoBean.setIdCatCatalogo(lstCatalogo.get(0).getIdCatCatalogo());
			bancoBean.setValor(lstCatalogo.get(0).getValor());
		}
		
		return bancoBean;
	}

	public List<IndicadorVO> agregarInfoCuenta(String cuenta) throws MitBusinessException {
		return ordenesService.agregarInfoCuenta(cuenta);
	}
	
	
	/**
	 * @return the ordenesService
	 */
	public IOrdenesService getOrdenesService() {
		return ordenesService;
	}

	/**
	 * @param ordenesService
	 *            the ordenesService to set
	 */
	public void setOrdenesService(IOrdenesService ordenesService) {
		this.ordenesService = ordenesService;
	}

}
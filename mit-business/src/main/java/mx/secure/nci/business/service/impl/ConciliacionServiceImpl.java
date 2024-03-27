package mx.secure.nci.business.service.impl;

import static mx.secure.nci.business.util.ConstantesCatalogos.ID_ORIGEN_APORTACION_DOMICILIACION;
import static mx.secure.nci.business.util.ConstantesCatalogos.ID_ORIGEN_DOMICILIACION_TRASPASO;
import static mx.secure.nci.business.util.ConstantesCatalogos.ID_ORIGEN_DOMICILIACION_E_SAR;
import static mx.secure.nci.business.util.ConstantesCatalogos.ID_ORIGEN_APORTACION_NOMINA;
import static mx.secure.nci.business.util.ConstantesCatalogos.ID_ORIGEN_APORTACION_RED_COMERCIAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeveris.core.exception.impl.BusinessException;
import com.jeveris.core.persistence.exception.PersistenceException;

import mx.secure.nci.business.exception.ErrorCodeService;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.IBancosService;
import mx.secure.nci.business.service.IConciliacionService;
import mx.secure.nci.business.service.IOrdenesService;
import mx.secure.nci.business.service.ISumConciliacionService;
import mx.secure.nci.business.util.ConstantesCatalogos;
import mx.secure.nci.business.util.NameSpaceBindingProvider;
import mx.secure.nci.business.util.UtilMethod;
import mx.secure.nci.business.util.WebSphereNamespace;
import mx.secure.nci.business.vo.BancosVO;
import mx.secure.nci.business.vo.ConciliacionVO;
import mx.secure.nci.business.vo.DiversificacionConciliacionVO;
import mx.secure.nci.business.vo.DiversificacionOrdenesVO;
import mx.secure.nci.business.vo.FolioBancoVO;
import mx.secure.nci.business.vo.GenericCatalogoVO;
import mx.secure.nci.business.vo.OrdenesVO;
import mx.secure.nci.business.vo.SumConciliacionVO;
import mx.secure.nci.business.wrapped.BancosFilter;
import mx.secure.nci.business.wrapped.ConciliacionFilter;
import mx.secure.nci.business.wrapped.ConciliarTotalesWrapped;
import mx.secure.nci.business.wrapped.SumConciliacionFilter;
import mx.secure.nci.persistence.ConciliacionPersistence;
import mx.secure.nci.persistence.DiversificacionConciliacionPersistence;
import mx.secure.nci.persistence.DiversificacionOrdenesPersistence;
import mx.secure.nci.persistence.SequencesManagerPersistence;

@Service("conciliacionService")
public class ConciliacionServiceImpl implements IConciliacionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConciliacionServiceImpl.class);
	
	private static final String  EXCEPTION_INSERT_CONCILIACION="Numero de registros(Conciliacion) insertados es cero";
	private static final String  EXCEPTION_INSERT_DIVERSI_CONCILIACION="Numero de registros(DiversificacionConciliacion) insertados es cero";
	private static final String  EXCEPTION_UPDATE_CONCILIACION="Numero de registros(Conciliacion) actualizado es cero";
	private static final String  EXCEPTION_UPDATE_DIVERSI_CONCILIACION="Numero de registros(DiversificacionConciliacion) actualizado es cero";

	
	@Autowired
	ConciliacionPersistence conciliacionPersistence;
	
	@Autowired
	SequencesManagerPersistence sequencesManagerPersistence;

	@Autowired
	DiversificacionConciliacionPersistence diversificacionConciliacionPersistence;

	@Autowired
	IBancosService BancosService;

	@Autowired
	ISumConciliacionService sumConciliacionService;
	
	@Autowired
	IOrdenesService ordenesService;
	

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Boolean insertar(ConciliacionVO conciliacionVO) throws MitBusinessException {
		try {
			final int numInsertParent = this.conciliacionPersistence.insert(conciliacionVO);
			
			if(numInsertParent > 0){			
					if(CollectionUtils.isNotEmpty(conciliacionVO.getDiversificaciones())){
						for(DiversificacionConciliacionVO diversificacion : conciliacionVO.getDiversificaciones()){
							diversificacion.setIdConciliacion(conciliacionVO.getIdConciliacion());
							if(diversificacion.getFondosecure() != null && diversificacion.getFondosecure().getId()!=null) {
								final int numInsertChild =	this.diversificacionConciliacionPersistence.insert(diversificacion);
								if(numInsertChild > 0){
									continue;
								}else{
									throw new Exception(EXCEPTION_INSERT_DIVERSI_CONCILIACION);
								}
							}else {
								MitBusinessException mitBusinessException = new MitBusinessException(
										GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION_FONDO_DIVERSIFICACION, "En insertar diversificacion."+diversificacion,
												new Object[] { "ConciliacionServiceImpl", "insertar()" },
												new Exception("Se debe incluir el valor del fondo de aportacion")));

								LOGGER.error(mitBusinessException.getMessage());
								throw mitBusinessException;
							}
						}	
						return true;
					}else{
						return true;
					}
			}else{
				throw new Exception(EXCEPTION_INSERT_CONCILIACION);
			}			
		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En insertar conciliacion",
							new Object[] { "ConciliacionServiceImpl", "insertar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Boolean actualizar(ConciliacionVO conciliacionVO) throws MitBusinessException {
		try {
			final int numUpdateParent = this.conciliacionPersistence.update(conciliacionVO);
			if(numUpdateParent > 0){			
				if(CollectionUtils.isNotEmpty(conciliacionVO.getDiversificaciones())){
					for(DiversificacionConciliacionVO diversificacion : conciliacionVO.getDiversificaciones()){
						if(diversificacion.getFondosecure() != null && diversificacion.getFondosecure().getId()!=null) {
							diversificacion.setIdConciliacion(conciliacionVO.getIdConciliacion());
							if(conciliacionVO.getUsuarioActualizacionFondo() != null) {
								diversificacion.setUsuarioActualizacion(conciliacionVO.getUsuarioActualizacionFondo());
							}else if(conciliacionVO.getUsuarioActualizacion() != null) {
								diversificacion.setUsuarioActualizacion(conciliacionVO.getUsuarioActualizacion());
							}else if(conciliacionVO.getUsuarioCreacion()!=null) {
								diversificacion.setUsuarioActualizacion(conciliacionVO.getUsuarioCreacion());
							}
							final int numUpdateChild =	this.diversificacionConciliacionPersistence.update(diversificacion);
							if(numUpdateChild > 0){
								continue;
							}else{
								if(diversificacion.getUsuarioActualizacion()!=null) {
									diversificacion.setUsuarioCreacion(diversificacion.getUsuarioActualizacion());
								}else {
									diversificacion.setUsuarioCreacion(diversificacion.getUsuarioCreacion());
								}
								diversificacionConciliacionPersistence.insert(diversificacion);
	//								throw new Exception(EXCEPTION_UPDATE_DIVERSI_CONCILIACION);
							}
						}else {
							MitBusinessException mitBusinessException = new MitBusinessException(
									GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION_FONDO_DIVERSIFICACION, "En insertar diversificacion."+diversificacion,
											new Object[] { "ConciliacionServiceImpl", "actualizar()" },
											new Exception("Se debe incluir el valor del fondo de aportacion")));

							LOGGER.error(mitBusinessException.getMessage());
							throw mitBusinessException;
						}
					}	
					return true;
				}else{
					return true;
				}
			}else{
				return false;
//				throw new Exception(EXCEPTION_UPDATE_CONCILIACION);
			}	
		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En actualizar conciliacion:"+ex.getMessage(),
							new Object[] { "ConciliacionServiceImpl", "actualizar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}

	public List<ConciliacionVO> consultar(ConciliacionFilter conciliacionFilter) throws MitBusinessException 
	{
		try 
		{
			return conciliacionPersistence.selectSelectivePantalla(conciliacionFilter);
		} 
		catch (PersistenceException ex) 
		{
			throw new MitBusinessException(ex.getExceptionDetails());
		} 
		catch (Exception ex) 
		{
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar conciliacion",
							new Object[] { "ConciliacionServiceImpl", "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
	
	public List<ConciliacionVO> consultarBasica(ConciliacionFilter conciliacionFilter) throws MitBusinessException 
	{
		try 
		{
			return conciliacionPersistence.selectSelective(conciliacionFilter);
		} 
		catch (PersistenceException ex) 
		{
			throw new MitBusinessException(ex.getExceptionDetails());
		} 
		catch (Exception ex) 
		{
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar conciliacion",
							new Object[] { "ConciliacionServiceImpl", "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
	
	
	public Integer consultarCuantosRegistros( ConciliacionFilter conciliacionFilter) throws MitBusinessException {
		try {
			return conciliacionPersistence.selectCount(conciliacionFilter);
		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar conciliacion",
							new Object[] { "ConciliacionServiceImpl", "consultar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}


	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public boolean conciliarTotales(String usuario,String folioConciliacion) throws BusinessException {
		boolean ejecutado = false;
		try {
			BancosFilter bancosFilter = new BancosFilter();
			bancosFilter.setRegConciliado((short) 0);
			List<BancosVO> listaBancos = BancosService.consultar(bancosFilter);
			
			SumConciliacionFilter sumConciliacionFilter = new SumConciliacionFilter();
			sumConciliacionFilter.setRegConciliado((short) 0);
			List<SumConciliacionVO> listaSumConcilia = sumConciliacionService.consultarSumConciliacionAConciliar(sumConciliacionFilter);
			
			ConciliarTotalesWrapped conciliacionFinal = this.conciliarTotales(listaBancos, listaSumConcilia, usuario,folioConciliacion);
			List<ConciliacionVO> conciliaciones = conciliacionFinal.getConciliaciones(); 
			List<BancosVO> bancos = conciliacionFinal.getBancos();
			List<SumConciliacionVO> SumConcilia = conciliacionFinal.getSumConcilia();
//			if(!conciliaciones.isEmpty() && !bancos.isEmpty() && !SumConcilia.isEmpty()){
			if(bancos != null && SumConcilia != null){
				BancosService.actualizarConciliado(conciliacionFinal.getBancos());
				sumConciliacionService.actualizarSumConciliacionConciliado(conciliacionFinal.getSumConcilia());
			}
			if(conciliaciones != null ){
				actualizarConciliado(conciliacionFinal.getConciliaciones());
			}
			ejecutado = true;
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En conciliar totales",
							new Object[] { "ConciliacionServiceImpl", "conciliarTotales()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
		return ejecutado;
	}

	public int actualizarConciliado(List<ConciliacionVO> conciliacionVO) throws BusinessException {
		int updated=0;
		//int vez = 0;
		String strFolio = "";
		try {
			for (ConciliacionVO concilia : conciliacionVO) {
				//vez++;
				//System.out.println("::::actualizando el numero:::" + vez + " :::de::: " + conciliacionVO.size() + " ::::del folio::" + concilia.getFolio());
				if(concilia.getFolio() != null) {
					if(!strFolio.equals(concilia.getFolio())) {
						updated = conciliacionPersistence.actualizarConciliado(concilia);

					}
					strFolio = concilia.getFolio();					
				}else {
					updated = conciliacionPersistence.actualizarConciliado(concilia);
				}
			}
			return updated;
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En actualizar conciliacion",
							new Object[] { "ConciliacionServiceImpl", "actualizar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

	private ConciliarTotalesWrapped conciliarTotales(final List<BancosVO> listaBancos,
			final List<SumConciliacionVO> listaSumConcilia, final String usuario, String folioConciliacion) throws MitBusinessException {
		ConciliarTotalesWrapped conciliarTotales = new ConciliarTotalesWrapped();
		List<ConciliacionVO> listaConciliacion = new ArrayList<ConciliacionVO>();
		List<BancosVO> listaBancosCon = new ArrayList<BancosVO>();
		List<SumConciliacionVO> listaSumConciliaCon = new ArrayList<SumConciliacionVO>();
		//int vez = 1;
		for (BancosVO bancos : listaBancos) {
			//System.out.println("::::BANCO:::::" + vez + ":::::"+ bancos.getFolio() + "::::" + bancos.getImporte() );
			for (SumConciliacionVO sumConcilia : listaSumConcilia) {
				boolean isEqualsImporteEmpresa =sumConcilia.getImporte().equals(bancos.getImporte())
						&& sumConcilia.getEmpresa()!=null && sumConcilia.getEmpresa().getId()!=null
						&& bancos.getEmpresa()!=null 
						&& sumConcilia.getEmpresa().getId().equals(bancos.getEmpresa().getId())
						&& sumConcilia.getRegConciliado() == 0
						&& bancos.getRegConciliado() == 0;
				
				boolean isEqualOrigenes =false;
				
				//System.out.println(":::FOP:::" + sumConcilia.getImporte());
				
				if(ID_ORIGEN_DOMICILIACION_TRASPASO.equals(sumConcilia.getOrigen().getId())
					|| ID_ORIGEN_DOMICILIACION_E_SAR.equals(sumConcilia.getOrigen().getId())
					|| ID_ORIGEN_APORTACION_DOMICILIACION.equals(sumConcilia.getOrigen().getId())){
					boolean isCveBancoDomi = false;
					if(bancos.getClaveBanco() != null && bancos.getClaveBanco().getId() != null && sumConcilia.getDetalleOrigen() != null && sumConcilia.getDetalleOrigen().getId() != null)
						isCveBancoDomi =bancos.getClaveBanco().getId().equals(sumConcilia.getDetalleOrigen().getId());
					
					isEqualOrigenes =ID_ORIGEN_APORTACION_DOMICILIACION.equals(bancos.getOrigenAportacion().getId())
					&& (ID_ORIGEN_DOMICILIACION_TRASPASO.equals(sumConcilia.getOrigen().getId())
						|| ID_ORIGEN_DOMICILIACION_E_SAR.equals(sumConcilia.getOrigen().getId())
						||ID_ORIGEN_APORTACION_DOMICILIACION.equals(sumConcilia.getOrigen().getId()))						
					&& isEqualsImporteEmpresa
					&& isCveBancoDomi;
				}else{
					isEqualOrigenes =sumConcilia.getOrigen().getId().equals(bancos.getOrigenAportacion().getId())
					&& isEqualsImporteEmpresa;
				}

				if (isEqualOrigenes) {
					ConciliacionFilter conciliacionFilter = new ConciliacionFilter();
					
					conciliacionFilter.setOrigenAportacion(sumConcilia.getOrigen());
//					conciliacionFilter.setImporte(sumConcilia.getImporte());
					conciliacionFilter.setFolio(sumConcilia.getFolio());
					conciliacionFilter.setRegistroConciliado((short) 0);
					//System.out.println("::FOP:: Voy a consultar el folio::" + sumConcilia.getFolio());
					//System.out.println("::FOP:: Voy a consultar el OrApo::" + sumConcilia.getOrigen());
					//Un archivo de domiciliacion normal puede traer domiciliaciones y domiciliaciones esar, osea que aparecen con un mismo folio
					List<ConciliacionVO> conciliacionSinEmpresa =new ArrayList<ConciliacionVO>();
					//obtiene las domiciliaciones normales
					conciliacionSinEmpresa.addAll(consultar(conciliacionFilter));
					if(conciliacionSinEmpresa.isEmpty()){
					//obtiene tambien los posibles depositos de domiciliacion esar
						conciliacionFilter.getOrigenAportacion().setId(ID_ORIGEN_DOMICILIACION_E_SAR);
						conciliacionSinEmpresa.addAll(consultar(conciliacionFilter));
						//si no encuentra domiciliaciones o domiciliaciones esar es posible que las domiciliaciones sean de domi traspaso que vienen en un archivo diferente
						if(ID_ORIGEN_APORTACION_DOMICILIACION.equals(sumConcilia.getOrigen().getId())){
							if(conciliacionSinEmpresa.isEmpty()){
								conciliacionFilter.getOrigenAportacion().setId(ID_ORIGEN_DOMICILIACION_TRASPASO);
								conciliacionSinEmpresa = consultar(conciliacionFilter);
							}
						}
					}
					
					for(ConciliacionVO conSinEmp : conciliacionSinEmpresa){
						ConciliacionVO conciliacion = new ConciliacionVO();
						
						conciliacion.setRegistroConciliado((short) 1);
						conciliacion.setUsuarioActualizacion(usuario);
						conciliacion.setFolioConciliacion(folioConciliacion);
						
						GenericCatalogoVO origenAportacion = new GenericCatalogoVO();
						origenAportacion.setId(conSinEmp.getOrigenAportacion().getId());
						conciliacion.setOrigenAportacion(origenAportacion);
						
						if(conSinEmp.getEmpresa() == null && conSinEmp.getClaveRedComercial() != null){
							GenericCatalogoVO redComercial = new GenericCatalogoVO();
							redComercial.setId(conSinEmp.getClaveRedComercial().getId());
							conciliacion.setClaveRedComercial(redComercial);
							if(conSinEmp.getFolio()!=null){
								conciliacion.setFolio(sumConcilia.getFolio());
							}else{
								conciliacion.setClaveSolicitud(conSinEmp.getClaveSolicitud());
							}
						}else{
							GenericCatalogoVO empresa = new GenericCatalogoVO();
							empresa.setId(sumConcilia.getEmpresa().getId());
							conciliacion.setEmpresa(empresa);
							conciliacion.setFolio(sumConcilia.getFolio());
							if(conSinEmp.getFolio()!=null){
								conciliacion.setFolio(sumConcilia.getFolio());
							}else{
								conciliacion.setClaveSolicitud(conSinEmp.getClaveSolicitud());
							}
						}
//						if(ID_ORIGEN_DOMICILIACION_TRASPASO.equals(conciliacion.getOrigenAportacion().getId()) || ID_ORIGEN_DOMICILIACION_E_SAR.equals(conciliacion.getOrigenAportacion().getId())){
//							sumConcilia.getOrigen().setId(ID_ORIGEN_APORTACION_DOMICILIACION);
//						}
						conciliacion.setClaveBanco(bancos.getClaveBanco().getId());
						
						listaConciliacion.add(conciliacion);
					}
					
					sumConcilia.setRegConciliado((short) 1);
					sumConcilia.setUsuarioActualizacion(usuario);
					listaSumConciliaCon.add(sumConcilia);
					
					bancos.setRegConciliado((short) 1);
					if(bancos.getFolioRelacionado()==null) {
						bancos.setFolioRelacionado(new ArrayList<FolioBancoVO>());
					}
					bancos.getFolioRelacionado().add(new FolioBancoVO(sumConcilia.getFolio()));
					bancos.setUsuarioActualizacion(usuario);
					listaBancosCon.add(bancos);
					
					break;
				}
			}
			//vez ++;
		}

		listaConciliacion.addAll(getConciliadosVentanilla(folioConciliacion, usuario));
		
		if(!listaConciliacion.isEmpty()){
			conciliarTotales.setBancos(listaBancosCon);
			conciliarTotales.setConciliaciones(listaConciliacion);
			conciliarTotales.setSumConcilia(listaSumConciliaCon);
		}
		return conciliarTotales;
	}
	
	private List<ConciliacionVO> getConciliadosVentanilla(String folioConciliacion,String usuario){
		ConciliacionFilter conciliacionFilter = new ConciliacionFilter();
		conciliacionFilter.setRegistroConciliado((short) 1);
		conciliacionFilter.setMovimientoGenerado((short) 0);
		conciliacionFilter.setIsFolioConciliacionNull(true);
		
		List<ConciliacionVO> ventanillas = conciliacionPersistence.selectSelective(conciliacionFilter);
		List<ConciliacionVO> conciliados = new ArrayList<ConciliacionVO>();
		
		for(ConciliacionVO vent:ventanillas){
			ConciliacionVO conciliacion = new ConciliacionVO();
			conciliacion.setRegistroConciliado((short) 1);
			conciliacion.setUsuarioActualizacion(usuario);
			conciliacion.setFolioConciliacion(folioConciliacion);
			conciliacion.setIdConciliacion(vent.getIdConciliacion());
			conciliados.add(conciliacion);
		}
		return conciliados;
	}
	

	public Long getIdPagosecure() throws BusinessException {
		Long id=null;
		try{
			Long idPagoSeq = sequencesManagerPersistence.selectSeqPagoId();
			String numFijo=  NameSpaceBindingProvider.getValue(WebSphereNamespace.MIT_NCI_AV_secure_PAGO_secure_ID_PREFIX);
			String referencia = UtilMethod.leftPadCeros(idPagoSeq, 6);
			String idPagoSeqsecure =  numFijo + referencia + UtilMethod.digitoVerificador( numFijo + referencia);
			id = Long.valueOf(idPagoSeqsecure);
		}catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al obtener el id Pago: "+ex.getMessage(),
							new Object[] { getClass().toString(), "getIdPagosecure()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		
		
		return id;
	}

	/* (non-Javadoc)
	 * @see mx.secure.nci.business.service.IConciliacionService#conciliarOrdenSpei(mx.secure.nci.business.vo.ConciliacionVO, mx.secure.nci.business.vo.OrdenesVO)
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void conciliarOrdenSpei(ConciliacionVO conciliacion, OrdenesVO orden)
			throws MitBusinessException {
		
		conciliacion.setDiversificaciones(
				getListDiversificacionConciliacion(
						conciliacion.getIdConciliacion(), 
						orden.getDiversificacionesOrdenes(),
						conciliacion.getUsuarioActualizacion()
				)
		);
		int numUpdate = this.conciliacionPersistence.update(conciliacion);
		if(numUpdate > 0){
			boolean bUpdateOrdenes = this.ordenesService.actualizar(orden);
			if(bUpdateOrdenes){
				this.diversificacionConciliacionPersistence.insertAll(conciliacion.getDiversificaciones());
			}
		}
	}
	
	/**
	 * Metodo para setear las diversificaciones conciliadas
	 * 
	 * @return
	 */
	private List<DiversificacionConciliacionVO> getListDiversificacionConciliacion(Integer idConciliacion, List<DiversificacionOrdenesVO> lstDiversificacionOrdenes,String usuarioCreacion){
		List<DiversificacionConciliacionVO> lstDiverConciliado = new ArrayList<DiversificacionConciliacionVO>();
		DiversificacionConciliacionVO diverConciliado;
		for(DiversificacionOrdenesVO diversificacionOrdenes : lstDiversificacionOrdenes){
			diverConciliado = new DiversificacionConciliacionVO();
			diverConciliado.setIdConciliacion(idConciliacion);
			diverConciliado.setFondosecure(diversificacionOrdenes.getFondosecure());
			diverConciliado.setMonto(diversificacionOrdenes.getMonto());
			diverConciliado.setPorcentaje(diversificacionOrdenes.getPorcentaje());
			diverConciliado.setUsuarioCreacion(usuarioCreacion);
			
			lstDiverConciliado.add(diverConciliado);
		}
		
		return lstDiverConciliado;
	}

	public List<String> consultarFolios(ConciliacionFilter conciliacionFilter) throws MitBusinessException {
		List<String> folios = null;
		try{
			if(conciliacionFilter.getBandera() == 0) {
				folios = conciliacionPersistence.consultarFolios(conciliacionFilter);
			}else if(conciliacionFilter.getBandera() == 1) {
				folios = conciliacionPersistence.consultarFoliosAFinalizar(conciliacionFilter);
			}else if(conciliacionFilter.getBandera() == 3) {
				folios = conciliacionPersistence.consultarFoliosByConciliacion(conciliacionFilter);
			}			
		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(
							ErrorCodeService.EX_EXCEPTION, 
							"Al consultar folios"+ex.getMessage(),
							new Object[] { getClass().getSimpleName(), "consultarFolios()" }, ex));
	
			LOGGER.error(mitBusinessException.getMessage(), ex);
	
			throw mitBusinessException;
		}

		return folios;

	}
	
	
	public int actualizarEstatusConciliacion(Integer idConciliacion, Short estatus, String usuario) throws MitBusinessException {
		int varReturn = 0;
		ConciliacionVO conciliacionVO = new ConciliacionVO(); 		
		conciliacionVO.setIdConciliacion(idConciliacion);
		GenericCatalogoVO cataEst = new GenericCatalogoVO();
		cataEst.setId(estatus);
		conciliacionVO.setEstatusTramite(cataEst);
		conciliacionVO.setUsuarioActualizacion(usuario);
		varReturn = this.conciliacionPersistence.update(conciliacionVO);
		return varReturn;
	}
}

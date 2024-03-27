package mx.profuturo.nci.web.service.impl;

import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_PROCESO_RECAUDACION;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_SUBPROCESO_APORTACION_VOLUNTARIA;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_TIPO_MOVIMIENTO_ABONO;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ORIGEN_APORTACION_VENTANILLA;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ORIGEN_APORTACION_DOMICILIACION;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IBancosService;
import mx.profuturo.nci.business.service.ICatalogosService;
import mx.profuturo.nci.business.service.IConciliacionService;
import mx.profuturo.nci.business.service.IIdentificarClienteService;
import mx.profuturo.nci.business.service.IIndicadoresService;
import mx.profuturo.nci.business.service.IMapaReferenciasService;
import mx.profuturo.nci.business.service.IMatrizConvivenciaService;
import mx.profuturo.nci.business.service.impl.WSPortTypeFactory;
import mx.profuturo.nci.business.util.ConstantesCatalogos;
import mx.profuturo.nci.business.util.IdentificarClienteEnum;
import mx.profuturo.nci.business.util.UtilMethod;
import mx.profuturo.nci.business.vo.BancosVO;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ConciliacionVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.MapaReferenciasVO;
import mx.profuturo.nci.business.vo.MatrizConvivenciaVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.business.wrapped.BancosFilter;
import mx.profuturo.nci.web.beans.ArchivosBean;
import mx.profuturo.nci.web.beans.BancosBean;
import mx.profuturo.nci.web.beans.BancosFilterBean;
import mx.profuturo.nci.web.beans.CuentaIndividualBean;
import mx.profuturo.nci.web.beans.GenericCatalogoBean;
import mx.profuturo.nci.web.service.IValidacionManualWebService;
import mx.profuturo.nci.web.util.UtilMapping;
import mx.profuturo.nci.ws.webservice.indicadores.IIndicadoresSoapWSConsultaBasicaResponse;
import profuturo.mx.iib.apovol.filenet.filenetservice.v1.types.ArchivoFileNet;
import profuturo.mx.iib.apovol.filenet.filenetservice.v1.types.ListaArchivosFileNet;
import profuturo.mx.iib.apovol.filenet.filenetservice.v1.types.SubirArchivosReq;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.ConsultarClienteBasicoResp;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.Correo;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.InformacionCliente;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.ListaCorreos;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.ListaTelefonos;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.Telefono;
import profuturo.mx.iib.nci.matrizconvivencia.matrizconvivenciaservice.v1.MatrizConvivenciaPortType;
import profuturo.mx.iib.nci.matrizconvivencia.matrizconvivenciaservice.v1.types.ConsultarConvivenciaReq;
import profuturo.mx.iib.nci.matrizconvivencia.matrizconvivenciaservice.v1.types.ConsultarConvivenciaResp;
import profuturo.mx.iib.nci.matrizconvivencia.matrizconvivenciaservice.v1.types.Motivo;

@Service("validacionManualService")
public class ValidacionManualWebServiceImpl implements IValidacionManualWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidacionManualWebServiceImpl.class);
	private static final Short ORIGEN_NO_IDENTIFICADO=758;
	private static final Short ORIGEN_VENTANILLA=287;
	private static final Short ORIGEN_INTERNET=288;
	private static final Short ORIGEN_DOMICILIACION=291;
	//private static final short ORIGEN_DOMICILIACION_E_SAR=844;
	//private static final short ORIGEN_DOMICILIACION_TRASPASOS=845;
	

	@Autowired
	ICatalogosService catalogosService;

	@Autowired
	IBancosService bancosService;

	@Autowired
	IIdentificarClienteService identificarClienteService;

	@Autowired
	IConciliacionService conciliacionService;

	@Autowired
	IIndicadoresService indicadoresService;
	
	@Autowired
	IMapaReferenciasService mapaReferenciasService;
	
	@Autowired 
	WSPortTypeFactory wsPortTypeFactory;
	
	@Autowired
	IMatrizConvivenciaService matrizConvivenciaService;
	
	public CuentaIndividualBean validarCuentaInd(Long cuenta) throws MitBusinessException {

		CuentaIndividualBean cuentaBean = new CuentaIndividualBean();
		try {
			IdentificarClienteEnum dentificarClienteEnum = null;
			dentificarClienteEnum = dentificarClienteEnum.IDENTIFICADOR_NUM_CUENTA;
			ConsultarClienteBasicoResp consultarClienteBasicoResp = identificarClienteService.consultarClienteBasico(dentificarClienteEnum, cuenta.toString());
			if(consultarClienteBasicoResp.getClientes() != null){
				String correoBandPref="";
				String correoPrimAct="";
				String telBanPref="";
				String telCel="";
				String telCas="";
				String telOfi="";
				
				
				InformacionCliente infoCliente = new InformacionCliente();
				boolean infoCli = false;
				if(consultarClienteBasicoResp.getClientes().getInformacionCliente().size()==1){
					infoCliente = consultarClienteBasicoResp.getClientes().getInformacionCliente().get(0);
					infoCli = true;
				}else{
					for(InformacionCliente infCli :  consultarClienteBasicoResp.getClientes().getInformacionCliente()){
						if(infCli.getInformacionComplementaria().getComplementaria().getCuentaVigente().equals("Vigente")){
							infoCliente = infCli;
							infoCli = true;
						}
					}
					if(!infoCli){
						infoCliente = consultarClienteBasicoResp.getClientes().getInformacionCliente().get(0);
					}
				}
				
				cuentaBean.setNombreCliente(infoCliente.getInformacionBasica().getPersona().getNombre()+" "+
						infoCliente.getInformacionBasica().getPersona().getApellidoPaterno()+ " "+
						infoCliente.getInformacionBasica().getPersona().getApellidoMaterno());
				cuentaBean.setNombre(infoCliente.getInformacionBasica().getPersona().getNombre());
				cuentaBean.setPaterno(infoCliente.getInformacionBasica().getPersona().getApellidoPaterno());
				cuentaBean.setMaterno(infoCliente.getInformacionBasica().getPersona().getApellidoMaterno());
				cuentaBean.setCurp(infoCliente.getInformacionBasica().getCliente().getCurp());
				cuentaBean.setRfc(infoCliente.getInformacionBasica().getCliente().getRfc());
				cuentaBean.setNss(UtilMethod.extractNumber(infoCliente.getInformacionBasica().getCliente().getNss()));
				cuentaBean.setCuentaIndividual(cuenta);
				if(infoCliente.getInformacionDemografica() != null)	{
					ListaCorreos   lstCor = infoCliente.getInformacionDemografica().getCorreos();
					ListaTelefonos lstTel = infoCliente.getInformacionDemografica().getTelefonos();
					
					if(lstCor!=null){
						for(Correo corTmp : lstCor.getCorreo()){
							if(corTmp.isValido() && corTmp.isPreferente()){
								correoBandPref = corTmp.getEmail();
								break;
							}else if(corTmp.isValido() && correoBandPref.isEmpty()){
								correoPrimAct = corTmp.getEmail();
							}
						}
					}
					
					if(lstTel!=null){
						for(Telefono telTmp : lstTel.getTelefono()){
							if(telTmp.isValido()) {
								if(telTmp.isPreferente()){
									telBanPref=telTmp.getNumero();	
									break;
								}else if(telTmp.getTipoTelefono().getClave().equals("3")){	//TELEFONO CELULAR
									telCel = telTmp.getNumero();
								}else if(telTmp.getTipoTelefono().getClave().equals("1")){	//TELEFONO PARTICULAR
									telCas = telTmp.getNumero();
								}else if(telTmp.getTipoTelefono().getClave().equals("2")){	//TELEFONO TRABAJO
									telOfi = telTmp.getNumero();
								}
							}
						}
					}
				}
				if(!correoBandPref.isEmpty()){
					cuentaBean.setCorreo(correoBandPref);
				}else if (!correoPrimAct.isEmpty()){
					cuentaBean.setCorreo(correoPrimAct);
				}else{
					cuentaBean.setCorreo(correoBandPref);
				}
					
				if(!telBanPref.isEmpty()){
					cuentaBean.setTelefono(telBanPref);
				}else if(!telCel.isEmpty()){
					cuentaBean.setTelefono(telCel);
					cuentaBean.setCelular(UtilMethod.extractNumber(telCel));
				}else if(!telCas.isEmpty()){
					cuentaBean.setTelefono(telCas);
				}else if(!telOfi.isEmpty()){
					cuentaBean.setTelefono(telOfi);
				}else{
					cuentaBean.setTelefono(correoBandPref);
				}
				
				List<Integer> listaIndicadores = new ArrayList<Integer>();
				listaIndicadores.add(58);
				List<Long> numerosCuenta = new ArrayList<Long>();
				numerosCuenta.add(cuenta);
				IIndicadoresSoapWSConsultaBasicaResponse response = indicadoresService.consultar(listaIndicadores, numerosCuenta);
				if(response.getResponse().getListaCuentasIndividuales().getCuentaIndividual().isEmpty())
				{
					cuentaBean.setIsVigente(false);
				}
				else
				{
					cuentaBean.setIsVigente(response.getResponse().getListaCuentasIndividuales().getCuentaIndividual().get(0).getListaConfiguracionesIndicadores().getConfiguracionIndicadores().get(0).getValorIndicador().equals("1") ? true : false);
				}
				
			}
			else
			{
				cuentaBean.setNombreCliente("No encontrado");
				cuentaBean.setCorreo("No encontrado");
				cuentaBean.setTelefono("No encontrado");
				cuentaBean.setIsVigente(false);
			}
			
			
		} catch (final MitBusinessException ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_WS_CLIENT, "Al validar cuenta:"+ex.getMessage(),
							new Object[] { this.getClass().getSimpleName(), "validarCuentaInd()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
		return cuentaBean;
	}
	
	public List<GenericCatalogoBean> obtenerBancos() throws MitBusinessException {
		List<GenericCatalogoBean> listaBancos = new ArrayList<GenericCatalogoBean>();
		try {
			ParamCatalogoVO paramCatalogoVO = new ParamCatalogoVO();
			paramCatalogoVO.setIdTipoCatalogo(ConstantesCatalogos.ID_BANCO);
			List<CatalogoVO> listaBancosVO = catalogosService.consultarLista(paramCatalogoVO);
			for (CatalogoVO catalogo : listaBancosVO) {
				GenericCatalogoBean bancos = new GenericCatalogoBean();
				bancos.setId(catalogo.getIdCatCatalogo());
				bancos.setValor(catalogo.getValor());
				listaBancos.add(bancos);
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar catalogo bancos",
							new Object[] { "BancosServiceImpl", "obtenerbancos()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
		return listaBancos;
	}

	public List<BancosBean> consultar(BancosFilterBean bancosFilter) throws MitBusinessException {
		List<BancosBean> listaBancos = new ArrayList<BancosBean>();
		try {
			BancosBean bancosBean = null;
			BancosFilter bancosFilterVO = new BancosFilter();
			bancosFilterVO.setRegConciliado((short)0);
			bancosFilterVO.setFechaInicio(bancosFilter.getFechaInicio() != null ? bancosFilter.getFechaInicio() : null);
			bancosFilterVO.setFechaFinal(bancosFilter.getFechaFinal() != null ? bancosFilter.getFechaFinal() : null);
			/**
			 * Atencion defecto 1519
			 * Por especificacion de EON, se elimina filtro de Origen de aportaciones.
			 * 01092017 Se muestran todos excepto origen domiciliacion.
			 * 08092017 Se dejan unicamente los origenes no identificados.
			 * 22092017 Se actualiza el criterio: se consideran los registros el indicador de IDC en 0 y los origenes de Ventanilla e internet
			 * */
			List<Short> lstOrigenAportaciones = new ArrayList<Short>();
			//lstOrigenAportaciones.add(ORIGEN_NO_IDENTIFICADO);
			lstOrigenAportaciones.add(ORIGEN_VENTANILLA);
			lstOrigenAportaciones.add(ORIGEN_INTERNET);
			bancosFilterVO.setOrigenAportaciones(lstOrigenAportaciones);
			bancosFilterVO.setEstatusIDC(new GenericCatalogoVO((short)0));// Se buscan los no identificados
			// Se debe buscar también por el nuevo estatus de convivencia los marcados que no conviven 0
			bancosFilterVO.setEstatusConvivencia(new GenericCatalogoVO((short)0));
			
			if (bancosFilter.getClaveBanco() != null) {
				GenericCatalogoVO claveBanco = new GenericCatalogoVO();
				claveBanco.setId(bancosFilter.getClaveBanco().getId() == null ? null : (bancosFilter.getClaveBanco().getId() == 0 ? null : bancosFilter.getClaveBanco().getId()));
				bancosFilterVO.setClaveBanco(claveBanco);
			}
			List<BancosVO> bancosList= bancosService.consultarMovs(bancosFilterVO);
			for (BancosVO bancosVO : bancosList) {
				bancosBean = new BancosBean();
				UtilMapping.mapVoToBean(bancosBean, bancosVO);
				if(bancosVO.getSubctaApovol() != null)
				{
					GenericCatalogoBean subcuenta = new GenericCatalogoBean();
					subcuenta.setValor(bancosVO.getSubctaApovol().getDescripcionItgy());
					bancosBean.setSubctaApovol(subcuenta);
				}
				
				if (bancosVO.getClaveBanco() != null) {
					GenericCatalogoBean claveBanco = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(claveBanco, bancosVO.getClaveBanco());
					bancosBean.setClaveBanco(claveBanco);
				}
				if (bancosVO.getEstatusProceso() != null) {
					GenericCatalogoBean estatusProceso = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(estatusProceso, bancosVO.getEstatusProceso());
					bancosBean.setEstatusProceso(estatusProceso);
				}
				if (bancosVO.getOrigenAportacion() != null) {
					GenericCatalogoBean origenAportacion = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(origenAportacion, bancosVO.getOrigenAportacion());
					bancosBean.setOrigenAportacion(origenAportacion);
				}
				if (bancosVO.getIdBanco() != null){
					bancosBean.setIdBanco(bancosVO.getIdBanco());
				}
				listaBancos.add(bancosBean);
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar bancos",
							new Object[] { "BancosServiceImpl", "consultar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		return listaBancos;
	}

	public List<GenericCatalogoBean> obtenerEmpresa() throws MitBusinessException {
		List<GenericCatalogoBean> listaEmpresa = new ArrayList<GenericCatalogoBean>();
		try {
			ParamCatalogoVO paramCatalogoVO = new ParamCatalogoVO();
			paramCatalogoVO.setIdTipoCatalogo(ConstantesCatalogos.ID_CATALOGO_EMPRESAS);
			List<CatalogoVO> listaBancosVO = catalogosService.consultarLista(paramCatalogoVO);
			for (CatalogoVO catalogo : listaBancosVO) {
				GenericCatalogoBean bancos = new GenericCatalogoBean();
				bancos.setId(catalogo.getIdCatCatalogo());
				bancos.setValor(catalogo.getValor());
				listaEmpresa.add(bancos);
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar catalogo bancos",
							new Object[] { "BancosServiceImpl", "obtenerbancos()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		return listaEmpresa;
	}

	public List<GenericCatalogoBean> obtenerOrigen() throws MitBusinessException {
		List<GenericCatalogoBean> listaOrigen = new ArrayList<GenericCatalogoBean>();
		try {
			ParamCatalogoVO paramCatalogoVO = new ParamCatalogoVO();
			paramCatalogoVO.setIdTipoCatalogo(ConstantesCatalogos.ID_ORIGEN_APORTACION);
			List<CatalogoVO> listaBancosVO = catalogosService.consultarLista(paramCatalogoVO);
			for (CatalogoVO origen : listaBancosVO) {
				GenericCatalogoBean origenBean = new GenericCatalogoBean();
				origenBean.setId(origen.getIdCatCatalogo());
				origenBean.setValor(origen.getValor());
				listaOrigen.add(origenBean);
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar catalogo ",
							new Object[] { "BancosServiceImpl", "obtenerbancos()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		return listaOrigen;
	}

	public List<GenericCatalogoBean> obtenerApovol() throws MitBusinessException {
		List<GenericCatalogoBean> listaApovol = new ArrayList<GenericCatalogoBean>();
		try {
			for(MapaReferenciasVO maps : mapaReferenciasService.consultar())
			{
				GenericCatalogoBean apovol = new GenericCatalogoBean();
				apovol.setId(maps.getClaveNCI());
				apovol.setValor(maps.getDescripcionItgy());
				listaApovol.add(apovol);
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar catalogo ",
							new Object[] { "BancosServiceImpl", "obtenerApovol()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		return listaApovol;
	}

	public String guardarInfo(BancosBean bancosBean, CuentaIndividualBean cuenta, Short idEmpresa, List<ArchivosBean> listaArchivos, String usuario) throws MitBusinessException {
		String mensaje =null;
		try {
			BancosVO bancosVo = new BancosVO();
//			bancosVo.setNumCuentaIndividual(bancosBean.getNumCuentaIndividual());
			if(cuenta!=null && cuenta.getCuentaIndividual()!=null) {
				bancosVo.setNumCuentaIndividual(cuenta.getCuentaIndividual());
			}
			if(idEmpresa!=null) {
				bancosVo.setEmpresa(new GenericCatalogoVO(idEmpresa));
			}
			bancosVo.setFolio(bancosBean.getFolio());

			GenericCatalogoVO origenAportacion = new GenericCatalogoVO();
			UtilMapping.mapBeanToVO(bancosBean.getOrigenAportacion(), origenAportacion);
			bancosVo.setOrigenAportacion(origenAportacion);

			MapaReferenciasVO mapaReferencias = new MapaReferenciasVO();
			mapaReferencias.setClaveNCI(bancosBean.getSubctaApovol().getId());
			bancosVo.setSubctaApovol(mapaReferencias);
			if(idEmpresa!=null){
				GenericCatalogoVO catEmp = new GenericCatalogoVO();
				catEmp.setId(idEmpresa);
				bancosVo.setEmpresa(catEmp);
			}
			bancosVo.setIdBanco(bancosBean.getIdBanco());
			bancosVo.setAutBanco(bancosBean.getAutBanco());
			bancosVo.setTipoIdentificacion((short)1);//VALIDACION MANUAL
			bancosVo.setEstatusIDC(new GenericCatalogoVO((short)1));
			bancosVo.setUsuarioActualizacion(usuario);
			
			if (ID_ORIGEN_APORTACION_VENTANILLA.equals(bancosBean.getOrigenAportacion().getId())) {//				 se debe validar convivencia, 
				GenericCatalogoVO motivoNoConvive = getMotivoNoConvive(bancosBean);
				if(motivoNoConvive==null){//SI CONVIVE: marcar la cuenta, actualizar nuevo campo identificador de convivencia en bancos 1, insertar en conciliación y actualizar registro en bancos
					MatrizConvivenciaVO vo = new MatrizConvivenciaVO();
					vo.setFolio(bancosBean.getFolio());
					vo.setNumeroCuentaIndividual(cuenta.getCuentaIndividual());
					vo.setEstatusMarca((byte)1);//EStatus marcado
					vo.setTipoSaldoOpera(new GenericCatalogoVO((short)0));
					vo.setUsuarioCreacion(usuario);
					if(matrizConvivenciaService.insertar(vo)){
						bancosVo.setEstatusConvivencia(new GenericCatalogoVO((short)1));
						if(guardarInfoConciliacion(bancosBean,cuenta, idEmpresa,usuario)){
							bancosVo.setRegConciliado((short)1);
						}else{
							bancosVo.setRegConciliado((short)0);
							mensaje = "No se actualizó la información de conciliación";
						}
					}else{
						bancosVo.setEstatusConvivencia(new GenericCatalogoVO((short)0));
					}
				}else{//	NO CONVIVE: marcar nuevo identificador de convivencia en bancos a 0, agregar motivo de rechazo, mostrar mensaje al usuario
					bancosVo.setRegConciliado((short)0);
					bancosVo.setEstatusConvivencia(new GenericCatalogoVO((short)0));
					bancosVo.setMotivoNoConvivencia(motivoNoConvive);
					mensaje = "No convive: "+motivoNoConvive.getValor();
				}
			}else if(ID_ORIGEN_APORTACION_DOMICILIACION.equals(bancosBean.getOrigenAportacion().getId()) && bancosBean.getClaveBanco()!=null) {
				bancosVo.setEmpresa(new GenericCatalogoVO(bancosBean.getClaveBanco().getId()));
			}
			int estatusOperacion = 0;
			if(bancosVo.getFolio()!=null) {
				estatusOperacion = this.bancosService.actualizar(bancosVo);
			}else {
				mensaje = "No se pudo actualizar la informacion";
			}
			if(estatusOperacion > 0){
				guardarArchivos(listaArchivos, bancosBean);
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al actualizar info Bancos"+ex.getMessage(),
							new Object[] { "BancosServiceImpl", "guardarInfo()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			mensaje=ex.getMessage();
			throw mitBusinessException;
		}
		return mensaje;

	}
	
	
	private GenericCatalogoVO getMotivoNoConvive(BancosBean bean){
		Boolean convive= false;
		GenericCatalogoVO motivoNoConvive = null;
		MatrizConvivenciaPortType port;
		try {
			port = wsPortTypeFactory.matrizConvivenciaPortType();
			ConsultarConvivenciaReq req = new ConsultarConvivenciaReq();
			req.setIdMovimiento(ID_TIPO_MOVIMIENTO_ABONO);//181 - Tipo Movimiento: ABONO
			req.setIdProceso(ID_PROCESO_RECAUDACION);//7-RECAUDACION
			req.setIdSubproceso(ID_SUBPROCESO_APORTACION_VOLUNTARIA);//101- APORTACION VOLUNTARIA
			req.setNumeroCuenta(bean.getNumCuentaIndividual());
			
			ConsultarConvivenciaResp resp =port.consultarConvivencia(req);
			convive = resp.isEstatusConvivencia();
			if(!convive && resp.getMotivosNoConvivencia()!=null 
					&& resp.getMotivosNoConvivencia().getMotivo()!=null
					&& !resp.getMotivosNoConvivencia().getMotivo().isEmpty()){
				Motivo motivo = resp.getMotivosNoConvivencia().getMotivo().get(0);//solo se guarda el primer motivo de no convivencia
				if(motivo!=null && motivo.getDetalle()!= null){
					motivoNoConvive= new GenericCatalogoVO(motivo.getDetalle().getId(),motivo.getDetalle().getDescripcion());
				}
			}
		} catch (BusinessException ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al validar convivencia "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "validaConvivencia()" }, ex));
			
			LOGGER.error(mitBusinessException.getMessage(), ex);
		}
		return motivoNoConvive;
	}

	public Boolean guardarInfoConciliacion(BancosBean bancosBean,CuentaIndividualBean cuenta, Short idEmpresa, String usuario) throws MitBusinessException {
		try {
			ConciliacionVO conciliacionVO = new ConciliacionVO();
			conciliacionVO.setFolio(bancosBean.getFolio());
			conciliacionVO.setNumeroCuentaIndividual(cuenta.getCuentaIndividual());
			//GenericCatalogoVO claveBanco = new GenericCatalogoVO();
			//claveBanco.setId((short) bancosBean.getClaveBanco().getId());
			//conciliacionVO.setClaveBanco(claveBanco);
			conciliacionVO.setClaveBanco(bancosBean.getClaveBanco().getId());
			GenericCatalogoVO origenAportacion = new GenericCatalogoVO();
			origenAportacion.setId((short) bancosBean.getOrigenAportacion().getId());
			conciliacionVO.setOrigenAportacion(origenAportacion);
//			GenericCatalogoVO subcta = new GenericCatalogoVO();
//			subcta.setId((short) bancosBean.getSubctaApovol().getId());
//			conciliacionVO.setReferenciaSubcuentaApovol(Integer.valueOf(bancosBean.getOrigenAportacion().getId()));
			if(bancosBean.getSubctaApovol() != null && bancosBean.getSubctaApovol().getId()!=null) {
				conciliacionVO.setReferenciaSubcuentaApovol(new BigInteger(bancosBean.getSubctaApovol().getId().toString()));
			}
			if(idEmpresa!=null) {
				GenericCatalogoVO claveEmpresa = new GenericCatalogoVO();
				claveEmpresa.setId(idEmpresa);
				conciliacionVO.setEmpresa(claveEmpresa);
			}
			conciliacionVO.setImporte(new BigDecimal(bancosBean.getImporte()));
			conciliacionVO.setRegistroConciliado((short)1);
			conciliacionVO.setMovimientoGenerado((short)0);
			conciliacionVO.setFechaCargaArchivo(bancosBean.getFechaCarga());
			conciliacionVO.setFechaPagoApovol(bancosBean.getFechaDeposito());
			conciliacionVO.setCurp(bancosBean.getCurp());
			conciliacionVO.setNombreCliente(cuenta.getNombre());
			conciliacionVO.setApellidoClientePaterno(cuenta.getPaterno());
			conciliacionVO.setApellidoClienteMaterno(cuenta.getMaterno());
			conciliacionVO.setCurp(cuenta.getCurp());
			conciliacionVO.setRfc(cuenta.getRfc());
			conciliacionVO.setNss(cuenta.getNss());
			conciliacionVO.setCorreoElectronico(cuenta.getCorreo());
			conciliacionVO.setCelular(cuenta.getCelular());
			conciliacionVO.setUsuarioCreacion(usuario);
			return conciliacionService.insertar(conciliacionVO);
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al guardar info Conciliacion",
							new Object[] { "BancosServiceImpl", "guardarInfoConciliacion()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

	public void guardarArchivos(List<ArchivosBean> listaArchivos, BancosBean bancosBean) throws MitBusinessException {
		if(!listaArchivos.isEmpty())
		{
			ListaArchivosFileNet value = new ListaArchivosFileNet();
			List<ArchivoFileNet> listaArchivosNet = new ArrayList<ArchivoFileNet>();
			SubirArchivosReq parameters =  new SubirArchivosReq();
			parameters.setClaveApp("");
			for(ArchivosBean archivo : listaArchivos)
			{
				ArchivoFileNet archivoNet = new ArchivoFileNet();
				archivoNet.setNombreArchivo(bancosBean.getNumCuentaIndividual()+"_"+bancosBean.getAutBanco()+"_"+archivo.getTipoArchivo().trim()+"_"+archivo.getExtension());
				archivoNet.setContenido(archivo.getContenidoArchivo());
				listaArchivosNet.add(archivoNet);
			}
			value.setArchivo(listaArchivosNet);
			parameters.setArchivos(value);
		}
			
	}

}
package mx.profuturo.nci.business.service.impl;

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
import mx.profuturo.nci.business.service.IGeneraFolioService;
import mx.profuturo.nci.business.vo.ConciliacionVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.wrapped.FolioFilter;
import mx.profuturo.nci.business.wrapped.GeneraFolioFilter;
import mx.profuturo.nci.persistence.ConciliacionPersistence;
import mx.profuturo.nci.persistence.FolioPersistence;
import mx.profuturo.nci.persistence.GeneraFolioPersistence;
import profuturo.mx.iib.apovol.conciliacion.conciliacionservice.v1.types.LiberarMovimientosReq;
import profuturo.mx.iib.apovol.conciliacion.conciliacionservice.v1.types.LiberarMovimientosResp;
import profuturo.mx.iib.apovol.conciliacion.conciliacionservice.v1.types.ListaCurp;

@Service("ogeneraFolioService")
public class GeneraFolioServiceImpl implements IGeneraFolioService 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(GeneraFolioServiceImpl.class);
	
	@Autowired
	GeneraFolioPersistence generaFolioPersistence;
	
	@Autowired
	FolioPersistence folioPersistence;
	
	@Autowired
	ConciliacionPersistence conciliaPesistence;
	
	@Autowired
	WSPortTypeFactory wsPortTypeFactory;
	
	public String generaFolioAforeMovil(Integer proceso, Integer subProceso, Integer canal, Short estatus, String usuario) throws MitBusinessException{
		String folio="";
		
		try
		{
			GeneraFolioFilter generaFolio = new GeneraFolioFilter();
			generaFolio.setIdProceso(proceso);
			generaFolio.setIdSubproceso(subProceso);
			generaFolio.setIdCanal(canal);
			this.generaFolioPersistence.generaFolio(generaFolio);
			
			if(generaFolio.getFolio()!=null){
				FolioFilter folioFilter = new FolioFilter();
				folioFilter.setFolio(generaFolio.getFolio());
				folioFilter.setIdProceso(proceso.shortValue());
				folioFilter.setIdSubproceso(subProceso.shortValue());
				folioFilter.setIdEstatusProceso(estatus);
				folioFilter.setIdCanal(canal.shortValue());
				folioFilter.setUsuarioCre(usuario);
				
				int valor = folioPersistence.insert(folioFilter);
				
				if(valor==1){
					folio = generaFolio.getFolio();
				}else{
					folio = "";
				}
			}
		}catch(Exception ex){
				MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
						ErrorCodeService.EX_EXCEPTION, "Al Generar folio nuevos ",
						new Object[] { "IGeneraFolioService", "generaFolio()" }, ex));
				LOGGER.error(mitBusinessException.getMessage(), ex);
				throw mitBusinessException;			
		}
		return folio;
	}
	
	public int generaRegistroConciliacion(String folio, Short origen, Long numCtaInd, BigDecimal importe, String nombre,
			                              String apPaterno, String apMaterno, String curp, String rfc, Long nss, String correo,
			                              Long celular, String usuario, BigInteger subctaApovol) throws MitBusinessException{
		ConciliacionVO concilFilter = new ConciliacionVO(); 
		concilFilter.setFolio(folio);
		
		GenericCatalogoVO cataOrigen = new GenericCatalogoVO();
		cataOrigen.setId(new Short(origen));
		concilFilter.setOrigenAportacion(cataOrigen);
		
		concilFilter.setNumeroCuentaIndividual(numCtaInd);
		concilFilter.setImporte(importe);
		concilFilter.setNombreCliente(nombre);
		concilFilter.setApellidoClientePaterno(apPaterno);
		concilFilter.setApellidoClienteMaterno(apMaterno);
		concilFilter.setCurp(curp);
		concilFilter.setRfc(rfc);
		concilFilter.setNss(new Long(nss));
		concilFilter.setCorreoElectronico(correo);
		concilFilter.setCelular(new Long(celular));
		concilFilter.setRegistroConciliado(new Short("0"));
		concilFilter.setMovimientoGenerado(new Short("0"));
		concilFilter.setMovimiento(new Long("0"));
		concilFilter.setUsuarioCreacion(usuario);
		concilFilter.setTipoAhorrador("02");
		concilFilter.setReferenciaSubcuentaApovol(subctaApovol);
		
		return conciliaPesistence.insert(concilFilter);
	}
	
	public String generaFolio(Integer proceso, Integer subProceso, Integer canal, Short estatus, String usuario, 
							  BigDecimal importe, String nombre, String apPaterno, String apMaterno, String curp,
							  String numCtaInd, String rfc, String nss, String correo, String celular)throws MitBusinessException{
		try
		{
			GeneraFolioFilter generaFolio = new GeneraFolioFilter();
			generaFolio.setIdProceso(proceso);
			generaFolio.setIdSubproceso(subProceso);
			generaFolio.setIdCanal(canal);
			this.generaFolioPersistence.generaFolio(generaFolio);
			
			if(generaFolio.getFolio()!=null){
				FolioFilter folioFilter = new FolioFilter();
				folioFilter.setFolio(generaFolio.getFolio());
				folioFilter.setIdProceso(proceso.shortValue());
				folioFilter.setIdSubproceso(subProceso.shortValue());
				folioFilter.setIdEstatusProceso(estatus);
				folioFilter.setIdCanal(canal.shortValue());
				folioFilter.setUsuarioCre(usuario);
				
				int valor = folioPersistence.insert(folioFilter);
				if(valor==1 && !importe.equals(BigDecimal.ZERO)){
					ConciliacionVO concilFilter = new ConciliacionVO(); 
					concilFilter.setFolio(folioFilter.getFolio());
					
					GenericCatalogoVO cataOrigen = new GenericCatalogoVO();
					cataOrigen.setId(new Short("1097"));
					concilFilter.setOrigenAportacion(cataOrigen);
					
					concilFilter.setNumeroCuentaIndividual(Long.parseLong(numCtaInd));
					concilFilter.setImporte(importe);
					concilFilter.setNombreCliente(nombre);
					concilFilter.setApellidoClientePaterno(apPaterno);
					concilFilter.setApellidoClienteMaterno(apMaterno);
					concilFilter.setCurp(curp);
					concilFilter.setRfc(rfc);
					if(nss!=null && !nss.isEmpty()){
						concilFilter.setNss(new Long(nss));
					}
					concilFilter.setCorreoElectronico(correo);
					if(celular!=null && !celular.isEmpty()){
						concilFilter.setCelular(new Long(celular));
					}
					concilFilter.setRegistroConciliado(new Short("0"));
					concilFilter.setMovimientoGenerado(new Short("0"));
					concilFilter.setMovimiento(new Long("0"));
					concilFilter.setUsuarioCreacion(usuario);
					
					int insertConcil = conciliaPesistence.insert(concilFilter);
				}

				LiberarMovimientosReq objLiberar = new LiberarMovimientosReq();
				objLiberar.setUsuario(usuario);
				
				
				ListaCurp lstCurpLiberar = new ListaCurp();
				List<String> lstCurp = new ArrayList<String>();
				lstCurp.add(curp);
				lstCurpLiberar.setCurp(lstCurp);
				objLiberar.setListaCurp(lstCurpLiberar);
				LiberarMovimientosResp response = wsPortTypeFactory.conciliacionPortType().liberarMovimientos(objLiberar);
			}
			
			return generaFolio.getFolio(); 
		}
		catch(Exception ex)
		{
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al Generar folio nuevos ",
					new Object[] { "IGeneraFolioService", "generaFolio()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;			
		}		
	}
	
	public boolean invocaServicioLiberar(List<String> lstCurp, String usuario) throws MitBusinessException{
		boolean varReturn = false;
		LiberarMovimientosReq objLiberar = new LiberarMovimientosReq();
		objLiberar.setUsuario(usuario);
		
		ListaCurp lstCurpLiberar = new ListaCurp();
		lstCurpLiberar.setCurp(lstCurp);
		objLiberar.setListaCurp(lstCurpLiberar);
		try {
			LiberarMovimientosResp response = wsPortTypeFactory.conciliacionPortType().liberarMovimientos(objLiberar);
			varReturn = true;
		} catch (BusinessException ex) {
			varReturn = false;
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al Invocar el servicio de liberar de BUS ",
					new Object[] { "IGeneraFolioService", "invocaServicioLiberar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;

		}
		return varReturn;
	}
}
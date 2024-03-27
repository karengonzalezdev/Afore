package mx.secure.nci.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.persistence.exception.PersistenceException;

import mx.secure.nci.business.exception.ErrorCodeService;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.ICIFService;
import mx.secure.nci.business.vo.cif.DevolucionesEnvioCifCabeceraVO;
import mx.secure.nci.business.vo.cif.DevolucionesEnvioCifDetalleVO;
import mx.secure.nci.business.vo.cif.secure_CIFVo;
import mx.secure.nci.business.wrapped.NCI_CIFExtVo;
import mx.secure.nci.business.wrapped.NCI_CIFFilter;
import mx.secure.nci.business.wrapped.cif.secure_CIFFilter;
import mx.secure.nci.cif.persistence.secureCIFPersistence;
import mx.secure.nci.persistence.NCICIFPersistence;

@Service("nciCIFPService")
public class NCICIFServiceImpl implements ICIFService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NCICIFServiceImpl.class);
	@Autowired NCICIFPersistence nci_cifPersistence;
	@Autowired secureCIFPersistence secure_cifPersistence;
	
	public List<NCI_CIFExtVo> consultarNCI(NCI_CIFFilter f) throws MitBusinessException {
		try{
			return nci_cifPersistence.select(f);
		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar NCI CIF:"+ex.getMessage(),
							new Object[] {this.getClass().getName(), "consultarNCI()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}
	
	public Integer actualizarNCI(NCI_CIFExtVo vo) throws MitBusinessException {
		try{
			return nci_cifPersistence.update(vo);
		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En actualizar NCI CIF:"+ex.getMessage(),
							new Object[] { this.getClass().getName(), "actualizarNCI()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}
	
	public Integer borrarNCI(NCI_CIFExtVo vo) throws MitBusinessException {
		try{
			return nci_cifPersistence.delete(vo);
		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En actualizar NCI CIF:"+ex.getMessage(),
							new Object[] { this.getClass().getName(), "actualizarNCI()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}	
	
	
	public List<secure_CIFVo> consultarsecure(secure_CIFFilter f) throws MitBusinessException{
		try{
			return secure_cifPersistence.select(f);
		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION_CIF_EXT_DATABASE, "En consultar secure CIF:"+ex.getMessage(),
							new Object[] {this.getClass().getName(), "consultarsecure()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}
	
	public Integer insertarsecure(secure_CIFVo vo) throws MitBusinessException {
		try{
			return secure_cifPersistence.insert(vo);
		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION_CIF_EXT_DATABASE, "En consultar secure CIF:"+ex.getMessage(),
							new Object[] {this.getClass().getName(), "insertarsecure()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}
	
	public List<DevolucionesEnvioCifDetalleVO> buscaListaEnvioCIFDetalle(String folio) {
		return nci_cifPersistence.buscaListaEnvioCIFDetalleByFolio(folio);
	}

	
	public List<DevolucionesEnvioCifDetalleVO> buscaListaEnvioCIFDetalle(DevolucionesEnvioCifDetalleVO filtro) {
		return nci_cifPersistence.buscaListaEnvioCIFDetalleByRango(filtro);
	}

	
	public DevolucionesEnvioCifCabeceraVO buscaListaEnvioCIFCabecera(String folio) {
		return nci_cifPersistence.buscaListaEnvioCIFCabeceraByFolio(folio);
	}
	
	public Integer updateDetalleDevCif(DevolucionesEnvioCifDetalleVO data) {
		return nci_cifPersistence.actualizaDetalleDevCif(data);
	}
	
	public Integer updateCabeceraDevCif(DevolucionesEnvioCifDetalleVO data) {
		return nci_cifPersistence.actualizaCabeceraDevCif(data);
	} 
	
	
}

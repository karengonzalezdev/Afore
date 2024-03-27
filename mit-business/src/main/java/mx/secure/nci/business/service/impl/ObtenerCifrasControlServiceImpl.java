package mx.secure.nci.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.secure.nci.business.service.IObtenerCifrasControlService;
import mx.secure.nci.business.vo.ObtenerCifrasControlVO;
import mx.secure.nci.persistence.ObtenerCifrasControlPersistence;

@Service("obtenerCifrasControlService")
public class ObtenerCifrasControlServiceImpl implements IObtenerCifrasControlService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ObtenerCifrasControlServiceImpl.class);

	@Autowired
	ObtenerCifrasControlPersistence obtenerCifrasControlPersistence;
		
	public List<ObtenerCifrasControlVO> obtenerListaCifrasMovimientosCif(ObtenerCifrasControlVO param)
			throws Exception {
		return obtenerCifrasControlPersistence.getLstCifrasMovimientosCif(param);
	}

	public List<ObtenerCifrasControlVO> obtenerListaBancosyCuentasDestino(ObtenerCifrasControlVO param)
			throws Exception {
		return obtenerCifrasControlPersistence.getLstBancosYCuentasDestino(param);
	}

	public List<ObtenerCifrasControlVO> obtenerListaTotalDevsecure(ObtenerCifrasControlVO param) throws Exception {
		
		List<ObtenerCifrasControlVO> lista = obtenerCifrasControlPersistence.getLstTotalDevsecure(param);
		lista.add(obtenerCifrasControlPersistence.getTotalDeTotales(param));
		
		return lista;
	}

	public ObtenerCifrasControlVO obtenerTotalDeTotales(ObtenerCifrasControlVO param) throws Exception {
		return obtenerCifrasControlPersistence.getTotalDeTotales(param);
	}
	
}
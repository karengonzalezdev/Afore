package mx.secure.nci.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.secure.nci.business.service.IServiciosIdcService;
import mx.secure.nci.business.vo.ActualizaEstatusIdcVO;
import mx.secure.nci.business.vo.DetalleExcepcionesIdcVO;
import mx.secure.nci.business.vo.ReporteDetalleIdcVO;
import mx.secure.nci.business.vo.ResultadoIdcVO;
import mx.secure.nci.persistence.ServiciosIdcPersistence;

@Service("serviciosIdcService")
public class ServiciosIdcServiceImpl implements IServiciosIdcService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiciosIdcServiceImpl.class);

	@Autowired
	ServiciosIdcPersistence serviciosIdcPersistence;
	
	public Integer actualizaEstatusIdc(ActualizaEstatusIdcVO actualizaEstatusVO) throws Exception {
		return serviciosIdcPersistence.actualizaEstatusIdc(actualizaEstatusVO);
	}

	public List<DetalleExcepcionesIdcVO> obtenerListaDetalleExcepcionesIdc(DetalleExcepcionesIdcVO param)
			throws Exception {
		return serviciosIdcPersistence.getListaExcepcionesIdc(param);
	}
	
	public List<ReporteDetalleIdcVO> obtenerListaDetalleIdc(ReporteDetalleIdcVO param) throws Exception {
		return serviciosIdcPersistence.getListaDetalleIdc(param);
		
	}	
	
	public ResultadoIdcVO consultarResultadoIdc(ResultadoIdcVO param) throws Exception {
		return serviciosIdcPersistence.getResultadoIdc(param);
	}
}

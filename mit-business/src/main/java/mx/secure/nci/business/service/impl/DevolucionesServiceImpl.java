package mx.secure.nci.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.secure.nci.alux.persistence.secureALUXPersistence;
import mx.secure.nci.business.service.IDevolucionesService;
import mx.secure.nci.business.vo.CatalogoVO;
import mx.secure.nci.business.vo.DevolucionesCIFDetVO;
import mx.secure.nci.business.vo.DevolucionesCIFVO;
import mx.secure.nci.business.vo.DevolucionesVO;
import mx.secure.nci.business.vo.LoteControlAluxVO;
import mx.secure.nci.business.vo.LoteControlNciVO;
import mx.secure.nci.business.vo.cif.DevolucionesCabeceraCifVO;
import mx.secure.nci.business.vo.cif.DevolucionesDetalleCifVO;
import mx.secure.nci.cif.persistence.secureCIFPersistence;
import mx.secure.nci.persistence.secureNCIPersistence;

@Service("devolucionesService")
public class DevolucionesServiceImpl implements IDevolucionesService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DevolucionesServiceImpl.class);

	@Autowired
	secureALUXPersistence aluxPeristence;
	
	@Autowired
	secureNCIPersistence  nciPeristence;
	
	@Autowired
	secureCIFPersistence  cifPeristence;
	
	public List<DevolucionesVO> obtenerListaDevoluciones(DevolucionesVO param) throws Exception {
		List<DevolucionesVO> rgs = new ArrayList<DevolucionesVO>();
		/*DevolucionesVO uno = new DevolucionesVO();
		uno.setFolio("Folio");
		uno.setFolioConc("folioConc");
		uno.setFolioConciliacion("folioConciliacion");
		uno.setClaveSolicitud("claveSolicitud");
		uno.setIdConciliacion(1);
		uno.setBanderaExcesoDias(2);
		uno.setIdLote(3);
		uno.setCuentaIndividual("cuentaIndividual");
		uno.setRegConciliado(4);
		uno.setEstatusTramite(5);
		uno.setOrigenSolicitud(6);
		uno.setNss("nss");
		uno.setCurp("curp");
		uno.setNombreCte("nombreCte");
		uno.setPaternoCte("paternoCte");
		uno.setMaternoCte("maternoCte");
		uno.setEstatusSolicitud(7);
		uno.setFechaCargo(new Date());
		uno.setEstatusCargo(8);
		uno.setImporte(0.00);
		uno.setCuentaBanco("cuentaBanco");
		uno.setFechaProximoCargo(new Date());
		uno.setFechaEstatusSolicitud(new Date());
		uno.setOrigenAportacion(9);
		uno.setFechaPresentacion(new Date());
		uno.setFechaCreacionConc(new Date());
		uno.setFechaCreacion(new Date());
		uno.setUsuarioCreacion("usuarioCreacion");
		uno.setFechaActualizacion(new Date());
		uno.setUsuarioActualizacion("usuarioActualizacion");
		uno.setDescEstatusCargo("descEstatusCargo");
		uno.setDescEstatusSolicitud("descEstatusSolicitud");
		uno.setDescEstatusTramite("descEstatusTramite");
		uno.setDescOrigenAportacion("descOrigenAportacion");
		uno.setDescOrigenSolicitud("descOrigenSolicitud");
		rgs.add(uno);
		return rgs;*/
		return nciPeristence.getListaTablaDevoluciones(param);
		
	}	
	
	public List<LoteControlAluxVO> obtenerLotesAlux(LoteControlAluxVO param) throws Exception {
		LOGGER.info("::::::FOP::::::::::: ingresando a buscar lotes para ALUX::::::::::::" );
		List<LoteControlAluxVO> lotes = aluxPeristence.getListaLotes(param);
		return lotes;
	}
	
	
	public List<LoteControlNciVO> obtenerLotesNCI(LoteControlAluxVO ltsAlux) throws Exception {
		return nciPeristence.getListaLotesByEstatus(ltsAlux);
	}
	
	public List<LoteControlNciVO> obtenerLotesNCI(List<LoteControlAluxVO> ltsAlux, String estatus) throws Exception {
		List<LoteControlNciVO> rgs = new ArrayList<LoteControlNciVO>();
		List<CatalogoVO> cat = nciPeristence.getCatalogoEstatus();
		for (LoteControlAluxVO loteAlux : ltsAlux) {
			List<LoteControlNciVO> ltsNci = null;
			if(estatus == null) {
				ltsNci = nciPeristence.getListaLotes(loteAlux);
				if(ltsNci.isEmpty()) {
					LoteControlNciVO loteNci = new LoteControlNciVO(loteAlux.getIdLote().toString(), loteAlux.getNombreArchivo(), loteAlux.getFechaGeneracion(),
							loteAlux.getFechaPresentacion(), loteAlux.getFechaPresentacion(), loteAlux.getTotalRegistros(), new Short("0"));
					loteNci.setBanderaProcDesc(this.obtenDesCat(cat, "0"));
					rgs.add(loteNci);
				}else {
					rgs.add(ltsNci.get(0));
				}				
			}else{
				loteAlux.setEstatus(new Short(estatus));
				ltsNci = nciPeristence.getListaLotes(loteAlux);
				if(ltsNci.isEmpty()) {
					LoteControlNciVO loteNci = new LoteControlNciVO(loteAlux.getIdLote().toString(), loteAlux.getNombreArchivo(), loteAlux.getFechaGeneracion(),
							loteAlux.getFechaPresentacion(), loteAlux.getFechaPresentacion(), loteAlux.getTotalRegistros(), new Short("0"));
					
					loteNci.setBanderaProcDesc(this.obtenDesCat(cat, "0"));
					rgs.add(loteNci);
				}else {
					rgs.add(ltsNci.get(0));
				}
			}
		}
		return rgs;
	}
	
	@Transactional
	public Integer enviaDevolucionesCIF(List<String> param) throws Exception {
		// Voy por listado de CIF detalle
		int iD = 0;
    	List<DevolucionesCIFVO> ltsCif = nciPeristence.getListaDevolucionesByCIF(param);
		List<DevolucionesCIFDetVO> ltsCifDet = nciPeristence.getListaDevolucionesByCIFDet(param);
		for (DevolucionesCIFVO cifVO : ltsCif) {
			int r = cifPeristence.creaDevolucionesCIF(new DevolucionesCabeceraCifVO(cifVO.getIdItemDev(), cifVO.getNombreBeneficiario(), cifVO.getIdBancoDestino(), cifVO.getCtaBeneficiario(), cifVO.getDigitoVerificador(), 
					cifVO.getNumCtaIndividual(), cifVO.getImporte(), cifVO.getTipoCuenta(), cifVO.getTipoPago(), cifVO.getUsuCreacion(), cifVO.getUsuReviso(), cifVO.getUsuAutorizo()));
			if(r > 0) {
				iD ++;
			}
		}
		for (DevolucionesCIFDetVO cifDetVO : ltsCifDet) {
			cifPeristence.creaDevolucionesDetalleCIF(new DevolucionesDetalleCifVO(cifDetVO.getIdItemDev(), cifDetVO.getMonto()));
		}
		return new Integer(iD);
	}
	
	public Integer actualizaDevolucion(DevolucionesVO param) throws Exception {
		return nciPeristence.actualizarDevoluciones(param);
	}

	private String obtenDesCat(List<CatalogoVO> cat, String estatus) {
		for (CatalogoVO catalogoVO : cat) {
			if (catalogoVO.getValor().equals(estatus)) {
				return catalogoVO.getDesc();
			}
		}
		return null;
	}
	
}

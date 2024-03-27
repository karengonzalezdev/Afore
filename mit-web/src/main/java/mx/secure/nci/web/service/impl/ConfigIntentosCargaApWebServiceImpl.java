package mx.secure.nci.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.secure.nci.business.service.ICifrasControlClienteService;
import mx.secure.nci.business.service.IConfigIntentosCargasecureService;
import mx.secure.nci.business.vo.CatalogoVO;
import mx.secure.nci.business.vo.ConfigIntentossecureVO;
import mx.secure.nci.business.wrapped.*;
import mx.secure.nci.web.beans.DomiIntentosCargaFilterBean;
import mx.secure.nci.web.service.IConfigIntentosCargasecureWebService;

@Service("configIntentosCargasecureWebService")
public class ConfigIntentosCargasecureWebServiceImpl implements IConfigIntentosCargasecureWebService {

	@Autowired
	IConfigIntentosCargasecureService configIntentosCargasecureService;

	public List<CatalogoVO> consultaCatOrigenDomi() throws Exception {
		return configIntentosCargasecureService.consultaCatOrigenDomi();
	}

	public List<CatalogoVO> consultaCatPeriodo() throws Exception {
		return configIntentosCargasecureService.consultaCatPeriodo();
	}

	public List<ConfigIntentossecureVO> consultaConfigIntentossecure(
			DomiIntentosCargaFilterBean domiIntentoCargaFilterBean) throws Exception {
		DomiIntentosCargaFilter domiIntentoCarFilter = new DomiIntentosCargaFilter();

		if (domiIntentoCargaFilterBean.getIdOrigenDomiciliacion() != null) {
			domiIntentoCarFilter.setIdOrigenDomiciliacion(domiIntentoCargaFilterBean.getIdOrigenDomiciliacion());
		}

		if (domiIntentoCargaFilterBean.getIdPeriodo() != null) {
			domiIntentoCarFilter.setIdPeriodo(domiIntentoCargaFilterBean.getIdPeriodo());
		}
		return configIntentosCargasecureService.consultaConfigIntentossecure(domiIntentoCarFilter);
	}

	public Integer insertConfIntentosCarga(List<ConfigIntentossecureVO> listConfIntentossecure) throws Exception {
		return configIntentosCargasecureService.insertConfIntentosCarga(listConfIntentossecure);
	}

	public Integer editaConfIntentosCarga(ConfigIntentossecureVO confIntentossecure) throws Exception {
		return configIntentosCargasecureService.editaConfIntentosCarga(confIntentossecure);
	}

}

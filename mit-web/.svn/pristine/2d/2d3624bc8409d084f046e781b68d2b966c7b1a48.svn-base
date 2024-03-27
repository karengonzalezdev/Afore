package mx.profuturo.nci.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.service.ICifrasControlClienteService;
import mx.profuturo.nci.business.service.IConfigIntentosCargaApoVolService;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ConfigIntentosApoVolVO;
import mx.profuturo.nci.business.wrapped.*;
import mx.profuturo.nci.web.beans.DomiIntentosCargaFilterBean;
import mx.profuturo.nci.web.service.IConfigIntentosCargaApoVolWebService;

@Service("configIntentosCargaApoVolWebService")
public class ConfigIntentosCargaApoVolWebServiceImpl implements IConfigIntentosCargaApoVolWebService {

	@Autowired
	IConfigIntentosCargaApoVolService configIntentosCargaApoVolService;

	public List<CatalogoVO> consultaCatOrigenDomi() throws Exception {
		return configIntentosCargaApoVolService.consultaCatOrigenDomi();
	}

	public List<CatalogoVO> consultaCatPeriodo() throws Exception {
		return configIntentosCargaApoVolService.consultaCatPeriodo();
	}

	public List<ConfigIntentosApoVolVO> consultaConfigIntentosApoVol(
			DomiIntentosCargaFilterBean domiIntentoCargaFilterBean) throws Exception {
		DomiIntentosCargaFilter domiIntentoCarFilter = new DomiIntentosCargaFilter();

		if (domiIntentoCargaFilterBean.getIdOrigenDomiciliacion() != null) {
			domiIntentoCarFilter.setIdOrigenDomiciliacion(domiIntentoCargaFilterBean.getIdOrigenDomiciliacion());
		}

		if (domiIntentoCargaFilterBean.getIdPeriodo() != null) {
			domiIntentoCarFilter.setIdPeriodo(domiIntentoCargaFilterBean.getIdPeriodo());
		}
		return configIntentosCargaApoVolService.consultaConfigIntentosApoVol(domiIntentoCarFilter);
	}

	public Integer insertConfIntentosCarga(List<ConfigIntentosApoVolVO> listConfIntentosApoVol) throws Exception {
		return configIntentosCargaApoVolService.insertConfIntentosCarga(listConfIntentosApoVol);
	}

	public Integer editaConfIntentosCarga(ConfigIntentosApoVolVO confIntentosApoVol) throws Exception {
		return configIntentosCargaApoVolService.editaConfIntentosCarga(confIntentosApoVol);
	}

}

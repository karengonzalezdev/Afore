package mx.profuturo.nci.business.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.profuturo.nci.business.service.IConfigIntentosCargaApoVolService;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ConfigIntentosApoVolVO;
import mx.profuturo.nci.business.wrapped.DomiIntentosCargaFilter;
import mx.profuturo.nci.persistence.ConfigIntentosCargaApoVolPersistence;

@Service("configIntentosCargaApoVolService")
public class ConfigIntentosCargaApoVolImpl implements IConfigIntentosCargaApoVolService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigIntentosCargaApoVolImpl.class);
    
	@Autowired
	ConfigIntentosCargaApoVolPersistence configIntentosCargaApoVolPersistence;


	public List<CatalogoVO> consultaCatOrigenDomi() throws Exception {
        try {
        	return configIntentosCargaApoVolPersistence.consultaCatOrigenDomi();
        } catch (Exception ex) {
            LOGGER.error("ERROR UBICACION      : " + ConfigIntentosCargaApoVolImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO         : " + "consultaCatOrigenDomi");
            LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
            LOGGER.error("ERROR DESCRIPCION    : ", ex);
            throw ex;
        }
		
	}
	
	public List<CatalogoVO> consultaCatPeriodo() throws Exception {
		
        try {
        	return configIntentosCargaApoVolPersistence.consultaCatPeriodo();
        } catch (Exception ex) {
            LOGGER.error("ERROR UBICACION      : " + ConfigIntentosCargaApoVolImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO         : " + "consultaCatPeriodo");
            LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
            LOGGER.error("ERROR DESCRIPCION    : ", ex);
            throw ex;
        }
    }

	public List<ConfigIntentosApoVolVO> consultaConfigIntentosApoVol(DomiIntentosCargaFilter domiIntentoCargaFilter) throws Exception {
        try {
        	return configIntentosCargaApoVolPersistence.consultaConfigIntentosApoVol(domiIntentoCargaFilter);
        } catch (Exception ex) {
            LOGGER.error("ERROR UBICACION      : " + ConfigIntentosCargaApoVolImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO         : " + "consultaConfigIntentosApoVol");
            LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
            LOGGER.error("ERROR DESCRIPCION    : ", ex);
            throw ex;
        }
	}

	public Integer insertConfIntentosCarga(List<ConfigIntentosApoVolVO> listConfIntentosApoVol)  throws Exception {
        try {
        	return configIntentosCargaApoVolPersistence.insertConfIntentosCarga(listConfIntentosApoVol);
        } catch (Exception ex) {
            LOGGER.error("ERROR UBICACION      : " + ConfigIntentosCargaApoVolImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO         : " + "insertConfIntentosCarga");
            LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
            LOGGER.error("ERROR DESCRIPCION    : ", ex);
            throw ex;
        }
	}

	public Integer editaConfIntentosCarga(ConfigIntentosApoVolVO confIntentosApoVol) throws Exception {
		// TODO Auto-generated method stub
        try {
        	return configIntentosCargaApoVolPersistence.editaConfIntentosCarga(confIntentosApoVol);
        } catch (Exception ex) {
            LOGGER.error("ERROR UBICACION      : " + ConfigIntentosCargaApoVolImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO         : " + "editaConfIntentosCarga");
            LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
            LOGGER.error("ERROR DESCRIPCION    : ", ex);
            throw ex;
        }
	}


}

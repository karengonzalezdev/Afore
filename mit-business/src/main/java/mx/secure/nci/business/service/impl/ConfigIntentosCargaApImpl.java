package mx.secure.nci.business.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.secure.nci.business.service.IConfigIntentosCargasecureService;
import mx.secure.nci.business.vo.CatalogoVO;
import mx.secure.nci.business.vo.ConfigIntentossecureVO;
import mx.secure.nci.business.wrapped.DomiIntentosCargaFilter;
import mx.secure.nci.persistence.ConfigIntentosCargasecurePersistence;

@Service("configIntentosCargasecureService")
public class ConfigIntentosCargasecureImpl implements IConfigIntentosCargasecureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigIntentosCargasecureImpl.class);
    
	@Autowired
	ConfigIntentosCargasecurePersistence configIntentosCargasecurePersistence;


	public List<CatalogoVO> consultaCatOrigenDomi() throws Exception {
        try {
        	return configIntentosCargasecurePersistence.consultaCatOrigenDomi();
        } catch (Exception ex) {
            LOGGER.error("ERROR UBICACION      : " + ConfigIntentosCargasecureImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO         : " + "consultaCatOrigenDomi");
            LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
            LOGGER.error("ERROR DESCRIPCION    : ", ex);
            throw ex;
        }
		
	}
	
	public List<CatalogoVO> consultaCatPeriodo() throws Exception {
		
        try {
        	return configIntentosCargasecurePersistence.consultaCatPeriodo();
        } catch (Exception ex) {
            LOGGER.error("ERROR UBICACION      : " + ConfigIntentosCargasecureImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO         : " + "consultaCatPeriodo");
            LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
            LOGGER.error("ERROR DESCRIPCION    : ", ex);
            throw ex;
        }
    }

	public List<ConfigIntentossecureVO> consultaConfigIntentossecure(DomiIntentosCargaFilter domiIntentoCargaFilter) throws Exception {
        try {
        	return configIntentosCargasecurePersistence.consultaConfigIntentossecure(domiIntentoCargaFilter);
        } catch (Exception ex) {
            LOGGER.error("ERROR UBICACION      : " + ConfigIntentosCargasecureImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO         : " + "consultaConfigIntentossecure");
            LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
            LOGGER.error("ERROR DESCRIPCION    : ", ex);
            throw ex;
        }
	}

	public Integer insertConfIntentosCarga(List<ConfigIntentossecureVO> listConfIntentossecure)  throws Exception {
        try {
        	return configIntentosCargasecurePersistence.insertConfIntentosCarga(listConfIntentossecure);
        } catch (Exception ex) {
            LOGGER.error("ERROR UBICACION      : " + ConfigIntentosCargasecureImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO         : " + "insertConfIntentosCarga");
            LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
            LOGGER.error("ERROR DESCRIPCION    : ", ex);
            throw ex;
        }
	}

	public Integer editaConfIntentosCarga(ConfigIntentossecureVO confIntentossecure) throws Exception {
		// TODO Auto-generated method stub
        try {
        	return configIntentosCargasecurePersistence.editaConfIntentosCarga(confIntentossecure);
        } catch (Exception ex) {
            LOGGER.error("ERROR UBICACION      : " + ConfigIntentosCargasecureImpl.class.getCanonicalName());
            LOGGER.error("ERROR METODO         : " + "editaConfIntentosCarga");
            LOGGER.error("ERROR MENSAJE        : " + ex.getMessage());
            LOGGER.error("ERROR DESCRIPCION    : ", ex);
            throw ex;
        }
	}


}

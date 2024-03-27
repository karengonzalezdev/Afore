package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.CatalogoVO;
import mx.secure.nci.business.vo.ConfigIntentossecureVO;
import mx.secure.nci.business.vo.ReporteCifrasControlVO;
import mx.secure.nci.business.vo.SolicitudReclasificacionVO;
import mx.secure.nci.business.wrapped.DomiIntentosCargaFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface ConfigIntentosCargasecurePersistence {
	
	/**
	 * NO997418
	 * Metodo utilizado para recuperar una lista con los origenes Domiciliados vigentes
	 * actuamente
	 * @return
	 */
	public List<CatalogoVO> consultaCatOrigenDomi();
	
	
	/**
	 * NO997418
	 * Metodo utilizado para recuperar una lista con los Periodos
	 * actuamente
	 * @return
	 */
	public List<CatalogoVO> consultaCatPeriodo();
	
	/**
	 * NO997418
	 * Metodo utilizado para llenar la tabla con los valores de la tabla ttafotras_config_inten_secure
	 * actuamente
	 * @return
	 */
	public List<ConfigIntentossecureVO> consultaConfigIntentossecure(DomiIntentosCargaFilter domiIntentoCargaFilter) throws Exception;
	
	/**
	 * NO997418
	 * Metodo utilizado para insertar una nueva configuracion
	 * actuamente
	 * @return
	 */
	public Integer insertConfIntentosCarga(List<ConfigIntentossecureVO> listConfIntentossecure);
		
	/**
	 * NO997418
	 * Metodo utilizado para editar una nueva configuracion
	 * actuamente
	 * @return
	 */
	public Integer editaConfIntentosCarga(ConfigIntentossecureVO confIntentossecure);
		
	
	
}

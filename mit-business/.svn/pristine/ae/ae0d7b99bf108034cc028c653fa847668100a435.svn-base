package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ConfigIntentosApoVolVO;
import mx.profuturo.nci.business.vo.ReporteCifrasControlVO;
import mx.profuturo.nci.business.vo.SolicitudReclasificacionVO;
import mx.profuturo.nci.business.wrapped.DomiIntentosCargaFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface ConfigIntentosCargaApoVolPersistence {
	
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
	 * Metodo utilizado para llenar la tabla con los valores de la tabla ttafotras_config_inten_apovol
	 * actuamente
	 * @return
	 */
	public List<ConfigIntentosApoVolVO> consultaConfigIntentosApoVol(DomiIntentosCargaFilter domiIntentoCargaFilter) throws Exception;
	
	/**
	 * NO997418
	 * Metodo utilizado para insertar una nueva configuracion
	 * actuamente
	 * @return
	 */
	public Integer insertConfIntentosCarga(List<ConfigIntentosApoVolVO> listConfIntentosApoVol);
		
	/**
	 * NO997418
	 * Metodo utilizado para editar una nueva configuracion
	 * actuamente
	 * @return
	 */
	public Integer editaConfIntentosCarga(ConfigIntentosApoVolVO confIntentosApoVol);
		
	
	
}

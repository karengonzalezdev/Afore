package mx.profuturo.nci.business.util;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("rawtypes")
public enum VOMap {

		/* >>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		 * >>>>>>>>>>> INICIO ENUM DE CATALOGOS <<<<<<<<<<<<<<<<
		 * >>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		 */
//		CANAL(CanalVO.class,TccrxgralCatCatalogo.class,
//				new HashMap<String, String>() {
//			private static final long serialVersionUID = 1L;
//			{   put("idCanal",ATTRIBUTES_ID_CAT_CATALOGO);
//	//			put("descripcion",ATTRIBUTES_VALOR_CAT_CATALOGO);
//			}
//		}),	

//			
			
			
	;
	
	
/*
 *  >>>>>>>>>>>  VARIABLES ESTATICAS  <<<<<<<<<<<<<<<<	
 */
	 
	 private static final String ATTRIBUTES_ID_CAT_CATALOGO 	 = "idCatCatalogo";
	 private static final String ATTRIBUTES_VALOR_CAT_CATALOGO 	 = "valor";
	 
	 
	 
	 
	private Class claseVO;
	private Class entity;
	private Map<String, String> mapaPropiedades;
	
	
	private VOMap(Class claseVO, Class entity, Map<String, String> mapaPropiedades) {
		this.claseVO=claseVO;
		this.entity=entity;
		this.mapaPropiedades=mapaPropiedades;
	}
	
	public static VOMap getVOMapOf(Class claseVO){
		for(VOMap map:values()){
			if(map.getClaseVO().equals(claseVO)){
				return map;
			}
		}
		return null;
	}
	
	
	public Class getClaseVO() {
		return claseVO;
	}
	
	public Class getEntity() {
		return entity;
	}
	
	public Map<String, String> getMapaPropiedades() {
		return mapaPropiedades;
	}

}

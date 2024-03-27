package mx.profuturo.nci.business.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashIndexed3DMatrix<T> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HashIndexed3DMatrix.class);
	
	private Map<String, Map<String,Map<String,T>>> matrix;
	
	public HashIndexed3DMatrix() {
		matrix = new HashMap<String, Map<String,Map<String,T>>>();
	}
	
	public void put(String x, String y, String z, T cell){
		if(!isMainAxisPresent(x)){
			matrix.put(x, new HashMap<String, Map<String,T>>());
		}
		if(!isLayerPositionPresent(x,y)){
			matrix.get(x).put(y, new HashMap<String, T>());
		}
		matrix.get(x).get(y).put(z,cell);
	}
	
	public T get(String x, String y, String z){
		LOGGER.trace("Obteniendo:("+x+","+y+","+z+"):");
		if(isCellPositionPresent(x,y,z)){
			T ret = matrix.get(x).get(y).get(z);
			LOGGER.trace(String.valueOf(ret));
			return ret;
		}else{
			LOGGER.trace("No Existe");
			return null;
		}
	}
	
	public boolean isCellPositionPresent(String x, String y, String z){
		return isMainAxisPresent(x) 
				&& isLayerPositionPresent(x,y)
				&& matrix.get(x).get(y).containsKey(z);
	}
	
	public boolean isLayerPositionPresent(String x, String y){
		return matrix.containsKey(x) 
				&& matrix.get(x).containsKey(y);
	}
	
	public boolean isMainAxisPresent(String x){
		return matrix.containsKey(x) ;
	}
	
	public void print(){
		for(String x:matrix.keySet()){
			for(String y: matrix.get(x).keySet()){
				for(String z:matrix.get(x).get(y).keySet()){
					LOGGER.trace(x+"->"+y+"->"+z+":"+matrix.get(x).get(y).get(z).toString());
				}
			}
		}
	}
}

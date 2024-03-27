package mx.profuturo.nci.business.util;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.GenericTypeValidator;
import org.apache.commons.validator.GenericValidator;

@SuppressWarnings("rawtypes")
public final class UtilValidate 
{

	private UtilValidate() {	
	}
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/**
	 * VALIDA EL FORMATO DE UNA CADENA QUE SEA ENTERO A CIERTA LONGITUD 
	 * @param numero
	 * @param longitud
	 * @return
	 */
	public static boolean validaFormatoInteger( String numero, int longitud ) {
		String regex	= "[0-9]{1,"+longitud+"}";
		if( numero != null ) {
			return Pattern.matches(regex, numero);
		}else {
			return true;
		}
	}
	
	public static boolean validaFormatoInteger( Integer numero, int longitud ) {
		String regex	= "[0-9]{1,"+longitud+"}";
		if( numero != null ) {
			return Pattern.matches(regex, Integer.toString(numero));
		}else {
			return true;
		}
	}
	
	/**
	 * Valida si el objeto es nulo.
	 *
	 *	 @param object Object
	 *
	 * @return boolean
    */
	public static boolean isNull(Object object) {
		return (object == null);
	}
	
	  /**
	   * Valida si un objeto puede ser convertido a float.
	   *
	   * @param object Object
	   *
	   * @return boolean
	   */
	  public static boolean isFloatNotNull(Object object) {
	    if(!isNull(object)) {
	      return GenericValidator.isFloat(object.toString());
	    }

	    return false;
	  }
	  
	  /**
	   * Valida si un objeto es numerico.
	   *
	   * @param object Object
	   *
	   * @return boolean
	   */
	  public static boolean isNumber(Object object) {
	    if(isIntegerNotNull(object) || isLongNotNull(object) || isFloatNotNull(object) || isDoubleNotNull(object)) {
	      return true;
	    }

	    return false;
	  }	
	
	public static BigDecimal safeAddValue(BigDecimal value){
		  return ((value==null)?BigDecimal.ZERO:value);
	  }

	public static boolean isBlankOrNull(String cadena) {
		return GenericValidator.isBlankOrNull(cadena);
	}

	public static boolean isIntegerNotNull(Object objecto) {
		if(!isNull(objecto)) {
			return GenericValidator.isInt(objecto.toString());
		}
		return false;
	}

	public static boolean isLongNotNull(Object objecto) {
		if(!isNull(objecto)) {
			return GenericValidator.isLong(objecto.toString());
		}
		return false;
	}

	  /**
	   * Valida si un objeto puede ser convertido a Date.
	   *
	   * @param object Object
	   *
	   * @return boolean
	   */
	public static boolean isDateNotNull(Object object) {
		if(!isNull(object)) {
			if(object instanceof Date) {
				return true;
			}
			return GenericValidator.isDate(object.toString(), "dd/MM/yyyy", true);
		}
		return false;
	}

	  /**
	   * Valida si un objeto puede ser convertido a double.
	   *
	   * @param object Object
	   *
	   * @return boolean
	   */
	public static boolean isDoubleNotNull(Object object) {
		if(!isNull(object)) {
			return GenericValidator.isDouble(object.toString());
	    }

	    return false;
	}

	  /**
	   * Valida si una coleccion es nula o vacia.
	   *
	   * @param collection Collection
	   *
	   * @return boolean
	   */
	  public static boolean isEmptyOrNull( Collection collection) {
	    return ((collection == null) || collection.isEmpty());
	  }

	  /**
	   * Valida si un arreglo es nulo o vacio.
	   *
	   * @param array Object[]
	   *
	   * @return boolean
	   */
	  public static boolean isEmptyOrNull(Object[] array) {
	    return ((array == null) || isZero(array.length));
	  }

	  /**
	   * Valida si un mapa es nulo o vacio.
	   *
	   * @param map Map
	   *
	   * @return boolean
	   */
	  public static boolean isEmptyOrNull(Map map) {
	    return ((map == null) || map.isEmpty());
	  }

	  /**
	   * Valida si un numero es igual a 0.
	   *
	   * @param value double
	   *
	   * @return boolean
	   */
	  public static boolean isZero(double value) {
	    return value == 0;
	  }
	  /**
	   * Valida si un objeto es numerico e igual a 0.
	   *
	   * @param object Object
	   *
	   * @return boolean
	   */
	  public static boolean isZero(Object object) {
	    if(isIntegerNotNull(object)) {
	      return (parseInt(object) == 0);
	    }
	    if(isLongNotNull(object)) {
	      return (parseLong(object) == 0);
	    }
	    if(isFloatNotNull(object)) {
	      return (parseFloat(object) == 0);
	    }
	    if(isDoubleNotNull(object)) {
	      return (parseDouble(object) == 0);
	    }

	    return false;
	  }
	  
	  /**
	   * Valida si una cadena es nula, vacia o el numero 0.
	   *
	   * @param string String
	   *
	   * @return boolean
	   */
	  public static boolean isZeroBlankOrNull(String string) {
	    return (isBlankOrNull(string) || isZero(string));
	  }
	  
	  /**
	   * Valida si el numero (objeto) es nulo o es igual 0.
	   *
	   * @param number Number
	   *
	   * @return boolean
	   */
	  public static boolean isZeroOrNull(Number number) {
	    return (isNull(number) || isZero(number.doubleValue()));
	  }

	  /**
	   * Si es posible convierte el objeto a Date.
	   *
	   * @param object Object
	   *
	   * @return Date
	   */
	  public static Date parseDate(Object object) {
	    if(isDateNotNull(object)) {
	      if(object instanceof Date) {
	        return (Date) object;
	      }

	      return GenericTypeValidator.formatDate(object.toString(), "dd/MM/yyyy", true);
	    }

	    return null;
	  }

	  /**
	   * Si es posible convierte el objeto a double.
	   *
	   * @param object Object
	   *
	   * @return double
	   */
	  public static double parseDouble(Object object) {
	    if(isDoubleNotNull(object)) {
	      return GenericTypeValidator.formatDouble(object.toString()).doubleValue();
	    }

	    return 0;
	  }

	  /**
	   * Si es posible convierte el objeto a float.
	   *
	   * @param object Object
	   *
	   * @return float
	   */
	  public static float parseFloat(Object object) {
	    if(isFloatNotNull(object)) {
	      return GenericTypeValidator.formatFloat(object.toString()).floatValue();
	    }

	    return 0;
	  }

	  /**
	   * Si es posible convierte el objeto a int.
	   *
	   * @param object Objeto
	   *
	   * @return int
	   */
	  public static int parseInt(Object object) {
	    if(isIntegerNotNull(object)) {
	      return GenericTypeValidator.formatInt(object.toString()).intValue();
	    }

	    return 0;
	  }

	  /**
	   * Si es posible convierte el objeto a long.
	   *
	   * @param object Object
	   *
	   * @return long
	   */
	  public static long parseLong(Object object) {
	    if(isLongNotNull(object)) {
	      return GenericTypeValidator.formatLong(object.toString()).longValue();
	    }

	    return 0;
	  }

	  /**
	   * Si es posible convierte el objeto a String.
	   *
	   * @param object Object
	   *
	   * @return String
	   */
	  public static String parseString(Object object) {
	    if(isNull(object)) {
	      return null;
	    }

	    return object.toString();
	  }

	  /**
	   * Si es posible convierte el objeto a String aplicando el patron especificado. Si el objeto es instancia de Date utiliza la utileria de SimpleDateFormat, si el objeto es instancia de Number
	   * utiliza la utileria DecimalFormat en cualquier otro caso utiliza la utileria MessageFormat.
	   *
	   * @param pattern String
	   * @param object Object
	   *
	   * @return String
	   */
	  public static String parseString(String pattern, Object object) {
	    if(isNull(object)) {
	      return null;
	    }
	    if(isNull(pattern)) {
	      return parseString(object);
	    }

	    if(object instanceof Date) {
	      return new SimpleDateFormat(pattern).format((Date) object);
	    }
	    if(object instanceof Number) {
	      return new DecimalFormat(pattern).format((Number) object);
	    }

	    return MessageFormat.format(pattern, object);
	  }
	  
	  /**
	   * Método de Negocio para Validar el Correo Electronico.
	   *
	   * @param correoElectronico String
	   * @return Boolean
	   * @throws Exception En caso de ocurrir un error.
	   *
	   */
	  public static boolean validarCorreo(String correoElectronico) {
		  Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		  Matcher matcher = pattern.matcher(correoElectronico);
		  return matcher.matches();
	  }
	  
	  /**
	   * Método de Negocio para Validar formato de fecha.
	   *
	   * @param fecha String
	   * @return Boolean
	   * @throws Exception En caso de ocurrir un error.
	   *
	   */
	  public static boolean validarFechaPorFormato(String fecha, String formato) {
	        try {
	            SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
	            formatoFecha.setLenient(false);
	            formatoFecha.parse(fecha);
	        } catch (Exception e) {
	            return false;
	        }
	        return true;
	    }

	public static boolean validaFormatoIntegerF4( String numero, int longitud ) {
		String regex	= "[0-9]{1,"+longitud+"}";
		if( numero != null ) {
			return Pattern.matches(regex, numero);
		}else {
			return false;
		}
	}

	public static boolean validaFormatoStringF4(String cadena, int longitud) {
		if (cadena != null) {
			return ( (cadena.length() <= longitud) && (cadena.length() > 0) );
		} else {
			return Boolean.FALSE;
		}
	}

	public static boolean validarFechaPorFormatoF4(String fecha, String formato) {
		try {
			if (fecha != null) {
				formato.contains("");
				SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
				formatoFecha.setLenient(false);
				formatoFecha.parse(fecha);
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}
	
	/**
	 * Metodo que permite decodificar un arreglo de byte con base 64 a string
	 * @param codigicado
	 */
	public static String decodificarArrayToBase64(byte[] codigicado) {
		String decodificado = new String(codigicado);
		return decodificado;
	}
	
	
	public static  Boolean generaArchivoCsv(String texto, String nombreCsv, String ruta) throws Exception {
		FileWriter fileWriter = null;
		try {
					
			fileWriter = new FileWriter(ruta+nombreCsv);
			fileWriter.append(texto);

			return true;
			
		} catch (Exception e) {
			throw new Exception("No fue posible crear el archivo");
			
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				throw e;
			}
		}
	}
	
	public static String[] splitToString(String pTexto, String pCaracterSplit) {
		String stringarray1[] = pTexto.split(pCaracterSplit);
		return stringarray1;
	}
	
	/**
	 * Metodo que permite identificar si el rrayToBase64 is empty
	 */
	public static boolean  arrayToBase64IsEmpty(byte[] array) {
		return ((array == null) || isZero(array.length));
	}
	
	/**
	 * Valida que la extencion de un archivo sea valida
	 */
	public static boolean  extensionValidaArchivo(String nombreArchivo, String extencionValida) {
		String nombreArch = nombreArchivo.toUpperCase();
		String extencion = extencionValida.toUpperCase();
		return nombreArch.contains(extencion);
	}
		

}

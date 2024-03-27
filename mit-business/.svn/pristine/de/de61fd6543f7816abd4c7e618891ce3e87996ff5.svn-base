package mx.profuturo.nci.business.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;

public final class UtilMethod 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilMethod.class);
	
	public static Boolean stringBooleanToBoolean(String stringBoolean)
	{
		if(StringUtils.isNotEmpty(stringBoolean) && stringBoolean.equals(Constantes.STRING_TRUE))
		{
			return Boolean.TRUE; 
		}
		else
		{
			 return Boolean.FALSE;	
		}
		 	
	}
	
	public static String booleanToStringBoolean(Boolean valueBoolean)
	{
		if(BooleanUtils.isTrue(valueBoolean))
		{
			return Constantes.STRING_TRUE;
		}
		else if(BooleanUtils.isFalse(valueBoolean)) 
		{
			return Constantes.STRING_FALSE;
		}
		else
		{
			return null;
		}
	}
	
	public static List<String> split(String cadena,String regex) throws MitBusinessException
	{
		List<String> listString=null;
		
		try
		{
			if(!cadena.isEmpty())
			{
				String[] arrayString =  cadena.split(regex);
				
				if(!ArrayUtils.isEmpty(arrayString))
				{				
					listString  = new ArrayList<String>(Arrays.asList(arrayString));				
				}
			}
			
			return listString;
		}
		catch(Exception ex)
		{
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(
							ErrorCodeService.EX_EXCEPTION,
							"Al realizar split", new Object[] {
									"UtilMethod", "split()" }, ex));
			
				LOGGER.error(mitBusinessException.getMessage(),ex);
			
				throw mitBusinessException;			
		}
		
	}
	
	
	public static String leftPadCerosNumeroCuenta(Long numeroCuenta) throws MitBusinessException
	{
		return leftPadCeros(numeroCuenta,10);
	}
	
	
	public static String leftPadCeros(Long numero,int lenght) throws MitBusinessException{
		return 	leftPadCeros(String.valueOf(numero) , lenght);
	}
	
	public static String leftPadCeros(String numero,int lenght) throws MitBusinessException{
		try{
			if(numero != null){
				return StringUtils.leftPad(numero , lenght, "0"); 				
			} else {
				return null; 
			}
			
		} catch(Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(
							ErrorCodeService.EX_EXCEPTION,
							"Al realizar LeftPad Ceros En el Numero", new Object[] {
									UtilMethod.class.toString(), "leftPadCeros()" }, ex));
				LOGGER.error(mitBusinessException.getMessage(),ex);
				throw mitBusinessException;			
		}		
	}
	
	public static Date truncateHour(Date date){
		Calendar cal = Calendar.getInstance(); // locale-specific
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Date(cal.getTimeInMillis());
	}

	public static Date getDateWithDayOffset(Date date, int dayOffset){
		Calendar cal = Calendar.getInstance(); // locale-specific
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, dayOffset);
		return new Date(cal.getTimeInMillis());
	}
	public static String dateFormatter(Date date, String format){
		SimpleDateFormat formato = new SimpleDateFormat(format);
		return formato.format(date);
	}
	
	public static boolean isDateOlderthanToday(Date fecha){
		return System.currentTimeMillis() - fecha.getTime() < 0;
	}
	
	public static Long extractNumber(String number) {
		Long num=null;
		try {
			num = Long.parseLong(number.replaceAll("\\D+",""));
		}catch(Exception e) {
			num=null;
		}
		return num;
	}
	
	/**
	 * Algoritmo entregado por Profuturo para el calculo del digito verificador del ID PAGO de APORTACIÓN VOLUNTARIA
	 * @param numero de referencia
	 * @return digito verificador de pago de aportacion voluntaria
	 * @throws Exception
	 */
	public static char digitoVerificador(String s) throws Exception {
		int i = 0;
		int ai[] = { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
		try {
			for (int j1 = 0; j1 < s.length(); j1++) {
				int j = (s.charAt(j1) - 48) * ai[j1];
				if (j >= 10) {
					j -= 9;
				}
				i += j;
			}

			int k = i / 10;
			k *= 10;
			int l = i - k;
			int i1 = 10 - l;
			if (i1 == 10) {
				i1 = 0;
			}
			char c = (char) (i1 + 48);
			return c;
		} catch (Exception exception) {
			throw new Exception(exception.getMessage());
		}
	}
	
	public static String createAjaxRedirectXml(String redirectUrl) {
		return new StringBuilder()
					.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
					.append("<partial-response><redirect url=\"")
					.append(redirectUrl)
					.append("\"></redirect></partial-response>")
					.toString();
	}
	
	/**
	 * 
	 * Metodos random para rellenar objetos dummy
	 * 
	 */
	public static final String[] names = 
			new String[] {"Carmen",	"Francisco Manuel",
					"María Dolores","Miguel",
					"Andrés","Julio",
					"Laura","Juan Carlos",
					"Julián","Manuel",
					"Alejandro","Antonia",
					"José Luis","Gemma",
					"Ana",	"Carlos",
					"Ana María", "Antonio",
					"Rafael","Francisco José",			};
	
	public static final String[] lastNames = 
			new String[] {"Márquez","López",
					"Esparza","Romero",
					"Navarro","Madrigal",
					"Cruz","Villar",
					"Castell","Muñoz",
					"Gallardo","Cornejo",
					"Díaz","Rodríguez",
					"Lorente","Sánchez",
					"Martínez","Moya",
					"Gutiérrez","Espinar",             };
	
	public static final String[] cusps = 
			new String[] {"HR998743",
					"EP998753",
					"KS006334",
					"RG500331",
					"LM500129",
					"FT003394",
					"VA004816",
					"CM006057",
					"FG003146",
					"LS500012",
					"OR003519",
					"LS500012",
					"JG500725"};
	
	public static Integer getRandomInteger(Integer max){
		Random random = new Random();
		int index = Math.abs(random.nextInt()) % (max+1);
		return index;
	}
	
	public static BigDecimal getRandomMonto(BigDecimal max) {
		BigDecimal monto = new BigDecimal(getRandomInteger(max.intValue())*100);
		return monto;
	}
	
	public static String getRandomName() {
		return names[getRandomInteger(names.length-1)];
	}
	
	
	public static String getRandomLastName() {
		return lastNames[getRandomInteger(lastNames.length-1)];
	}
	
	public static String getRandomCusps() {
		return cusps[getRandomInteger(cusps.length-1)];
	}
	
	public static String getRandomNumericString(int maxLenght) {
		StringBuffer sb = new StringBuffer();
		for(int i =0;i<maxLenght;i++) {
			sb.append(getRandomInteger(10));
		}
		return sb.toString();
	}
	
	public static Date getRandomDate(Integer maxDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -getRandomInteger(maxDays));
		return calendar.getTime();
	}
	
	public static <T>T getRandomFromArray(T[] t) {
		return t[getRandomInteger(t.length-1)];
	}
	
	public static Date getDateToString(String fecha, String formato) {
		try {
			return new SimpleDateFormat(formato).parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;  
	}	
}

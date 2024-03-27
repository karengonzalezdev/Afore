package mx.profuturo.nci.business.file.generator.handler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.beanio.types.TypeConversionException;
import org.beanio.types.TypeHandler;

public class CurrencyTypeHandler implements TypeHandler{
	DecimalFormat df = new DecimalFormat();
	private static final BigDecimal BD_100=new BigDecimal(100);
	
	public String format(Object paramObject) {
		BigDecimal val = null;
		
		if(paramObject!=null) {
			val = (BigDecimal)paramObject;
		}else{
			val= new BigDecimal(0);
		}
		BigDecimal newValue =val.multiply(BD_100);
		newValue=newValue.setScale(0, RoundingMode.FLOOR);
		return  newValue.toString();
	}

	public Class<?> getType() {
		return BigDecimal.class;
	}

	public Object parse(String paramString) throws TypeConversionException {
		return new BigDecimal(paramString);
	}

	
}

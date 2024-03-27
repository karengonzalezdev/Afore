package mx.profuturo.nci.business.file.generator.service.impl;

import java.io.StringWriter;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.beanio.builder.FixedLengthParserBuilder;
import org.beanio.builder.StreamBuilder;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.file.generator.handler.CurrencyTypeHandler;
import mx.profuturo.nci.business.file.generator.service.IFilesGeneratorService;

@Service("fileGeneratorService")
public class FilesGeneratorServiceImpl implements IFilesGeneratorService {

	public <T> StringWriter generateFile(T record) {
    	StringWriter sWriter = new StringWriter();
        StreamFactory factory = StreamFactory.newInstance();
        FixedLengthParserBuilder flpb = new FixedLengthParserBuilder();
        flpb.recordTerminator("\r\n");
        
        StreamBuilder builder = 
        		new StreamBuilder("recordSet")
				        .format("fixedlength")
				        .parser(flpb)
				        .addGroup(record.getClass())
				        .addTypeHandler("currencyTypeHandler", new CurrencyTypeHandler());
        
        factory.define(builder);
        
        BeanWriter out = factory.createWriter("recordSet", sWriter);        
              
        out.write(record);
        
        out.flush();
        out.close();
    	
    	return sWriter;
	}
	
	
	public <T> StringWriter generateFileF4(T record) {
		
		StringWriter sWriter 			= null;
		StreamFactory factory 			= null;
		FixedLengthParserBuilder flpb 	= null;
		StreamBuilder builder 			= null;
		BeanWriter out 					= null;
		
		try {
			sWriter = new StringWriter();
	        factory = StreamFactory.newInstance();
	        flpb 	= new FixedLengthParserBuilder();
	        flpb.recordTerminator("\r\n");
	        
	        builder = 
	        		new StreamBuilder("recordSet")
					        .format("fixedlength")
					        .parser(flpb)
					        .addGroup(record.getClass())
					        .addTypeHandler("currencyTypeHandler", new CurrencyTypeHandler());
	        
	        factory.define(builder);
	        out = factory.createWriter("recordSet", sWriter);        
	        out.write(record);
	        out.flush();
	        out.close();
	    	return sWriter;
		}catch( Exception e ) {
			return null;
		}finally {
			sWriter 	= null;
			factory 	= null;
			flpb 		= null;
			builder 	= null;
			out 		= null;
		}
    	
	}
	
}

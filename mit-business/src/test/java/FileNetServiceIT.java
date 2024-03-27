package mx.secure.nci.business.service.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jeveris.core.exception.impl.BusinessException;

import mx.secure.nci.business.service.IFilenetService;
import mx.secure.nci.business.service.impl.WSPortTypeFactory;
import secure.mx.iib.secure.filenet.filenetservice.v1.types.ArchivoFileNet;
import secure.mx.iib.secure.filenet.filenetservice.v1.types.ListaArchivosFileNet;
import secure.mx.iib.secure.filenet.filenetservice.v1.types.SubirArchivosReq;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/integration-core-config.xml",
		"classpath:/spring/app-nci-business-beans-test.xml", "classpath:/spring/app-nci-persistence-db-test.xml",
		"classpath:/spring/app-nci-persistence-mybatis-test.xml" })
public class FileNetServiceIT {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileNetServiceIT.class);
	
	@Autowired
	WSPortTypeFactory wsPortTypeFactory;

	@Autowired
	IFilenetService filenetService;

	@Test
	public void consultarIT() throws BusinessException {
		try
		{
			byte[] bFile = readBytesFromFile("C:\\Users\\efuentem\\Documents\\FASE 2 JAVA\\archivo.txt");
			SubirArchivosReq parameters = null;
			parameters = new SubirArchivosReq();
			parameters.setClaveApp("prueba");
			ListaArchivosFileNet value = new ListaArchivosFileNet();		
			List<ArchivoFileNet> listaArchivos = new ArrayList<ArchivoFileNet>();
			ArchivoFileNet archivo = new ArchivoFileNet();
			archivo.setNombreArchivo("uno");
			archivo.setContenido(bFile);
			listaArchivos.add(archivo);
			value.setArchivo(listaArchivos);
			parameters.setArchivos(value);
			//LOGGER.info(wsPortTypeFactory.fileNetPortType().subirArchivos(parameters));
			LOGGER.info(""+filenetService.guardarArchivos(parameters));
		}
		catch(Exception e)
		{
			LOGGER.error("Error consumiendo recurso "+e.getMessage(),e);
		}
	}
	  private static byte[] readBytesFromFile(String filePath) {

	        FileInputStream fileInputStream = null;
	        byte[] bytesArray = null;

	        try {

	            File file = new File(filePath);
	            bytesArray = new byte[(int) file.length()];

	            //read file into bytes[]
	            fileInputStream = new FileInputStream(file);
	            fileInputStream.read(bytesArray);

	        } catch (IOException e) {
	        	LOGGER.error("Error consumiendo recurso "+e.getMessage(),e);
	        } finally {
	            if (fileInputStream != null) {
	                try {
	                    fileInputStream.close();
	                } catch (IOException e) {
	                    LOGGER.error("ERROR UBICACION",e);
	                }
	            }

	        }

	        return bytesArray;

	    }

}

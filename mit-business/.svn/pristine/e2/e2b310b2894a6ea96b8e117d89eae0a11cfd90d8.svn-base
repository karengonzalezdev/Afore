package mx.profuturo.nci.business.file.generator.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.file.generator.bean.DataFileBean;
import mx.profuturo.nci.business.file.generator.service.IFileTransferService;

@Service("fileTransferService")
public class LocaFileTransferServiceImpl implements IFileTransferService{

	public Boolean sendFile(StringBuffer sb, String path) throws IOException {
		File f = new File(path);
		FileWriter fw  = new FileWriter(f);
		Boolean resp=Boolean.FALSE;
		fw.write(sb.toString());	
		fw.flush();
		fw.close();
		resp=f.exists();
		return resp;
	}
	
	public Boolean sendFile(DataFileBean dfb) throws IOException {
		Boolean resp=Boolean.FALSE;
		System.out.println("ENVIANDO ARCHIVO F4 :::");
		try {
			
			if(dfb!=null && dfb.getStream()!=null && dfb.getFile()!=null){
				FileOutputStream fos = new FileOutputStream(dfb.getFile());
				fos.write(dfb.getStream(),0,dfb.getStream().length);	
				fos.flush();
				fos.close();
				resp=dfb.getFile().exists();
			}
			
			System.out.println("ENVIANDO ARCHIVO F4 OK :::");
			return resp;
			
		}catch( Exception e ) {
			System.out.println("ERROR SEND FILE F4:: " + e.getMessage());
			e.printStackTrace();
			throw ((IOException) e);
		}
	}

}

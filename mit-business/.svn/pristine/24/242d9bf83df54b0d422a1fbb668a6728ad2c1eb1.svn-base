package mx.profuturo.nci.business.file.generator.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import mx.profuturo.nci.business.file.generator.bean.DataFileBean;
import mx.profuturo.nci.business.file.generator.service.IFileTransferService;
import mx.profuturo.nci.business.service.impl.GeneracionArchivoDomiServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static mx.profuturo.nci.business.util.Constantes.PROFUTURO_REPOSITORIO_TESORERIA_DOMAIN;
import static mx.profuturo.nci.business.util.Constantes.PROFUTURO_REPOSITORIO_TESORERIA_USERNAME;
import static mx.profuturo.nci.business.util.Constantes.PROFUTURO_REPOSITORIO_TESORERIA_PASSWORD;

//@Service("fileTransferService")
public class SambaFileTransferServiceImpl implements IFileTransferService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SambaFileTransferServiceImpl.class);
//    static String SAMBAPATH="smb://PATH";

	public Boolean sendFile(StringBuffer sb, String sambaPath) throws IOException {
		Boolean response=Boolean.FALSE;
    	NtlmPasswordAuthentication auth = 
    			new NtlmPasswordAuthentication(
    					PROFUTURO_REPOSITORIO_TESORERIA_DOMAIN, 
    					PROFUTURO_REPOSITORIO_TESORERIA_USERNAME, 
    					PROFUTURO_REPOSITORIO_TESORERIA_PASSWORD);
        SmbFile smb=null;
        SmbFileOutputStream sops =null;
		try {
			smb = new SmbFile(sambaPath, auth);
			sops = new SmbFileOutputStream(smb);
			
			StringReader sr = new StringReader(sb.toString());
			
			BufferedReader brl = new BufferedReader(sr);
			String b = null;
			while((b=brl.readLine())!=null){
				sops.write(b.getBytes());
			}
			
			sops.flush();
			response=Boolean.TRUE;
		}finally{
				try {
					if(sops!=null){	sops.close();	}
				} catch (IOException e) {
					LOGGER.error("ERROR UBICACION :",e);
				}
		}
		return response;
	}

	public Boolean sendFile(DataFileBean dfb) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	

}

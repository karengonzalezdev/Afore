package mx.secure.nci.business.service;

import mx.secure.nci.business.exception.MitBusinessException;
import secure.mx.iib.secure.filenet.filenetservice.v1.types.SubirArchivosReq;

public interface IFilenetService {
	
	public int guardarArchivos(SubirArchivosReq subirArchivosReq) throws MitBusinessException;
}

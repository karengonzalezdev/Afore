package mx.secure.nci.business.file.generator.service;

import java.io.StringWriter;

public interface IFilesGeneratorService {

	<T>StringWriter generateFile(T record);
	<T>StringWriter generateFileF4(T record);
	
}

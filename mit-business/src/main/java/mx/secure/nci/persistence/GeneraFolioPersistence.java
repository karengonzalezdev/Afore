package mx.secure.nci.persistence;

import mx.secure.nci.business.wrapped.GeneraFolioFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface GeneraFolioPersistence 
{
	void generaFolio(GeneraFolioFilter foliofilter);	
}

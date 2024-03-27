package mx.secure.nci.persistence;

import mx.secure.nci.business.wrapped.FolioFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface FolioPersistence 
{
	public int insert(FolioFilter foliofilter);	
}

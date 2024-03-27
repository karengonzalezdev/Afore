package mx.secure.nci.web.service;

import java.util.List;

import javax.faces.model.SelectItem;

import mx.secure.nci.business.exception.MitBusinessException;

public interface ICatalogoWebService {
	
	public List<SelectItem> consultaCatalogo(List<Short> lstIdsCatalogos) throws MitBusinessException;
}
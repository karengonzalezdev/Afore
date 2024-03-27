package mx.secure.nci.web.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.web.beans.ConfigSubprocesoOrigenBean;

public interface IDepositoArchivoWebService 
{
	public List<ConfigSubprocesoOrigenBean> obtenerConfigSubprocesoOrigen() throws MitBusinessException;

}

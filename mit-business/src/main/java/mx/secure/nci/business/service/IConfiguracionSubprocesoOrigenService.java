package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.ConfigSubprocesoOrigenVO;

public interface IConfiguracionSubprocesoOrigenService
{
	
	public List<ConfigSubprocesoOrigenVO> consultarTodo() throws MitBusinessException;

}

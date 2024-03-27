package mx.profuturo.nci.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.cliente.service.IWSCOperacionesLdapService;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.ICatalogosService;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ParamCataConfigVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.business.vo.RolVO;
import mx.profuturo.nci.web.service.ICatalogoWebService;
import mx.profuturo.nci.web.service.IRolesWebService;

@Service("rolesWebService")
public class RolesWebServiceImpl implements
IRolesWebService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RolesWebServiceImpl.class);
	
	@Autowired IWSCOperacionesLdapService wscOperacionesLdapService;

	public List<RolVO> obtenerRoles(String username) throws MitBusinessException{
		List<RolVO> lstRoles = new ArrayList<RolVO>();
		
		try {
			lstRoles = wscOperacionesLdapService.getRoles(username);
		} catch (Exception e) {
			throw new BadCredentialsException(e.getMessage());
		}
		return lstRoles;
	}
}
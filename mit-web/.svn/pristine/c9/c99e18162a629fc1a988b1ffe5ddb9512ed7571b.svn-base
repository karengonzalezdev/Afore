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
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.ICatalogosService;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ParamCataConfigVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.web.service.ICatalogoWebService;

@Service("catalogoWebService")
public class CatalogoWebServiceImpl implements
ICatalogoWebService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogoWebServiceImpl.class);
	
	@Autowired ICatalogosService catalogosService;

	public List<SelectItem> consultaCatalogo(List<Short> lstIdsCatalogos) throws MitBusinessException{
		List<SelectItem> lstCata = new ArrayList<SelectItem>();
		
		for(Short bean:lstIdsCatalogos){
			ParamCatalogoVO param = new ParamCatalogoVO();
			param.setIdCatalogo(bean);
			
			List<CatalogoVO> resConsulta = catalogosService.consultarLista(param);
			for(CatalogoVO vo : resConsulta){
				SelectItem item = new SelectItem(vo.getIdCatCatalogo(), vo.getValor()); 
				lstCata.add(item);
			}
		}
		return lstCata;
	}
}
package mx.profuturo.nci.business.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import mx.profuturo.nci.business.exception.MitBusinessException;

public interface IGeneraFolioService 
{
	String generaFolioAforeMovil(Integer proceso, Integer subProceso, Integer canal, Short estatus, 
			                     String usuario)throws MitBusinessException;
	
	String generaFolio(Integer proceso, Integer subProceso, Integer canal, Short estatus, String usuario, 
			           BigDecimal importe, String nombre, String apPaterno, String apMaterno, String curp,
			           String numCtaInd, String rfc, String nss, String correo, String celular)throws MitBusinessException;
	
	int generaRegistroConciliacion(String folio, Short origen, Long numCtaInd, BigDecimal importe, String nombre,
            String apPaterno, String apMaterno, String curp, String rfc, Long nss, String correo,
            Long celular, String usuario, BigInteger subctaApovol) throws MitBusinessException;
	
	boolean invocaServicioLiberar(List<String> lstCurp, String usuario) throws MitBusinessException;
}

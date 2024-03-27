package mx.profuturo.nci.web.util;

import java.util.HashMap;
import java.util.Map;

import mx.profuturo.nci.business.vo.ArchivosGeneradosVO;
import mx.profuturo.nci.business.vo.BancosVO;
import mx.profuturo.nci.business.vo.ConfigSubprocesoOrigenVO;
import mx.profuturo.nci.business.vo.ConfiguracionSubprocesoVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.ProcesoVO;
import mx.profuturo.nci.business.vo.SubprocesoVO;
import mx.profuturo.nci.business.wrapped.NCI_CIFExtVo;
import mx.profuturo.nci.web.beans.ArchivosGeneradosBean;
import mx.profuturo.nci.web.beans.BancosBean;
import mx.profuturo.nci.web.beans.ConfigSubprocesoOrigenBean;
import mx.profuturo.nci.web.beans.ConfiguracionSubprocesoBean;
import mx.profuturo.nci.web.beans.GenericCatalogoBean;
import mx.profuturo.nci.web.beans.ProcesoBean;
import mx.profuturo.nci.web.beans.SubprocesoBean;
import mx.profuturo.nci.web.beans.NCI_CIFDataBean;

@SuppressWarnings("rawtypes")
public enum BeanMap 
{
	
	GENERIC_CATALOGO(GenericCatalogoBean.class, GenericCatalogoVO.class,
			new HashMap<String, String>() 
			{
				private static final long serialVersionUID = 1L;
				{
					put(ATTRIBUTE_CAT_GENERICO_ID,ATTRIBUTE_CAT_GENERICO_ID);
					put(ATTRIBUTE_CAT_GENERICO_VALOR,ATTRIBUTE_CAT_GENERICO_VALOR);
			
				}
			}),
	BANCOS(BancosBean.class, BancosVO.class,
			new HashMap<String, String>() 
			{
				private static final long serialVersionUID = 1L;
				{
					put("folio","folio");
//					put("folioRelacionado","folioRelacionado");
					put("cuenta","cuenta");
					put("autBanco","autBanco");
					put("importe","importe");
					put("fechaCarga","fechaCarga");
					put("fechaDeposito","fechaDeposito");
					put("numCuentaIndividual","numCuentaIndividual");
					put("curp","curp");
					put("regConciliado","regConciliado");
					put("rowId","rowId");
				}
			}),
	ARCHIVOS_GENERADOS(ArchivosGeneradosBean.class, ArchivosGeneradosVO.class,
			new HashMap<String, String>() 
			{
				private static final long serialVersionUID = 1L;
				{
					put("idArchivo","idArchivo");
					put("fechaInicio","fechaInicio");
					put("fechaFin","fechaFin");
					put("estatus","estatus");
					put("claveBanco","claveBanco");
					//put("idTipoCuenta","idTipoCuenta");
					
				}
			}),
			
		CONFIG_SUBPROCESO_ORIGEN(ConfigSubprocesoOrigenBean.class,
				ConfigSubprocesoOrigenVO.class, new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("mascaraArchivo", "mascaraArchivo");
						put("indiceFecha", "indiceFecha");
						put("formatoFecha", "formatoFecha");
						put("vigencia", "vigencia");
					}
				}),

		CONFIG_SUBPROCESO(ConfiguracionSubprocesoBean.class,
				ConfiguracionSubprocesoVO.class, new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("mascaraArchivo", "mascaraArchivo");
						put("rutaFilesystem", "rutaFilesystem");
					}
				}),
				PROCESO(ProcesoBean.class, ProcesoVO.class, new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("idProceso", "idProceso");
						put("descProceso", "descProceso");
					}
				}),

		SUBPROCESO(SubprocesoBean.class, SubprocesoVO.class,
				new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("idSubproceso", "idSubproceso");
						put("descSubproceso", "descSubproceso");
					}
				}),
		CIF_BEAN(NCI_CIFDataBean.class,
				NCI_CIFExtVo.class, new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("idConciliacion", "idConciliacion");
						put("seleccion", "seleccion");
						put("fechaCreacion", "fechaCreacion");
						put("usuarioCreacion", "usuarioCreacion");
						put("fechaActualizacion", "fechaActualizacion");
						put("usuarioActualizacion", "usuarioActualizacion");
						put("fechaEnvioCIF", "fechaEnvioCIF");
						put("usuarioEnvioCIF", "usuarioEnvioCIF");
						put("idItem", "idItem");
						put("idItemNew", "idItemNew");
						put("unidadDeNegocio", "unidadDeNegocio");
						put("importe", "importe");
						put("idCliente", "idCliente");
						put("fechaItem", "fechaItem");
						put("fechaContable", "fechaContable");
						put("fechaIntroduccion", "fechaIntroduccion");
						put("metodoCobro", "metodoCobro");
						put("condicionCobro", "condicionCobro");
						put("tipo", "tipo");
						put("motivo", "motivo");
						put("codigoMoneda", "codigoMoneda");
						put("cuenta", "cuenta");
						put("tipoProceso", "tipoProceso");
						put("estatus", "estatus");
						put("secuencia", "secuencia");
						put("idBanco", "idBanco");
						put("consecutivo", "consecutivo");
						put("fechaDepositoBancario", "fechaDepositoBancario");
						put("referencia", "referencia");
						put("referenciaAmpliada", "referenciaAmpliada");
						put("banco", "banco");
						put("origenAportacion", "origenAportacion");
						put("origenAportacionDesc", "origenAportacionDesc");
						put("folioConciliacion", "folioConciliacion");
						
					}
				}),
	
	;

	/*
	 * >>>>>>>>>>> VARIABLES ESTATICAS <<<<<<<<<<<<<<<<
	 fop 05/09/2018 .- Time Out MyBatis
	 */

	
	private static final String ATTRIBUTE_CAT_GENERICO_ID = "id";
	private static final String ATTRIBUTE_CAT_GENERICO_VALOR = "valor";
	

	private Class claseBean;
	private Class claseVo;
	private Map<String, String> mapaPropiedades;

	private BeanMap(Class claseBean, Class claseVo,
			Map<String, String> mapaPropiedades) {
		this.claseBean = claseBean;
		this.claseVo = claseVo;
		this.mapaPropiedades = mapaPropiedades;
	}

	public static BeanMap getBeanMapOf(Class claseBean, Class claseVO) {

		for (BeanMap map : values()) {
			if (map.getClaseBean().equals(claseBean)
					&& map.getClaseVo().equals(claseVO)) {
				return map;
			}
		}
		return null;
	}

	public Class getClaseBean() {
		return claseBean;
	}

	public Class getClaseVo() {
		return claseVo;
	}

	public Map<String, String> getMapaPropiedades() {
		return mapaPropiedades;
	}

}

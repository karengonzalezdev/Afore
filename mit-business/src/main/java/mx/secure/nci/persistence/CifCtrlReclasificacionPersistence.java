package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.cifctrlreclasificacion.CCOrigDestReclasificacionVO;
import mx.secure.nci.business.vo.cifctrlreclasificacion.CCTotalReclasificacionVO;
import mx.secure.nci.business.vo.cifctrlreclasificacion.CCTotalSubcuentaReclasificacionVO;
import mx.secure.nci.business.wrapped.cifctrlreclasificacion.CifCtrlReclasificacionFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface CifCtrlReclasificacionPersistence {
	
	public CCTotalReclasificacionVO consultaTotalReclasificacion(CifCtrlReclasificacionFilter filter);
	public List<CCOrigDestReclasificacionVO> consultaOrigDestReclasificacion(CifCtrlReclasificacionFilter filter);
	public List<CCTotalSubcuentaReclasificacionVO> consultaTotalPorSubcuentas(CifCtrlReclasificacionFilter filter);
	public List<CCTotalSubcuentaReclasificacionVO> consultaCarAboPorSubcuentas(CifCtrlReclasificacionFilter filter);
	
	public List<CCTotalSubcuentaReclasificacionVO> consultaTotalPorSubcuentasV2(CifCtrlReclasificacionFilter filter);
	public List<CCTotalSubcuentaReclasificacionVO> consultaCarAboPorSubcuentasV2(CifCtrlReclasificacionFilter filter);
	public CCTotalReclasificacionVO consultaGeneralCifrasControl(CifCtrlReclasificacionFilter filter);
}

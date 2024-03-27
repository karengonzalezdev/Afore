package mx.secure.nci.web.service;

import java.util.Date;
import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.ReporteVO;
import mx.secure.nci.business.vo.cif.DevolucionesCabeceraCifVO;
import mx.secure.nci.business.vo.cif.DevolucionesDetalleCifVO;
import mx.secure.nci.business.vo.cif.DevolucionesEnvioCifCabeceraVO;
import mx.secure.nci.business.vo.cif.DevolucionesEnvioCifDetalleVO;
import mx.secure.nci.business.vo.cif.DevolucionesReporteCDCifVO;
import mx.secure.nci.business.vo.cif.secure_CIFVo;
import mx.secure.nci.business.wrapped.NCI_CIFExtVo;
import mx.secure.nci.web.beans.NCI_CIFDataBean;
import mx.secure.nci.web.beans.NCI_CIFDataFilter;

public interface IMovimientosCifWebService {
	List<secure_CIFVo> send2CIF(List<secure_CIFVo> vos,String usuario) throws MitBusinessException;
	ReporteVO generaReporte(List<secure_CIFVo> dataReportBean) throws MitBusinessException;
	ReporteVO generaReportePrev(List<secure_CIFVo> dataReportBean) throws MitBusinessException;
	List<secure_CIFVo> getDataSended(List<secure_CIFVo> sended) throws MitBusinessException;
	List<NCI_CIFDataBean> consultar(NCI_CIFDataFilter filter) throws MitBusinessException;
	Integer actualizarNCICIF(NCI_CIFExtVo vo) throws MitBusinessException;
	Integer borrarNCICIF(NCI_CIFExtVo vo) throws MitBusinessException;
	void generaIdItem(NCI_CIFDataBean bean);
	
	List<DevolucionesEnvioCifDetalleVO> getListaEnvioCIFDetalle(String folio);
	List<DevolucionesEnvioCifDetalleVO> getListaEnvioCIFDetalle(Date fechaIni, Date fechaFin);
	DevolucionesEnvioCifCabeceraVO getListaEnvioCIFCabecera(String folio);
	Integer actualizaDevolCif(DevolucionesEnvioCifDetalleVO folio);
	ReporteVO generaReportePrevDev(List<DevolucionesReporteCDCifVO> dataReportBean) throws MitBusinessException;
	String enviaDevCIF(DevolucionesCabeceraCifVO cab, DevolucionesDetalleCifVO det);
	ReporteVO generaReporteDevSeg(List<DevolucionesCabeceraCifVO> ltsData) throws MitBusinessException;
	List<DevolucionesCabeceraCifVO> getListaSeguimientoEstatusCIF(DevolucionesCabeceraCifVO data);
}

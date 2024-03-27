package mx.profuturo.nci.web.service;

import java.util.Date;
import java.util.List;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesCabeceraCifVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesDetalleCifVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesEnvioCifCabeceraVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesEnvioCifDetalleVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesReporteCDCifVO;
import mx.profuturo.nci.business.vo.cif.Profuturo_CIFVo;
import mx.profuturo.nci.business.wrapped.NCI_CIFExtVo;
import mx.profuturo.nci.web.beans.NCI_CIFDataBean;
import mx.profuturo.nci.web.beans.NCI_CIFDataFilter;

public interface IMovimientosCifWebService {
	List<Profuturo_CIFVo> send2CIF(List<Profuturo_CIFVo> vos,String usuario) throws MitBusinessException;
	ReporteVO generaReporte(List<Profuturo_CIFVo> dataReportBean) throws MitBusinessException;
	ReporteVO generaReportePrev(List<Profuturo_CIFVo> dataReportBean) throws MitBusinessException;
	List<Profuturo_CIFVo> getDataSended(List<Profuturo_CIFVo> sended) throws MitBusinessException;
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

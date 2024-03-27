package mx.secure.nci.ws.service.impl.cifrasgenerales;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.ws.service.exception.WebServiceException;

import mx.secure.nci.business.exception.ErrorCodeSoap;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.ICifrasGenerales;
import mx.secure.nci.business.vo.CifrasGeneralesTipoVO;
import mx.secure.nci.business.vo.CifrasGeneralesVO;
import mx.secure.nci.business.wrapped.CifrasGeneralesFilter;
import mx.secure.nci.ws.beans.CifrasGeneralesBean;
import mx.secure.nci.ws.beans.CifrasGeneralesPorTipoBean;
import mx.secure.nci.ws.beans.request.CifrasGeneralesPorBancoBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasGeneralesPorOrigenBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasGeneralesPorSBCBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasGeneralesPorTipoBeanRequest;
import mx.secure.nci.ws.beans.response.CifrasGeneralesPorBancoBeanResponse;
import mx.secure.nci.ws.beans.response.CifrasGeneralesPorOrigenBeanResponse;
import mx.secure.nci.ws.beans.response.CifrasGeneralesPorSBCBeanResponse;
import mx.secure.nci.ws.beans.response.CifrasGeneralesPorTipoBeanResponse;
import mx.secure.nci.ws.service.cifrasgenerales.ICifrasGeneralesSoapService;
import mx.secure.nci.ws.util.UtilMapping;

@Service("cifrasGeneralesSoapService")
public class CifrasGeneralesSoapServiceImpl implements ICifrasGeneralesSoapService{

	private static final Logger LOGGER = LoggerFactory.getLogger(CifrasGeneralesSoapServiceImpl.class);
	
	@Autowired
	ICifrasGenerales cifrasGenerales;
	
	@Override
	public CifrasGeneralesPorOrigenBeanResponse consultarPorOrigen(
			CifrasGeneralesPorOrigenBeanRequest cifrasGeneralesPorOrigenBeanRequest) {
		List<CifrasGeneralesBean> ListaCifrasGenerales = new ArrayList<CifrasGeneralesBean>();
		try
		{
			CifrasGeneralesFilter cifrasGeneralesFilter = new CifrasGeneralesFilter();
			cifrasGeneralesFilter.setIdOrigenAportacion(cifrasGeneralesPorOrigenBeanRequest.getIdOrigen() != null ? cifrasGeneralesPorOrigenBeanRequest.getIdOrigen() : null);
			cifrasGeneralesFilter.setFolioConciliacion(cifrasGeneralesPorOrigenBeanRequest.getFolioConciliacion() != null ? cifrasGeneralesPorOrigenBeanRequest.getFolioConciliacion() : null); 
			for(CifrasGeneralesVO cifras : cifrasGenerales.consultarPorOrigen(cifrasGeneralesFilter))
			{
				ListaCifrasGenerales.add(UtilMapping.cifraVOToCifraBean(cifras));
			}
		}
		catch (MitBusinessException ex) {
			throw new WebServiceException(ex.getExceptionDetails());
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "BancosSoapServiceImpl", "consultar(...)" }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;
		}
		CifrasGeneralesPorOrigenBeanResponse cifrasGeneralesPorOrigenBeanResponse = new CifrasGeneralesPorOrigenBeanResponse(ListaCifrasGenerales);
		return cifrasGeneralesPorOrigenBeanResponse;
	}
	
	@Override
	public CifrasGeneralesPorTipoBeanResponse consultaPorTipoReporte(
			CifrasGeneralesPorTipoBeanRequest cifrasGeneralesPorTipoBeanRequest) {
		List <CifrasGeneralesPorTipoBean> ListaCifrasGeneralesTipo = new ArrayList<CifrasGeneralesPorTipoBean>();
		try
		{
			CifrasGeneralesFilter cifrasGeneralesFilter = new CifrasGeneralesFilter();
			cifrasGeneralesFilter.setIdTipo(cifrasGeneralesPorTipoBeanRequest.getIdTipoCuenta() != null ? cifrasGeneralesPorTipoBeanRequest.getIdTipoCuenta() : null);
			cifrasGeneralesFilter.setFolioConciliacion(cifrasGeneralesPorTipoBeanRequest.getFolioConciliacion() != null ? cifrasGeneralesPorTipoBeanRequest.getFolioConciliacion() : null);
			CifrasGeneralesTipoVO a= cifrasGenerales.consultarPorTipoReporte(cifrasGeneralesFilter);
			ListaCifrasGeneralesTipo.add(UtilMapping.cifraTipoVOToCifraTipoBeanLP(a));
			ListaCifrasGeneralesTipo.add(UtilMapping.cifraTipoVOToCifraTipoBeanCP(a));  
		}
		catch (MitBusinessException ex) {
			throw new WebServiceException(ex.getExceptionDetails());
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "BancosSoapServiceImpl", "consultar(...)" }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;
		}
		CifrasGeneralesPorTipoBeanResponse cifrasGeneralesPorTipoBeanResponse = new CifrasGeneralesPorTipoBeanResponse(ListaCifrasGeneralesTipo);
		return cifrasGeneralesPorTipoBeanResponse;
	}

	@Override
	public CifrasGeneralesPorTipoBeanResponse consultaPorTipo(
			CifrasGeneralesPorTipoBeanRequest cifrasGeneralesPorTipoBeanRequest) {
		List <CifrasGeneralesPorTipoBean> ListaCifrasGeneralesTipo = new ArrayList<CifrasGeneralesPorTipoBean>();
		try
		{
			CifrasGeneralesFilter cifrasGeneralesFilter = new CifrasGeneralesFilter();
			cifrasGeneralesFilter.setIdTipo(cifrasGeneralesPorTipoBeanRequest.getIdTipoCuenta() != null ? cifrasGeneralesPorTipoBeanRequest.getIdTipoCuenta() : null);
			cifrasGeneralesFilter.setFolioConciliacion(cifrasGeneralesPorTipoBeanRequest.getFolioConciliacion() != null ? cifrasGeneralesPorTipoBeanRequest.getFolioConciliacion() : null);
			CifrasGeneralesTipoVO a= cifrasGenerales.consultarPorTipo(cifrasGeneralesFilter);
			ListaCifrasGeneralesTipo.add(UtilMapping.cifraTipoVOToCifraTipoBeanLP(a));
			ListaCifrasGeneralesTipo.add(UtilMapping.cifraTipoVOToCifraTipoBeanCP(a));
//			for(CifrasGeneralesTipoVO cifras : cifrasGenerales.consultarPorTipo(cifrasGeneralesFilter))
//			{	
//				ListaCifrasGeneralesTipo.add(UtilMapping.cifraTipoVOToCifraTipoBeanLP(cifras));
//			}  
//			for(CifrasGeneralesTipoVO cifras : cifrasGenerales.consultarPorTipo(cifrasGeneralesFilter))
//			{	
//				ListaCifrasGeneralesTipo.add(UtilMapping.cifraTipoVOToCifraTipoBeanCP(cifras));
//			}  
		}
		catch (MitBusinessException ex) {
			throw new WebServiceException(ex.getExceptionDetails());
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "BancosSoapServiceImpl", "consultar(...)" }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;
		}
		CifrasGeneralesPorTipoBeanResponse cifrasGeneralesPorTipoBeanResponse = new CifrasGeneralesPorTipoBeanResponse(ListaCifrasGeneralesTipo);
		return cifrasGeneralesPorTipoBeanResponse;
	}

	@Override
	public CifrasGeneralesPorBancoBeanResponse consultaPorBanco(
			CifrasGeneralesPorBancoBeanRequest cifrasGeneralesPorBancoBeanRequest) {
		List<CifrasGeneralesBean> ListaCifrasGenerales = new ArrayList<CifrasGeneralesBean>();
		try
		{
			CifrasGeneralesFilter cifrasGeneralesFilter = new CifrasGeneralesFilter();
			cifrasGeneralesFilter.setIdBanco(cifrasGeneralesPorBancoBeanRequest.getIdClaveBanco() != null ? cifrasGeneralesPorBancoBeanRequest.getIdClaveBanco() : null);
			cifrasGeneralesFilter.setFolioConciliacion(cifrasGeneralesPorBancoBeanRequest.getFolioConciliacion() != null ? cifrasGeneralesPorBancoBeanRequest.getFolioConciliacion() : null);
			for(CifrasGeneralesVO cifras : cifrasGenerales.consultarPorBanco(cifrasGeneralesFilter))
			{	
				ListaCifrasGenerales.add(UtilMapping.cifraVOToCifraBean(cifras));
			}	
		}
		catch (MitBusinessException ex) {
			throw new WebServiceException(ex.getExceptionDetails());
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "BancosSoapServiceImpl", "consultar(...)" }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;
		}
		CifrasGeneralesPorBancoBeanResponse cifrasGeneralesPorBancoBeanResponse = new CifrasGeneralesPorBancoBeanResponse(ListaCifrasGenerales);
		return cifrasGeneralesPorBancoBeanResponse;
	}

	@Override
	public CifrasGeneralesPorSBCBeanResponse consultaPorSaldoBuenCobro(
			CifrasGeneralesPorSBCBeanRequest cifrasGeneralesPorSBCBeanRequest) {
		List<CifrasGeneralesBean> ListaCifrasGenerales = new ArrayList<CifrasGeneralesBean>();
		try
		{
			CifrasGeneralesFilter cifrasGeneralesFilter = new CifrasGeneralesFilter();
			cifrasGeneralesFilter.setIdSBC(cifrasGeneralesPorSBCBeanRequest.getIdSBC() != null ? cifrasGeneralesPorSBCBeanRequest.getIdSBC() : null);
			for(CifrasGeneralesVO cifras : cifrasGenerales.consultarPorSBC(cifrasGeneralesFilter))
			{	
				ListaCifrasGenerales.add(UtilMapping.cifraVOToCifraBean(cifras));
			}	
		}
		catch (MitBusinessException ex) {
			throw new WebServiceException(ex.getExceptionDetails());
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "BancosSoapServiceImpl", "consultaPorSaldoBuenCobro(...)" }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;
		}
		CifrasGeneralesPorSBCBeanResponse cifrasGeneralesPorSBCBeanResponse = new CifrasGeneralesPorSBCBeanResponse(ListaCifrasGenerales);
		return cifrasGeneralesPorSBCBeanResponse;
	}




}

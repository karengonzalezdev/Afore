package mx.secure.nci.ws.service.impl.sumconcilia;

import java.math.BigDecimal;
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
import mx.secure.nci.business.service.ISumConciliacionService;
import mx.secure.nci.business.vo.GenericCatalogoVO;
import mx.secure.nci.business.vo.SumConciliacionVO;
import mx.secure.nci.business.wrapped.SumConciliacionFilter;
import mx.secure.nci.ws.beans.GenericoCatalogoBean;
import mx.secure.nci.ws.beans.SumConciliacionBean;
import mx.secure.nci.ws.beans.request.SumConciliaActualizarBeanRequest;
import mx.secure.nci.ws.beans.request.SumConciliaConsultarBeanRequest;
import mx.secure.nci.ws.beans.response.SumConciliaConsultarBeanResponse;
import mx.secure.nci.ws.service.sumconcilia.ISumConciliaSoapService;
import mx.secure.nci.ws.util.UtilMapping;

@Service("sumConciliaSoapService")
public class SumConciliaSoapServiceImpl implements ISumConciliaSoapService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SumConciliaSoapServiceImpl.class);
	@Autowired
	ISumConciliacionService sumConciliacionService;

	@Override
	public SumConciliaConsultarBeanResponse consultar(SumConciliaConsultarBeanRequest sumConciliaConsultarBeanRequest) {
		List<SumConciliacionBean> lista = new ArrayList<SumConciliacionBean>();

		try {
			if (sumConciliaConsultarBeanRequest != null) {
				SumConciliacionFilter sumConciliacionFilter = new SumConciliacionFilter();
				sumConciliacionFilter.setFolio(sumConciliaConsultarBeanRequest.getFolio() != null
						? sumConciliaConsultarBeanRequest.getFolio() : null);
				if (sumConciliaConsultarBeanRequest.getIdEmpresa() != null) {
					GenericCatalogoVO empresa = new GenericCatalogoVO();
					empresa.setId(sumConciliaConsultarBeanRequest.getIdEmpresa());
					sumConciliacionFilter.setEmpresa(empresa);
				}
				if (sumConciliaConsultarBeanRequest.getIdOrigen() != null) {
					GenericCatalogoVO origenApo = new GenericCatalogoVO();
					origenApo.setId(sumConciliaConsultarBeanRequest.getIdOrigen());
					sumConciliacionFilter.setOrigen(origenApo);
				}
				sumConciliacionFilter.setImporte(new BigDecimal(100));
				
				for (SumConciliacionVO sumConcil : sumConciliacionService
						.consultarSumConciliacion(sumConciliacionFilter)) {
					SumConciliacionBean sumConciliacionBean = new SumConciliacionBean();
					UtilMapping.mapVoToBean(sumConciliacionBean, sumConcil);
					if (sumConcil.getEmpresa() != null) {
						GenericoCatalogoBean empresa = new GenericoCatalogoBean();
						UtilMapping.mapVoToBean(empresa, sumConcil.getEmpresa());
						sumConciliacionBean.setEmpresa(empresa);
					}
					if (sumConcil.getOrigen() != null) {
						GenericoCatalogoBean origenApo = new GenericoCatalogoBean();
						UtilMapping.mapVoToBean(origenApo, sumConcil.getOrigen());
						sumConciliacionBean.setOrigen(origenApo);
					}
					lista.add(sumConciliacionBean);
				}
			}
		} catch (MitBusinessException ex) {
			throw new WebServiceException(ex.getExceptionDetails());
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "PreSolicitudDomiciliacionSoapServiceImpl", "insertar(...)" }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;
		}

		SumConciliaConsultarBeanResponse sumConciliaConsultarBeanResponse = new SumConciliaConsultarBeanResponse(lista);

		return sumConciliaConsultarBeanResponse;
	}

	@Override
	public int actualizar(SumConciliaActualizarBeanRequest sumConciliaActualizarBeanRequest) {
		int estatusOperacion = 0;
		try {
			if (sumConciliaActualizarBeanRequest.getSumConcilia() != null) {
				SumConciliacionVO sumConciliacionVO = new SumConciliacionVO();
				UtilMapping.mapBeanToVO(sumConciliaActualizarBeanRequest.getSumConcilia(), sumConciliacionVO);
				sumConciliacionVO.setUsuarioActualizacion(sumConciliaActualizarBeanRequest.getSumConcilia().getUsuario());
				sumConciliacionVO.setRegConciliado(sumConciliaActualizarBeanRequest.getSumConcilia().getRegConciliado());
				if(sumConciliaActualizarBeanRequest.getSumConcilia().getOrigen() != null)
				{
					GenericCatalogoVO origenApo = new GenericCatalogoVO();
					UtilMapping.mapBeanToVO(sumConciliaActualizarBeanRequest.getSumConcilia().getOrigen(), origenApo);
					sumConciliacionVO.setOrigen(origenApo);
				}
				if(sumConciliaActualizarBeanRequest.getSumConcilia().getEmpresa() != null)
				{
					GenericCatalogoVO empresa = new GenericCatalogoVO();
					UtilMapping.mapBeanToVO(sumConciliaActualizarBeanRequest.getSumConcilia().getEmpresa(), empresa);
					sumConciliacionVO.setEmpresa(empresa);
				}
				return sumConciliacionService.actualizarSumConciliacion(sumConciliacionVO);
			}
		} catch (MitBusinessException ex) {
			throw new WebServiceException(ex.getExceptionDetails());
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "PreSolicitudDomiciliacionSoapServiceImpl", "actualizar(...)" }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;
		}
		return estatusOperacion;
	}
}

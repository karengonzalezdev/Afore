package mx.profuturo.nci.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.service.IConsultasBaseService;
import mx.profuturo.nci.business.util.ConstantesCatalogos;
import mx.profuturo.nci.business.wrapped.GeneraFolioFilter;
import mx.profuturo.nci.persistence.GeneraFolioPersistence;

@Service("consultasBaseService")
public class ConsultasBaseServiceImpl implements IConsultasBaseService{
		
	@Autowired
	GeneraFolioPersistence generaFolioPersistence;

	public String generaFolioDB() throws Exception {
		GeneraFolioFilter generaFolio = null;
		try {
			generaFolio = new GeneraFolioFilter();
			generaFolio.setIdProceso(Integer.parseInt( ConstantesCatalogos.ID_PROCESO_REGISTRO + "") );
			generaFolio.setIdSubproceso(Integer.parseInt( ConstantesCatalogos.ID_SUBPROCESO_REGISTRO_NORMAL + ""));
			generaFolioPersistence.generaFolio(generaFolio);
			return generaFolio.getFolio();
		}catch( Exception e ) {
			System.out.println("ERROR AL GENERAR FOLIO ::");
			e.printStackTrace();
			return null;
		}finally {
			
		}
	}


}

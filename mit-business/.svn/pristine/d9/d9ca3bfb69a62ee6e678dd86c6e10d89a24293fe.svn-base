package mx.profuturo.nci.business.cliente.service.impl;

import java.util.ArrayList;
import java.util.List;

import mx.profuturo.ldap.operacionesldap.ConsultaGruposPorUidIn;
import mx.profuturo.ldap.operacionesldap.ConsultaGruposPorUidOut;
import mx.profuturo.ldap.operacionesldap.OperacionesLdapServices;
import mx.profuturo.nci.business.cliente.service.IWSCOperacionesLdapService;
import mx.profuturo.nci.business.service.impl.WSPortTypeFactory;
import mx.profuturo.nci.business.vo.RolVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;
import com.jeveris.core.exception.impl.ExceptionDetails;

@Service("wscOperacionesLdapService")
public class WSCOperacionesLdapServiceImpl implements IWSCOperacionesLdapService{
	private static final Logger LOGGER = LoggerFactory.getLogger(WSCOperacionesLdapServiceImpl.class);
	@Autowired WSPortTypeFactory wsPortTypeFactory;

	public List<RolVO> getRoles(String userId) throws BusinessException{
		List<RolVO> resp = new ArrayList<RolVO>();
		try {
			OperacionesLdapServices port = wsPortTypeFactory.operacionesLdapServices();
			ConsultaGruposPorUidIn in = new ConsultaGruposPorUidIn();
			in.setUid(userId);
			ConsultaGruposPorUidOut out= port.consultaGruposPorUid(in);
			List<String> grupos = out.getGrupos();
			for(String grupoLDAP: grupos){
				String cn = extractMainCNForGroup(grupoLDAP);
				if(cn != null){
					RolVO rol = new RolVO();
					rol.setDescRol(cn);
					resp.add(rol);
				}
			}
			
		} catch (Exception e) {
			String errorCode = "2000", causeMessage = e.getMessage();
			ExceptionDetails exceptionDetails = new ExceptionDetails(errorCode,causeMessage);
			exceptionDetails.setBaseException(e);
			LOGGER.error(causeMessage,e);
			throw new BusinessException(exceptionDetails);
		
		}
		
		return resp;
	}

	private String extractMainCNForGroup(String grupoLDAP) {
		String cn = null;
		if(grupoLDAP!=null){
			String[] members = grupoLDAP.split(",");
			if(members.length > 1){
				String ou = members[members.length-1].toLowerCase().replace("ou=","").trim();
				if("grupos".equals(ou)){
					cn = members[0].toLowerCase().replace("cn=","").trim();
				}
			}
		}
		return cn;
	}
	
}

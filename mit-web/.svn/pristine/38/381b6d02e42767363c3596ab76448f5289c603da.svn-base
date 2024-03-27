package mx.profuturo.nci.web.service.impl;

import static mx.profuturo.nci.business.util.Constantes.ID_APLICACION;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mx.com.profuturo_gnp.comun.catalogo.CatalogoComun;
import mx.com.profuturo_gnp.comun.catalogo.ConsultarEmpleadoIn;
import mx.com.profuturo_gnp.comun.catalogo.ConsultarEmpleadoOut;
import mx.com.profuturo_gnp.comun.catalogo.ServiceException;
import mx.com.profuturo_gnp.ws.sso.controlacceso.ConfirmarAccesoRequest;
import mx.com.profuturo_gnp.ws.sso.controlacceso.ConfirmarAccesoResponse;
import mx.com.profuturo_gnp.ws.sso.controlacceso.ControlAccesoSSOPortType;
import mx.com.profuturo_gnp.ws.sso.controlacceso.SSOFault;
import mx.profuturo.nci.business.cliente.service.IWSCOperacionesLdapService;
import mx.profuturo.nci.business.service.IConfiguracionPermisoSeccionService;
import mx.profuturo.nci.business.service.impl.WSPortTypeFactory;
import mx.profuturo.nci.business.vo.RolVO;
import mx.profuturo.nci.web.beans.AppRolBean;
import mx.profuturo.nci.web.beans.AppUserBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;

@Service("wsAuthenticationManager")
public class WebServiceAuthenticationManagerImpl implements AuthenticationProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceAuthenticationManagerImpl.class);
//	private static final String ID_APLICACION = "ncimonitor";

	@Autowired WSPortTypeFactory wsPortTypeFactory;
	@Autowired IConfiguracionPermisoSeccionService configuracionPermisoSeccionService;
	@Autowired IWSCOperacionesLdapService wscOperacionesLdapService;
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String username = authentication.getName();
        String pw       = authentication.getCredentials().toString();
        
        try {
			if (username == null) {
				throw new BadCredentialsException("Debe introducir el usuario");
			}
			if (pw == null) {
				throw new BadCredentialsException("Debe introducir un password");
			}
			String numEmpleado = autentica(username, pw);
			if (numEmpleado != null) {

				LOGGER.debug("El usuario " + username + " entro a la aplicacion");
//				ConsultarEmpleadoOut datos = getDatoEmpleado(username/*numEmpleado - VERIFICAR*/);
				List<RolVO> roles = wscOperacionesLdapService.getRoles(username);
				LOGGER.debug("Roles: "+roles);
				if(roles!=null && !roles.isEmpty()){
					AppUserBean user = new AppUserBean();
//					user.setFirstName(datos.getNombre());
//					user.setLastName(datos.getApellidoPaterno());
					user.setPassword(pw);
					user.setUsername(username);
					List<AppRolBean> secciones= new ArrayList<AppRolBean>();
					List<String> idSecciones =//configuracionPermisoSeccionService.getSecciones((short)44/*comentar invocacion y descomentar la de abajo*/);
					//List<String> id2=	
											configuracionPermisoSeccionService.getSecciones(roles);
//					LOGGER.debug("NUEVAS SECCIONES:"+idSecciones);
					for(String ids : idSecciones){
						LOGGER.debug("Secciones:"+ids);
						AppRolBean rol =new AppRolBean();
						rol.setName(ids);
						secciones.add(rol);
					}
					user.setAuthorities(secciones);
	
					Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
	
					return new UsernamePasswordAuthenticationToken(user, pw,authorities);
				}else{
					throw new BadCredentialsException("El usuario no tiene asignados datos en la consulta de empleado");
				}
			} else {
				throw new BadCredentialsException("El usuario no cuenta con id de Empleado");
			}
		} catch (Exception e) {
			throw new BadCredentialsException(e.getMessage());
		}
		
	}
	
	
	
	private String autentica(String username,String pw) throws BusinessException, SSOFault{
		ControlAccesoSSOPortType port = wsPortTypeFactory.controlAccesoSSOPortType();
		ConfirmarAccesoRequest req = new ConfirmarAccesoRequest();
		req.setContrasena(pw);
		req.setUsuario(username);
		req.setAplicacion(ID_APLICACION);
		ConfirmarAccesoResponse resp = port.confirmarAccesoUsuarioSSO(req);
		if(resp.isConfirmacion()){
			return resp.getNumeroEmpleado();
		}else{
			return null;
		}
	}
	
	private ConsultarEmpleadoOut getDatoEmpleado(String numEmpleado) throws BusinessException, ServiceException{
		CatalogoComun port = wsPortTypeFactory.catalogoComun();
		ConsultarEmpleadoIn consultarEmpleadoIn = new ConsultarEmpleadoIn();
		consultarEmpleadoIn.setUserId(numEmpleado);
		return port.consultarEmpleado(consultarEmpleadoIn);
	}

	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}

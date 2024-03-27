package mx.profuturo.nci.web.views;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import mx.profuturo.nci.business.util.UtilMethod;

@ManagedBean(name = "sessionValidatorView")
@Component
public class SessionValidatorView {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionValidatorView.class);

	public void onIdle() {
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse res =(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		try {
			LOGGER.error("Terminando la sesion por exceder el tiempo de inactividad");
			String contextPath = req.getContextPath();
            String redirectUrl = contextPath;
            LOGGER.error("Peticion Ajax: La sesion ha expirado se redirije la peticion a: "+ redirectUrl);
            
            String ajaxRedirectXml =  UtilMethod.createAjaxRedirectXml(redirectUrl);
            LOGGER.error("Peticion Parcial Ajax:"+  ajaxRedirectXml);

            res.setContentType("text/xml");
            res.getWriter().write(ajaxRedirectXml);
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					FacesContext.getCurrentInstance().getExternalContext().getContextName() 
			);
		} catch (IOException e) {
			LOGGER.error("Error al redireccionar al expirar la sesion", e);
		}
	}

	public void onActive() {
		LOGGER.error("Actividad en la página");
	}
	
}

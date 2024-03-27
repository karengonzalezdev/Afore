package mx.profuturo.nci.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.util.StringUtils;

import mx.profuturo.nci.business.util.UtilMethod;

public class RedirectInvocationStrategy implements InvalidSessionStrategy{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedirectInvocationStrategy.class);
    private static final String FACES_REQUEST_HEADER = "faces-request";

    private String invalidSessionUrl;

	public void onInvalidSessionDetected(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		boolean isAjax = "partial/ajax".equals(req.getHeader(FACES_REQUEST_HEADER));
        if(isAjax) {
            String contextPath = req.getContextPath();
            String redirectUrl = contextPath + invalidSessionUrl;
            LOGGER.debug("Peticion Ajax: La sesion ha expirado se redirije la peticion a: "+ redirectUrl);
            
            String ajaxRedirectXml = UtilMethod.createAjaxRedirectXml(redirectUrl);
            LOGGER.debug("Peticion Parcial Ajax:"+  ajaxRedirectXml);

            res.setContentType("text/xml");
            res.getWriter().write(ajaxRedirectXml);
        } else {
        	// Se quita para evitar el punto: A10 Unvalidated Redirects and Forwards de OWASP
        	//Se deja directa la redirecccion hacia la pantalla de login
            String requestURI = req.getContextPath();//getRequestUrl(req); 
            LOGGER.debug("Peticion normal: La sesion ha expirado: "+ requestURI);
            req.getSession(true);
            res.sendRedirect(requestURI);
        }
		
	}

//	private String getRequestUrl(HttpServletRequest request) {
//		StringBuffer requestURL = request.getRequestURL();
//
//		String queryString = request.getQueryString();
//		if (StringUtils.hasText(queryString)) {
//			requestURL.append("?").append(queryString);
//		}
//
//		return requestURL.toString();
//	}

//	private String createAjaxRedirectXml(String redirectUrl) {
//		return new StringBuilder()
//					.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
//					.append("<partial-response><redirect url=\"")
//					.append(redirectUrl)
//					.append("\"></redirect></partial-response>")
//					.toString();
//	}

	public void setInvalidSessionUrl(String invalidSessionUrl) {
		this.invalidSessionUrl = invalidSessionUrl;
	}

}

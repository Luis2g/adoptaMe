package mx.edu.utez.adoptaMe.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import mx.edu.utez.adoptaMe.helpers.Session;
import mx.edu.utez.adoptaMe.service.AccessServiceImpl;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private AccessServiceImpl accessServiceImpl;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String authoritiesForSending = "";

		boolean hasAdministradorRole = false;
		boolean hasAdoptadorRole = false;

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			
			authoritiesForSending = authoritiesForSending +  grantedAuthority.toString()+ ", ";
			
			if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				hasAdministradorRole = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_VOLUNTEER")) {
				hasAdoptadorRole = true;
				break;
			}
		}

//		if (Session.getUrl() == "") {
			if (hasAdministradorRole) {
				accessServiceImpl.registerAccess(authentication.getName(), authoritiesForSending);
				redirectStrategy.sendRedirect(request, response, "/solicitudesParaPublicar");
			} else if (hasAdoptadorRole) {
				accessServiceImpl.registerAccess(authentication.getName(), authoritiesForSending);
				redirectStrategy.sendRedirect(request, response, "/misPublicaciones");
			} else {
				accessServiceImpl.registerAccess(authentication.getName(), authoritiesForSending);
				redirectStrategy.sendRedirect(request, response, "/inicio");
			}
//		}else{
//			accessServiceImpl.registerAccess(authentication.getName(), authoritiesForSending);
//			redirectStrategy.sendRedirect(request, response, Session.getUrl());
//		}

	}

}
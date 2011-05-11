package com.excilys.formation.yaeba.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("authenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {

		for (GrantedAuthority ga : authentication.getAuthorities()) {
			if (ga.getAuthority().equals("ROLE_ADMIN")) {
				response.sendRedirect("admin/admin.html");
				return;
			}
		}

		response.sendRedirect("user/comptes/comptes.html");
	}
}
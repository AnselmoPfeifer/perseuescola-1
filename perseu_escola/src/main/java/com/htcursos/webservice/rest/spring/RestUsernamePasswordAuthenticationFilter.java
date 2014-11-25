package com.htcursos.webservice.rest.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * FILTRO DE AUTENTICACAO DO REST
 * Authentication filter for REST services
 * Apenas retorna true ou false dependendo das credenciais do usuário
 * 
 * @author virmerson
 *
 */
public class RestUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request,
			HttpServletResponse response) {
		boolean retVal = false;
		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		if (username != null && password != null) {
			Authentication authResult = null;
			try {
				/**
				 * A autenticacao é realizada no attemptAuthentication e
				 * dependendo do resultado executará unsuccessfulAuthentication
				 * ou successfulAuthentication como proximo passo
				 **/
				authResult = attemptAuthentication(request, response);
				if (authResult == null) {
					retVal = false;
				}
			} catch (AuthenticationException failed) {
				try {
					/**
					 * Inicia o comportamento padrao de desautenticacao do
					 * Spring
					 **/
					unsuccessfulAuthentication(request, response, failed);
				} catch (IOException e) {
					retVal = false;
				} catch (ServletException e) {
					retVal = false;
				}
				retVal = false;
			}
			try {
				/** Inicia o compartamento padrao para autenticacao do Spring **/
				successfulAuthentication(request, response, authResult);
			} catch (IOException e) {
				retVal = false;
			} catch (ServletException e) {
				retVal = false;
			}
			return false;
		} else {
			retVal = true;
		}
		return retVal;
	}
}

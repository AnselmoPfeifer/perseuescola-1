package com.htcursos.webservice.rest.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * MANIPULADOR DE AUTENTICACAO BEM SUCEDIDA
 * 
 * As implementações do manipulador de autenticação bem-sucedida pode fazer
 * quase tudo, por exemplo controlar a navegação para o destino subseqüente
 * (redirecionar ou encaminhar). Na causa de Web Service RESTful que não
 * queremos fazer nada depois de fazer login, mas simplesmente retornar uma
 * resposta de serviço. Classe Handler implementa
 * AuthenticationSuccessHandler,deixa vazio o método onAuthenticationSuccess.
 * 
 * @author virmerson
 *
 */
public class RestAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

}

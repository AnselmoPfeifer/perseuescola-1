package com.htcursos.model.util;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.htcursos.model.entity.Usuario;
import com.htcursos.model.service.UsuarioService;

@Component
public class SessaoUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String EMPRESA_LOGADA = "empresaLogada";
	
	@Autowired
	UsuarioService usuarioService;

	/**
	 * Apenas checa se a empresa está na sessao ou nao
	 * 
	 * @return
	 */
	public boolean isSessaoEmpresaLogada() {
		HttpSession session = getSession();
		if (session.getAttribute(EMPRESA_LOGADA) != null) {
			return true;
		}
		return false;

	}

	/**
	 * Apenas obtem um httpSession
	 * 
	 * @return
	 */
	private HttpSession getSession() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		return session;
	}

	public void logout() {
		HttpSession session = getSession();
		
		session.setAttribute(EMPRESA_LOGADA, null);
		session.invalidate();
		
	}
	
	/**
	 * 
	 * @return Username do usuário  
	 */
	public String getUserNameUsuarioLogado() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		return ((User) authentication.getPrincipal()).getUsername();
	}
	
	public Usuario getUsuarioLogadoPersistido(){
		return usuarioService.buscarPorUserName(getUserNameUsuarioLogado());
	}
}

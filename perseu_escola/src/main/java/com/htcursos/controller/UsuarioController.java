package com.htcursos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.controller.util.faces.JsfMessages;
import com.htcursos.model.entity.Usuario;
import com.htcursos.model.service.ServiceExpcetion;
import com.htcursos.model.service.UsuarioService;

@Controller("usuarioController")
@Scope("view")
@ManagedBean
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioService usuarioService;

	// Dados da tela
	private Usuario usuario = new Usuario();

	private Usuario usuarioExcluir;

	private List<Usuario> usuarioList = new ArrayList<Usuario>();

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			usuarioService.salvar(usuario);
			atualiza();
			usuario = new Usuario();
			JsfMessages.adicionaMensagemInfo("Usuario salvo");
		} catch (ServiceExpcetion e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		// remove do banco
		getUsuarioService().excluir(usuarioExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		usuarioList.remove(usuarioExcluir);
		usuarioExcluir = null;
		// JsfMessages.adicionaMensagemInfo("Usuario removido");
	}

	public void cancelar() {
		usuario = new Usuario();
	}

	public void editar(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarioList() {
		return getUsuarioService().buscarTodos();
	}

	public Usuario getUsuarioExcluir() {
		return usuarioExcluir;
	}

	public void setUsuarioExcluir(Usuario usuarioExcluir) {
		this.usuarioExcluir = usuarioExcluir;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void atualiza() {
		usuarioList = getUsuarioService().buscarTodos();
	}

}

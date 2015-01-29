package com.htcursos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.controller.util.faces.JsfMessages;
import com.htcursos.model.entity.Grupo;
import com.htcursos.model.service.GrupoService;
import com.htcursos.model.service.ServiceException;

@Controller("grupoController")
@Scope("view")
@ManagedBean
public class GrupoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GrupoService grupoService;
	private Grupo grupo = new Grupo();

	// dados da tela
	private Grupo grupoExcluir;

	private List<Grupo> grupoList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			grupoService.salvar(grupo);
			atualiza();
			grupo = new Grupo();
			JsfMessages.adicionaMensagemInfo("Grupo salvo");
		} catch (ServiceException e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		// remove do banco
		getGrupoService().excluir(grupoExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		grupoList.remove(grupoExcluir);
		grupoExcluir = null;
		JsfMessages.adicionaMensagemInfo("Grupo removido");

	}

	public void cancelar() {
		grupo = new Grupo();
	}

	public void editar(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getGrupoList() {
		return getGrupoService().buscarTodos();
	}

	public void atualiza() {
		grupoList = getGrupoService().buscarTodos();
	}

	// getter and setters
	public GrupoService getGrupoService() {
		return grupoService;
	}

	public void setGrupoService(GrupoService grupoService) {
		this.grupoService = grupoService;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Grupo getGrupoExcluir() {
		return grupoExcluir;
	}

	public void setGrupoExcluir(Grupo grupoExcluir) {
		this.grupoExcluir = grupoExcluir;
	}

	public void setGrupoList(List<Grupo> grupoList) {
		this.grupoList = grupoList;
	}

}

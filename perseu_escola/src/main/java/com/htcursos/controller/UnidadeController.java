package com.htcursos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.controller.util.faces.JsfMessages;
import com.htcursos.model.entity.Unidade;
import com.htcursos.model.service.ServiceException;
import com.htcursos.model.service.UnidadeService;

@Controller("unidadeController")
@Scope("view")
@ManagedBean
public class UnidadeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UnidadeService unidadeService;
	private Unidade unidade = new Unidade();

	// dados da tela
	private Unidade unidadeExcluir;

	private List<Unidade> unidadeList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			unidadeService.salvar(unidade);
			atualiza();
			unidade = new Unidade();
			JsfMessages.adicionaMensagemInfo("Unidade salvo");
		} catch (ServiceException e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}
		
	}

	public void excluir() {
		// remove do banco
		getUnidadeService().excluir(unidadeExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		unidadeList.remove(unidadeExcluir);
		unidadeExcluir = null;
		JsfMessages.adicionaMensagemInfo("Unidade removido");

	}

	public void cancelar() {
		unidade = new Unidade();
	}

	public void editar(Unidade unidade) {
		this.unidade = unidade;
	}

	public List<Unidade> getUnidadeList() {
		return getUnidadeService().buscarTodos();
	}

	public void atualiza() {
		unidadeList = getUnidadeService().buscarTodos();
	}

	public void buscarPorCep(String cep) {
		System.out.println("1 Busca cep" + cep);

	}

	// getter and setters
	public UnidadeService getUnidadeService() {
		return unidadeService;
	}

	public void setUnidadeService(UnidadeService unidadeService) {
		this.unidadeService = unidadeService;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Unidade getUnidadeExcluir() {
		return unidadeExcluir;
	}

	public void setUnidadeExcluir(Unidade unidadeExcluir) {
		this.unidadeExcluir = unidadeExcluir;
	}

	public void setUnidadeList(List<Unidade> unidadeList) {
		this.unidadeList = unidadeList;
	}
}

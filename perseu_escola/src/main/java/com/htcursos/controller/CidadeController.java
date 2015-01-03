package com.htcursos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.controller.util.faces.JsfMessages;
import com.htcursos.model.entity.Cidade;
import com.htcursos.model.service.CidadeService;
import com.htcursos.model.service.ServiceExpcetion;

@Controller("cidadeController")
@Scope("view")
@ManagedBean
public class CidadeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CidadeService cidadeService;

	// Dados da tela
	private Cidade cidade = new Cidade();

	private Cidade cidadeExcluir;

	private List<Cidade> cidadeList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {

		try {
			cidadeService.salvar(cidade);
			atualiza();
			cidade = new Cidade();
			JsfMessages.adicionaMensagemInfo("Cidade salvo");
		} catch (ServiceExpcetion e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		// remove do banco
		getCidadeService().excluir(cidadeExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		cidadeList.remove(cidadeExcluir);
		cidadeExcluir = null;
		// JsfMessages.adicionaMensagemInfo("Cidade removido");

	}

	public void cancelar() {
		cidade = new Cidade();
	}

	public void editar(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidadeList() {
		return getCidadeService().buscarTodos();
	}

	public Cidade getCidadeExcluir() {
		return cidadeExcluir;
	}

	public void setCidadeExcluir(Cidade cidadeExcluir) {
		this.cidadeExcluir = cidadeExcluir;
	}

	public void setCidadeList(List<Cidade> cidadeList) {
		this.cidadeList = cidadeList;
	}

	public CidadeService getCidadeService() {
		return cidadeService;
	}

	public void setCidadeService(CidadeService cidadeService) {
		this.cidadeService = cidadeService;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void atualiza() {
		cidadeList = getCidadeService().buscarTodos();
	}

}

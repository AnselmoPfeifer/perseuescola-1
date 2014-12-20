package com.htcursos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.controller.util.faces.JsfMessages;
import com.htcursos.model.entity.Uf;
import com.htcursos.model.service.ServiceExpcetion;
import com.htcursos.model.service.UfService;

@Controller("ufController")
@Scope("view")
@ManagedBean
public class UfController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UfService ufService;
	private Uf uf = new Uf();

	// dados da tela
	private Uf ufExcluir;

	private List<Uf> ufList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			ufService.salvar(uf);
			atualiza();
			uf = new Uf();
			JsfMessages.adicionaMensagemInfo("Uf salvo");
		} catch (ServiceExpcetion e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		// remove do banco
		getUfService().excluir(ufExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		ufList.remove(ufExcluir);
		ufExcluir = null;
		JsfMessages.adicionaMensagemInfo("Uf removido");

	}

	public void cancelar() {
		uf = new Uf();
	}

	public void editar(Uf uf) {
		this.uf = uf;
	}

	public List<Uf> getUfList() {
		return getUfService().buscarTodos();
	}

	public void atualiza() {
		ufList = getUfService().buscarTodos();
	}

	// getter and setters
	public UfService getUfService() {
		return ufService;
	}

	public void setUfService(UfService ufService) {
		this.ufService = ufService;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public Uf getUfExcluir() {
		return ufExcluir;
	}

	public void setUfExcluir(Uf ufExcluir) {
		this.ufExcluir = ufExcluir;
	}

	public void setUfList(List<Uf> ufList) {
		this.ufList = ufList;
	}
}

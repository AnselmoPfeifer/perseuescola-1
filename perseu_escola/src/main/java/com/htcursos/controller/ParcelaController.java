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
import com.htcursos.model.entity.Parcela;
import com.htcursos.model.service.ParcelaService;
import com.htcursos.model.service.ServiceExpcetion;

@Controller("parcelaController")
@Scope("view")
@ManagedBean
public class ParcelaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ParcelaService parcelaService;
	private Parcela parcela = new Parcela();

	// dados da tela
	private Parcela parcelaExcluir;

	private List<Parcela> parcelaList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			parcelaService.salvar(parcela);
			atualiza();
			parcela = new Parcela();
			JsfMessages.adicionaMensagemInfo("Parcela salvo");
		} catch (ServiceExpcetion e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		// remove do banco
		getParcelaService().excluir(parcelaExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		parcelaList.remove(parcelaExcluir);
		parcelaExcluir = null;
		JsfMessages.adicionaMensagemInfo("Parcela removido");

	}

	public void cancelar() {
		parcela = new Parcela();
	}

	public void editar(Parcela parcela) {
		this.parcela = parcela;
	}

	public List<Parcela> getParcelaList() {
		return getParcelaService().buscarTodos();
	}

	public void atualiza() {
		parcelaList = getParcelaService().buscarTodos();
	}

	// getter and setters
	public ParcelaService getParcelaService() {
		return parcelaService;
	}

	public void setParcelaService(ParcelaService parcelaService) {
		this.parcelaService = parcelaService;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public Parcela getParcelaExcluir() {
		return parcelaExcluir;
	}

	public void setParcelaExcluir(Parcela parcelaExcluir) {
		this.parcelaExcluir = parcelaExcluir;
	}

	public void setParcelaList(List<Parcela> parcelaList) {
		this.parcelaList = parcelaList;
	}
}

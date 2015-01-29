package com.htcursos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.controller.util.faces.JsfMessages;
import com.htcursos.model.entity.Sala;
import com.htcursos.model.service.SalaService;
import com.htcursos.model.service.ServiceException;

@Controller("salaController")
@Scope("view")
@ManagedBean
public class SalaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SalaService salaService;
	private Sala sala = new Sala();

	// dados da tela
	private Sala salaExcluir;

	private List<Sala> salaList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			salaService.salvar(sala);
			atualiza();
			sala = new Sala();
			JsfMessages.adicionaMensagemInfo("Sala salvo");
		} catch (ServiceException e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		// remove do banco
		getSalaService().excluir(salaExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		salaList.remove(salaExcluir);
		salaExcluir = null;
		JsfMessages.adicionaMensagemInfo("Sala removido");

	}

	public void cancelar() {
		sala = new Sala();
	}

	public void editar(Sala sala) {
		this.sala = sala;
	}

	public List<Sala> getSalaList() {
		return getSalaService().buscarTodos();
	}

	public void atualiza() {
		salaList = getSalaService().buscarTodos();
	}

	// getter and setters
	public SalaService getSalaService() {
		return salaService;
	}

	public void setSalaService(SalaService salaService) {
		this.salaService = salaService;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Sala getSalaExcluir() {
		return salaExcluir;
	}

	public void setSalaExcluir(Sala salaExcluir) {
		this.salaExcluir = salaExcluir;
	}

	public void setSalaList(List<Sala> salaList) {
		this.salaList = salaList;
	}
}

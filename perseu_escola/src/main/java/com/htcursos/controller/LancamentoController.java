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
import com.htcursos.model.entity.Lancamento;
import com.htcursos.model.service.LancamentoService;
import com.htcursos.model.service.ServiceException;

@Controller("lancamentoController")
@Scope("view")
@ManagedBean
public class LancamentoController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private LancamentoService lancamentoService;
	private Lancamento lancamento = new Lancamento();
	
	//dados da tela
	private Lancamento lancamentoExcluir;

	private List<Lancamento> lancamentoList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			lancamentoService.salvar(lancamento);
			atualiza();
			lancamento = new Lancamento();
			JsfMessages.adicionaMensagemInfo("Lancamento salvo");
		} catch (ServiceException e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}
		
	}
	
	public void excluir() {
		// remove do banco
		getLancamentoService().excluir(lancamentoExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		lancamentoList.remove(lancamentoExcluir);
		lancamentoExcluir = null;
		JsfMessages.adicionaMensagemInfo("Lancamento removido");

	}

	public void cancelar() {
		lancamento = new Lancamento();
	}

	public void editar(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<Lancamento> getLancamentoList() {
		return getLancamentoService().buscarTodos();
	}
	
	public void atualiza() {
		lancamentoList = getLancamentoService().buscarTodos();
	}

		
	//getter and setters
	public LancamentoService getLancamentoService() {
		return lancamentoService;
	}

	public void setLancamentoService(LancamentoService lancamentoService) {
		this.lancamentoService = lancamentoService;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public Lancamento getLancamentoExcluir() {
		return lancamentoExcluir;
	}

	public void setLancamentoExcluir(Lancamento lancamentoExcluir) {
		this.lancamentoExcluir = lancamentoExcluir;
	}

	public void setLancamentoList(List<Lancamento> lancamentoList) {
		this.lancamentoList = lancamentoList;
	}
}

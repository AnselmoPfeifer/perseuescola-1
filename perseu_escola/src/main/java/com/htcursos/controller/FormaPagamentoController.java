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
import com.htcursos.model.entity.FormaPagamento;
import com.htcursos.model.service.FormaPagamentoService;
import com.htcursos.model.service.ServiceExpcetion;

@Controller("formaPagamentoController")
@Scope("view")
@ManagedBean
public class FormaPagamentoController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private FormaPagamentoService formaPagamentoService;
	private FormaPagamento formaPagamento = new FormaPagamento();
	
	//dados da tela
	private FormaPagamento formaPagamentoExcluir;

	private List<FormaPagamento> formaPagamentoList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			formaPagamentoService.salvar(formaPagamento);
			atualiza();
			formaPagamento = new FormaPagamento();
			JsfMessages.adicionaMensagemInfo("FormaPagamento salvo");
		} catch (ServiceExpcetion e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}
		
	}
	
	public void excluir() {
		// remove do banco
		getFormaPagamentoService().excluir(formaPagamentoExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		formaPagamentoList.remove(formaPagamentoExcluir);
		formaPagamentoExcluir = null;
		JsfMessages.adicionaMensagemInfo("FormaPagamento removido");

	}

	public void cancelar() {
		formaPagamento = new FormaPagamento();
	}

	public void editar(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public List<FormaPagamento> getFormaPagamentoList() {
		return getFormaPagamentoService().buscarTodos();
	}
	
	public void atualiza() {
		formaPagamentoList = getFormaPagamentoService().buscarTodos();
	}

		
	//getter and setters
	public FormaPagamentoService getFormaPagamentoService() {
		return formaPagamentoService;
	}

	public void setFormaPagamentoService(FormaPagamentoService formaPagamentoService) {
		this.formaPagamentoService = formaPagamentoService;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public FormaPagamento getFormaPagamentoExcluir() {
		return formaPagamentoExcluir;
	}

	public void setFormaPagamentoExcluir(FormaPagamento formaPagamentoExcluir) {
		this.formaPagamentoExcluir = formaPagamentoExcluir;
	}

	public void setFormaPagamentoList(List<FormaPagamento> formaPagamentoList) {
		this.formaPagamentoList = formaPagamentoList;
	}
}

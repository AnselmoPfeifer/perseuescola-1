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
import com.htcursos.model.entity.Pagamento;
import com.htcursos.model.service.PagamentoService;
import com.htcursos.model.service.ServiceException;

@Controller("pagamentoController")
@Scope("view")
@ManagedBean
public class PagamentoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PagamentoService pagamentoService;
	private Pagamento pagamento = new Pagamento();

	// dados da tela
	private Pagamento pagamentoExcluir;

	private List<Pagamento> pagamentoList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			pagamentoService.salvar(pagamento);
			atualiza();
			pagamento = new Pagamento();
			JsfMessages.adicionaMensagemInfo("Pagamento salvo");
		} catch (ServiceException e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		// remove do banco
		getPagamentoService().excluir(pagamentoExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		pagamentoList.remove(pagamentoExcluir);
		pagamentoExcluir = null;
		JsfMessages.adicionaMensagemInfo("Pagamento removido");

	}

	public void cancelar() {
		pagamento = new Pagamento();
	}

	public void editar(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<Pagamento> getPagamentoList() {
		return getPagamentoService().buscarTodos();
	}

	public void atualiza() {
		pagamentoList = getPagamentoService().buscarTodos();
	}

	public PagamentoService getPagamentoService() {
		return pagamentoService;
	}

	public void setPagamentoService(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Pagamento getPagamentoExcluir() {
		return pagamentoExcluir;
	}

	public void setPagamentoExcluir(Pagamento pagamentoExcluir) {
		this.pagamentoExcluir = pagamentoExcluir;
	}

	public void setPagamentoList(List<Pagamento> pagamentoList) {
		this.pagamentoList = pagamentoList;
	}

}

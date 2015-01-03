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
import com.htcursos.model.entity.Cartao;
import com.htcursos.model.service.CartaoService;
import com.htcursos.model.service.ServiceExpcetion;

@Controller("cartaoController")
@Scope("view")
@ManagedBean
public class CartaoController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private CartaoService cartaoService;
	private Cartao cartao = new Cartao();
	
	//dados da tela
	private Cartao cartaoExcluir;

	private List<Cartao> cartaoList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			cartaoService.salvar(cartao);
			atualiza();
			cartao = new Cartao();
			JsfMessages.adicionaMensagemInfo("Cartao salvo");
		} catch (ServiceExpcetion e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}
		
	}
	
	public void excluir() {
		// remove do banco
		getCartaoService().excluir(cartaoExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		cartaoList.remove(cartaoExcluir);
		cartaoExcluir = null;
		JsfMessages.adicionaMensagemInfo("Cartao removido");

	}

	public void cancelar() {
		cartao = new Cartao();
	}

	public void editar(Cartao cartao) {
		this.cartao = cartao;
	}

	public List<Cartao> getCartaoList() {
		return getCartaoService().buscarTodos();
	}
	
	public void atualiza() {
		cartaoList = getCartaoService().buscarTodos();
	}

		
	//getter and setters
	public CartaoService getCartaoService() {
		return cartaoService;
	}

	public void setCartaoService(CartaoService cartaoService) {
		this.cartaoService = cartaoService;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public Cartao getCartaoExcluir() {
		return cartaoExcluir;
	}

	public void setCartaoExcluir(Cartao cartaoExcluir) {
		this.cartaoExcluir = cartaoExcluir;
	}

	public void setCartaoList(List<Cartao> cartaoList) {
		this.cartaoList = cartaoList;
	}
}

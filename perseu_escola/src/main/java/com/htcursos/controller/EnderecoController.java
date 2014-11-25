package com.htcursos.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.controller.util.faces.JsfMessages;
import com.htcursos.model.entity.Endereco;
import com.htcursos.model.tools.CepWebService;

@Controller("enderecoController")
@Scope("view")
@ManagedBean
public class EnderecoController {
	@Autowired
	private CepWebService cepWebService;
	
	private Endereco endereco = new Endereco();
	private Endereco enderecoEditar;
	
	
	public void encontraCEP() {
		
		getCepWebService().cepWebBuscar(endereco);
		if (getCepWebService().getResultado() == 1) {
			endereco.setCep(endereco.getCep());
			endereco.setTipo_logradouro(getCepWebService().getEndereco().getTipo_logradouro());
			endereco.setLogradouro(getCepWebService().getEndereco().getLogradouro());
			endereco.setCidade(getCepWebService().getEndereco().getCidade());
			endereco.setBairro(getCepWebService().getEndereco().getBairro());
		} else {
			JsfMessages.adicionaMensagemInfo("WebService nao esta respondendo");		}
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		System.out.println("setei endereco");
		this.endereco = endereco;
	}

	public Endereco getEnderecoEditar() {
		return enderecoEditar;
	}

	public void setEnderecoEditar(Endereco enderecoEditar) {
		System.out.println("setei endereco1");
		this.enderecoEditar = enderecoEditar;
		setEndereco(enderecoEditar);
	}
	
	public void editar (Endereco endereco){
		System.out.println("fiz");
		this.endereco = endereco;
	}

	public CepWebService getCepWebService() {
		return cepWebService;
	}

	public void setCepWebService(CepWebService cepWebService) {
		this.cepWebService = cepWebService;
	}
	
	

}

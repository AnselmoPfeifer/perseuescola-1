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
import com.htcursos.model.entity.ClienteMatricula;
import com.htcursos.model.enums.TipoContratacaoEnum;
import com.htcursos.model.service.ClienteMatriculaService;
import com.htcursos.model.service.ServiceExpcetion;

@Controller("clienteMatriculaController")
@Scope("view")
@ManagedBean
public class ClienteMatriculaController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteMatriculaService clienteMatriculaService;	
	private ClienteMatricula clienteMatricula = new ClienteMatricula();
	private List<ClienteMatricula> clienteMatriculaList;
	
	private ClienteMatricula clienteMatriculaExcluir;
	
	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			clienteMatriculaService.salvar(clienteMatricula);
			atualiza();
			clienteMatricula = new ClienteMatricula();
			JsfMessages.adicionaMensagemInfo("ClienteMatricula salvo");
		} catch (ServiceExpcetion e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}
		
	}
	
	public void excluir() {
		// remove do banco
		getClienteMatriculaService().excluir(clienteMatriculaExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		clienteMatriculaList.remove(clienteMatriculaExcluir);
		clienteMatriculaExcluir = null;
		JsfMessages.adicionaMensagemInfo("ClienteMatricula removido");

	}

	public void cancelar() {
		clienteMatricula = new ClienteMatricula();
	}

	public void editar(ClienteMatricula clienteMatricula) {
		this.clienteMatricula = clienteMatricula;
	}

	public List<ClienteMatricula> getClienteMatriculaList() {
		return getClienteMatriculaService().buscarTodos();
	}
	
	public void atualiza() {
		clienteMatriculaList = getClienteMatriculaService().buscarTodos();
	}
	
	public TipoContratacaoEnum[] getTipoContratacaoValor() {
	    return TipoContratacaoEnum.values();
	  }
		
	//getter and setters
	public ClienteMatriculaService getClienteMatriculaService() {
		return clienteMatriculaService;
	}

	public void setClienteMatriculaService(ClienteMatriculaService clienteMatriculaService) {
		this.clienteMatriculaService = clienteMatriculaService;
	}

	public ClienteMatricula getClienteMatricula() {
		return clienteMatricula;
	}

	public void setClienteMatricula(ClienteMatricula clienteMatricula) {
		this.clienteMatricula = clienteMatricula;
	}

	public ClienteMatricula getClienteMatriculaExcluir() {
		return clienteMatriculaExcluir;
	}

	public void setClienteMatriculaExcluir(ClienteMatricula clienteMatriculaExcluir) {
		this.clienteMatriculaExcluir = clienteMatriculaExcluir;
	}

	public void setClienteMatriculaList(List<ClienteMatricula> clienteMatriculaList) {
		this.clienteMatriculaList = clienteMatriculaList;
	}

}

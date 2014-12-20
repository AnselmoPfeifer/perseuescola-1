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
import com.htcursos.model.entity.ClienteMatriculaCalendario;
import com.htcursos.model.service.ClienteMatriculaCalendarioService;
import com.htcursos.model.service.ServiceExpcetion;

@Controller("ClienteMatriculaCalendarioController")
@Scope("view")
@ManagedBean
public class ClienteMatriculaCalendarioController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteMatriculaCalendarioService ClienteMatriculaCalendarioService;
	private ClienteMatriculaCalendario ClienteMatriculaCalendario = new ClienteMatriculaCalendario();
	
	//dados da tela
	private ClienteMatriculaCalendario ClienteMatriculaCalendarioExcluir;

	private List<ClienteMatriculaCalendario> ClienteMatriculaCalendarioList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			ClienteMatriculaCalendarioService
					.salvar(ClienteMatriculaCalendario);
			atualiza();
			ClienteMatriculaCalendario = new ClienteMatriculaCalendario();
			JsfMessages
					.adicionaMensagemInfo("ClienteMatriculaCalendario salvo");
		} catch (ServiceExpcetion e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}
		
	}
	
	public void excluir() {
		// remove do banco
		getClienteMatriculaCalendarioService().excluir(ClienteMatriculaCalendarioExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		ClienteMatriculaCalendarioList.remove(ClienteMatriculaCalendarioExcluir);
		ClienteMatriculaCalendarioExcluir = null;
		JsfMessages.adicionaMensagemInfo("ClienteMatriculaCalendario removido");

	}

	public void cancelar() {
		ClienteMatriculaCalendario = new ClienteMatriculaCalendario();
	}

	public void editar(ClienteMatriculaCalendario ClienteMatriculaCalendario) {
		this.ClienteMatriculaCalendario = ClienteMatriculaCalendario;
	}

	public List<ClienteMatriculaCalendario> getClienteMatriculaCalendarioList() {
		return getClienteMatriculaCalendarioService().buscarTodos();
	}
	
	public void atualiza() {
		ClienteMatriculaCalendarioList = getClienteMatriculaCalendarioService().buscarTodos();
	}

		
	//getter and setters
	public ClienteMatriculaCalendarioService getClienteMatriculaCalendarioService() {
		return ClienteMatriculaCalendarioService;
	}

	public void setClienteMatriculaCalendarioService(ClienteMatriculaCalendarioService ClienteMatriculaCalendarioService) {
		this.ClienteMatriculaCalendarioService = ClienteMatriculaCalendarioService;
	}

	public ClienteMatriculaCalendario getClienteMatriculaCalendario() {
		return ClienteMatriculaCalendario;
	}

	public void setClienteMatriculaCalendario(ClienteMatriculaCalendario ClienteMatriculaCalendario) {
		this.ClienteMatriculaCalendario = ClienteMatriculaCalendario;
	}

	public ClienteMatriculaCalendario getClienteMatriculaCalendarioExcluir() {
		return ClienteMatriculaCalendarioExcluir;
	}

	public void setClienteMatriculaCalendarioExcluir(ClienteMatriculaCalendario ClienteMatriculaCalendarioExcluir) {
		this.ClienteMatriculaCalendarioExcluir = ClienteMatriculaCalendarioExcluir;
	}

	public void setClienteMatriculaCalendarioList(List<ClienteMatriculaCalendario> ClienteMatriculaCalendarioList) {
		this.ClienteMatriculaCalendarioList = ClienteMatriculaCalendarioList;
	}
}

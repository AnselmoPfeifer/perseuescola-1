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
import com.htcursos.model.entity.Cliente;
import com.htcursos.model.entity.Contato;
import com.htcursos.model.entity.TipoContato;
import com.htcursos.model.service.ClienteService;
import com.htcursos.model.service.ContatoService;
import com.htcursos.model.service.ServiceExpcetion;
import com.htcursos.model.service.TipoContatoService;

@Controller("contatoController")
@Scope("view")
@ManagedBean
public class ContatoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContatoService contatoService;
	@Autowired
	private TipoContatoService tipoContatoService;
	@Autowired
	private ClienteService clienteService;

	private Contato contato = new Contato();
	private TipoContato tipoContato = new TipoContato();
	private Cliente cliente = new Cliente();

	// dados da tela
	private Contato contatoExcluir;

	private List<Contato> contatoList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			contatoService.salvar(contato);
			atualiza();
			contato = new Contato();
			JsfMessages.adicionaMensagemInfo("Contato salvo");
		} catch (ServiceExpcetion e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		// remove do banco
		getContatoService().excluir(contatoExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		contatoList.remove(contatoExcluir);
		contatoExcluir = null;
		JsfMessages.adicionaMensagemInfo("Contato removido");

	}

	public void cancelar() {
		contato = new Contato();
	}

	public void editar(Contato contato) {
		this.contato = contato;
	}

	public List<Contato> getContatoList() {
		return getContatoService().buscarTodos();
	}

	public void atualiza() {
		contatoList = getContatoService().buscarTodos();
	}

	// getter and setters
	public ContatoService getContatoService() {
		return contatoService;
	}

	public void setContatoService(ContatoService contatoService) {
		this.contatoService = contatoService;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Contato getContatoExcluir() {
		return contatoExcluir;
	}

	public void setContatoExcluir(Contato contatoExcluir) {
		this.contatoExcluir = contatoExcluir;
	}

	public void setContatoList(List<Contato> contatoList) {
		this.contatoList = contatoList;
	}

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}



package com.htcursos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.controller.util.faces.JsfMessages;
import com.htcursos.model.entity.Cliente;
import com.htcursos.model.entity.Contato;
import com.htcursos.model.entity.EnumCamisetas;
import com.htcursos.model.enums.TipoClienteEnum;
import com.htcursos.model.enums.TipoSexoEnum;
import com.htcursos.model.service.ClienteService;
import com.htcursos.model.service.ServiceExpcetion;
import com.htcursos.model.tools.CepWebService;

@Controller("clienteController")
@Scope("view")
@ManagedBean
public class ClienteController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private CepWebService cepWebService;

	public void encontraCEP() {

		getCepWebService().cepWebBuscar(cliente.getEndereco());
		if (getCepWebService().getResultado() == 1) {
			// Setando o endereco buscado para o cliente
			cliente.setEndereco(getCepWebService().getEndereco());
		} else {
			JsfMessages.adicionaMensagemInfo("WebService nao esta respondendo");
		}
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	private Cliente cliente = new Cliente();

	// dados da tela
	private Cliente clienteExcluir;

	private List<Cliente> clienteList;

	private Contato contato = new Contato();

	private Cliente clienteSalvo;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public List<Cliente> obterClientes(String nomeBusca) {

		return clienteService.buscarTodos(nomeBusca);
	}

	// //Formata a data que vem da tela
	// String dataFormatada;
	//
	// public String getDataFormatada() {
	// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	// if(cliente.getPfDatanascimento() != null)
	// return sdf.format(cliente.getPfDatanascimento());
	// return "";
	// }
	//
	// public void setDataFormatada(String dataFormatada) {
	// this.dataFormatada = dataFormatada;
	// }

	public void editarContato(Contato contato) {
		this.contato = contato;
	}

	public void adicionarContato() {
		contato.setCliente(cliente);
		if (cliente.getContatoList() == null) {
			cliente.setContatoList(new ArrayList<Contato>());
		}
		cliente.getContatoList().add(contato);

		contato = new Contato();
	}

	public void excluirContato() {
		cliente.getContatoList().remove(contato);
	}

	public void salvar() {
		try {
			clienteService.salvar(cliente);
			setClienteSalvo(cliente);
			atualiza();
			cliente = new Cliente();
			JsfMessages.adicionaMensagemInfo("Cliente salvo");
		} catch (ServiceExpcetion e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void excluir() {
		// remove do banco
		getClienteService().excluir(clienteExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		clienteList.remove(clienteExcluir);
		clienteExcluir = null;
		JsfMessages.adicionaMensagemInfo("Cliente removido");
	}

	// Enums
	public TipoClienteEnum[] getTipoClienteValor() {
		return TipoClienteEnum.values();
	}

	public TipoSexoEnum[] getTipoSexoValues() {
		return TipoSexoEnum.values();
	}

	public EnumCamisetas[] getCamisetas() {
		return EnumCamisetas.values();
	}
	
	public Date testeData(){
		return new Date();
		
	}

	public void cancelar() {
		cliente = new Cliente();
	}

	public void editar(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClienteList() {
		return getClienteService().buscarTodos();
	}

	public void atualiza() {
		clienteList = getClienteService().buscarTodos();
	}

	// getter and setters
	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteExcluir() {
		return clienteExcluir;
	}

	public void setClienteExcluir(Cliente clienteExcluir) {
		this.clienteExcluir = clienteExcluir;
	}

	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}

	public Cliente getClienteSalvo() {
		return clienteSalvo;
	}

	public void setClienteSalvo(Cliente clienteSalvo) {
		this.clienteSalvo = clienteSalvo;
	}

	public CepWebService getCepWebService() {
		return cepWebService;
	}

	public void setCepWebService(CepWebService cepWebService) {
		this.cepWebService = cepWebService;
	}

}


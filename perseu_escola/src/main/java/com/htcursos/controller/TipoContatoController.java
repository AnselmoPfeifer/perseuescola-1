package com.htcursos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.controller.util.faces.JsfMessages;
import com.htcursos.model.entity.TipoContato;
import com.htcursos.model.service.ServiceException;
import com.htcursos.model.service.TipoContatoService;

@Controller("tipoContatoController")
@Scope("view")
@ManagedBean
public class TipoContatoController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private TipoContatoService tipoContatoService;
	private TipoContato tipoContato = new TipoContato();
	
	//dados da tela
	private TipoContato tipoContatoExcluir;

	private List<TipoContato> tipoContatoList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			tipoContatoService.salvar(tipoContato);
			atualiza();
			tipoContato = new TipoContato();
			JsfMessages.adicionaMensagemInfo("Tipo de Contato salvo");
		} catch (ServiceException e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}
		
	}
	
	public void excluir() {
		// remove do banco
		getTipoContatoService().excluir(tipoContatoExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		tipoContatoList.remove(tipoContatoExcluir);
		tipoContatoExcluir = null;
		JsfMessages.adicionaMensagemInfo("TipoContato removido");

	}

	public void cancelar() {
		tipoContato = new TipoContato();
	}

	public void editar(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public List<TipoContato> getTipoContatoList() {
		return getTipoContatoService().buscarTodos();
	}
	
	public void atualiza() {
		tipoContatoList = getTipoContatoService().buscarTodos();
	}

		
	//getter and setters
	public TipoContatoService getTipoContatoService() {
		return tipoContatoService;
	}

	public void setTipoContatoService(TipoContatoService tipoContatoService) {
		this.tipoContatoService = tipoContatoService;
	}

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public TipoContato getTipoContatoExcluir() {
		return tipoContatoExcluir;
	}

	public void setTipoContatoExcluir(TipoContato tipoContatoExcluir) {
		this.tipoContatoExcluir = tipoContatoExcluir;
	}

	public void setTipoContatoList(List<TipoContato> tipoContatoList) {
		this.tipoContatoList = tipoContatoList;
	}
}

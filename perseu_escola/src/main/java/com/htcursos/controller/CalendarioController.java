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
import com.htcursos.model.entity.Calendario;
import com.htcursos.model.service.CalendarioService;
import com.htcursos.model.service.ServiceExpcetion;

@Controller("calendarioController")
@Scope("view")
@ManagedBean
public class CalendarioController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private CalendarioService calendarioService;
	
	private Calendario calendario = new Calendario();
	
	//dados da tela
	private Calendario calendarioExcluir;
	private List<Calendario> calendarioList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			calendarioService.salvar(calendario);
	
		atualiza();
		calendario = new Calendario();
		JsfMessages.adicionaMensagemInfo("Calendario salvo");
		} catch (ServiceExpcetion e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void excluir() {
		// remove do banco
		getCalendarioService().excluir(calendarioExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		calendarioList.remove(calendarioExcluir);
		calendarioExcluir = null;
		JsfMessages.adicionaMensagemInfo("Calendario removido");
	}
	

	public void cancelar() {
		calendario = new Calendario();
	}

	public void editar(Calendario calendario) {
		this.calendario = calendario;
	}

	public List<Calendario> getCalendarioList() {
		return getCalendarioService().buscarTodos();
	}
	
	public void atualiza() {
		calendarioList = getCalendarioService().buscarTodos();
	}

		
	//getter and setters
	public CalendarioService getCalendarioService() {
		return calendarioService;
	}

	public void setCalendarioService(CalendarioService calendarioService) {
		this.calendarioService = calendarioService;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public Calendario getCalendarioExcluir() {
		return calendarioExcluir;
	}

	public void setCalendarioExcluir(Calendario calendarioExcluir) {
		this.calendarioExcluir = calendarioExcluir;
	}

	public void setCalendarioList(List<Calendario> calendarioList) {
		this.calendarioList = calendarioList;
	}
}

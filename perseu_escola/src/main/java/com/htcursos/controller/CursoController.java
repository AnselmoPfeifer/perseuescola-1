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
import com.htcursos.model.entity.Curso;
import com.htcursos.model.service.CursoService;
import com.htcursos.model.service.ServiceException;

@Controller("cursoController")
@Scope("view")
@ManagedBean
public class CursoController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private CursoService cursoService;
	
	private Curso curso = new Curso();
	
	//dados da tela
	private Curso cursoExcluir;

	private List<Curso> cursoList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			cursoService.salvar(curso);
			atualiza();
			curso = new Curso();
			JsfMessages.adicionaMensagemInfo("Curso salvo");
		} catch (ServiceException e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}
		
	}
	
	public void excluir() {
		// remove do banco
		getCursoService().excluir(cursoExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		cursoList.remove(cursoExcluir);
		cursoExcluir = null;
		JsfMessages.adicionaMensagemInfo("Curso removido");

	}

	public void cancelar() {
		curso = new Curso();
	}

	public void editar(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getCursoList() {
		return getCursoService().buscarTodos();
	}
	
	public void atualiza() {
		cursoList = getCursoService().buscarTodos();
	}

		
	//getter and setters
	public CursoService getCursoService() {
		return cursoService;
	}

	public void setCursoService(CursoService cursoService) {
		this.cursoService = cursoService;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Curso getCursoExcluir() {
		return cursoExcluir;
	}

	public void setCursoExcluir(Curso cursoExcluir) {
		this.cursoExcluir = cursoExcluir;
	}

	public void setCursoList(List<Curso> cursoList) {
		this.cursoList = cursoList;
	}
}

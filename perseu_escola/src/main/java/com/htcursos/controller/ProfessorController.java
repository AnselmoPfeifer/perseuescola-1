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
import com.htcursos.model.entity.Professor;
import com.htcursos.model.service.ProfessorService;
import com.htcursos.model.service.ServiceExpcetion;

@Controller("professorController")
@Scope("view")
@ManagedBean
public class ProfessorController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProfessorService professorService;
	private Professor professor = new Professor();
	
	//dados da tela
	private Professor professorExcluir;

	private List<Professor> professorList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			professorService.salvar(professor);
			atualiza();
			professor = new Professor();
			JsfMessages.adicionaMensagemInfo("Professor salvo");
		} catch (ServiceExpcetion e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}
	
	}
	
	public void excluir() {
		// remove do banco
		getProfessorService().excluir(professorExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		professorList.remove(professorExcluir);
		professorExcluir = null;
		JsfMessages.adicionaMensagemInfo("Professor removido");

	}

	public void cancelar() {
		professor = new Professor();
	}

	public void editar(Professor professor) {
		this.professor = professor;
	}

	public List<Professor> getProfessorList() {
		return getProfessorService().buscarTodos();
	}
	
	public void atualiza() {
		professorList = getProfessorService().buscarTodos();
	}

		
	//getter and setters
	public ProfessorService getProfessorService() {
		return professorService;
	}

	public void setProfessorService(ProfessorService professorService) {
		this.professorService = professorService;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Professor getProfessorExcluir() {
		return professorExcluir;
	}

	public void setProfessorExcluir(Professor professorExcluir) {
		this.professorExcluir = professorExcluir;
	}

	public void setProfessorList(List<Professor> professorList) {
		this.professorList = professorList;
	}
}

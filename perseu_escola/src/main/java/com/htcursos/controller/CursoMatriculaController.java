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
import com.htcursos.model.entity.CursoMatricula;
import com.htcursos.model.service.CursoMatriculaService;
import com.htcursos.model.service.ServiceException;

@Controller("cursoMatriculaController")
@Scope("view")
@ManagedBean
public class CursoMatriculaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CursoMatriculaService cursoMatriculaService;
	private CursoMatricula cursoMatricula = new CursoMatricula();

	// dados da tela
	private CursoMatricula cursoMatriculaExcluir;

	private List<CursoMatricula> cursoMatriculaList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			cursoMatriculaService.salvar(cursoMatricula);
			atualiza();
			cursoMatricula = new CursoMatricula();
			JsfMessages.adicionaMensagemInfo("CursoMatricula salvo");
		} catch (ServiceException e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		// remove do banco
		getCursoMatriculaService().excluir(cursoMatriculaExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		cursoMatriculaList.remove(cursoMatriculaExcluir);
		cursoMatriculaExcluir = null;
		JsfMessages.adicionaMensagemInfo("CursoMatricula removido");

	}

	public void cancelar() {
		cursoMatricula = new CursoMatricula();
	}

	public void editar(CursoMatricula cursoMatricula) {
		this.cursoMatricula = cursoMatricula;
	}

	public List<CursoMatricula> getCursoMatriculaList() {
		return getCursoMatriculaService().buscarTodos();
	}

	public void atualiza() {
		cursoMatriculaList = getCursoMatriculaService().buscarTodos();
	}

	// getter and setters
	public CursoMatriculaService getCursoMatriculaService() {
		return cursoMatriculaService;
	}

	public void setCursoMatriculaService(
			CursoMatriculaService cursoMatriculaService) {
		this.cursoMatriculaService = cursoMatriculaService;
	}

	public CursoMatricula getCursoMatricula() {
		return cursoMatricula;
	}

	public void setCursoMatricula(CursoMatricula cursoMatricula) {
		this.cursoMatricula = cursoMatricula;
	}

	public CursoMatricula getCursoMatriculaExcluir() {
		return cursoMatriculaExcluir;
	}

	public void setCursoMatriculaExcluir(CursoMatricula cursoMatriculaExcluir) {
		this.cursoMatriculaExcluir = cursoMatriculaExcluir;
	}

	public void setCursoMatriculaList(List<CursoMatricula> cursoMatriculaList) {
		this.cursoMatriculaList = cursoMatriculaList;
	}
}

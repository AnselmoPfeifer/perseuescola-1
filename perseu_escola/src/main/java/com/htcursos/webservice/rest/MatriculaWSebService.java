package com.htcursos.webservice.rest;

import java.util.List;

//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.htcursos.model.entity.Matricula;
import com.htcursos.model.service.MatriculaService;


//@Path("/matriculaService")
@Service
@Scope("request")
public class MatriculaWSebService {
	
	@Autowired
	private MatriculaService matriculaService;
	
	//@GET
	//@Path("/getMatriculaList")
	//@Produces("text/plain")
	//@Produces(MediaType.APPLICATION_JSON)
	public List<Matricula> getMatriculas(){
		return matriculaService.buscarTodos();
	}
	
	
//	public String getMatriculas(){
//		return matriculaService.buscarTodos().toString();
//	}
//	
	
}
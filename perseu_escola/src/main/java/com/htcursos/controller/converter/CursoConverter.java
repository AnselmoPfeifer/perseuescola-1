package com.htcursos.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.htcursos.model.dao.CursoDAO;
import com.htcursos.model.entity.Curso;
@Component
public class CursoConverter implements Converter {
	@Autowired
	CursoDAO cursoDao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		try{
			if (arg2.trim().equals("")) {
	
			} else {
				return cursoDao.buscarPorId(Integer.parseInt(arg2));
			}
		}catch(NumberFormatException e){
			return null;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String temp = null;
		if (arg2 == null || arg2.equals("")) {

		} else {
			Curso curso = (Curso) arg2;
			return String.valueOf(curso.getId());
		}
		return null;
	}
}
	

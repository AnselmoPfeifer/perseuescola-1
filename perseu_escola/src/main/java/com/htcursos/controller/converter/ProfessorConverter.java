package com.htcursos.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.htcursos.model.dao.ProfessorDAO;
import com.htcursos.model.entity.Professor;

@Component
public class ProfessorConverter implements Converter {
	@Autowired
	ProfessorDAO professorDao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2.trim().equals("")){		
		}else{
			return professorDao.buscarPorId(Integer.parseInt(arg2));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String temp = null;
		if (arg2 == null || arg2.equals("")){
			
		}else{
			Professor professor = (Professor)arg2;
			return String.valueOf(professor.getId());
		}
		return null;
	}

}

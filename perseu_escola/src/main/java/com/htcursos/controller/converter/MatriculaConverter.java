package com.htcursos.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.htcursos.model.dao.MatriculaDAO;
import com.htcursos.model.entity.Matricula;
@Component
public class MatriculaConverter implements Converter {
	@Autowired
	MatriculaDAO matriculaDao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2.trim().equals("")) {

		} else {
			return matriculaDao.buscarPorId(Integer.parseInt(arg2));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String temp = null;
		if (arg2 == null || arg2.equals("")) {

		} else {
			Matricula matricula= (Matricula) arg2;
			return String.valueOf(matricula.getId());
		}
		return null;
	}


}

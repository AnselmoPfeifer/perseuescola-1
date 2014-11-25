package com.htcursos.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.htcursos.model.dao.CalendarioDAO;
import com.htcursos.model.entity.Calendario;
@Component
public class CalendarioConverter implements Converter {
	@Autowired
	CalendarioDAO calendarioDao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2.trim().equals("")) {

		} else {
			return calendarioDao.buscarPorId(Integer.parseInt(arg2));
		}
		return null;
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String temp = null;
		if (arg2 == null || arg2.equals("")) {

		} else {
			Calendario calendario = (Calendario) arg2;
			return String.valueOf(calendario.getId());
		}
		return null;
	}
}

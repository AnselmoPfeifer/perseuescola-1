package com.htcursos.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.htcursos.model.dao.TipoContatoDAO;
import com.htcursos.model.entity.TipoContato;

@Component("tipoContatoConverter")
public class TipoContatoConverter implements Converter {
	@Autowired
	TipoContatoDAO tipoContatoDao;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.trim().equals("")) {
			try {
				return tipoContatoDao.buscarPorId(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value instanceof TipoContato) {
			TipoContato tipoContato = (TipoContato) value;
			return String.valueOf(tipoContato.getId());
		}
		return null;
	}
}
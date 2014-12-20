package com.htcursos.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.htcursos.model.dao.FormaPagamentoDAO;
import com.htcursos.model.entity.FormaPagamento;
@Component
public class FormaPagamentoConverter implements Converter {
	@Autowired
	FormaPagamentoDAO formaPagamentoDao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		if (value == null || value.isEmpty() || value.indexOf("Selecione") > -1) {
			return null;
		}

		return formaPagamentoDao.buscarPorId(Integer.parseInt(value));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (!(value instanceof FormaPagamento)) {
			return null;
		}

		return String.valueOf(((FormaPagamento) value).getId());
	}

}

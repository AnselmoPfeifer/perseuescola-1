package com.htcursos.controller.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.htcursos.model.dao.UnidadeDAO;
import com.htcursos.model.entity.Unidade;

@Component("unidadeConverter")
public class UnidadeConverter implements javax.faces.convert.Converter {
	@Autowired
	UnidadeDAO unidadeDao;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		if (value.trim().equals("")) {
			return null;
		} else {
			try {
				return unidadeDao.buscarPorId(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro de Conversão",
						"Opção Inválida"));
			}
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if (value == null || value.equals("") || !(value instanceof Unidade)) {
	            return "";
	     } 
	
		return String.valueOf(((Unidade) value).getId());
	}

}
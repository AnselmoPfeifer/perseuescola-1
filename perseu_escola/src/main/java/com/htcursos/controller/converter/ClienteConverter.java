package com.htcursos.controller.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.htcursos.model.dao.ClienteDAO;
import com.htcursos.model.entity.Cliente;

@Component("clienteConverter")
public class ClienteConverter implements Converter {
	@Autowired
	ClienteDAO clienteDao;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value.trim().equals("")) {
			return null;
		} else {
			try {
				return clienteDao.buscarPorId(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro de Conversão",
						"Opção Inválida"));
			}
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value == null || value.equals("") || !(value instanceof Cliente)) {
			return "";
		}
		Cliente cliente = (Cliente) value;
		return String.valueOf(cliente.getId());
	}
}

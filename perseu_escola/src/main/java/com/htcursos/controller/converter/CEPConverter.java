package com.htcursos.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;



@FacesConverter(value="cepConverter") 
public class CEPConverter implements javax.faces.convert.Converter{
	@Override	
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        String cep = value;
        if (value!= null && !value.equals(""))
        	cep = value.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("/", "");
        return cep;
   }
@Override
   public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        String cep = value.toString();
        if (cep != null && cep.length() == 8)
        	cep = cep.substring(0, 2) + "." + cep.substring(2, 5) + "-" + cep.substring(5, 8);
        return cep;
   }
}	
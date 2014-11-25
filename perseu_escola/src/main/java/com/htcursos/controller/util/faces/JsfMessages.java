package com.htcursos.controller.util.faces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JsfMessages {
	
	public static void adicionaMensagemInfo(String message){
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}
	
	public static void adicionaMensagemErro(String message){
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}
	
	public static void adicionaMensagemAviso(String message){
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
	}

}

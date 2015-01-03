package com.htcursos.controller.util.faces;

import javax.faces.application.FacesMessage;  
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;  
import org.primefaces.event.ToggleEvent; 
import org.springframework.stereotype.Controller;

@Controller("FieldsetBean")
@RequestScoped
public class FieldsetBean {  
	  
    public void handleToggle(ToggleEvent event) {  
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fieldset Toggled", "Visibility:" + event.getVisibility());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
}  
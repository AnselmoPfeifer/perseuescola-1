package com.htcursos.controller.util.faces;

import java.util.Date;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Controller;



@Controller("ValidarDatas")
@RequestScoped
public class ValidarDatas {
	private Date dataInicio;

	private Date dataFim;
	
	public void validateBeginDate(FacesContext context, UIComponent component, Object value) {

		dataInicio = (Date) value;

		}

		public void validateEndDate(FacesContext context, UIComponent component, Object value) {

		dataFim = (Date) value;

		if (! dataFim.after(dataInicio)) {
			System.out.println("Data final maior que data inicial");
				//new adicionaMensagemErro("Data final maior que data inicial");
		//throw new ValidatorException(new FacesMessage(“Data final maior que data inicial”));

		} else {
			System.out.println("data ok");
		//throw new ValidatorException(new FacesMessage(“Datas OK”));

		}

		}
}

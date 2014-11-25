package com.htcursos.model.enums;

public enum TipoAtivoEnum {
	SIM("Sim"), NAO("Nao");
	
	private String label;
	
	private TipoAtivoEnum(String label){
		this.label=label;
	}
	
	public String getLabel(){
		return label;
	}
}

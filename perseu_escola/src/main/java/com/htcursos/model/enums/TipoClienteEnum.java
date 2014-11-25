package com.htcursos.model.enums;

public enum TipoClienteEnum {
	PF("Pessoa Fisica"),PJ("Pessoa Juridica");
	private String label;
	
	private TipoClienteEnum(String label) {
		this.label=label;
	}

	public String getLabel() {
		return label;
	}

	
}

package com.htcursos.model.enums;

public enum TipoContratacaoEnum {
	CONTRATANTECONSUMIDOR("Contratante / Consumidor"),
	CONTRATANTE("Contratante"),
	CONSUMIDOR("Consumidor");
	
	private String label;

	private TipoContratacaoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}

package com.htcursos.model.enums;

public enum TipoSexoEnum {
	M("Masculino"),F("Feminino");
	
	private String label;
	
	private TipoSexoEnum(String label) {
		this.label=label;
	}

	public String getLabel() {
		return label;
	}
	
}

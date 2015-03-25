package com.htcursos.model.entity;

public enum GrauInstrucaoEnum {
	FUNDAMENTAL("Grau Fundamental"), MEDIO("Grau Médio"), SUPERIOR_INCOMPLETO(
			"Grau Superior Incompleto"), SUPERIOR_COMPLETO(
			"Grau Superior Completo"), POS_GRADUACAO("Pós-Graduação");

	private String label;

	private GrauInstrucaoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
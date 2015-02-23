package com.htcursos.model.entity;

public enum DiasExperienciaEnum {
	TRINTA_DIAS("30(trinta) dias"), SESSENTA_DIAS("60(sessenta) dias"), NOVENTA_DIAS(
			"90(noventa) dias");

	private String label;

	private DiasExperienciaEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
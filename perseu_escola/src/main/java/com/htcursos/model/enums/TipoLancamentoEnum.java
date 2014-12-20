package com.htcursos.model.enums;

public enum TipoLancamentoEnum {
	
	DEBITO ("D�bito"), 
	CREDITO("Cr�dito");
	
	private String label;

	private TipoLancamentoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}

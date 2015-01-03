package com.htcursos.model.enums;

public enum TipoLancamentoEnum {
	
	DEBITO ("Débito"), 
	CREDITO("Crédito");
	
	private String label;

	private TipoLancamentoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}

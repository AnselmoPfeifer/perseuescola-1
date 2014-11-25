package com.htcursos.model.service;

public class ServiceExpcetion extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ServiceExpcetion(String mensagem, Exception causa) {
		super(mensagem, causa);
	}

	public ServiceExpcetion(Exception causa) {
		super("Não pode realizar essa operação! motivo: "+ causa);
	}

	public ServiceExpcetion(String string) {
		super(string);
	}
}

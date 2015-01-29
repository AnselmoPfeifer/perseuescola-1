package com.htcursos.model.service;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ServiceException(String mensagem, Exception causa) {
		super(mensagem, causa);
	}

	public ServiceException(Exception causa) {
		super("Não pode realizar essa operação! motivo: "+ causa);
	}

	public ServiceException(String string) {
		super(string);
	}
}

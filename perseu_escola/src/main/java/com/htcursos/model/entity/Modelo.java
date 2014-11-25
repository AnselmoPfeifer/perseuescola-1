package com.htcursos.model.entity;

import java.io.Serializable;
/**
 * Interface que deve ser implementada por todas as nossas entity para 
 * auxiliar no DAOGeneric
 * @author Virmerson
 *
 * @param <ID>
 */
public interface Modelo<ID extends Serializable> {
	public ID getId();
}

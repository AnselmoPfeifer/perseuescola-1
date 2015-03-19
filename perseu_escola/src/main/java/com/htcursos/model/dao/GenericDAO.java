package com.htcursos.model.dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Modelo;

@Repository
@Transactional
public abstract class GenericDAO<T extends Modelo<ID> ,ID extends Serializable> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2244285077690517465L;

	@PersistenceContext
	protected EntityManager em;
	
	private Class<T> tipo;
	
	@Autowired
	public GenericDAO(Class<T> tipo) {
		this.tipo=tipo;
	}
	
	@Transactional
	public T salvar(T o ){
		if(o.getId()==null){
			em.persist(o);
		}else{
			em.merge(o);
		}
		return o;
	}
	

	public T buscarPorId(ID id){
		return (T)em.find(tipo, id);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> buscarTodos() {

		List list = (List) em.createQuery("from " + tipo.getName() + " t order by t.id ").getResultList();
		if (list == null || list.size() == 0) {
			return list;
		}

		if (list.get(0) instanceof Comparable) {
			Collections.sort(list);
		}

		return list;
	}

	
	public List<T> buscarTodosIDDescrencente() {

		List list = (List) em.createQuery("from " + tipo.getName() + " t order by t.id desc").getResultList();
		if (list == null || list.size() == 0) {
			return list;
		}

		if (list.get(0) instanceof Comparable) {
			Collections.sort(list);
		}

		return list;
	}

	
	public T get(ID id){
		return (T) em.find(tipo, id);
	}
	
	
	@Transactional
	public void excluir(ID id){
		excluir(em.getReference(tipo, id));
	}
	
	
	@Transactional
	public void excluir(T obj){
		try {
			em.remove(get(obj.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void excluir(List<ID> ids){
		for (ID id : ids) {
			excluir(id);
		}
	}


}

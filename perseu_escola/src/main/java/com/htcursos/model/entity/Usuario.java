package com.htcursos.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;


@Entity
public class Usuario implements Serializable,Modelo<Integer>  {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_usuario")
	@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", initialValue = 1)
	private Integer id;
	
	@Column(unique=true)
	private String username;

	private String password;
	
	private String nome;	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "enable", columnDefinition = "BOOLEAN")
	private boolean enable = true;
	@ManyToMany(cascade = { CascadeType.MERGE , CascadeType.PERSIST})
	private List<Autorizacao> autorizacoes;

	public Usuario() {
		autorizacoes = new ArrayList<Autorizacao>();
	}
	
	public String autorizacoesToString(){
		String buff = new String();
		for (Autorizacao autorizacao : getAutorizacoes()) {
			buff += autorizacao.getAuthority()+" ";
		}
		return buff;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isEnable() {
		return enable;
	}



	public void setEnable(boolean enable) {
		this.enable = enable;
	}



	public List<Autorizacao> getAutorizacoes() {
		return autorizacoes;
	}



	public void setAutorizacoes(List<Autorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((autorizacoes == null) ? 0 : autorizacoes.hashCode());
		result = prime * result + (enable ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (autorizacoes == null) {
			if (other.autorizacoes != null)
				return false;
		} else if (!autorizacoes.equals(other.autorizacoes))
			return false;
		if (enable != other.enable)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password="
				+ password + ", enable=" + enable + ", autorizacoes="
				+ autorizacoes + "]";
	}



}


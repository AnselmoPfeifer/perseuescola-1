package com.htcursos.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.htcursos.model.enums.TipoLancamentoEnum;

@Entity
public class Lancamento  implements Serializable,Modelo<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator (name="seq_lancamento",sequenceName="seq_lancamento", initialValue=1, allocationSize=10)
	@GeneratedValue(generator="seq_lancamento", strategy=GenerationType.AUTO)
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date data;
	private TipoLancamentoEnum tipo;
	private BigDecimal valor;
	@JoinColumn
	@ManyToOne
	private Conta conta;

	private String historico;

	public Lancamento() {
		super();
	}
		

	public Lancamento(Date data, TipoLancamentoEnum tipo, BigDecimal valor,
			Conta conta, Matricula matricula, String historico) {
		super();
		this.data = data;
		this.tipo = tipo;
		this.valor = valor;
		this.conta = conta;
		this.historico = historico;
	}




	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Lancamento other = (Lancamento) obj;
		if (historico == null) {
			if (other.historico != null)
				return false;
		} else if (!historico.equals(other.historico))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", data=" + data + ", tipo=" + tipo
				+ ", valor=" + valor + ", conta=" + conta 
				+", historico=" + historico + "]";
	}
	
	
	
}

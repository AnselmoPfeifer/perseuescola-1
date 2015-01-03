package com.htcursos.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.internal.NotNull;

@Entity
public class Parcela implements Serializable, Modelo<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "seq_parcela", sequenceName = "seq_parcela", initialValue = 1, allocationSize = 10)
	@GeneratedValue(generator = "seq_parcela", strategy = GenerationType.AUTO)
	private Integer id;
	private BigDecimal valor;
	@Temporal(TemporalType.DATE)
	private Date datavencimento;
	
	@Temporal(TemporalType.DATE)
	private Date databaixa;

	@JoinColumn
	@ManyToOne
	private Pagamento pagamento;


	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private StatusParcelaEnum status = StatusParcelaEnum.ABERTO;
	
	private Double valorpago;
	@Temporal(TemporalType.DATE)
	private Date dtEmissao;
	
	@Column(name="tipo_parcela")
	@Enumerated(EnumType.ORDINAL)
	private TipoParcelaEnum tipoParcelaEnum;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_BAIXA")
	private TipoParcelaBaixaEnum tipoParcelaBaixa;
	

	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDatavencimento() {
		return datavencimento;
	}
	public String getDatavencimentoFormatada() {
		if (datavencimento != null)
			return new SimpleDateFormat("dd/MM/yyyy").format(datavencimento);
		else
			return "";
	}
	
	public void setDatavencimento(Date datavencimento) {
		this.datavencimento = datavencimento;
	}

	public Date getDatabaixa() {
		return databaixa;
	}

	public void setDatabaixa(Date databaixa) {
		this.databaixa = databaixa;
	}


	public Double getValorpago() {
		return valorpago;
	}

	public void setValorpago(Double valorpago) {
		this.valorpago = valorpago;
	}

	
	public Date getDtEmissao() {
		return dtEmissao;
	}

	public void setDtEmissao(Date dtEmissao) {
		this.dtEmissao = dtEmissao;
	}

	

	public StatusParcelaEnum getStatus() {
		return status;
	}

	public void setStatus(StatusParcelaEnum status) {
		this.status = status;
	}

	public TipoParcelaEnum getTipoParcelaEnum() {
		return tipoParcelaEnum;
	}

	public void setTipoParcelaEnum(TipoParcelaEnum tipoParcelaEnum) {
		this.tipoParcelaEnum = tipoParcelaEnum;
	}

	public TipoParcelaBaixaEnum getTipoParcelaBaixa() {
		return tipoParcelaBaixa;
	}

	public void setTipoParcelaBaixa(TipoParcelaBaixaEnum tipoParcelaBaixa) {
		this.tipoParcelaBaixa = tipoParcelaBaixa;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dtEmissao == null) ? 0 : dtEmissao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Parcela other = (Parcela) obj;
		if (dtEmissao == null) {
			if (other.dtEmissao != null)
				return false;
		} else if (!dtEmissao.equals(other.dtEmissao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	

	
}

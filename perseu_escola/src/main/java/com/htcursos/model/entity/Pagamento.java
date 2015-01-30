package com.htcursos.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
public class Pagamento implements Serializable, Modelo<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="seq_pagamento", sequenceName="seq_pagamento", initialValue=1, allocationSize=0)
	@GeneratedValue(generator="seq_pagamento", strategy = GenerationType.AUTO)
	private Integer id;
	@JoinColumn
	@ManyToOne
	private Matricula matricula;
	@JoinColumn
	@ManyToOne
	private FormaPagamento formaPagamento;
	private Date dataEmissao = new Date();
	private Date dataVencimento = new Date() ;
	private BigDecimal valor = new BigDecimal(0);
	private Integer numeroParcelas;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="pagamento", cascade={CascadeType.MERGE,CascadeType.PERSIST})
	private List<Parcela> parcelaList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public String getDataEmissaoFormatada() {
		if (dataEmissao != null)
			return new SimpleDateFormat("dd/MM/yyyy").format(dataEmissao);
		else
			return "";
	}
	
	
	public String getDataVencimentoFormatada() {
		if (dataVencimento != null)
			return new SimpleDateFormat("dd/MM/yyyy").format(dataVencimento);
		else
			return "";
	}
	

	public BigDecimal getValor() {
		return valor;
	}
	
	public String getValorFmt() {

		// moeda
		NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(new Locale(
				"pt", "BR")); // para formatar os numeros na moeda do Brasil.

		moneyFormat.setMinimumFractionDigits(2);

		String valorFtm = moneyFormat.format(valor) ;

		return valorFtm;
	}

	
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}
	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	public List<Parcela> getParcelaList() {
		return parcelaList;
	}
	public void setParcelaList(List<Parcela> parcelaList) {
		this.parcelaList = parcelaList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((formaPagamento == null) ? 0 : formaPagamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
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
		Pagamento other = (Pagamento) obj;
		if (formaPagamento == null) {
			if (other.formaPagamento != null)
				return false;
		} else if (!formaPagamento.equals(other.formaPagamento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	/**
	 * Gera Parcelas e j�� seta na propria lista
	 * @return
	 */
	public List<Parcela> gerarParcelas() {
		parcelaList =  new ArrayList<Parcela>();
		BigDecimal valorParcela = this.getValorParcela();
		Calendar dataVencimento = Calendar.getInstance();
		dataVencimento.setTime(this.getDataVencimento());
		for (int i = 0; i < numeroParcelas.intValue(); i++) {

			Parcela parc = new Parcela();
			parc.setPagamento(this);
			parc.setValor(valorParcela);
			parc.setDatavencimento(dataVencimento.getTime());
			//somando 1 mes
			dataVencimento.add(Calendar.MONTH, 1);
			
			parcelaList.add(parc);
		}
		return parcelaList;
	}
	/**
	 * calculando o valor da parcela
	 * @return
	 */
	public BigDecimal getValorParcela() {
		BigDecimal numeroParcelas = new BigDecimal( this.getNumeroParcelas() );
		return this.getValor().divide(numeroParcelas, MathContext.DECIMAL128);
	}
	
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	

//	@Override
//	public String toString() {
//		return "Pagamento [id=" + id + ", matricula=" + matricula
//				+ ", formaPagamento=" + formaPagamento + ", data=" + data
//				+ ", valor=" + valor + ", numeroParcelas=" + numeroParcelas
//				+ ", parcelaList=" + parcelaList + "]";
//	}
	
	

}

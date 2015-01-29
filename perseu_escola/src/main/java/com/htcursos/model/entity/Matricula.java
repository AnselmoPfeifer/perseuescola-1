package com.htcursos.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.htcursos.model.enums.TipoContratacaoEnum;
import com.htcursos.model.util.FormataUtil;
import com.htcursos.model.util.Um;

@XmlRootElement
@Entity
public class Matricula implements Serializable, Modelo<Integer> {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	@Id
	@SequenceGenerator(name = "seq_matricula", sequenceName = "seq_matricula", initialValue = 1, allocationSize = 10)
	@GeneratedValue(generator = "seq_matricula", strategy = GenerationType.AUTO)
	private Integer id;

	@JoinColumn
	@ManyToOne
	private Unidade unidade;
	@Temporal(TemporalType.DATE)
	/**
	 * // Data do Contrato que pode ser mudada
	 */
	private Date dataContrato = new Date();
	/**
	 * Data do Cadastro �� autom��tico
	 */
	private Date dataCadastro = new Date();

	/**
	 * Data Prevista do curso
	 */
	private Date dataPrevista = new Date();

	private String motivoCancelamento;
	private Integer status;
	@Temporal(TemporalType.DATE)
	private Date dataCancelamento;
	@JoinColumn
	@ManyToOne
	private Usuario usuario;
	private String observacao;

	// Contrato
	private Double descontoAteVencimento = 10.0;
	// Contrato
	private Double multa = 2.0;
	// Contrato
	private Double taxaAtrasoPorDia = 0.50;

	// Taxa de Inscricao
	private Double taxaInscricao;

	public Double getMulta() {
		return multa;
	}

	public void setMulta(Double multa) {
		this.multa = multa;
	}

	public Double getTaxaAtrasoPorDia() {

		return taxaAtrasoPorDia;
	}

	public String getTaxaAtrasoPorDiaFtm() {

		String valorFtm = getTaxaAtrasoPorDia() + "(" + Um.valorPorExtenso(taxaAtrasoPorDia) + ")";

		return valorFtm;
	}

	public void setTaxaAtrasoPorDia(Double taxaAtrasoPorDia) {
		this.taxaAtrasoPorDia = taxaAtrasoPorDia;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "matricula", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Pagamento> pagamentoList = new ArrayList<Pagamento>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "matricula", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<ClienteMatricula> clienteMatriculaList = new ArrayList<ClienteMatricula>();
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "matricula", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<CursoMatricula> cursoMatriculaList = new ArrayList<CursoMatricula>();
	/** nao vai existir no banco, apenas para ser singleton no getContratante */
	@Transient
	private Cliente contratante;

	// gets e setts

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Date getDataContrato() {
		return dataContrato;
	}

	public String getDataFmt() {
		return FormataUtil.formataDataBrasil(dataContrato);
	}

	public void setDatContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}

	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getDatacancelamento() {
		return dataCancelamento;
	}

	public String getDatacancelamentoFmt() {
		return FormataUtil.formataDataBrasil(dataCancelamento);
	}

	public void setDatacancelamento(Date datacancelamento) {
		this.dataCancelamento = datacancelamento;
	}

	public List<Pagamento> getPagamentoList() {
		return pagamentoList;
	}

	public void setPagamentoList(List<Pagamento> pagamentoList) {
		this.pagamentoList = pagamentoList;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Matricula other = (Matricula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	//
	// @Override
	// public String toString() {
	// return "Matricula [id=" + id + ", valor=" + valor + ", unidade="
	// + unidade + ", data=" + data + ", motivocancelamento="
	// + motivocancelamento + ", status=" + status
	// + ", datacancelamento=" + datacancelamento + ", usuario="
	// + usuario + ", pagamentoList=" + pagamentoList + "]";
	// }
	/**
	 * Gera o valor do campo valor baseano na soma dos pagamentos parcelado
	 * 
	 * @return
	 */
	public BigDecimal getValorTotal() {

		BigDecimal valor = new BigDecimal(0);
		// somando o valor de todas os pagamentos
		for (Pagamento p : pagamentoList) {
			valor = valor.add(p.getValor());
		}

		return valor;

	}

	public String getValorTotalFmtExtenso() {
		String valorFtm = getValorTotalFmtSemExtenso() + "(" + Um.valorPorExtenso(getValorTotal().doubleValue()) + ")";
		return valorFtm;
	}

	public String getValorTotalFmtSemExtenso() {

		return FormataUtil.formataMoedaBrasil(getValorTotal());
	}

	public List<ClienteMatricula> getClienteMatriculaList() {
		return clienteMatriculaList;
	}

	public String getClientes() {
		StringBuffer clientes = new StringBuffer();
		for (ClienteMatricula cm : this.getClienteMatriculaList()) {
			clientes.append(cm.getCliente().getNome() + ", ");
		}
		return clientes.toString();
	}

	public void setClienteMatriculaList(List<ClienteMatricula> clienteMatriculaList) {
		this.clienteMatriculaList = clienteMatriculaList;
	}

	public List<CursoMatricula> getCursoMatriculaList() {
		return cursoMatriculaList;
	}

	public void setCursoMatriculaList(List<CursoMatricula> cursoMatriculaList) {
		this.cursoMatriculaList = cursoMatriculaList;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public BigDecimal getTotalCursos() {
		BigDecimal totalCursos = new BigDecimal(0);

		for (CursoMatricula cm : this.getCursoMatriculaList()) {
			totalCursos = totalCursos.add(cm.getCurso().getValor());
		}

		return totalCursos;
	}

	public BigDecimal getTotalPagamentos() {
		BigDecimal totalPagamentos = new BigDecimal(0);
		for (Pagamento pg : this.getPagamentoList()) {
			totalPagamentos = totalPagamentos.add(pg.getValor());
		}
		return totalPagamentos;
	}

	public BigDecimal calcularDiferenca() {
		BigDecimal totalCursos = getTotalCursos();
		BigDecimal totalPagamento = getTotalPagamentos();
		return totalCursos.subtract(totalPagamento);
	}

	public int getQuantidadeContratantes() {
		int qtd = 0;
		for (ClienteMatricula cm : this.getClienteMatriculaList()) {
			if (cm.getTipoContratacao() == TipoContratacaoEnum.CONTRATANTE
					|| cm.getTipoContratacao() == TipoContratacaoEnum.CONTRATANTECONSUMIDOR) {
				qtd++;
			}
		}
		return qtd;
	}

	public Cliente getContratante() {
		//Procura a primeira vez
		if (this.contratante==null){
			for (ClienteMatricula cm : this.getClienteMatriculaList()) {
				if (cm.getTipoContratacao() == TipoContratacaoEnum.CONTRATANTE
						|| cm.getTipoContratacao() == TipoContratacaoEnum.CONTRATANTECONSUMIDOR) {
					//seta quando encontrar
					return contratante = cm.getCliente() ;
				}
			}
		}else{
			
			return this.contratante;
		}
		return null;
	}

	public String getCursoMatriculaListStr() {
		StringBuffer strCursos = new StringBuffer();
		CursoMatricula c;
		int qtd = cursoMatriculaList.size();
		for (int i = 0; i < qtd; i++) {
			c = cursoMatriculaList.get(i);
			strCursos.append(c.getCurso().getTitulo().toUpperCase());
			if (i < qtd - 1) {
				strCursos.append(",");
			}
		}
		return strCursos.toString();
	}

	/**
	 * Forma de Pagamento com valor e data para contrato
	 * 
	 * @return String concatenada com informacoes dos pagamentos
	 */
	public String getPagamentoListStr() {

		StringBuffer strPagamentos = new StringBuffer();

		Pagamento p;
		int qtd = pagamentoList.size();
		for (int i = 0; i < qtd; i++) {
			p = pagamentoList.get(i);

			strPagamentos.append(FormataUtil.formataMoedaBrasil(p.getValor()) + "("
					+ Um.valorPorExtenso(p.getValor().doubleValue()) + ")" + " parcelado em " + p.getNumeroParcelas()
					+ " parcela(s)  de " + FormataUtil.formataMoedaBrasil(p.getValorParcela()) + "("
					+ Um.valorPorExtenso(p.getValorParcela().doubleValue()) + ")" + " na forma de pagamento "
					+ p.getFormaPagamento().getDescricao().toUpperCase() + " com vencimento a partir de "
					+ FormataUtil.formataDataBrasil(p.getDataVencimento()));

			// for (Parcela parcela : p.getParcelaList()){
			// strPagamentos.append(parcela.getFormapagamento().getDescricao().toUpperCase()
			// +" " + parcela.getDatavencimentoFormatada() +" "
			// +parcela.getValor() );
			// }

			if (i < qtd - 1) {
				strPagamentos.append(",");
			}
		}
		return strPagamentos.toString();
	}

	/**
	 * Consumidores para o contrato sepatados por virgula
	 * 
	 * @return String concatenada de consumidores
	 */
	public String getConsumidores() {
		StringBuffer strConsumidores = new StringBuffer();
		ClienteMatricula cm;
		int qtd = this.getClienteMatriculaList().size();
		for (int i = 0; i < qtd; i++) {
			cm = getClienteMatriculaList().get(i);
			if (cm.getTipoContratacao() == TipoContratacaoEnum.CONSUMIDOR) {
				strConsumidores.append(cm.getCliente().getNome());
				if (i < qtd - 1) {
					strConsumidores.append(",");
				}
			}
		}
		return null;
	}

	public boolean getCobrancaPropria() {

		for (Pagamento p : pagamentoList) {
			if (p.getFormaPagamento().getCobrancaPropria() == true) {
				return true;
			}
		}
		return false;
	}

	public boolean getImprimirRecibo() {

		return !getCobrancaPropria();
	}

	public Double getDescontoAteVencimento() {
		return descontoAteVencimento;
	}

	public void setDescontoAteVencimento(Double descontoAteVencimento) {
		this.descontoAteVencimento = descontoAteVencimento;
	}

	public boolean validaPagamentos() {
		for (Pagamento p : this.getPagamentoList()) {
			if (p.getFormaPagamento() == null || p.getDataVencimento() == null || p.getValor() == null
					|| p.getNumeroParcelas() == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Somando a carga hor��rio de todos os cursos da matricula
	 * 
	 * @return
	 */
	public Integer cargaHorariaTotal() {
		int total = 0;
		for (CursoMatricula cm : this.cursoMatriculaList) {
			Integer horaCurso = cm.getCurso().getCargahoraria();
			total += horaCurso;
		}
		return total;
	}

	public Double getTaxaInscricao() {
		return taxaInscricao;
	}

	public void setTaxaInscricao(Double taxaInscricao) {
		this.taxaInscricao = taxaInscricao;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public String getDataPrevistaFmt() {
		return FormataUtil.formataDataBrasil(dataPrevista);
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public String getTaxaInscricaoFmt() {
		StringBuffer str = new StringBuffer();
		str.append("");
		if (taxaInscricao == null || taxaInscricao == 0.0)
			return "";
		try {

			str.append("Taxa de matricula no valor de ");
			str.append(FormataUtil.formataMoedaBrasil(new BigDecimal(taxaInscricao)) + "("
					+ Um.valorPorExtenso(taxaInscricao) + ") com pagamento em " + getDataFmt() + " .");
			return str.toString();
		} catch (Exception e) {

			e.printStackTrace();
			return "";
		}
	}

}

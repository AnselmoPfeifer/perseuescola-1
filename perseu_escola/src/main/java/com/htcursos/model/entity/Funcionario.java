package com.htcursos.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.htcursos.model.enums.TipoSexoEnum;

@Entity
public class Funcionario implements Modelo<Integer> {
	
	@Id
	@SequenceGenerator(name = "seq_funcionario", sequenceName = "seq_funcionario", initialValue = 1)
	@GeneratedValue(generator = "seq_funcionario", strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotEmpty(message="Campo Nome obrigatório!")
	private String nome;
	
	@NotEmpty(message="Campo Nome Pai obrigatório!")
	private String nomePai;

	@NotEmpty(message="Campo Nome Mãe obrigatório!")
	private String nomeMae;
	
	@JoinColumn
	@ManyToOne
	private Cidade cidadeNascimento;
	
	@NotEmpty(message="Campo Número Carteira de Trabalho obrigatório!")
	private String numeroCarteiraTrabalho;

	private String serieCarteiraTrabalho;
	@NotEmpty(message="Campo CPF obrigatório!")
	private String cpf;
	
	@NotEmpty(message="Campo Número Título Eleitor obrigatório!")
	private String numeroTituloEleitor;
	
	@NotEmpty(message="Campo Zona obrigatório!")
	private String zonaTituloEleitor;
	
	@NotEmpty(message="Campo Seção obrigatório!")
	private String secaoTituloEleitor;
	
	@NotEmpty(message="Campo UF obrigatório!")
	private String ufTituloEleitor;
	
	@NotEmpty(message="Campo RG obrigatório!")
	private String numeroRG;
	
	@NotEmpty(message="Campo Órgão Emissor obrigatório!")
	private String  OrgaoEmissorRG;
	
	private Date  dataRG;
	
	private String  numeroCertificadoReservista;
	
	private String  serieCertificadoReservista;
	
	private String  categoriaCertificadoReservista;
	
	private String numeroCNH;
	
	private Date dataVencimentoCNH;
	
	private String categoriaCNH;
	
	private String numeroPis;

	private GrauInstrucaoEnum grauInstrucao;
	
	private String nomeConjugue;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "funcionario", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	private List<Filho> filhos;
	
	private String cargo;
	
	private String funcao;
	
	private Boolean residenciaPropria;
	
	private Boolean imovelAdquiridoFGTS;
	
	private Date dataAdmimissao;
	
	private Date dataExameMedicoAdmissao;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoSexoEnum sexo;
	
	//Código Brasileiro de Ocupação
	private Integer cbo;
	
	private Boolean admissaoPorContratoExperiencia;
	@Enumerated(EnumType.ORDINAL)
	private DiasExperienciaEnum diasExperiencia;
	
	@Embedded
	private Endereco endereco = new Endereco();
	
	@Email (message="E-mail inválido!")
	private String email;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "matricula", fetch = FetchType.EAGER)
	@org.hibernate.annotations.Fetch(FetchMode.SUBSELECT)
	private List<Anexo> anexos = new ArrayList<>();
	
	@NotNull (message= "Selecione a unidade!")
	@JoinColumn
	@ManyToOne
	private Unidade unidade;
	
	public void adicionarAnexo(Anexo anexo) {
		if (this.getAnexos() == null) {
			setAnexos(new ArrayList<Anexo>());
		}
		this.getAnexos().add(anexo);

	}
	
	
	//Getters ans Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public Cidade getCidadeNascimento() {
		return cidadeNascimento;
	}

	public void setCidadeNascimento(Cidade cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}

	public String getNumeroCarteiraTrabalho() {
		return numeroCarteiraTrabalho;
	}

	public void setNumeroCarteiraTrabalho(String numeroCarteiraTrabalho) {
		this.numeroCarteiraTrabalho = numeroCarteiraTrabalho;
	}

	public String getSerieCarteiraTrabalho() {
		return serieCarteiraTrabalho;
	}

	public void setSerieCarteiraTrabalho(String serieCarteiraTrabalho) {
		this.serieCarteiraTrabalho = serieCarteiraTrabalho;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumeroTituloEleitor() {
		return numeroTituloEleitor;
	}

	public void setNumeroTituloEleitor(String numeroTituloEleitor) {
		this.numeroTituloEleitor = numeroTituloEleitor;
	}

	public String getZonaTituloEleitor() {
		return zonaTituloEleitor;
	}

	public void setZonaTituloEleitor(String zonaTituloEleitor) {
		this.zonaTituloEleitor = zonaTituloEleitor;
	}

	public String getSecaoTituloEleitor() {
		return secaoTituloEleitor;
	}

	public void setSecaoTituloEleitor(String secaoTituloEleitor) {
		this.secaoTituloEleitor = secaoTituloEleitor;
	}

	public String getUfTituloEleitor() {
		return ufTituloEleitor;
	}

	public void setUfTituloEleitor(String ufTituloEleitor) {
		this.ufTituloEleitor = ufTituloEleitor;
	}

	public String getNumeroRG() {
		return numeroRG;
	}

	public void setNumeroRG(String numeroRG) {
		this.numeroRG = numeroRG;
	}

	public String getOrgaoEmissorRG() {
		return OrgaoEmissorRG;
	}

	public void setOrgaoEmissorRG(String orgaoEmissorRG) {
		OrgaoEmissorRG = orgaoEmissorRG;
	}

	public Date getDataRG() {
		return dataRG;
	}

	public void setDataRG(Date dataRG) {
		this.dataRG = dataRG;
	}

	public String getNumeroCertificadoReservista() {
		return numeroCertificadoReservista;
	}

	public void setNumeroCertificadoReservista(String numeroCertificadoReservista) {
		this.numeroCertificadoReservista = numeroCertificadoReservista;
	}

	public String getSerieCertificadoReservista() {
		return serieCertificadoReservista;
	}

	public void setSerieCertificadoReservista(String serieCertificadoReservista) {
		this.serieCertificadoReservista = serieCertificadoReservista;
	}

	public String getCategoriaCertificadoReservista() {
		return categoriaCertificadoReservista;
	}

	public void setCategoriaCertificadoReservista(
			String categoriaCertificadoReservista) {
		this.categoriaCertificadoReservista = categoriaCertificadoReservista;
	}

	public String getNumeroCNH() {
		return numeroCNH;
	}

	public void setNumeroCNH(String numeroCNH) {
		this.numeroCNH = numeroCNH;
	}

	public Date getDataVencimentoCNH() {
		return dataVencimentoCNH;
	}

	public void setDataVencimentoCNH(Date dataVencimentoCNH) {
		this.dataVencimentoCNH = dataVencimentoCNH;
	}

	public String getCategoriaCNH() {
		return categoriaCNH;
	}

	public void setCategoriaCNH(String categoriaCNH) {
		this.categoriaCNH = categoriaCNH;
	}

	public String getNumeroPis() {
		return numeroPis;
	}

	public void setNumeroPis(String numeroPis) {
		this.numeroPis = numeroPis;
	}

	public GrauInstrucaoEnum getGrauInstrucao() {
		return grauInstrucao;
	}

	public void setGrauInstrucao(GrauInstrucaoEnum grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}

	public String getNomeConjugue() {
		return nomeConjugue;
	}

	public void setNomeConjugue(String nomeConjugue) {
		this.nomeConjugue = nomeConjugue;
	}

	public List<Filho> getFilhos() {
		return filhos;
	}

	public void setFilhos(List<Filho> filhos) {
		this.filhos = filhos;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Boolean getResidenciaPropria() {
		return residenciaPropria;
	}

	public void setResidenciaPropria(Boolean residenciaPropria) {
		this.residenciaPropria = residenciaPropria;
	}

	public Boolean getImovelAdquiridoFGTS() {
		return imovelAdquiridoFGTS;
	}

	public void setImovelAdquiridoFGTS(Boolean imovelAdquiridoFGTS) {
		this.imovelAdquiridoFGTS = imovelAdquiridoFGTS;
	}

	public Date getDataAdmimissao() {
		return dataAdmimissao;
	}

	public void setDataAdmimissao(Date dataAdmimissao) {
		this.dataAdmimissao = dataAdmimissao;
	}

	public Date getDataExameMedicoAdmissao() {
		return dataExameMedicoAdmissao;
	}

	public void setDataExameMedicoAdmissao(Date dataExameMedicoAdmissao) {
		this.dataExameMedicoAdmissao = dataExameMedicoAdmissao;
	}

	public Integer getCbo() {
		return cbo;
	}

	public void setCbo(Integer cbo) {
		this.cbo = cbo;
	}

	public Boolean getAdmissaoPorContratoExperiencia() {
		return admissaoPorContratoExperiencia;
	}

	public void setAdmissaoPorContratoExperiencia(
			Boolean admissaoPorContratoExperiencia) {
		this.admissaoPorContratoExperiencia = admissaoPorContratoExperiencia;
	}

	public DiasExperienciaEnum getDiasExperiencia() {
		return diasExperiencia;
	}

	public void setDiasExperiencia(DiasExperienciaEnum diasExperiencia) {
		this.diasExperiencia = diasExperiencia;
	}

	public List<Anexo> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<Anexo> anexos) {
		this.anexos = anexos;
	}

	
}
package com.htcursos.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.htcursos.model.enums.TipoAtivoEnum;
import com.htcursos.model.enums.TipoClienteEnum;
import com.htcursos.model.enums.TipoSexoEnum;
import com.htcursos.model.util.FormataUtil;

/**
 * XmlRootElement Objeto Será Cadastrado pelo Web-Service
 * 
 * @author virmerson
 *
 */
@XmlRootElement
@Entity
public class Cliente implements Serializable, Modelo<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente", initialValue = 1)
	@GeneratedValue(generator = "seq_cliente", strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull (message= "Selecione a unidade!")
	@JoinColumn
	@ManyToOne
	private Unidade unidade;
	@Enumerated(EnumType.STRING)
	private TipoClienteEnum tipo;
	private String pfRg;
	private String pfCpf;
	private Date pfDatanascimento;
	@Enumerated(EnumType.ORDINAL)
	private TipoSexoEnum sexo;
	private String pjCnpj;
	private String pjRazaoSocial;
	private String unicoId;
	@NotNull (message="Campo Nome obrigatório!")
	@NotEmpty(message="Campo Nome obrigatório!")
	private String nome;
	private String origem;
	private String unicoIdPai;
	private String senha;
	private String salt;
	@Temporal(TemporalType.DATE)
	private Date criadoEm;
	@Temporal(TemporalType.DATE)
	private Date alteradoEm;
	@Enumerated(EnumType.STRING)
	private TipoAtivoEnum ativo;
	private String permissao;
	@NotNull (message= "Campo E-mail obrigatório!")
	@Email (message="E-mail inválido!")
	private String email;
	private String camiseta;

	@Embedded
	private Endereco endereco = new Endereco();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "cliente", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Contato> contatoList;

	public List<Contato> getContatoList() {
		return contatoList;
	}

	public void setContatoList(List<Contato> contatoList) {
		this.contatoList = contatoList;
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

	public String getPfRg() {
		return pfRg;
	}

	public void setPfRg(String pfRg) {
		this.pfRg = pfRg;
	}

	public String getPfCpf() {
		return pfCpf;
	}

	public void setPfCpf(String pfCpf) {
		this.pfCpf = pfCpf;
	}

	public Date getPfDatanascimento() {
		return pfDatanascimento;
	}

	public String getPfDatanascimentoFmt() {
		return FormataUtil.formataDataBrasil(pfDatanascimento);
	}

	public void setPfDatanascimento(Date pfDatanascimento) {
		this.pfDatanascimento = pfDatanascimento;
	}

	public String getPjCnpj() {
		return pjCnpj;
	}

	public void setPjCnpj(String pjCnpj) {
		this.pjCnpj = pjCnpj;
	}

	public String getPjRazaoSocial() {
		return pjRazaoSocial;
	}

	public void setPjRazaoSocial(String pjRazaoSocial) {
		this.pjRazaoSocial = pjRazaoSocial;
	}

	public String getUnicoId() {
		return unicoId;
	}

	public void setUnicoId(String unicoId) {
		this.unicoId = unicoId;
	}

	public String getNome() {
		return nome;
	}

	public String getNomeSemEspacos() {
		return nome.replace(" ", "_");
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getUnicoIdPai() {
		return unicoIdPai;
	}

	public void setUnicoIdPai(String unicoIdPai) {
		this.unicoIdPai = unicoIdPai;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Date getAlteradoEm() {
		return alteradoEm;
	}

	public void setAlteradoEm(Date alteradoEm) {
		this.alteradoEm = alteradoEm;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public TipoClienteEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoClienteEnum tipo) {
		this.tipo = tipo;
	}

	public TipoAtivoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(TipoAtivoEnum ativo) {
		this.ativo = ativo;
	}

	public TipoSexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(TipoSexoEnum sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String getCPFCNPJStr() {

		if (this.getPfCpf() != null) {
			return this.getPfCpf();
		} else {
			return this.getPjCnpj();
		}
	}

	public boolean possuiDadosCompletos() {
		// Colocar Localidade
		if (nome != null && pfCpf != null) {
			// if ((pfCpf != null && pfRg != null && pfDatanascimento != null)
			// || (pjCnpj != null && pjRazaoSocial != null)) {
			// return true;
			// }
			return true;

		}
		return false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		if (endereco == null)
			endereco = new Endereco();
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCamiseta() {
		return camiseta;
	}

	public void setCamiseta(String camiseta) {
		this.camiseta = camiseta;
	}

}

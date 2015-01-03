package com.htcursos.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.htcursos.model.util.FormataUtil;

@Entity
public class Curso implements Serializable, Modelo<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "seq_curso", sequenceName = "seq_curso", initialValue = 1, allocationSize = 10)
	@GeneratedValue(generator = "seq_curso", strategy = GenerationType.AUTO)
	private Integer id;
	private String titulo;
	private String texto;
	private Integer ordem;
	private String conteudo;
	private String icone;
	private String sigla;
	private String objetivo;
	private String publicoalvo;
	private String prerequisito;
	private Integer cargahoraria=30;
	private String resumo;
	private Integer disponivel;
	private Boolean programacao;
	private String videoUrl;

	private Integer minicurso;
	private Integer free;
	private BigDecimal valor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getPublicoalvo() {
		return publicoalvo;
	}

	public void setPublicoalvo(String publicoalvo) {
		this.publicoalvo = publicoalvo;
	}

	public String getPrerequisito() {
		return prerequisito;
	}

	public void setPrerequisito(String prerequisito) {
		this.prerequisito = prerequisito;
	}

	public Integer getCargahoraria() {
		//Setando um padrao quando nao foi definida no banco
		if(cargahoraria==null){
			cargahoraria=30;
		}
		return cargahoraria;
	}

	public void setCargahoraria(Integer cargahoraria) {
		this.cargahoraria = cargahoraria;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public Integer getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Integer disponivel) {
		this.disponivel = disponivel;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getMinicurso() {
		return minicurso;
	}

	public void setMinicurso(Integer minicurso) {
		this.minicurso = minicurso;
	}

	public Integer getFree() {
		return free;
	}

	public void setFree(Integer free) {
		this.free = free;
	}

	public Boolean getProgramacao() {
		return programacao;
	}

	public void setProgramacao(Boolean programacao) {
		this.programacao = programacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getValorFmt() {

		return FormataUtil.formataMoedaBrasil(valor);
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", titulo=" + titulo + ", texto=" + texto
				+ ", ordem=" + ordem + ", conteudo=" + conteudo + ", icone="
				+ icone + ", sigla=" + sigla + ", objetivo=" + objetivo
				+ ", publicoalvo=" + publicoalvo + ", prerequisito="
				+ prerequisito + ", cargahoraria=" + cargahoraria + ", resumo="
				+ resumo + ", disponivel=" + disponivel + ", programacao="
				+ programacao + ", videoUrl=" + videoUrl + ", minicurso="
				+ minicurso + ", free=" + free + ", valor=" + valor + "]";
	}

}

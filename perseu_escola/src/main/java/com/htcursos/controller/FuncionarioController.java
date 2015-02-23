
package com.htcursos.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.controller.util.faces.JsfMessages;
import com.htcursos.model.entity.DiasExperienciaEnum;
import com.htcursos.model.entity.Funcionario;
import com.htcursos.model.entity.EnumCamisetas;
import com.htcursos.model.entity.GrauInstrucaoEnum;
import com.htcursos.model.enums.TipoSexoEnum;
import com.htcursos.model.service.FuncionarioService;
import com.htcursos.model.service.ServiceException;
import com.htcursos.model.tools.CepWebService;

@Controller("funcionarioController")
@ManagedBean(name="funcionarioController")
public class FuncionarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private FuncionarioService FuncionarioService;

	@Autowired
	private CepWebService cepWebService;

	public void encontraCEP() {

		getCepWebService().cepWebBuscar(Funcionario.getEndereco());
		if (getCepWebService().getResultado() == 1) {
			// Setando o endereco buscado para o Funcionario
			Funcionario.setEndereco(getCepWebService().getEndereco());
		} else {
			JsfMessages.adicionaMensagemInfo("WebService nao esta respondendo");
		}
	}

	private Funcionario Funcionario = new Funcionario();

	// dados da tela
	private Funcionario FuncionarioExcluir;

	private List<Funcionario> FuncionarioList;

	private Funcionario FuncionarioSalvo;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public List<Funcionario> obterFuncionarios(String nomeBusca) {

		return FuncionarioService.buscarTodos(nomeBusca);
	}

	// //Formata a data que vem da tela
	// String dataFormatada;
	//
	// public String getDataFormatada() {
	// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	// if(Funcionario.getPfDatanascimento() != null)
	// return sdf.format(Funcionario.getPfDatanascimento());
	// return "";
	// }
	//
	// public void setDataFormatada(String dataFormatada) {
	// this.dataFormatada = dataFormatada;
	// }

	public void salvar() {
		try {
			FuncionarioService.salvar(Funcionario);
			setFuncionarioSalvo(Funcionario);
			atualiza();
			Funcionario = new Funcionario();
			JsfMessages.adicionaMensagemInfo("Funcionario salvo");
		} catch (ServiceException e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void excluir() {
		// remove do banco
		getFuncionarioService().excluir(FuncionarioExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		FuncionarioList.remove(FuncionarioExcluir);
		FuncionarioExcluir = null;
		JsfMessages.adicionaMensagemInfo("Funcionario removido");
	}

	// Enums
	public TipoSexoEnum[] getTipoSexoValues() {
		return TipoSexoEnum.values();
	}
	
	public DiasExperienciaEnum[] getDiasExperienciaValues() {
		return DiasExperienciaEnum.values();
	}
	
	public GrauInstrucaoEnum[] getGrauInstrucaoValues() {
		return GrauInstrucaoEnum.values();
	}

	public EnumCamisetas[] getCamisetas() {
		return EnumCamisetas.values();
	}
	
	public Date testeData(){
		return new Date();
		
	}

	public void cancelar() {
		Funcionario = new Funcionario();
	}

	public void editar(Funcionario Funcionario) {
		this.Funcionario = Funcionario;
	}

	public List<Funcionario> getFuncionarioList() {
		return getFuncionarioService().buscarTodos();
	}

	public void atualiza() {
		FuncionarioList = getFuncionarioService().buscarTodos();
	}

	// getter and setters
	public FuncionarioService getFuncionarioService() {
		return FuncionarioService;
	}

	public void setFuncionarioService(FuncionarioService FuncionarioService) {
		this.FuncionarioService = FuncionarioService;
	}

	public Funcionario getFuncionario() {
		return Funcionario;
	}

	public void setFuncionario(Funcionario Funcionario) {
		this.Funcionario = Funcionario;
	}

	public Funcionario getFuncionarioExcluir() {
		return FuncionarioExcluir;
	}

	public void setFuncionarioExcluir(Funcionario FuncionarioExcluir) {
		this.FuncionarioExcluir = FuncionarioExcluir;
	}

	public void setFuncionarioList(List<Funcionario> FuncionarioList) {
		this.FuncionarioList = FuncionarioList;
	}

	public Funcionario getFuncionarioSalvo() {
		return FuncionarioSalvo;
	}

	public void setFuncionarioSalvo(Funcionario FuncionarioSalvo) {
		this.FuncionarioSalvo = FuncionarioSalvo;
	}

	public CepWebService getCepWebService() {
		return cepWebService;
	}

	public void setCepWebService(CepWebService cepWebService) {
		this.cepWebService = cepWebService;
	}

}


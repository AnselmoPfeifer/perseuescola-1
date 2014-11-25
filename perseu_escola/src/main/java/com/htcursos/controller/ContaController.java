package com.htcursos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.controller.util.faces.JsfMessages;
import com.htcursos.model.entity.Conta;
import com.htcursos.model.service.ContaService;
import com.htcursos.model.service.ServiceExpcetion;

@Controller("contaController")
@Scope("view")
@ManagedBean
public class ContaController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContaService contaService;
	private Conta conta = new Conta();
	
	//dados da tela
	private Conta contaExcluir;

	private List<Conta> contaList;

	@PostConstruct
	protected void init() {
		atualiza();
	}

	public void salvar() {
		try {
			contaService.salvar(conta);
			atualiza();
			conta = new Conta();
			JsfMessages.adicionaMensagemInfo("Conta salvo");
		} catch (ServiceExpcetion e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}
		
	}
	
	public void excluir() {
		// remove do banco
		getContaService().excluir(contaExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		contaList.remove(contaExcluir);
		contaExcluir = null;
		JsfMessages.adicionaMensagemInfo("Conta removido");
	}

	public void cancelar() {
		conta = new Conta();
	}

	public void editar(Conta conta) {
		this.conta = conta;
	}

	public List<Conta> getContaList() {
		return getContaService().buscarTodos();
	}
	
	public void atualiza() {
		contaList = getContaService().buscarTodos();
	}

	private TreeNode raiz;
	private List<TreeNode> contaTree;
	  
    public List<Conta> contaArvore(List<TreeNode> contaTree, TreeNode posicao){  
        raiz = new DefaultTreeNode(conta, null);  
        TreeNode node0 = new DefaultTreeNode(conta, raiz);  
//        TreeNode node1 = new DefaultTreeNode("Node 1", root);  
//        TreeNode node2 = new DefaultTreeNode("Node 2", root);  
//          
        TreeNode node00 = new DefaultTreeNode("2", node0);  
//        TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);  
//          
//        TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);  
//        TreeNode node11 = new DefaultTreeNode("Node 1.1", node1);  
//          
        TreeNode node000 = new DefaultTreeNode("3", node00);  
//        TreeNode node001 = new DefaultTreeNode("Node 0.0.1", node00);  
//        TreeNode node010 = new DefaultTreeNode("Node 0.1.0", node01);  
//          
//        TreeNode node100 = new DefaultTreeNode("Node 1.0.0", node10);  
    	return contaArvore(contaTree, posicao);
    }  
  
    public TreeNode getRoot() {  
        return raiz;  
    }  
			
	//getter and setters
	public ContaService getContaService() {
		return contaService;
	}

	public void setContaService(ContaService contaService) {
		this.contaService = contaService;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Conta getContaExcluir() {
		return contaExcluir;
	}

	public void setContaExcluir(Conta contaExcluir) {
		this.contaExcluir = contaExcluir;
	}

	public void setContaList(List<Conta> contaList) {
		this.contaList = contaList;
	}
}

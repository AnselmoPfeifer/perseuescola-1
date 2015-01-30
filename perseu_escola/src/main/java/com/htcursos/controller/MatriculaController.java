package com.htcursos.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.NoResultException;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.controller.util.faces.JsfMessages;
import com.htcursos.model.entity.Cliente;
import com.htcursos.model.entity.ClienteMatricula;
import com.htcursos.model.entity.Curso;
import com.htcursos.model.entity.CursoMatricula;
import com.htcursos.model.entity.Matricula;
import com.htcursos.model.entity.Pagamento;
import com.htcursos.model.entity.Parcela;
import com.htcursos.model.entity.Unidade;
import com.htcursos.model.entity.Usuario;
import com.htcursos.model.enums.TipoContratacaoEnum;
import com.htcursos.model.service.ClienteMatriculaService;
import com.htcursos.model.service.ClienteService;
import com.htcursos.model.service.CursoMatriculaService;
import com.htcursos.model.service.CursoService;
import com.htcursos.model.service.MatriculaService;
import com.htcursos.model.service.PagamentoService;
import com.htcursos.model.service.ParcelaService;
import com.htcursos.model.service.ServiceException;
import com.htcursos.model.service.UnidadeService;
import com.htcursos.model.service.UsuarioService;
import com.htcursos.model.util.FormataUtil;
import com.htcursos.model.util.SessaoUtil;

@Controller("matriculaController")
@Scope("view")
@ManagedBean
public class MatriculaController implements Serializable {

	private static final long serialVersionUID = 1L;

	// objetos pra acesso ao DAO instanciados automaticamente pelo hibernate
	@Autowired
	private MatriculaService matriculaService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private CursoService cursoService;
	@Autowired
	private ClienteMatriculaService clienteMatriculaService;
	@Autowired
	private CursoMatriculaService cursoMatriculaService;
	@Autowired
	private PagamentoService pagamentoService;
	@Autowired
	private ParcelaService parcelaService;
	@Autowired
	private UnidadeService unidadeService;
	@Autowired
	private UsuarioService usuarioService;
	// Objetos de manipulacao de dados
	private Matricula matricula = new Matricula();
	private Matricula matriculaContrato = new Matricula();
	// OBJETO UTILIZADO SOMENTE PARA BUSCAS DE MATRICULAS
	private ClienteMatricula clienteMatriculaBusca = new ClienteMatricula(
			new Cliente(), new Matricula());
	private Matricula matriculaSetada = new Matricula();
	private Cliente cliente = new Cliente();
	private Cliente clienteBuscado = new Cliente();
	private Curso curso = new Curso();
	private CursoMatricula cursoMatricula = new CursoMatricula();
	private Curso cursoBuscado = new Curso();
	private ClienteMatricula clienteMatricula = new ClienteMatricula();
	private ClienteMatricula clienteExcluir = new ClienteMatricula();
	private Pagamento pagamento = new Pagamento();
	private Pagamento pagamentoNovo = new Pagamento();
	private Pagamento pagamentoSelecionado = new Pagamento();
	private Parcela parcela;
	private BigDecimal totalCursos = new BigDecimal(0);
	private BigDecimal totalPagamentos = new BigDecimal(0);
	private Pagamento pagamentoDetalhe;
	private Matricula matriculaExcluir;
	private CursoMatricula cursoExcluir = new CursoMatricula();
	private Pagamento pagamentoExcluir = new Pagamento();
	private Parcela parcelaExcluir = new Parcela();
	private BigDecimal diferenca = new BigDecimal(0);
	private BigDecimal valorParcela = new BigDecimal(0);
	private Unidade unidade = new Unidade();
	private Usuario usuario;
	// Lista de objetos
	private List<Matricula> matriculaList;
	private List<Curso> listaCursos;
	private List<Parcela> parcelaList;
	private List<ClienteMatricula> clienteMatriculaList = new ArrayList<ClienteMatricula>();

	@Autowired
	private SessaoUtil sessaoUtil;

	public MatriculaController() {

		System.out.println("instanciando matricula controller");
	}

	//
	// public List<Cliente> obterClientes(String nomeBusca) {
	// List<Cliente> clientes, sclientes = new ArrayList<Cliente>();
	// try {
	// clientes = clienteService.buscarTodos(nomeBusca);
	// if (nomeBusca.trim().equals(""))
	// return clientes;
	// for (Cliente cli : clientes)
	// if (cli.getNome().toString().contains(nomeBusca)
	// || cli.getNome().toLowerCase()
	// .contains(nomeBusca.toLowerCase())) {
	// sclientes.add(cli);
	// }
	// return sclientes;
	// } catch (Exception e) {
	// //
	// JsfUtil.showFacesMsg(e,"Erro ao buscar clientes ",FacesMessage.SEVERITY_WARN);
	// e.printStackTrace();
	//
	// return null;
	// }
	// }
	public void adicionarPagamento() {

		pagamentoNovo.setMatricula(matricula);
		matricula.getPagamentoList().add(pagamentoNovo);

		// // objeto usado pra mostrar suas parcelas quando selecionado
		// //pagamentoSelecionado = pagamentoNovo;
		pagamentoNovo = new Pagamento();
		totalizar();
		// pagamentoNovo.setValor(diferenca);

	}

	public void onRowSelect(SelectEvent event) {
		pagamentoSelecionado = (Pagamento) event.getObject();
	}

	public void adicionarCursos() {

		if (!contemCurso(cursoBuscado)) {
			cursoMatricula.setCurso(cursoBuscado);
			cursoMatricula.setMatricula(matricula);
			cursoMatricula.setValor(cursoBuscado.getValor());

			matricula.getCursoMatriculaList().add(cursoMatricula);
			totalizar();
			pagamento.setValor(diferenca);
			cursoMatricula = new CursoMatricula();
		}
	}

	private boolean contemCurso(Curso cursoBuscado) {
		for (CursoMatricula cm : matricula.getCursoMatriculaList()) {
			if (cm.getCurso().getTitulo().equals(cursoBuscado.getTitulo())) {
				return true;
			}
		}
		return false;
	}

	public void totalizar() {
		totalCursos = matricula.getTotalCursos();
		totalPagamentos = matricula.getTotalPagamentos();
		diferenca = matricula.calcularDiferenca();
	}

	public void buscarCliente() {
		try {
			clienteBuscado = clienteService.buscarPorCpf(clienteBuscado
					.getPfCpf());
		} catch (NoResultException e) {
			clienteBuscado = new Cliente();
			JsfMessages.adicionaMensagemInfo("Cliente nao Encontrado");
		}
	}

	public void adicionarCliente() {
		System.out.println("Adicionando cliente");

		if (!contem(clienteBuscado)) {
			clienteMatricula.setCliente(clienteBuscado);
			clienteMatricula.setMatricula(matricula);
			// clienteMatricula
			// .setTipoContratacao(TipoContratacaoEnum.CONTRATANTE);
			// clienteMatriculaList.add(clienteMatricula);
			matricula.getClienteMatriculaList().add(clienteMatricula);
			clienteBuscado = new Cliente();
			clienteMatricula = new ClienteMatricula();
		}
	}

	private boolean contem(Cliente clienteBuscado) {
		for (ClienteMatricula cm : matricula.getClienteMatriculaList()) {

			if (cm.getCliente().getId().equals(clienteBuscado.getId())) {
				return true;
			}

		}
		return false;
	}

	@PostConstruct
	protected void init() {
		matricula = new Matricula();

		// atualiza();
		atualizaCurso();
		// Setando o F��brica como padrao
		// curso = cursoService.buscarPorId(52);

		// atualizaPagamento();
		// atualizaClienteMatricula();

	}

	public void buscarMatricula() {
		
		System.out.println("Camando o método buscar matrícula");

		matriculaList = clienteMatriculaService
				.buscarMatriculas(clienteMatriculaBusca);

	}

	/**
	 * usado para <f:event type="preRenderView">
	 * 
	 * @param event
	 */
	public void preRender(ComponentSystemEvent event) {

	}

	/**
	 * usado em <h:inputXxx valueChangeListener>
	 * 
	 * @param event
	 */
	public void change(ValueChangeEvent event) {

	}

	/**
	 * Usado para <f:ajax listener>
	 * 
	 * @param event
	 */
	public void ajaxListener(AjaxBehaviorEvent event) {

	}

	/**
	 * <h:commandXxx actionListener>
	 * 
	 * @param event
	 */
	public void actionListener(ActionEvent event) {

	}

	public void atualizaCurso() {
		listaCursos = getCursoService().buscarTodos();
	}

	public void atualizaPagamento() {
		matricula.setPagamentoList(getPagamentoService().buscarTodos());
	}

	public void atualizaClienteMatricula() {
		matricula.setClienteMatriculaList(getClienteMatriculaService()
				.buscarTodosIDDescrencente());
	}

	public void salvar() {
		System.out.println(" Chamou salvar");
		// buscando clientes

		for (ClienteMatricula cm : matricula.getClienteMatriculaList()) {
			cm.setCliente(clienteService.buscarPorId(cm.getCliente().getId()));
		}

		try {
			matricula.setUsuario(sessaoUtil.getUsuarioLogadoPersistido());

			matriculaService.salvar(matricula);
			matricula = new Matricula();
			atualizaCurso();
			JsfMessages.adicionaMensagemInfo("Matricula salvo");
		} catch (ServiceException e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void excluir() {
		// remove do banco
		getMatriculaService().excluir(matriculaExcluir.getId());
		// removendo da ArrayLista para evitar uma nova consulta
		matriculaList.remove(matriculaExcluir);
		matriculaExcluir = null;
		JsfMessages.adicionaMensagemInfo("Matricula removido");
	}

	public void excluirCliente() {
		matricula.getClienteMatriculaList().remove(clienteExcluir);
		clienteExcluir = null;
	}

	public void excluirCurso() {
		matricula.getCursoMatriculaList().remove(cursoExcluir);
		cursoExcluir = null;
		totalizar();
	}

	public void excluirPagamento() {
		matricula.getPagamentoList().remove(pagamentoExcluir);
		pagamentoExcluir = null;

		totalizar();
	}

	public void excluirParcela() {
		pagamentoSelecionado.getParcelaList().remove(parcelaExcluir);
		parcelaExcluir = null;
		totalizar();
	}

	public void cancelar() {
		matricula = new Matricula();
		totalizar();
	}

	public void editar(Matricula matricula) {
		this.matricula = matricula;

		totalizar();

	}

	public List<Matricula> getmatriculaSet() {
		return getMatriculaService().buscarTodos();
	}

	public ClienteMatriculaService getClienteMatriculaService() {
		return clienteMatriculaService;
	}

	public void setClienteMatriculaService(
			ClienteMatriculaService clienteMatriculaService) {
		this.clienteMatriculaService = clienteMatriculaService;
	}

	public void atualiza() {
		matriculaList = getMatriculaService().buscarTodosIDDescrencente();
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public MatriculaService getMatriculaService() {
		return matriculaService;
	}

	public void setMatriculaService(MatriculaService matriculaService) {
		this.matriculaService = matriculaService;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Matricula getMatriculaExcluir() {
		return matriculaExcluir;
	}

	public void setMatriculaExcluir(Matricula matriculaExcluir) {
		this.matriculaExcluir = matriculaExcluir;
	}

	public void setmatriculaSet(List<Matricula> matriculaList) {
		this.matriculaList = matriculaList;
	}

	public CursoService getCursoService() {
		return cursoService;
	}

	public void setCursoService(CursoService cursoService) {
		this.cursoService = cursoService;
	}

	public CursoMatricula getCursoMatricula() {
		return cursoMatricula;
	}

	public void setCursoMatricula(CursoMatricula cursoMatricula) {
		this.cursoMatricula = cursoMatricula;
	}

	public Cliente getClienteBuscado() {
		return clienteBuscado;
	}

	public void setClienteBuscado(Cliente clienteBuscado) {
		this.clienteBuscado = clienteBuscado;
	}

	public ClienteMatricula getClienteMatricula() {
		return clienteMatricula;
	}

	public void setClienteMatricula(ClienteMatricula clienteMatricula) {
		this.clienteMatricula = clienteMatricula;
	}

	public TipoContratacaoEnum[] getTipoContratacaoValor() {
		return TipoContratacaoEnum.values();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public Curso getCursoBuscado() {
		if (cursoBuscado == null)
			cursoBuscado = new Curso();
		return cursoBuscado;
	}

	public void setCursoBuscado(Curso cursoBuscado) {
		this.cursoBuscado = cursoBuscado;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Matricula> getMatriculaList() {
		return matriculaList;
	}

	public void setMatriculaList(List<Matricula> matriculaList) {
		this.matriculaList = matriculaList;
	}

	public PagamentoService getPagamentoService() {
		return pagamentoService;
	}

	public void setPagamentoService(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}

	public Pagamento getPagamentoNovo() {
		return pagamentoNovo;
	}

	public void setPagamentoNovo(Pagamento pagamentoNovo) {
		this.pagamentoNovo = pagamentoNovo;
	}

	public ParcelaService getParcelaService() {
		return parcelaService;
	}

	public void setParcelaService(ParcelaService parcelaService) {
		this.parcelaService = parcelaService;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public List<Parcela> getParcelaList() {
		return parcelaList;
	}

	public void setParcelaList(List<Parcela> parcelaList) {
		this.parcelaList = parcelaList;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public CursoMatriculaService getCursoMatriculaService() {
		return cursoMatriculaService;
	}

	public void setCursoMatriculaService(
			CursoMatriculaService cursoMatriculaService) {
		this.cursoMatriculaService = cursoMatriculaService;
	}

	public BigDecimal getTotalCursos() {
		return totalCursos;
	}

	public String getTotalCursosFmt() {
		return FormataUtil.formataMoedaBrasil(totalCursos);
	}

	public void setTotalCursos(BigDecimal totalCursos) {
		this.totalCursos = totalCursos;
	}

	public Pagamento getPagamentoDetalhe() {
		return pagamentoDetalhe;
	}

	public void setPagamentoDetalhe(Pagamento pagamentoDetalhe) {
		this.pagamentoDetalhe = pagamentoDetalhe;
	}

	public Pagamento getPagamentoSelecionado() {
		return pagamentoSelecionado;
	}

	public void setPagamentoSelecionado(Pagamento pagamentoSelecionado) {
		this.pagamentoSelecionado = pagamentoSelecionado;
	}

	public ClienteMatricula getClienteExcluir() {
		return clienteExcluir;
	}

	public void setClienteExcluir(ClienteMatricula clienteExcluir) {
		this.clienteExcluir = clienteExcluir;
	}

	public CursoMatricula getCursoExcluir() {
		return cursoExcluir;
	}

	public void setCursoExcluir(CursoMatricula cursoExcluir) {
		this.cursoExcluir = cursoExcluir;
	}

	public Pagamento getPagamentoExcluir() {
		return pagamentoExcluir;
	}

	public void setPagamentoExcluir(Pagamento pagamentoExcluir) {
		this.pagamentoExcluir = pagamentoExcluir;
	}

	public Parcela getParcelaExcluir() {
		return parcelaExcluir;
	}

	public void setParcelaExcluir(Parcela parcelaExcluir) {
		this.parcelaExcluir = parcelaExcluir;
	}

	public BigDecimal getTotalPagamentos() {
		return totalPagamentos;
	}

	public String getTotalPagamentosFmt() {
		return FormataUtil.formataMoedaBrasil(totalPagamentos);
	}

	public void setTotalPagamentos(BigDecimal totalPagamentos) {
		this.totalPagamentos = totalPagamentos;
	}

	public String getDiferencaFmt() {
		return FormataUtil.formataMoedaBrasil(diferenca);
	}

	public BigDecimal getDiferenca() {
		return diferenca;
	}

	public void setDiferenca(BigDecimal diferenca) {
		this.diferenca = diferenca;
	}

	public UnidadeService getUnidadeService() {
		return unidadeService;
	}

	public void setUnidadeService(UnidadeService unidadeService) {
		this.unidadeService = unidadeService;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Matricula getMatriculaSetada() {
		return matriculaSetada;
	}

	public void setMatriculaSetada(Matricula matriculaSetada) {
		this.matriculaSetada = matriculaSetada;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public List<ClienteMatricula> getClienteMatriculaList() {
		return clienteMatriculaList;
	}

	public void setClienteMatriculaList(
			List<ClienteMatricula> clienteMatriculaList) {
		this.clienteMatriculaList = clienteMatriculaList;
	}

	public SessaoUtil getSessaoUtil() {
		return sessaoUtil;
	}

	public void setSessaoUtil(SessaoUtil sessaoUtil) {
		this.sessaoUtil = sessaoUtil;
	}

	public Matricula getMatriculaContrato() {
		return matriculaContrato;
	}

	public void setMatriculaContrato(Matricula matriculaContrato) {
		this.matriculaContrato = matriculaContrato;
	}

	public ClienteMatricula getClienteMatriculaBusca() {
		return clienteMatriculaBusca;
	}

	public void setClienteMatriculaBusca(ClienteMatricula clienteMatriculaBusca) {
		this.clienteMatriculaBusca = clienteMatriculaBusca;
	}

}

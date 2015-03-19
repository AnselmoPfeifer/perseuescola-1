package com.htcursos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.htcursos.controller.util.faces.JsfMessages;
import com.htcursos.model.entity.Parcela;
import com.htcursos.model.entity.StatusParcelaEnum;
import com.htcursos.model.entity.TipoParcelaBaixaEnum;
import com.htcursos.model.service.ParcelaService;
import com.htcursos.model.service.ServiceException;

@Controller("parcelaController")
@ViewScoped
@ManagedBean
public class ParcelaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ParcelaService parcelaService;
	private Parcela parcela = new Parcela();
	private Parcela parcelaSelecionada = new Parcela();
	private boolean colunaValorAtualizado = false;

	private List<Parcela> parcelaList;
	private List<Parcela> parcelaRecebidaList;

	@PostConstruct
	protected void init() {
		atualizaListas();
	}

	public void salvar() {
		try {
			JsfMessages.adicionaMensagemInfo("Parcela Paga");
			parcelaService.salvar(parcela);
			atualizaListas();
			parcela = new Parcela();
		} catch (ServiceException e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}

	}

	/** Salva a nova parcela parcial gerada no banco
	 * @param parcela
	 */
	public void salvarNovaParcela(Parcela parcela) {
		try {
			parcelaService.salvar(parcela);
			JsfMessages.adicionaMensagemInfo("Nova Parcela Gerada");
		} catch (ServiceException e) {
			JsfMessages.adicionaMensagemErro(e.getMessage());
		}

	}

	public void excluir(Parcela parcela) {
		// removendo da ArrayLista para evitar uma nova consulta
		parcelaService.excluir(parcela);
		atualizaListas();
		JsfMessages.adicionaMensagemInfo("Parcela removido");

	}


	/** Faz a baixa da parcela
	 * 
	 */
	public void baixarParcela() {
		setColunaValorAtualizado(false);
		if (parcelaService.verificaPagamentoNoVencimento(parcela)) {
			if (parcelaService.verificaValorPagoInteiro(parcela)) {
				salvar();
			} else {
				salvarNovaParcela(parcelaService.geraNovaParcela(parcela));
				salvar();
			}
		} else {
			if (parcelaService.verificaValorPagoInteiro(parcela)) {
				salvar();
			} else {
				JsfMessages.adicionaMensagemErro("Parcela não foi Paga");
			}
		}
	}


	/**
	 * Atualiza o valor atualizado da parcela
	 * */
	public void atualizaValorAPagar() {
		if (parcela.getDatabaixa() != null) {
			parcelaService.insereValorAtualizadoNaParcela(parcela);
			setColunaValorAtualizado(true);
		}
	}

	public void cancelar() {
		parcela = new Parcela();
	}

	public void editar(Parcela parcela) {
		this.parcela = parcela;
	}

	/**
	 * Atualiza lista de parcelas
	 * */
	public void atualizaListas() {
		List<Parcela> parcelaListTemp = getParcelaService().buscarTodos();
		parcelaRecebidaList = new ArrayList<Parcela>();
		setParcelaList(new ArrayList<Parcela>());
		for (int i = 0; i < parcelaListTemp.size(); i++) {
			if (parcelaListTemp.get(i).getStatus()
					.equals(StatusParcelaEnum.LIQUIDADO) || parcelaListTemp.get(i).getStatus()
					.equals(StatusParcelaEnum.RECEBIMENTO_PARCIAL) ) {
				parcelaRecebidaList.add(parcelaListTemp.get(i));
				continue;
			} else {
				getParcelaList().add(parcelaListTemp.get(i));
			}
		}
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

	public List<Parcela> getParcelaRecebidaList() {
		return parcelaRecebidaList;
	}

	public void setParcelaRecebidaList(List<Parcela> parcelaRecebidaList) {
		this.parcelaRecebidaList = parcelaRecebidaList;
	}

	public List<Parcela> getParcelaList() {
		return parcelaList;
	}

	public void setParcelaList(List<Parcela> parcelaList) {
		this.parcelaList = parcelaList;
	}

	public boolean isColunaValorAtualizado() {
		return colunaValorAtualizado;
	}

	public void setColunaValorAtualizado(boolean colunaValorAtualizado) {
		this.colunaValorAtualizado = colunaValorAtualizado;
	}

	public Parcela getParcelaSelecionada() {
		return parcelaSelecionada;
	}

	public void setParcelaSelecionada(Parcela parcelaSelecionada) {
		this.parcelaSelecionada = parcelaSelecionada;
	}
}

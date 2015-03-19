package com.htcursos.model.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.dao.ParcelaDAO;
import com.htcursos.model.entity.Parcela;
import com.htcursos.model.entity.StatusParcelaEnum;
import com.htcursos.model.entity.TipoParcelaBaixaEnum;

@Service
public class ParcelaService extends GenericService<Parcela, Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7396649201318389188L;
	@Autowired
	private ParcelaDAO parcelaDAO;

	@Override
	public GenericDAO<Parcela, Integer> getDao() {
		// TODO Auto-generated method stub
		return parcelaDAO;
	}

	public void excluir(Parcela parcela) {
		parcelaDAO.excluir(parcela);
	}

	/** Verifica se o pagamento da parcela foi feito no dia do vencimento
	 * @param parcela
	 * @return <code>true</code> se pagar no dia
	 * @return <code>true</code> se pagar antes ou depois
	 */
	public boolean verificaPagamentoNoVencimento(Parcela parcela) {
		if (parcela.getDatabaixa().before(parcela.getDatavencimento())|| parcela.getDatabaixa().after(parcela.getDatavencimento())) {
			return false;
		}
		return true;
	}

	/** Insere o valor no campo "Valor Atualizado" da parcela
	 * @param parcela
	 * @return {@link Void}
	 */
	public void insereValorAtualizadoNaParcela(Parcela parcela) {
		if (!verificaPagamentoNoVencimento(parcela)) {
			BigDecimal porcentagem = calculaJuros(parcela);
			if (parcela.getDatabaixa().before(parcela.getDatavencimento())) {
				parcela.setValorAtualizado(parcela.getValor().subtract(porcentagem));
				parcela.setTipoParcelaBaixa(TipoParcelaBaixaEnum.COM_DESCONTO);
				return;
			}
			parcela.setValorAtualizado(parcela.getValor().add(porcentagem));
			parcela.setTipoParcelaBaixa(TipoParcelaBaixaEnum.COM_JUROS);
			return;

		} else {
			parcela.setValorAtualizado(parcela.getValor());
			parcela.setTipoParcelaBaixa(TipoParcelaBaixaEnum.NORMAL);
		}

	}

	/** Verifica se foi pago o valor inteiro
	 * @param parcela
	 * @return <code>true</code> se pagar o valor inteiro
	 */
	public boolean verificaValorPagoInteiro(Parcela parcela) {
		if ((!verificaPagamentoNoVencimento(parcela) && valorPagoIgualAtualizado(parcela)) || (verificaPagamentoNoVencimento(parcela) && valorPagoIgualAtualizado(parcela))) {
			parcela.setStatus(StatusParcelaEnum.LIQUIDADO);
			return true;
		}
		return false;
	}

	/** Verifica se o valor pago é igual o valor atualizado
	 * @param parcela
	 * @return <code>true</code> Se for pago o valor atualizado
	 */
	public boolean valorPagoIgualAtualizado(Parcela parcela) {
		return (parcela.getValorpago().compareTo(parcela.getValorAtualizado()) == 0) ? true : false;
	}

	/** Calcula o Juros da Parcela
	 * @param parcela
	 * @return {@link BigDecimal}
	 */
	private BigDecimal calculaJuros(Parcela parcela) {
		return parcela.getValor().multiply(new BigDecimal(0.10))
				.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	/** Gera nova Parcela
	 * @param parcela
	 * @return {@link Parcela}
	 * */
	public Parcela geraNovaParcela(Parcela parcela) {
		parcela.setStatus(StatusParcelaEnum.RECEBIMENTO_PARCIAL);
		parcela.setTipoParcelaBaixa(TipoParcelaBaixaEnum.PARCIAL);
		Parcela novaParcela = new Parcela();
		novaParcela.setValor(valorNovaParcela(parcela));
		novaParcela.setDatavencimento(geraDataNovaParcela(parcela));
		novaParcela.setStatus(StatusParcelaEnum.PAGAMENTO_PARCIAL);
		novaParcela.setPagamento(parcela.getPagamento());
		return novaParcela;
	}

	/** Gera o valor para a nova parcela
	 * @param parcela
	 * @return {@link BigDecimal}
	 */
	private BigDecimal valorNovaParcela(Parcela parcela) {
		return parcela.getValor().subtract(parcela.getValorpago());
	}

	/** Gera a data para a nova parcela
	 * @param parcela
	 * @return {@link Date}
	 */
	private Date geraDataNovaParcela(Parcela parcela) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parcela.getDatabaixa());
		calendar.add(calendar.DATE, 30);
		if (calendar.get((Calendar.DAY_OF_WEEK)) == Calendar.SATURDAY) {
			calendar.add(calendar.DATE, 2);
		} else if (calendar.get((Calendar.DAY_OF_WEEK)) == Calendar.SUNDAY) {
			calendar.add(calendar.DATE, 1);
		}
		return calendar.getTime();
	}
}

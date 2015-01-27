package com.htcursos.model.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.ContaDAO;
import com.htcursos.model.dao.FormaPagamentoDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.dao.LancamentoDAO;
import com.htcursos.model.dao.MatriculaDAO;
import com.htcursos.model.dao.ParcelaDAO;
import com.htcursos.model.entity.Conta;
import com.htcursos.model.entity.FormaPagamento;
import com.htcursos.model.entity.Lancamento;
import com.htcursos.model.entity.Matricula;
import com.htcursos.model.entity.Pagamento;
import com.htcursos.model.entity.Parcela;
import com.htcursos.model.enums.TipoLancamentoEnum;

@Service
public class MatriculaService extends GenericService<Matricula, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7226324300870358213L;

	@Autowired
	private MatriculaDAO matriculaDAO;

	@Autowired
	private LancamentoDAO lancamentoDAO;

	@Autowired
	private ContaDAO contaDAO;

	@Autowired
	private FormaPagamentoDAO formaPagamentoDAO;

	@Override
	public GenericDAO<Matricula, Integer> getDao() {

		return matriculaDAO;
	}

	/**
	 * M���todo Sobrescrito para salvar matricula efetuando os lancamentos
	 * 
	 * @throws ServiceExpcetion
	 */
	public Matricula salvar(Matricula matricula) throws ServiceExpcetion {
		int qtdContratante = matricula.getQuantidadeContratantes();

		 if(!matricula.getContratante().possuiDadosCompletos()){
		 throw new ServiceExpcetion(
		 "Este cliente possui dados incompletos, verifique seu cadastro.");
		 }

		if (qtdContratante == 0) {
			throw new ServiceExpcetion(
					"Insira pelo menos um cliente contratante na matrícula.");
		}
		if (qtdContratante > 1) {
			throw new ServiceExpcetion(
					"Insira apenas uma cliente contratante na matrícula.");
		}

		if (matricula.getTotalCursos().doubleValue() == 0.0) {
			throw new ServiceExpcetion(
					"Insira cursos com valores na matrícula.");
		}
		
		if( !matricula.validaPagamentos()){
			throw new ServiceExpcetion(
					"Pagamentos com dados incompletos");
		}
			
		if (matricula.getTotalPagamentos().doubleValue() == 0.0) {
			throw new ServiceExpcetion(
					"Insira pagamentos com valores na matrícula.");
		}

		if (matricula.calcularDiferenca().doubleValue() != 0.0) {
			throw new ServiceExpcetion(
					"Os valores dos pagamentos não batem com os valores dos cursos. Verifique a diferença");
		}

		matriculaDAO.salvar(matricula);

		gerarLancamentos(matricula);

		return matricula;
	}

	private void gerarLancamentos(Matricula matricula) {
		// Partida
		// Gerando Lancamentos para cada forma de pagamento
		for (Pagamento pagamento : matricula.getPagamentoList()) {
			// Buscando Forma de Pagamento por Id
			FormaPagamento formaPagamento = formaPagamentoDAO
					.buscarPorId(pagamento.getFormaPagamento().getId());

			// Buscando um objeto conta referente a forma de pagamento
			Conta conta = contaDAO.buscarPorId(formaPagamento.getConta()
					.getId());

			// Instanciando outro objeto lancamento - Partida
			Lancamento lancamento = new Lancamento(new Date(),
					TipoLancamentoEnum.DEBITO, pagamento.getValor(), conta,
					null, "Cadastro de Matricula");
			
			salvarParcela(pagamento);	
			
			lancamentoDAO.salvar(lancamento);			
		
		}

		// Contrapartida

		// Buscando um objeto conta referente a contra partida
		Conta conta = contaDAO.buscarPorCodigoReduzido(21611);

		// Instanciando objeto lancamento - Contra Partida
		Lancamento lancamento = new Lancamento(new Date(),
				TipoLancamentoEnum.CREDITO, matricula.getValorTotal(), conta,
				matricula, "Cadastro de Matricula");

		// Persistindo o lancamento
		lancamentoDAO.salvar(lancamento);

		// Calculando soma das parcelas
		// matricula.gerarValorTotal();
	}
	@Autowired
	private ParcelaDAO parcelaDAO;
	public void salvarParcela(Pagamento pagamento){
		
		List<Parcela> parcelas =  pagamento.gerarParcelas();
		
		for(Parcela p: parcelas){
			parcelaDAO.salvar(p);
		}
		
		
	}

	

}

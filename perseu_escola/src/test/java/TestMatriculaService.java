import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.FormaPagamento;
import com.htcursos.model.entity.Matricula;
import com.htcursos.model.entity.Pagamento;
import com.htcursos.model.entity.Parcela;
import com.htcursos.model.entity.StatusParcelaEnum;
import com.htcursos.model.entity.Unidade;
import com.htcursos.model.entity.Usuario;
import com.htcursos.model.service.FormaPagamentoService;
import com.htcursos.model.service.MatriculaService;
import com.htcursos.model.service.ServiceExpcetion;
import com.htcursos.model.service.UnidadeService;
import com.htcursos.model.service.UsuarioService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
@TransactionConfiguration( transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class TestMatriculaService {
	
	@Autowired
	MatriculaService matriculaService;
	@Autowired
	FormaPagamentoService formaPagamentoService;
	
	@Autowired
	UnidadeService unidadeService;
	
	@Autowired
	UsuarioService usuarioService;
	

	
	@Test
	@Rollback(value=true)
	public void testSalvar() throws ServiceExpcetion{
		
		
		Matricula matricula = new Matricula();
		matricula.setData(new Date());
		
		//Lista de pagamentos
		List<Pagamento> pagamentoList =  new ArrayList<Pagamento>();
		
		//Vetores com os dados dos pagamentos conforme modelo documentacao
		FormaPagamento[] arrformaPagamento =  {formaPagamentoService.buscarPorId(1)  , formaPagamentoService.buscarPorId(2) , formaPagamentoService.buscarPorId(3), formaPagamentoService.buscarPorId(4),  formaPagamentoService.buscarPorId(5), formaPagamentoService.buscarPorId(6)     };
		BigDecimal[] arrValores = {new BigDecimal(200.0) ,  new BigDecimal(50), new BigDecimal(50), new BigDecimal(400), new BigDecimal(60), new BigDecimal(140)};
		Integer[] arrNumeroParcelas =  {2, 2, 2, 2 , 2, 2};
		
		//Gerando pagamentos conforme os dados dos vetores acima
		for (int i = 0; i < arrformaPagamento.length ; i++) {
			
			Pagamento p = new Pagamento();
			p.setFormaPagamento(arrformaPagamento[i]);
			p.setMatricula(matricula);
			p.setNumeroParcelas(arrNumeroParcelas[i]);
			p.setValor(arrValores[i]);
		
			List <Parcela> parcelasDoPagamento = new ArrayList<Parcela>();
			//Gerando Parcelas para o Pagamento
			for (int j = 0; j< arrNumeroParcelas[i] ; j++ ){
			
				Parcela parcela =  new Parcela();
				parcela.setPagamento(p);
				parcela.setDatavencimento(new Date());
				parcela.setStatus(StatusParcelaEnum.ABERTO);
				parcela.setValor(p.getValor().divide(new BigDecimal(arrNumeroParcelas[i])));
				parcelasDoPagamento.add(parcela);
			
			}
			p.setParcelaList(parcelasDoPagamento);		
			//adicionando cada pagamento na lista
			pagamentoList.add(p);
		}
		
		
		//essa unidade dever estar na sessao o logado
		Unidade unidade =  unidadeService.buscarPorId(1); 
		Usuario usuario = usuarioService.buscarPorId(1);
		
		//setando lista de pagamentos gerados
		matricula.setPagamentoList(pagamentoList);
		matricula.setUnidade(unidade);
		matricula.setUsuario(usuario);
	
		
		matriculaService.salvar(matricula);
		Assert.assertTrue(true);
	}
}

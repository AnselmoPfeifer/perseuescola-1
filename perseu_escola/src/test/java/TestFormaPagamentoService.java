import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Conta;
import com.htcursos.model.entity.FormaPagamento;
import com.htcursos.model.service.ContaService;
import com.htcursos.model.service.FormaPagamentoService;
import com.htcursos.model.service.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
@TransactionConfiguration( transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class TestFormaPagamentoService {
	
		@Autowired
		FormaPagamentoService formaPagamentoService;
		
		@Autowired
		ContaService contaService;

		@Test
		@Transactional
		public void testSalvar() throws ServiceException{
			
			Integer[] contas = {11211, 11321, 11211, 11311,11211, 11211};
		//	String [] descricoes = {"Dinheiro", "Cartão Débito", "Boleto", "Cartão de Crédito" ,  "Cheque", "Nota Promissória"};
			
			String [] descricoes = {"F2B"};
			
			
			for (int i = 0; i < descricoes.length; i++) {
				Conta conta = contaService.buscarPorCodigoReduzido(contas[i]);
				
				FormaPagamento fp1 =  new FormaPagamento();
				fp1.setDescricao(descricoes[i]);
				fp1.setConta(conta);
				fp1.setCobrancaPropria(false);
				formaPagamentoService.salvar(fp1);
			}	
		
			
			
		}
}

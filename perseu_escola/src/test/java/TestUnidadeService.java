import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Cidade;
import com.htcursos.model.entity.Conta;
import com.htcursos.model.entity.Endereco;
import com.htcursos.model.entity.FormaPagamento;
import com.htcursos.model.entity.Uf;
import com.htcursos.model.entity.Unidade;
import com.htcursos.model.service.ContaService;
import com.htcursos.model.service.FormaPagamentoService;
import com.htcursos.model.service.ServiceExpcetion;
import com.htcursos.model.service.UnidadeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
@TransactionConfiguration( transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class TestUnidadeService {
	

		
		@Autowired
		UnidadeService unidadeService;

		@Test
		public void testSalvar() throws ServiceExpcetion{
			
				Uf uf =  new Uf();
				uf.setDescricao("Mato Grosso do Sul");
				
				Cidade cidade = new Cidade();
				cidade.setUf(uf);
				cidade.setDescricao("Campo Grande");
			
				Endereco endereco = new Endereco();
				endereco.setBairro("Betaville");
				endereco.setCep("79060-231");
			//	endereco.setCidade(cidade);
			
				Unidade unidade = new Unidade();
				unidade.setEndereco(endereco);
				unidade.setNome("High Tech Campo Grande MS");
				
				unidadeService.salvar(unidade);
			
		}
}

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Conta;
import com.htcursos.model.service.ContaService;
import com.htcursos.model.service.ServiceExpcetion;
import com.htcursos.model.util.PoiReadExcelFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
@TransactionConfiguration( transactionManager="transactionManager", defaultRollback=false)
@Transactional 
public class TestImportacaoService {
	
	@Autowired
	ContaService contaService;
	
	//Aqui vai variar a cada maquina
	String path = "/home/virmerson/Dropbox/High Tech Cursos/workspace_perseu/perseu-escola/src/test/resources/PlanoDeContas.xlsx";

	@Test
	public void deveImportarPlanoDeContas() throws ServiceExpcetion {
		List<Conta> contas = new ArrayList<Conta>();
		contas = PoiReadExcelFile.importarXLSX(path, "planilha");

		for (Conta conta : contas) {
			contaService.salvar(conta);
		}
		
		Assert.assertTrue(true);
	}
}
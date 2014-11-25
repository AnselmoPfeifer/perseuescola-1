import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.dao.UsuarioDAO;
import com.htcursos.model.entity.Autorizacao;
import com.htcursos.model.entity.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
@TransactionConfiguration( transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class TestCadastroUsuario {

	@Autowired
	private UsuarioDAO usuarioDAO;
	

	@Test
	@Transactional
	//@Rollback(value=true)
	public void testCadastroUsuario() {
		Usuario usuario =  new Usuario();
		usuario.setNome("Administrador");
		usuario.setUsername("admin");
		usuario.setPassword("123");
		usuario.setEnable(true);
		
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setAuthority("ROLE_ADMIN");
		
		usuario.setAutorizacoes(new ArrayList<Autorizacao>());
		usuario.getAutorizacoes().add(autorizacao);
		
		Assert.assertNotNull( usuarioDAO.salvar(usuario) );
		
		System.out.println(usuario);
		
	}
}

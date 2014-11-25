import com.htcursos.model.entity.Autorizacao;
import com.htcursos.model.entity.Unidade;


public class TestCriarUsuario {

	
	public void testCriarUsuario(){
		
		Autorizacao autorizacao =  new Autorizacao();
		autorizacao.setAuthority("ROLE_ADMIN");

		Unidade unidade = new Unidade();
		unidade.setNome("Unidade Básica de Saúde");
		
		
		
	}
}

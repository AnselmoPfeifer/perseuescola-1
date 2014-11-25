

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Autorizacao;
import com.htcursos.model.entity.Curso;
import com.htcursos.model.entity.FormaPagamento;
import com.htcursos.model.entity.Usuario;
import com.htcursos.model.service.AutorizacaoService;
import com.htcursos.model.service.CursoService;
import com.htcursos.model.service.FormaPagamentoService;
import com.htcursos.model.service.ServiceExpcetion;
import com.htcursos.model.service.UfService;
import com.htcursos.model.service.UsuarioService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src//main//webapp//WEB-INF//applicationContext.xml" })
@Transactional
public class TestPopulaBanco {
	
	@Autowired
	private UfService ufService;
	

	@Autowired
	private AutorizacaoService autorizacaoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Test
	@Rollback(false)
	public void polularBanco() throws ServiceExpcetion  {
		
		Autorizacao roleAdmin = new Autorizacao();
		roleAdmin.setAuthority("ROLE_ADMIN");
		autorizacaoService.salvar(roleAdmin);
////
		Usuario usuario = new Usuario();	
		usuario.setId(1);
////		
////		
		List<Autorizacao> listaAutorizacoes = new ArrayList<Autorizacao>();
		listaAutorizacoes.add(roleAdmin);
		usuario.setAutorizacoes(listaAutorizacoes);
		usuario.setEnable(true);
		usuario.setNome("Virmerson");
		usuario.setPassword("1");
		usuario.setUsername("ADM");
////		
////		
		usuarioService.salvar(usuario);
		
//		List<Curso> cursos = new ArrayList<Curso>();
//		
//		Curso cjalg = new Curso();
//		cjalg.setTitulo("Java e Algoritmos");
//		cjalg.setSigla("cjalg");
//		
//		cursos.add(cjalg);
//		
//		Curso cjoo = new Curso();
//		cjoo.setTitulo("Java e Orientação a Objetos");
//		cjoo.setSigla("cjoo");
//		cursos.add(cjoo);
//		
//		Curso cjweb1 = new Curso();
//		cjweb1.setTitulo("Java Web Fundamentos");
//		cjweb1.setSigla("cjweb1");
//		
//		cursos.add(cjweb1);
//		
//		Curso cjweb2 = new Curso();
//		cjweb2.setTitulo("Java Web Frameworks");
//		cjweb2.setSigla("cjweb2");
//		
//		cursos.add(cjweb2);
//		
//		for(Curso c: cursos){
//			cursoService.salvar(c);
//		}
		
//		FormaPagamento dinheiro = new FormaPagamento();
//		dinheiro.setDescricao("Dinheiro");
//	
//		FormaPagamento cartaoDebito = new FormaPagamento();
//		cartaoDebito.setDescricao("Cartão de Débito");
//		
//		FormaPagamento boleto = new FormaPagamento();
//		boleto.setDescricao("Boleto");
//		
//		FormaPagamento cartaoCredito = new FormaPagamento();
//		cartaoCredito.setDescricao("Cartão de Crédido");
//		
//		FormaPagamento cheque = new FormaPagamento();
//		cheque.setDescricao("Cheque");
//		
//		FormaPagamento notaPromissoria = new FormaPagamento();
//		notaPromissoria.setDescricao("Nota Promissória");
//	
//		
//		List<FormaPagamento> formas =  new ArrayList<FormaPagamento>();
//		formas.add(dinheiro);
//		formas.add(cartaoDebito);
//		formas.add(boleto);
//		formas.add(cartaoCredito);
//		formas.add(cheque);
//		formas.add(notaPromissoria);
//		
//		for(FormaPagamento fp: formas){
//			formaPagamentoService.salvar(fp);
//		}
//		
	}

}

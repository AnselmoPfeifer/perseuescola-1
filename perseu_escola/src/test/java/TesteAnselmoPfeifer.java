import static org.junit.Assert.*;

import org.junit.Test;

public class TesteAnselmoPfeifer {

	@Test
	public void testar(){
		int val1 = 5;
	    int val2 = 6;
	    assertTrue (val1 < val2);
	    
	    assertFalse(val1 > val2);
	}
	
	@Test
	public void testeNome(){
		String primeiroNome = "Anselmo";
		String segundoNome = "Anselmo";
		
		assertTrue(primeiroNome == segundoNome);
//		assertFalse(primeiroNome == segundoNome);
		
	}
}

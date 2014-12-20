import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;


public class TestArrayList {


    
  public static void main(String[] args) {  
        
      Collection lista = new ArrayList();  
      lista.add("1");  
      lista.add("2");  
      lista.add("4");  
      lista.add("5");  
      lista.add("1");  
      lista.add("1");  
        
      lista = Collections.singleton(new HashSet(lista));  
        
      for (Object object : lista) {  
          System.out.println(object.toString());  
      }  
  }  
}  
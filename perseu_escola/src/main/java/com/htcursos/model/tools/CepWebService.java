package com.htcursos.model.tools;

import java.io.Serializable;
import java.net.URL;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.htcursos.model.entity.Endereco;

@Service("cepWebService")
public class CepWebService implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3877566775246883282L;
	private int resultado = 0;
	/**
	 * Endereco buscado
	 */
	private Endereco endereco = new Endereco();
	
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public CepWebService() {
		super();
	}
	
	/**
	 * @param enderecoBuscar  Necessario apenas o CEP
	 */
	@SuppressWarnings("rawtypes")
	public void cepWebBuscar(Endereco enderecoBuscar) {

		try {
			URL url = new URL(
					"http://cep.republicavirtual.com.br/web_cep.php?cep=" + enderecoBuscar.getCep()	+ "&formato=xml");

			Document document = getDocumento(url);
			
			Element root = document.getRootElement();

			for (Iterator i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();

				if (element.getQualifiedName().equals("uf"))
					enderecoBuscar.setEstado( element.getText());

				if (element.getQualifiedName().equals("cidade"))
					enderecoBuscar.setCidade(element.getText());

				if (element.getQualifiedName().equals("bairro"))
					enderecoBuscar.setBairro(element.getText());

				if (element.getQualifiedName().equals("tipo_logradouro"))
					enderecoBuscar.setTipo_logradouro(element.getText());

				if (element.getQualifiedName().equals("logradouro"))
					enderecoBuscar.setLogradouro(element.getText());

				if (element.getQualifiedName().equals("resultado"))
					setResultado(Integer.parseInt(element.getText()));
			}
			
			setEndereco(enderecoBuscar);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}

	public Document getDocumento(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);

		return document;
	}


	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	
}

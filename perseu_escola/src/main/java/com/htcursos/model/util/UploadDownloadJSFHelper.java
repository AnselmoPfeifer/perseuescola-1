package com.htcursos.model.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.UploadedFile;

/**
 * Objeto para envio de arquivo
 * 
 * @author virmerson
 *
 */
public class UploadDownloadJSFHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UploadedFile arquivo;
	public final static String PASTA_RAIZ;
	/**
	 * Ao ser utilizada a classe pela primeira vez a constante PASTA_RAIZ será setada
	 */
	static{
		//contexto para acessar a pasta raiz
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		//Para concaternar depois pegando automatico o caminho raiz
		String realPath = ctx.getRealPath("/");
		
		//Setando na mao o caminho
		PASTA_RAIZ = "/var/lib/tomcat7/webapps/perseu_escola/";
		//PASTA_RAIZ = "/Users/mac/Documents/dev/git/perseu_escola/perseu_escola/src/main/webapp/";
		//PASTA_RAIZ = "/Library/STS Bundle/projects/maven.1422999252121/perseu_escola/src/main/webapp";
	}

	public UploadDownloadJSFHelper() {


	}

	/** Upload de Arquivo **/
	/**
	 * 
	 * @return
	 */
	public void enviarArquivo(String nomeGerado) {

		String novoNomeArquivo = PASTA_RAIZ + File.separator + "uploads"
				+ File.separator + nomeGerado;

		try {
			copiarArquivo(novoNomeArquivo, getArquivo().getInputstream());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public String getExtensao() {
		String extensao[] = getArquivo().getFileName().split("\\.");
		String ext = extensao[extensao.length - 1];
		return ext;
	}

	public void copiarArquivo(String fileName, InputStream in) {
		try {

			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("Novo Arquivo Criado!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Download JSF
	 * @param nomeArquivo
	 * @param fileLocation
	 * @param mimeType
	 * @param facesContext
	 */
	public static synchronized void downloadArquivo(String nomeArquivo,  String tituloArquivo, 
			String fileLocation, String mimeType, FacesContext facesContext) {

		ExternalContext context = facesContext.getExternalContext(); // Context
		String path = fileLocation; // Localizacao do arquivo
		String fullFileName = path + nomeArquivo;
		File file = new File(fullFileName); // LINHA ALTERADA

		HttpServletResponse response = (HttpServletResponse) context
				.getResponse();
		//Um nome est�� no disco, mas para baixar gera com o titulo que �� o nome original do envio
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ tituloArquivo + "\""); // aki eu seto o header e o nome q vai
									// aparecer na hr do donwload
		response.setContentLength((int) file.length()); // O tamanho do arquivo
		response.setContentType(mimeType); // e obviamente o tipo

		try {
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();

			byte[] buf = new byte[(int) file.length()];
			int count;
			while ((count = in.read(buf)) >= 0) {
				out.write(buf, 0, count);
			}
			in.close();
			out.flush();
			out.close();
			facesContext.responseComplete();
		} catch (IOException ex) {
			System.out.println("Error in downloadFile: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public UploadedFile getArquivo() {
		return arquivo;
	}

	public String getNomeArquivo() {
		return getArquivo().getFileName();
	}

	public String getNomeArquivoGerado() {
		
		String nomeArquivoGerado = System.currentTimeMillis() + (Math.random()*100) + "."
				+ getExtensao();
		System.out.println("Executando o Math.random: "+(Math.random()*100));
		
		return nomeArquivoGerado;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
		
	}

	public void excluir(String caminho) {
		
		File file = new File(PASTA_RAIZ+caminho);
		file.delete();
		
	}
}

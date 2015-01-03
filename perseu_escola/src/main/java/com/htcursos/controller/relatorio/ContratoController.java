package com.htcursos.controller.relatorio;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.model.entity.Matricula;
import com.htcursos.model.util.SessaoUtil;

@Controller
@Scope("view")
@ManagedBean(name = "contratoController")
public class ContratoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessaoUtil sessaoUtil;
	
	private Matricula matricula;

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula)  {
		this.matricula = matricula;
		
	}

	@PostConstruct
	protected void init() {
		
	}
	
	public String carregarContrato(Matricula matricula){
		setMatricula(matricula);
		return "contrato?faces-redirect=true";
	}

	public void gerarContrato(Matricula matricula) throws IOException {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("USUARIO_LOGADO", sessaoUtil.getUserNameUsuarioLogado());
		parametros.put("NOME", matricula.getContratante().getNome());
		parametros.put("CPFCNPJ", matricula.getContratante().getCPFCNPJStr());
		parametros.put("CURSOS", matricula.getCursoMatriculaListStr());	
		parametros.put("PAGAMENTOS", matricula.getPagamentoListStr());	
		
		gerarRelatorio(parametros, "contrato_matricula", matricula.getContratante().getNomeSemEspacos());

	}

	
	public void gerarRelatorio(	Map<String, Object> parametros, String arquivoJasper, String arquivoContrato) {

		
		String reportPath = FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath("/WEB-INF/reports/" + arquivoJasper + ".jasper");

		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,
					parametros, new JREmptyDataSource());

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=" + arquivoContrato + ".pdf");

			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint,
					servletOutputStream);

			FacesContext.getCurrentInstance().responseComplete();

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

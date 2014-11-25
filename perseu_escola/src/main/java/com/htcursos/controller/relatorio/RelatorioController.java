package com.htcursos.controller.relatorio;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioController {
	public void gerarRelatorio(Collection<?> lista,
			Map<String, Object> parametros, String arquivo) {

		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(
				lista);

		String reportPath = FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath("/WEB-INF/report/" + arquivo + ".jasper");

		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,
					parametros, datasource);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=" + arquivo + ".pdf");

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

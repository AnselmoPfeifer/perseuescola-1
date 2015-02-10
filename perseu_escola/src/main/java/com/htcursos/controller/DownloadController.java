package com.htcursos.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.htcursos.model.entity.Anexo;
import com.htcursos.model.util.UploadDownloadJSFHelper;


@Controller
@Scope("view")
@ManagedBean
public class DownloadController implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void download(Anexo anexo) {

		
		UploadDownloadJSFHelper.downloadArquivo(anexo.getNomeGerado(),
				anexo.getNomeOriginal(), UploadDownloadJSFHelper.PASTA_RAIZ + "/uploads/", anexo.getExtensao(),
				FacesContext.getCurrentInstance());
	}
}
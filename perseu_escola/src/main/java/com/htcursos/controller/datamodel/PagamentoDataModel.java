package com.htcursos.controller.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.htcursos.model.entity.Pagamento;

public class PagamentoDataModel extends ListDataModel<Pagamento> implements
		SelectableDataModel<Pagamento> {
	
	public PagamentoDataModel() {
		// TODO Auto-generated constructor stub
	}
	
	public PagamentoDataModel(List<Pagamento> pagamentoList) {
		super(pagamentoList);
	}
	

	@Override
	public Object getRowKey(Pagamento pagamento) {
		return pagamento.getId();
	}

	@Override
	public Pagamento getRowData(String rowKey) {
		 List<Pagamento> listPagamentos = (List<Pagamento>) getWrappedData();  
         
	        for(Pagamento pagamento : listPagamentos) {  
	            if(pagamento.getId().equals(rowKey))  
	                return pagamento;  
	}
	        return null;  
	}
}


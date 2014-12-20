package com.htcursos.model.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Conta;

@Service
@Transactional
public  class PoiReadExcelFile {

	public static List<Conta> importarXLSX(String path , String sheet) {
		
		@SuppressWarnings("unchecked")
		List<Conta> contas = new ArrayList();
		
		Conta conta;
		XSSFRow row;
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet worksheet = workbook.getSheet(sheet);
			@SuppressWarnings("rawtypes")
			Iterator rows = worksheet.iterator();

			
			while (rows.hasNext()) {
				try{
				conta = new Conta();	
				row = (XSSFRow) rows.next();
			
				
					System.out.println(row);
				conta.setCodigoReduzido((int) row.getCell(0).getNumericCellValue());
				conta.setClassificacao(row.getCell(1).getStringCellValue());
				conta.setDescricao(row.getCell(2).getStringCellValue());			
				contas.add(conta);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return contas;
	}

}
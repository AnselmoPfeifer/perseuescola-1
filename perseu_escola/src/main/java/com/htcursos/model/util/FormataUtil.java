package com.htcursos.model.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormataUtil {
	
	public static String formataMoedaBrasil(BigDecimal valor) {
		// moeda
		NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(new Locale(
				"pt", "BR")); // para formatar os numeros na moeda do Brasil.
		moneyFormat.setMinimumFractionDigits(2);
		String valorFtm = moneyFormat.format(valor) ;
		return valorFtm;
	}
	
	public static String formataDataBrasil(Date data) {
		if (data != null)
			return new SimpleDateFormat("dd/MM/yyyy").format(data);
		else
			return "";
	}


}

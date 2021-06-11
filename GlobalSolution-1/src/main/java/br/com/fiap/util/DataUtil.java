package br.com.fiap.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
	
	public static String dataAtual() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return sdf.format(date);
	}
	

}

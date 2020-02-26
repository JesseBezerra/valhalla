package br.com.jdsb.valhalla.sql.core.texto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	private final String CONSTANTE_CABECALHO =  "--<DS_SCRIPT>                                                                                           \n"+
											    "-- DESCRIÇÃO..: %s                                                                                      \n"+
												"-- RESPONSAVEL: %s                                                                                      \n"+
												"-- DATA.......: %s                                                                                      \n"+
												"-- PRODUTO....: %s                                                                                      \n"+
												"-- APLICAÇÃO..: SOUL                                                                                    \n"+
												"-- ARTEFATO...: %s.%s                                                                                   \n"+
												"--</DS_SCRIPT>                                                                                          \n"+
												"--<USUARIO=DBAMV>	                                                                                    \n\n";

	public String converteData(Date dtData) throws ParseException{
		SimpleDateFormat out = new SimpleDateFormat("yyMMddHHmm");
		String result = out.format(dtData);
		return result;
    }

	public String converteDataCabecalho(Date dtData) throws ParseException{
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String result = out.format(dtData);
		return result;
    }

	public StringBuilder cabecalho(String supri, String descricao,String produto,String owner, String tabela) throws ParseException{
		StringBuilder retorno = new StringBuilder();
		retorno = retorno.append(String.format(CONSTANTE_CABECALHO, supri,descricao,converteDataCabecalho(new Date()),produto,owner,tabela));
		return retorno;
	}

}

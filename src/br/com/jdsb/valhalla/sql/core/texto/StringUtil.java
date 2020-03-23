package br.com.jdsb.valhalla.sql.core.texto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
	public final String CONSTANTE_CONEXAO =  "#CONFIGURAR CONEXAO ECLIPSE (ARQUIVO appdatalayer)                                                                                                                           \n"+
			                                 "#%s                                                                                                                                                                          \n"+
											 "connectionSettings.host=%s:%s\n"+
											 "connectionSettings.password=%s\n"+
											 "connectionSettings.user=%s\n"+
											 "connectionSettings.database=%s\n"+
											 "connectionSettings.url=jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=%s)(PORT=%s)))(CONNECT_DATA\\=(SERVICE_NAME\\=${connectionSettings.database})))";

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

	public String converteDataParametro(Date dtData){
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		String result = out.format(dtData);
		return result;
    }

	public StringBuilder cabecalho(String supri, String descricao,String produto,String owner, String tabela) throws ParseException{
		StringBuilder retorno = new StringBuilder();
		retorno = retorno.append(String.format(CONSTANTE_CABECALHO, supri,descricao,converteDataCabecalho(new Date()),produto,owner,tabela));
		return retorno;
	}

	public String quebrarTexto(String texto){
		List<String> strings = new ArrayList<String>();
		String retorno = "";
		strings.addAll(Arrays.asList(texto.split("(?<=\\G.{130})")));
		for (String str : strings){
			retorno = retorno.concat(str).concat("\n");
		}

		return retorno;
	}

}

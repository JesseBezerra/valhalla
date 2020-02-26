package br.com.jdsb.valhalla.sql.core.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.Date;

import br.com.jdsb.valhalla.sql.core.texto.StringUtil;

public class Arquivo {

	public void escreverArquivo(String texto,String supri) throws IOException, ParseException{
		StringUtil util = new StringUtil();
		StringBuilder diretorio = new StringBuilder();
		diretorio = diretorio.append("C:\\Empacotador\\");
		diretorio = diretorio.append(util.converteData(new Date()));
		diretorio = diretorio.append("_");
		diretorio = diretorio.append(supri);
		diretorio = diretorio.append("_SUPRI.sql");
		File file = new File(diretorio.toString());
		OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(file, true), "windows-1252"
                );
		writer.append(texto);
		writer.close();
	}

}

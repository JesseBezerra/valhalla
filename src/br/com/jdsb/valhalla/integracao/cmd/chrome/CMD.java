package br.com.jdsb.valhalla.integracao.cmd.chrome;

import java.io.IOException;

public class CMD {
 public static void executarComandoDoes(String comando){
	 try {
		Runtime.getRuntime().exec(comando);
	} catch (IOException e) {
		e.printStackTrace();
	}
 }

 public static void chamaChromeDoes(String comando){
	 executarComandoDoes("cmd.exe /C start chrome.exe ".concat(comando));
 }

 public static void chamaChromeDoesParametro(String comando){
	 executarComandoDoes("cmd.exe /c start chrome \"".concat(comando).concat("\""));
 }

}

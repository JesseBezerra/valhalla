package br.com.jdsb.valhalla.sql.core.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMysql {

	private static Connection connection;

	public static Connection getConnection() throws SQLException{
      if(connection==null || connection.isClosed()){
    	  connection = DriverManager.getConnection("jdbc:mysql://213.190.6.169:3306/u175132392_empacotador", "u175132392_empacotador", "francisco18");
      }

      return connection;
	}

	private static Connection connectionService;

	public static Connection getConnection(String host, String porta, String sid, String usuario, String senha,String snService) throws SQLException{
	      if(connectionService==null || connectionService.isClosed()){
	    	  String url = "jdbc:oracle:thin:@%s:%s:%s";
	    	  if(snService!=null && snService.equals("Sim")){
	    		  url = "jdbc:oracle:thin:@%s:%s/%s";
	    	  }
	    	  connectionService = DriverManager.getConnection(String.format(url, host,porta,sid), usuario, senha);
	      }
	      return connectionService;
    }

	public static Connection obterConexaoValida() throws SQLException{
	      return connectionService;
	}



}

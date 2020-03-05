package br.com.jdsb.valhalla.sql.core.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSystem {

	private static Connection connection;

	public static Connection getConnectio() throws SQLException{
      if(connection==null || connection.isClosed()){
    	  connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.103:1512:clnqsoul", "dbamv", "dbamv");
      }

      return connection;
	}

	private static Connection connectionService;

	public static Connection getConnection(String host, String porta, String sid, String usuario, String senha) throws SQLException{
	      if(connectionService==null || connectionService.isClosed()){
	    	  String url = "jdbc:oracle:thin:@%s:%s:%s";
	    	  connectionService = DriverManager.getConnection(String.format(url, host,porta,sid), usuario, senha);
	      }
	      return connectionService;
    }

	public static Connection obterConexaoValida() throws SQLException{
	      return connectionService;
	}



}

package br.com.jdsb.valhalla.sql.core.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static Connection connection;

	public static Connection getConnectio() throws SQLException{
      if(connection==null || connection.isClosed()){
    	  connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.103:1512:clnqsoul", "dbamv", "dbamv");
      }

      return connection;
	}

}

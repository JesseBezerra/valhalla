package br.com.jdsb.valhalla.sql.core.connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoLite {

	  public static void createNewDatabase(String fileName) {

	        String url = "jdbc:sqlite:C:/empacotador/valhalla/" + fileName;
	        if(!new File("C:/empacotador/valhalla/valhalla.db").exists()){
	        new File("C:/empacotador/valhalla/").mkdir();
	        try (Connection conn = DriverManager.getConnection(url)) {
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());
	                System.out.println("A new database has been created.");
	            }

	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        }else{
	        	System.out.println("diretorio ja existe");
	        }
	    }

	  public static void main(String[] args) {
		ConexaoLite conexaoLite = new ConexaoLite();
		conexaoLite.createNewDatabase("valhalla.db");
	}

	  private static Connection connection;

		public static Connection getConnection() throws SQLException{
	      if(!new File("C:/empacotador/valhalla/valhalla.db").exists()){
             createNewDatabase("valhalla.db");
	      }
		  if(connection==null || connection.isClosed()){
	    	  connection = DriverManager.getConnection("jdbc:sqlite:C:/empacotador/valhalla/valhalla.db");
	      }
	      return connection;
		}


}

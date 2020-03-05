package br.com.jdsb.valhalla.sql.core.comum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.jdsb.valhalla.sql.core.connection.ConnectionSystem;
import br.com.jdsb.valhalla.sql.core.texto.StringUtil;
import br.com.jdsb.valhalla.sql.core.writer.Arquivo;

public class Comum {

	public static void main(String[] args) {
		Comum comum = new Comum();
		try {
			String supri = "SUPRI-13334";
			StringBuilder arquivo = new StringBuilder();
			StringUtil util = new StringUtil();
			arquivo = arquivo.append(util.cabecalho(supri, "GERACAO DE SCRIPTS PARA TESTES ", "SUPRI", "DBAMV", "CONFIGEST"));
			arquivo = arquivo.append(comum.obtemColuna("SN_GERA_SC_SOLICITADO", "CONFIGEST", "DBAMV"));
			arquivo = arquivo.append(comum.obterConstrainte("SN_GERA_SC_SOLICITADO", "CONFIGEST", "DBAMV"));
			arquivo = arquivo.append(comum.obterComentario("SN_GERA_SC_SOLICITADO", "CONFIGEST", "DBAMV"));

			Arquivo escrita = new Arquivo();
			escrita.escreverArquivo(arquivo.toString(),supri);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String obterConstrainte(String coluna, String tabela, String owner) throws SQLException{
		String retorno = null;
		String consulta =   "   SELECT ACC.OWNER,                                       "+
							"	       ACC.COLUMN_NAME,                                 "+
							"	       ACC.TABLE_NAME,                                  "+
							"	       ACC.CONSTRAINT_NAME,                             "+
							"	       AC.CONSTRAINT_TYPE,                              "+
							"	       AC.SEARCH_CONDITION                              "+
							"	FROM ALL_CONS_COLUMNS ACC,                              "+
							"	     ALL_CONSTRAINTS AC                                 "+
							"	WHERE ACC.COLUMN_NAME = ?                               "+
							"	AND ACC.TABLE_NAME= ?                                   "+
							"	AND ACC.OWNER = ?                                       "+
							"	AND ACC.CONSTRAINT_NAME = AC.CONSTRAINT_NAME            "+
							"	AND ACC.TABLE_NAME = AC.TABLE_NAME                      "+
							"	AND AC.STATUS = 'ENABLED'                               "+
							"	AND ACC.CONSTRAINT_NAME NOT LIKE 'SYS%'                 ";
		   Connection con = ConnectionSystem.getConnectio();
	       PreparedStatement pstmt = con.prepareStatement(consulta);
	       pstmt.setString(1, coluna);
	       pstmt.setString(2, tabela);
	       pstmt.setString(3, owner);
	       ResultSet rs = pstmt.executeQuery();

	       if(rs!=null){
	    	   rs.next();
	    	   retorno = processaConstraint(rs).toString();
	       }
	       pstmt.close();

		return retorno;

	}

	public String obterComentario(String coluna, String tabela, String owner) throws SQLException{
		String retorno = null;

		String consulta =  "        SELECT OWNER,                                         "+
						   "		       TABLE_NAME,                                    "+
						   "		       COLUMN_NAME,                                   "+
						   "		       COMMENTS                                       "+
						   "		FROM ALL_COL_COMMENTS                                 "+
						   "		WHERE COLUMN_NAME = ?                                 "+
						   "		AND TABLE_NAME=?                                      "+
						   "		AND OWNER = ?                                         ";
		   Connection con = ConnectionSystem.getConnectio();
	       PreparedStatement pstmt = con.prepareStatement(consulta);
	       pstmt.setString(1, coluna);
	       pstmt.setString(2, tabela);
	       pstmt.setString(3, owner);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs!=null){
	    	   rs.next();
	    	   retorno = processaComentario(rs).toString();
	       }
	       pstmt.close();

		return retorno;
	}

	public String obtemColuna(String coluna, String tabela, String owner) throws SQLException{
       String retorno = null;

       String consulta = "  SELECT OWNER,                                 "+
						 "        TABLE_NAME,                              "+
						 "        COLUMN_NAME,                            "+
						 "        DATA_TYPE,                              "+
						 "        DATA_LENGTH,                            "+
						 "        NULLABLE,                               "+
						 "        DATA_DEFAULT                            "+
						 " FROM ALL_TAB_COLUMNS                           "+
						 " WHERE COLUMN_NAME = ?                          "+
						 " AND TABLE_NAME= ?                              "+
						 " AND OWNER = ?      	                          ";
       Connection con = ConnectionSystem.getConnectio();
       PreparedStatement pstmt = con.prepareStatement(consulta);
       pstmt.setString(1, coluna);
       pstmt.setString(2, tabela);
       pstmt.setString(3, owner);
       ResultSet rs = pstmt.executeQuery();
       if(rs!=null){
    	   rs.next();
    	   retorno = processaColuna(rs).toString();
       }
       pstmt.close();
       return retorno;
	}

	public StringBuilder processaComentario(ResultSet resultado) throws SQLException{
		StringBuilder retorno = new StringBuilder();
		retorno = retorno.append("COMMENT ON COLUMN ").append(resultado.getString("OWNER")).append(".").append(resultado.getString("TABLE_NAME")).append(".").append(resultado.getString("COLUMN_NAME")).append(" '").append(resultado.getString("COMMENTS")).append("'");
		retorno = retorno.append("\n");
		retorno = retorno.append("/ ");
		retorno = retorno.append("\n");
		return retorno;
	}

	public StringBuilder processaConstraint(ResultSet resultado) throws SQLException{
		StringBuilder retorno = new StringBuilder();
		retorno = retorno.append("ALTER TABLE ").append(resultado.getString("OWNER")).append(".").append(resultado.getString("TABLE_NAME"));
		if(resultado.getString("CONSTRAINT_TYPE").equals("C")){
			retorno = retorno.append("\n");
			retorno = retorno.append("ADD CONSTRAINT ").append(resultado.getString("CONSTRAINT_NAME")).append(" CHECK ( ");
			retorno = retorno.append(resultado.getString("SEARCH_CONDITION")).append(" ");
			retorno = retorno.append("\n");
			retorno = retorno.append(" ) ");
			retorno = retorno.append("\n");
			retorno = retorno.append("/ ");
			retorno = retorno.append("\n");
		}
		return retorno;
	}

	public StringBuilder processaColuna(ResultSet resultado) throws SQLException{
	  StringBuilder retorno = new StringBuilder();
	  retorno = retorno.append("ALTER TABLE ").append(resultado.getString("OWNER")).append(".").append(resultado.getString("TABLE_NAME")).append(" ");
	  retorno = retorno.append("ADD ");
	  retorno = retorno.append(" ( ").append(resultado.getString("COLUMN_NAME")).append(" ").append(resultado.getString("DATA_TYPE")).append("(").append(resultado.getBigDecimal("DATA_LENGTH")).append(")").append(" ");

      String dataDefault = resultado.getString("DATA_DEFAULT");
      if(dataDefault !=null){
    	  retorno = retorno.append(" DEFAULT ").append(dataDefault);
      }

      if(resultado.getString("NULLABLE")!=null && resultado.getString("NULLABLE").equals("N")){
     	 retorno = retorno.append(" NOT NULL ");
       }

      retorno = retorno.append(" ) ");
      retorno = retorno.append(" \n");
      retorno = retorno.append("/ ");
      retorno = retorno.append("\n");
      return retorno;
	}

}

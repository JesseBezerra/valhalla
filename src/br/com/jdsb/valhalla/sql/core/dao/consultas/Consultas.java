package br.com.jdsb.valhalla.sql.core.dao.consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConnectionSystem;
import br.com.jdsb.valhalla.sql.objects.conexao.Conexao;

public class Consultas {

	public List<String> listar(Conexao conexao) {
		 List<String> retorno = new ArrayList<String>();

	      String consulta = "SELECT CD_MULTI_EMPRESA || ' - ' || DS_MULTI_EMPRESA AS DS_MULTI_EMPRESA FROM DBAMV.MULTI_EMPRESAS ORDER BY CD_MULTI_EMPRESA";
	      try {
				Connection connection = ConnectionSystem.getConnection(conexao.getDsUrl(), conexao.getDsPorta(), conexao.getDsSid(), conexao.getDsUsuario(), conexao.getDsSenha());
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
                   retorno.add(rs.getString("DS_MULTI_EMPRESA"));
			    }
			    pstmt.close();
			    connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	public List<String> listarEstoques(Conexao conexao,String cdMultiEmpresa) {
		 List<String> retorno = new ArrayList<String>();

	      String consulta = "SELECT CD_ESTOQUE || ' - ' || DS_ESTOQUE AS DS_ESTOQUE FROM DBAMV.ESTOQUE WHERE CD_MULTI_EMPRESA = ? ORDER BY CD_ESTOQUE";
	      try {
				Connection connection = ConnectionSystem.getConnection(conexao.getDsUrl(), conexao.getDsPorta(), conexao.getDsSid(), conexao.getDsUsuario(), conexao.getDsSenha());
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, cdMultiEmpresa);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
                  retorno.add(rs.getString("DS_ESTOQUE"));
			    }
			    pstmt.close();
			    connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

}

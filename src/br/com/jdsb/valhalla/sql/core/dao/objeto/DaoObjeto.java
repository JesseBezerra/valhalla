package br.com.jdsb.valhalla.sql.core.dao.objeto;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.objects.objeto.Objeto;

public class DaoObjeto implements Dao<Objeto> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void criarTabela() {

		String comando = "CREATE TABLE IF NOT EXISTS OBJETO (CD_OBJETO INTEGER PRIMARY KEY AUTOINCREMENT,DS_OBJETO VARCHAR,SN_PADRAO VARCHAR DEFAULT 'N' , SN_ATIVO VARCHAR DEFAULT 'S' ) ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void salvar(Objeto t) {
		 String comando = "INSERT INTO OBJETO(DS_OBJETO,SN_ATIVO,SN_PADRAO) VALUES (?,?,?)";
         try {
 			Connection connection = ConexaoLite.getConnection();
 		    PreparedStatement pstmt = connection.prepareStatement(comando);
 		    pstmt.setString(1, t.getDsObjeto());
 		    pstmt.setString(2, t.getSnAtivo());
 		    pstmt.setString(3, t.getSnPadrao());
 		    pstmt.execute();
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}

	}

	@Override
	public Objeto consultar(String condicao) {
		Objeto retorno = null;
	      String consulta = "SELECT CD_OBJETO, DS_OBJETO, SN_ATIVO, SN_PADRAO FROM OBJETO WHERE DS_OBJETO = ?";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, condicao);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno =  new Objeto(BigInteger.valueOf(rs.getInt("CD_OBJETO")), rs.getString("DS_OBJETO"), rs.getString("SN_ATIVO"), rs.getString("SN_PADRAO"));
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public List<Objeto> listar() {
		 List<Objeto> retorno = new ArrayList<Objeto>();

	      String consulta = "SELECT CD_OBJETO, DS_OBJETO, SN_ATIVO, SN_PADRAO FROM OBJETO";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno.add(new Objeto(BigInteger.valueOf(rs.getInt("CD_OBJETO")), rs.getString("DS_OBJETO"), rs.getString("SN_ATIVO"), rs.getString("SN_PADRAO")));
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	public static void main(String[] args) {
		DaoObjeto daoObjeto = new DaoObjeto();
		daoObjeto.criarTabela();
	}

}

package br.com.jdsb.valhalla.sql.core.dao.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.core.connection.ConnectionMysql;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;

public class DaoUsuario implements Dao<Usuario> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void criarTabela() {
		String comando = "CREATE TABLE IF NOT EXISTS USUARIO (CD_USUARIO VARCHAR PRIMARY KEY,NM_USUARIO VARCHAR,TP_USUARIO VARCHAR , SN_ATIVO VARCHAR) ";
        try {
			Connection connection = ConnectionMysql.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void salvar(Usuario t) {
		String comando = "INSERT INTO USUARIO(CD_USUARIO,NM_USUARIO,TP_USUARIO,SN_ATIVO) VALUES (?,?,?,?)";
        try {
			Connection connection = ConnectionMysql.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, t.getCdUsuario());
		    pstmt.setString(2, t.getNmUsuario());
		    pstmt.setString(3, t.getTpPerfil());
		    pstmt.setString(4, t.getSnAtivo());
		    pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Usuario consultar(String condicao) {
		   Usuario retorno = null;
	      String consulta = "SELECT CD_USUARIO,NM_USUARIO,TP_USUARIO,SN_ATIVO FROM USUARIO WHERE CD_USUARIO = ?";
	      try {
				Connection connection = ConnectionMysql.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, condicao);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno = (new Usuario(rs.getString("CD_USUARIO"),rs.getString("NM_USUARIO"),rs.getString("TP_USUARIO"),rs.getString("SN_ATIVO")));
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	public Usuario consultarPorNome(String condicao) {
		   Usuario retorno = null;
	      String consulta = "SELECT CD_USUARIO,NM_USUARIO,TP_USUARIO,SN_ATIVO FROM USUARIO WHERE NM_USUARIO = ?";
	      try {
				Connection connection = ConnectionMysql.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, condicao);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno = (new Usuario(rs.getString("CD_USUARIO"),rs.getString("NM_USUARIO"),rs.getString("TP_USUARIO"),rs.getString("SN_ATIVO")));
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public List<Usuario> listar() {
		List<Usuario> retorno = new ArrayList<Usuario>();
	      String consulta = "SELECT CD_USUARIO,NM_USUARIO,TP_USUARIO,SN_ATIVO FROM USUARIO WHERE SN_ATIVO = 'Sim' ";
	      try {
				Connection connection = ConnectionMysql.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno.add(new Usuario(rs.getString("CD_USUARIO"),rs.getString("NM_USUARIO"),rs.getString("TP_USUARIO"),rs.getString("SN_ATIVO")));
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public void atualizar(Usuario t) {
		String comando = "UPDATE USUARIO SET NM_USUARIO = ?, TP_USUARIO = ?, SN_ATIVO = ? WHERE CD_USUARIO = ? ";
        try {
			Connection connection = ConnectionMysql.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, t.getNmUsuario());
		    pstmt.setString(2, t.getTpPerfil());
		    pstmt.setString(3, t.getSnAtivo());
		    pstmt.setString(4, t.getCdUsuario());
		    pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void remover(Usuario t) {
		String comando = "DELETE FROM USUARIO WHERE CD_USUARIO = ? ";
        try {
			Connection connection = ConnectionMysql.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, t.getCdUsuario());
		    pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean validaPadrao(Usuario t) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		DaoUsuario daoUsuario = new DaoUsuario();
		daoUsuario.criarTabela();
	}

}

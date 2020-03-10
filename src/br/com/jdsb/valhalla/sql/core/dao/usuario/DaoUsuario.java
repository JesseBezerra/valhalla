package br.com.jdsb.valhalla.sql.core.dao.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
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
			Connection connection = ConexaoLite.getConnection();
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
			Connection connection = ConexaoLite.getConnection();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Usuario t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Usuario t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validaPadrao(Usuario t) {
		// TODO Auto-generated method stub
		return false;
	}

}

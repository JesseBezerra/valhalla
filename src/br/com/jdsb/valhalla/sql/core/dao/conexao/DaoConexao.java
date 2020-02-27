package br.com.jdsb.valhalla.sql.core.dao.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.objects.conexao.Conexao;

public class DaoConexao implements Dao<Conexao> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	Connection con;

	public DaoConexao() {
		try {
			con = ConexaoLite.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void criarTabela() {
		String comando = "CREATE TABLE IF NOT EXISTS CONEXAO(CD_CONEXAO INTEGER PRIMARY KEY AUTOINCREMENT, DS_CONEXAO VARCHAR,TP_CONEXAO VARCHAR,SN_CLIENTE VARCHAR,DS_URL VARCHAR,DS_PORTA VARCHAR,DS_SID VARCHAR,DS_USUARIO VARCHAR,DS_SENHA VARCHAR, SN_ATIVO VARCHAR)";
        try {
        	PreparedStatement pstmt = con.prepareStatement(comando);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void salvar(Conexao t) {
		String comando = "INSERT INTO PREFIXO(DS_CONEXAO,TP_CONEXAO,SN_CLIENTE,DS_URL,DS_PORTA,DS_SID,DS_USUARIO,DS_SENHA, SN_ATIVO) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
        	PreparedStatement pstmt = con.prepareStatement(comando);
        	pstmt.setString(1, t.getDsConexao());
        	pstmt.setString(2, t.getTpConexao());
        	pstmt.setString(3, t.isCliente() ? "Sim":"Não");
        	pstmt.setString(4, t.getDsUrl());
        	pstmt.setString(5, t.getDsPorta());
        	pstmt.setString(5, t.getDsSid());
        	pstmt.setString(5, t.getDsUsuario());
        	pstmt.setString(5, t.getDsSenha());
        	pstmt.setString(5, t.getSnAtivo());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Conexao consultar(String condicao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Conexao> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		Dao<Conexao> dao = new DaoConexao();
		dao.criarTabela();
	}

}

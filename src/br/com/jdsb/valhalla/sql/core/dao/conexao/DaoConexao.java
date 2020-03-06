package br.com.jdsb.valhalla.sql.core.dao.conexao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
			e.printStackTrace();
		}
	}

	@Override
	public void criarTabela() {
		String comando = "CREATE TABLE IF NOT EXISTS CONEXAO(CD_CONEXAO INTEGER PRIMARY KEY AUTOINCREMENT, DS_CONEXAO VARCHAR,TP_CONEXAO VARCHAR,SN_CLIENTE VARCHAR,DS_URL VARCHAR,DS_PORTA VARCHAR,DS_SID VARCHAR,DS_USUARIO VARCHAR,DS_SENHA VARCHAR, SN_ATIVO VARCHAR, SN_SERVICE VARCHAR)";
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
		String comando = "INSERT INTO CONEXAO(DS_CONEXAO,TP_CONEXAO,SN_CLIENTE,DS_URL,DS_PORTA,DS_SID,DS_USUARIO,DS_SENHA, SN_ATIVO, SN_SERVICE) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
        	PreparedStatement pstmt = con.prepareStatement(comando);
        	pstmt.setString(1, t.getDsConexao());
        	pstmt.setString(2, t.getTpConexao());
        	pstmt.setString(3, t.getSnCliente());
        	pstmt.setString(4, t.getDsUrl());
        	pstmt.setString(5, t.getDsPorta());
        	pstmt.setString(6, t.getDsSid());
        	pstmt.setString(7, t.getDsUsuario());
        	pstmt.setString(8, t.getDsSenha());
        	pstmt.setString(9, t.getSnAtivo());
        	pstmt.setString(10, t.getSnService());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Conexao consultar(String condicao) {
		Conexao retorno = null ;

	      String consulta = "SELECT CD_CONEXAO,DS_CONEXAO,TP_CONEXAO,SN_CLIENTE,DS_URL,DS_PORTA,DS_SID,DS_USUARIO,DS_SENHA, SN_ATIVO,SN_SERVICE FROM CONEXAO WHERE DS_CONEXAO = ?  ";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, condicao);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno =  (new Conexao(BigInteger.valueOf(rs.getInt("CD_CONEXAO")), rs.getString("DS_CONEXAO"), rs.getString("DS_URL"), rs.getString("DS_PORTA"),rs.getString("DS_SID"), rs.getString("TP_CONEXAO"), rs.getString("DS_USUARIO"), rs.getString("DS_SENHA"), rs.getString("SN_CLIENTE"),rs.getString("SN_ATIVO"),rs.getString("SN_SERVICE")));
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public List<Conexao> listar() {
		 List<Conexao> retorno = new ArrayList<Conexao>();

	      String consulta = "SELECT CD_CONEXAO,DS_CONEXAO,TP_CONEXAO,SN_CLIENTE,DS_URL,DS_PORTA,DS_SID,DS_USUARIO,DS_SENHA, SN_ATIVO,SN_SERVICE FROM CONEXAO ";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno.add(new Conexao(BigInteger.valueOf(rs.getInt("CD_CONEXAO")), rs.getString("DS_CONEXAO"), rs.getString("DS_URL"), rs.getString("DS_PORTA"),rs.getString("DS_SID"), rs.getString("TP_CONEXAO"), rs.getString("DS_USUARIO"), rs.getString("DS_SENHA"), rs.getString("SN_CLIENTE"),rs.getString("SN_ATIVO"),rs.getString("SN_SERVICE")));
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	public static void main(String[] args) {
		Dao<Conexao> dao = new DaoConexao();
		dao.criarTabela();
		dao.salvar(new Conexao(null, "V_60D", "phoenix", "1521", "v60d", "Executar", "dbamv", "dbamv", "Não", "Sim","Não"));
		for(Conexao conexao:dao.listar()){
            System.out.println(conexao);
		}
	}

	@Override
	public void atualizar(Conexao t) {
		String comando = "UPDATE CONEXAO SET DS_CONEXAO = ?,TP_CONEXAO = ?,SN_CLIENTE = ?,DS_URL = ? ,DS_PORTA= ?,DS_SID = ?,DS_USUARIO = ?,DS_SENHA = ?, SN_ATIVO = ?, SN_SERVICE=? WHERE CD_CONEXAO = ? ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, t.getDsConexao());
        	pstmt.setString(2, t.getTpConexao());
        	pstmt.setString(3, t.getSnCliente());
        	pstmt.setString(4, t.getDsUrl());
        	pstmt.setString(5, t.getDsPorta());
        	pstmt.setString(6, t.getDsSid());
        	pstmt.setString(7, t.getDsUsuario());
        	pstmt.setString(8, t.getDsSenha());
        	pstmt.setString(9, t.getSnAtivo());
        	pstmt.setString(10, t.getSnService());
        	pstmt.setLong(11, t.getCdConexao().longValue());
		    pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remover(Conexao t) {
		String comando = "DELETE FROM CONEXAO WHERE CD_CONEXAO = ? ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setLong(1, t.getCdConexao().longValue());
		    pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean validaPadrao(Conexao t) {
		return false;
	}

}

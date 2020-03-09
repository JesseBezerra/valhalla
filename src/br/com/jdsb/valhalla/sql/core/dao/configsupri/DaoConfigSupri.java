package br.com.jdsb.valhalla.sql.core.dao.configsupri;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.objects.configsupri.ConfigSupri;

public class DaoConfigSupri implements Dao<ConfigSupri> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void criarTabela() {

		String comando = "CREATE TABLE IF NOT EXISTS CONFIG_SUPRI (CD_CONFIG_SUPRI INTEGER PRIMARY KEY AUTOINCREMENT, "
				                                                + "CD_CONEXAO INTEGER, "
				                                                + "CD_MULTI_EMPRESA VARCHAR, "
				                                                + "CD_ESTOQUE VARCHAR, "
				                                                + "CD_ESPECIE VARCHAR, "
				                                                + "CD_CLASSE VARCHAR, "
				                                                + "CD_SUB_CLASSE VARCHAR, "
				                                                + "QT_SALDO_PADRAO VARCHAR,"
				                                                + "DS_UNIDADE_PADRAO "
				                                                + ") ";
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
	public void salvar(ConfigSupri t) {
		String comando = "INSERT INTO CONFIG_SUPRI(CD_CONEXAO,CD_MULTI_EMPRESA,CD_ESTOQUE,CD_ESPECIE,CD_CLASSE,CD_SUB_CLASSE,QT_SALDO_PADRAO,DS_UNIDADE_PADRAO) VALUES (?,?,?,?,?,?,?,?)";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setLong(1, t.getCdConexao().longValue());
		    pstmt.setString(2, t.getCdMultiEmpresa());
		    pstmt.setString(3, t.getCdEstoque());
		    pstmt.setString(4, t.getCdEspec());
		    pstmt.setString(5, t.getCdClass());
		    pstmt.setString(6, t.getCdSubClass());
		    pstmt.setString(7, t.getQtSaldoPadrao());
		    pstmt.setString(8, t.getDsUnidadePadrao());
		    pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	@Override
	public ConfigSupri consultar(String condicao) {
		ConfigSupri retorno = null;

	      String consulta = "SELECT CD_CONFIG_SUPRI,CD_CONEXAO,CD_MULTI_EMPRESA,CD_ESTOQUE,CD_ESPECIE,CD_CLASSE,CD_SUB_CLASSE,QT_SALDO_PADRAO,DS_UNIDADE_PADRAO FROM CONFIG_SUPRI WHERE CD_CONEXAO = ?";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setLong(1, Long.parseLong(condicao));
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno = (new ConfigSupri(new BigInteger(rs.getString("CD_CONFIG_SUPRI")), new BigInteger(rs.getString("CD_CONEXAO")), rs.getString("CD_MULTI_EMPRESA"), rs.getString("CD_ESTOQUE"), rs.getString("CD_ESPECIE"), rs.getString("CD_CLASSE"), rs.getString("CD_SUB_CLASSE"), rs.getString("QT_SALDO_PADRAO"), rs.getString("DS_UNIDADE_PADRAO")));
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public List<ConfigSupri> listar() {
		List<ConfigSupri> retorno = new ArrayList<ConfigSupri>();

	      String consulta = "SELECT CD_CONFIG_SUPRI,CD_CONEXAO,CD_MULTI_EMPRESA,CD_ESTOQUE,CD_ESPECIE,CD_CLASSE,CD_SUB_CLASSE,QT_SALDO_PADRAO,DS_UNIDADE_PADRAO FROM CONFIG_SUPRI";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno.add(new ConfigSupri(new BigInteger(rs.getString("CD_CONFIG_SUPRI")), new BigInteger(rs.getString("CD_CONEXAO")), rs.getString("CD_MULTI_EMPRESA"), rs.getString("CD_ESTOQUE"), rs.getString("CD_ESPECIE"), rs.getString("CD_CLASSE"), rs.getString("CD_SUB_CLASSE"), rs.getString("QT_SALDO_PADRAO"), rs.getString("DS_UNIDADE_PADRAO")));
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public void atualizar(ConfigSupri t) {
		String comando = "UPDATE CONFIG_SUPRI SET CD_CONEXAO = ? ,CD_MULTI_EMPRESA = ? ,CD_ESTOQUE = ? ,CD_ESPECIE = ? ,CD_CLASSE = ?,CD_SUB_CLASSE = ? ,QT_SALDO_PADRAO = ? ,DS_UNIDADE_PADRAO= ? WHERE CD_CONEXAO = ?";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setLong(1, t.getCdConexao().longValue());
		    pstmt.setString(2, t.getCdMultiEmpresa());
		    pstmt.setString(3, t.getCdEstoque());
		    pstmt.setString(4, t.getCdEspec());
		    pstmt.setString(5, t.getCdClass());
		    pstmt.setString(6, t.getCdSubClass());
		    pstmt.setString(7, t.getQtSaldoPadrao());
		    pstmt.setString(8, t.getDsUnidadePadrao());
		    pstmt.setLong(9, t.getCdConexao().longValue());
		    pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void remover(ConfigSupri t) {
		String comando = "DELETE FROM CONFIG_SUPRI WHERE CD_CONFIG_SUPRI = ? ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setLong(1, t.getCdConfigSupri().longValue());
		    pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean validaPadrao(ConfigSupri t) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		DaoConfigSupri daoConfigSupri = new DaoConfigSupri();
		daoConfigSupri.criarTabela();
		//daoConfigSupri.salvar(new ConfigSupri(new BigInteger("1"), new BigInteger("1"),"1", "66", "1", "1", "1", "1000", "AMP"));
		for(ConfigSupri supri: daoConfigSupri.listar()){
			System.out.println(supri);
		}

		ConfigSupri supri = daoConfigSupri.consultar("1");
		System.out.println(supri);

		//daoConfigSupri.remover(supri);

	}

}

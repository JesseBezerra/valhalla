package br.com.jdsb.valhalla.sql.core.dao.estoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.objects.estoque.Estoque;

public class DaoEstoque implements Dao<Estoque> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void criarTabela() {
		String comando = "CREATE TABLE IF NOT EXISTS ESTOQUE (CD_ESTOQUE VARCHAR,DS_ESTOQUE VARCHAR,CD_CONFIG_SUPRI VARCHAR) ";
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
	public void salvar(Estoque t) {
		String comando = "INSERT INTO ESTOQUE(CD_ESTOQUE,DS_ESTOQUE,CD_CONFIG_SUPRI) VALUES (?,?,?)";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, t.getCdEstoque());
		    pstmt.setString(2, t.getDsEstoque());
		    pstmt.setString(3, t.getCdConfigSupri());
		    pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Estoque consultar(String condicao) {
		Estoque retorno = null;

	      String consulta = "SELECT CD_ESTOQUE,DS_ESTOQUE,CD_CONFIG_SUPRI FROM ESTOQUE WHERE CD_ESTOQUE = ?";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, condicao);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno = (new Estoque(rs.getString("CD_ESTOQUE"), rs.getString("DS_ESTOQUE"), rs.getString("CD_CONFIG_SUPRI")));
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public List<Estoque> listar() {
		List<Estoque> retorno = new ArrayList<Estoque>();

	      String consulta = "SELECT CD_ESTOQUE,DS_ESTOQUE,CD_CONFIG_SUPRI FROM ESTOQUE";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno.add(new Estoque(rs.getString("CD_ESTOQUE"), rs.getString("DS_ESTOQUE"), rs.getString("CD_CONFIG_SUPRI")));
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public void atualizar(Estoque t) {
		String comando = "UPDATE ESTOQUE SET DS_ESTOQUE = ?, CD_CONFIG_SUPRI = ? WHERE CD_ESTOQUE = ? ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, t.getDsEstoque());
		    pstmt.setString(2, t.getCdConfigSupri());
		    pstmt.setString(3, t.getCdEstoque());
		    pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void remover(Estoque t) {
		String comando = "DELETE FROM ESTOQUE WHERE CD_ESTOQUE = ? ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, t.getCdEstoque());
		    pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean validaPadrao(Estoque t) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		DaoEstoque dao = new DaoEstoque();
		dao.criarTabela();
		dao.salvar(new Estoque("1", "TESTE", "1"));
        for(Estoque estoque:dao.listar()){
        	System.out.println(estoque);
        }

        Estoque estoque = dao.consultar("1");
        System.out.println(estoque);
        dao.remover(estoque);
	}

}

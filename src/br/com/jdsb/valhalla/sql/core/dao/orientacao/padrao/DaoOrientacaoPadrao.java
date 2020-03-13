package br.com.jdsb.valhalla.sql.core.dao.orientacao.padrao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.orientacao.ticket.DaoOrientacaoTicket;
import br.com.jdsb.valhalla.sql.objects.orientacao.padrao.OrientacaoPadrao;

public class DaoOrientacaoPadrao implements Dao<OrientacaoPadrao> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void criarTabela() {
		String comando = "CREATE TABLE IF NOT EXISTS ORIENTACAO_PADRAO (CD_ORIENTACAO_PADRAO INTEGER PRIMARY KEY AUTOINCREMENT,NM_ORIENTACAO_PADRAO VARCHAR,DS_ORIENTACAO_PADRAO VARCHAR,SN_ATIVO VARCHAR DEFAULT 'Sim') ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void salvar(OrientacaoPadrao t) {
		 String comando = "INSERT INTO ORIENTACAO_PADRAO(NM_ORIENTACAO_PADRAO,DS_ORIENTACAO_PADRAO,SN_ATIVO) VALUES (?,?,?)";
         try {
 			Connection connection = ConexaoLite.getConnection();
 		    PreparedStatement pstmt = connection.prepareStatement(comando);
 		    pstmt.setString(1, t.getNmOrientacaoPadrao());
 		    pstmt.setString(2, t.getDsOrientacaoPadrao());
 		    pstmt.setString(3, t.getSnAtivo());
 		    pstmt.execute();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}

	}

	@Override
	public OrientacaoPadrao consultar(String condicao) {
		OrientacaoPadrao retorno = null;

	      String consulta = "SELECT CD_ORIENTACAO_PADRAO, NM_ORIENTACAO_PADRAO,DS_ORIENTACAO_PADRAO,SN_ATIVO FROM ORIENTACAO_PADRAO WHERE NM_ORIENTACAO_PADRAO = ?";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, condicao);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno = (new OrientacaoPadrao(BigInteger.valueOf(rs.getInt("CD_ORIENTACAO_PADRAO")), rs.getString("DS_ORIENTACAO_PADRAO"), rs.getString("SN_ATIVO"), rs.getString("SN_PADRAO")));
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public List<OrientacaoPadrao> listar() {
		List<OrientacaoPadrao> retorno = new ArrayList<OrientacaoPadrao>();

	      String consulta = "SELECT CD_ORIENTACAO_PADRAO, NM_ORIENTACAO_PADRAO,DS_ORIENTACAO_PADRAO,SN_ATIVO FROM ORIENTACAO_PADRAO WHERE SN_ATIVO = 'Sim'";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno.add(new OrientacaoPadrao(BigInteger.valueOf(rs.getInt("CD_ORIENTACAO_PADRAO")), rs.getString("NM_ORIENTACAO_PADRAO"), rs.getString("DS_ORIENTACAO_PADRAO"), rs.getString("SN_ATIVO")));
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public void atualizar(OrientacaoPadrao t) {
		String comando = "UPDATE OBJETO SET NM_ORIENTACAO_PADRAO = ? ,DS_ORIENTACAO_PADRAO = ?,SN_ATIVO = ? WHERE ORIENTACAO_PADRAO = ? ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, t.getNmOrientacaoPadrao());
		    pstmt.setString(2, t.getDsOrientacaoPadrao());
		    pstmt.setString(3, t.getSnAtivo());
		    pstmt.setLong(4, t.getCdOrientacaoPadrao().longValue());
		    pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void remover(OrientacaoPadrao t) {
		String comando = "DELETE FROM ORIENTACAO_PADRAO WHERE ORIENTACAO_PADRAO = ? ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setLong(1, t.getCdOrientacaoPadrao().longValue());
		    pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean validaPadrao(OrientacaoPadrao t) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
            DaoOrientacaoPadrao daoOrientacaoPadrao = new DaoOrientacaoPadrao();
            daoOrientacaoPadrao.criarTabela();

            DaoOrientacaoTicket daoOrientacaoTicket = new DaoOrientacaoTicket();
            daoOrientacaoTicket.criarTabela();
	}


}

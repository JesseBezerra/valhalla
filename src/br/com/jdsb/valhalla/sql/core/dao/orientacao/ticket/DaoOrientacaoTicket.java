package br.com.jdsb.valhalla.sql.core.dao.orientacao.ticket;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.objects.orientacao.ticket.OrientacaoTicket;

public class DaoOrientacaoTicket implements Dao<OrientacaoTicket>{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void criarTabela() {
		String comando = "CREATE TABLE IF NOT EXISTS ORIENTACAO_TICKET(CD_ORIENTACAO INTEGER PRIMARY KEY AUTOINCREMENT,CD_TICKET VARCHAR,DS_ORIENTACAO VARCHAR) ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void salvar(OrientacaoTicket t) {
		 String comando = "INSERT INTO ORIENTACAO_TICKET(CD_TICKET,DS_ORIENTACAO) VALUES (?,?)";
         try {
 			Connection connection = ConexaoLite.getConnection();
 		    PreparedStatement pstmt = connection.prepareStatement(comando);
 		    pstmt.setString(1, t.getCdTicket());
 		    pstmt.setString(2, t.getDsOrientacao());
 		    pstmt.execute();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}

	}

	@Override
	public OrientacaoTicket consultar(String condicao) {
		OrientacaoTicket retorno = null;

	      String consulta = "SELECT CD_ORIENTACAO, CD_TICKET,DS_ORIENTACAO FROM ORIENTACAO_TICKET WHERE CD_TICKET = ?";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, condicao);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno = (new OrientacaoTicket(BigInteger.valueOf(rs.getInt("CD_ORIENTACAO")), rs.getString("CD_TICKET"), rs.getString("DS_ORIENTACAO")));
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public List<OrientacaoTicket> listar() {
		List<OrientacaoTicket> retorno = new ArrayList<OrientacaoTicket>();

	      String consulta = "SELECT CD_ORIENTACAO, CD_TICKET,DS_ORIENTACAO FROM ORIENTACAO_TICKET";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno.add(new OrientacaoTicket(BigInteger.valueOf(rs.getInt("CD_ORIENTACAO")), rs.getString("CD_TICKET"), rs.getString("DS_ORIENTACAO")));
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public void atualizar(OrientacaoTicket t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(OrientacaoTicket t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validaPadrao(OrientacaoTicket t) {
		// TODO Auto-generated method stub
		return false;
	}

}

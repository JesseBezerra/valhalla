package br.com.jdsb.valhalla.sql.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.objects.ticket.Ticket;

public class DaoTicket implements Dao<Ticket> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void criarTabela() {
		String comando = "CREATE TABLE IF NOT EXISTS TICKET (CD_TICKET VARCHAR PRIMARY KEY ,DS_TICKET VARCHAR,CD_MODULO VARCHAR,DS_PROBLEMA VARCHAR, RELEASE_NOTES VARCHAR, SN_ATIVO VARCHAR DEFAULT 'S' ) ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void salvar(Ticket t) {
		 String comando = "INSERT INTO TICKET(CD_TICKET,DS_TICKET,CD_MODULO,DS_PROBLEMA,RELEASE_NOTES,SN_ATIVO) VALUES (?,?,?,?,?,?)";
         try {
 			Connection connection = ConexaoLite.getConnection();
 		    PreparedStatement pstmt = connection.prepareStatement(comando);
 		    pstmt.setString(1, t.getCdTicket());
 		    pstmt.setString(2, t.getDsTicket());
 		    pstmt.setString(3, t.getCdModulo());
 		    pstmt.setString(4, t.getDsProblema());
		    pstmt.setString(5, t.getReleaseNotes());
		    pstmt.setString(6, t.getSnAtivo());
 		    pstmt.execute();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}

	}

	@Override
	public Ticket consultar(String condicao) {
		Ticket retorno = null;

	      String consulta = "SELECT CD_TICKET,DS_TICKET,CD_MODULO,DS_PROBLEMA,RELEASE_NOTES,SN_ATIVO FROM TICKET WHERE CD_TICKET = ?";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, condicao);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno = (new Ticket(rs.getString("CD_TICKET"), rs.getString("DS_TICKET"), rs.getString("CD_MODULO"), rs.getString("DS_TICKET"), rs.getString("DS_PROBLEMA"), rs.getString("RELEASE_NOTES"), rs.getString("SN_ATIVO")));
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public List<Ticket> listar() {
		List<Ticket> retorno = new ArrayList<Ticket>();

	      String consulta = "SELECT CD_TICKET,DS_TICKET,CD_MODULO,DS_PROBLEMA,RELEASE_NOTES,SN_ATIVO FROM TICKET";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno.add(new Ticket(rs.getString("CD_TICKET"), rs.getString("DS_TICKET"), rs.getString("CD_MODULO"), rs.getString("DS_TICKET"), rs.getString("DS_PROBLEMA"), rs.getString("RELEASE_NOTES"), rs.getString("SN_ATIVO")));
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public void atualizar(Ticket t) {
		String comando = "UPDATE TICKET SET DS_TICKET = ? ,CD_MODULO = ? ,DS_PROBLEMA= ? ,RELEASE_NOTES = ?,SN_ATIVO= ?  WHERE CD_TICKET = ? ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, t.getDsTicket());
		    pstmt.setString(2, t.getCdModulo());
		    pstmt.setString(3, t.getDsProblema());
		    pstmt.setString(4, t.getReleaseNotes());
		    pstmt.setString(5, t.getSnAtivo());
		    pstmt.setString(6, t.getCdTicket());
		    pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void remover(Ticket t) {
		String comando = "DELETE FROM TICKET WHERE CD_TICKET = ? ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, t.getCdTicket());
		    pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean validaPadrao(Ticket t) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		DaoTicket daoTicket = new DaoTicket();
		daoTicket.criarTabela();

		for(Ticket ticket:daoTicket.listar()){
			System.out.println(ticket);
		}

		if(daoTicket.consultar("SUPRI-1213")!=null){
			System.out.println("TEM");
		}else{
			System.out.println("NAO TEM");
		}
	}

}

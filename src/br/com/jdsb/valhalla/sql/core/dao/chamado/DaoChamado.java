package br.com.jdsb.valhalla.sql.core.dao.chamado;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.objects.chamado.Chamado;
import br.com.jdsb.valhalla.sql.objects.objeto.Objeto;

public class DaoChamado implements Dao<Chamado> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * CD_TICKET,
	 * DS_TICKET,
	 * CD_USUARIO,
	 * DS_OBSERVACAO,
	 * SN_ATIVO,
	 * TOTAL_MINUTOS_TRABALHADOS,
	 * TOTAL_PERCENTUAL_CONCLUSAO
	 * CD_TICKET_ASSOCIADO
	 */

	@Override
	public void criarTabela() {
		String comando = "CREATE TABLE IF NOT EXISTS CHAMADO (CD_TICKET VARCHAR PRIMARY KEY, DS_TICKET VARCHAR,CD_USUARIO VARCHAR ,DS_OBSERVACAO VARCHAR, SN_ATIVO VARCHAR,TOTAL_MINUTOS_TRABALHADOS INTEGER,TOTAL_PERCENTUAL_CONCLUSAO INTEGER,CD_TICKET_ASSOCIADO VARCHAR,SN_PRIORITARIO VARCHAR, NR_ORDEM_PRIORIDADE VARCHAR)";
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
	public void salvar(Chamado t) {

		/**
		 * CD_TICKET,
		 * DS_TICKET,
		 * CD_USUARIO,
		 * DS_OBSERVACAO,
		 * SN_ATIVO,
		 * TOTAL_MINUTOS_TRABALHADOS,
		 * TOTAL_PERCENTUAL_CONCLUSAO
		 * CD_TICKET_ASSOCIADO
		 * SN_PRIORITARIO,
		 * NR_ORDEM_PRIORIDADE
		 */

		 String comando = "INSERT INTO CHAMADO(CD_TICKET,"
		 		                            + "DS_TICKET,"
		 		                            + "CD_USUARIO,"
		 		                            + "DS_OBSERVACAO,"
		 		                            + "SN_ATIVO,"
		 		                            + "TOTAL_MINUTOS_TRABALHADOS,"
		 		                            + "TOTAL_PERCENTUAL_CONCLUSAO"
		 		                            + "CD_TICKET_ASSOCIADO,"
		 		                            + "SN_PRIORITARIO,"
		 		                            + "NR_ORDEM_PRIORIDADE) VALUES (?,?,?,?,?,?,?,?,?,?)";
         try {
 			Connection connection = ConexaoLite.getConnection();
 		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, t.getCdTicket());
 		    pstmt.setString(2, t.getDsTicket());
 		    pstmt.setString(3, t.getCdUsuario());
 		    pstmt.setString(4, t.getDsObservacao());
 		    pstmt.setString(5, t.getSnAtivo());
 		    pstmt.setLong(6, t.getTotalMinutosTrabalhados().longValue());
 		    pstmt.setLong(7, t.getTotalPercentualConclusao().longValue());
 		    pstmt.setString(8, t.getCdTicketAssociado());
 		    pstmt.setString(9, t.getSnPrioritario());
 		    pstmt.setString(10, t.getNrOrdemPrioridade());
 		    pstmt.execute();
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}

	}

	@Override
	public Chamado consultar(String condicao) {
		Chamado retorno = null;

	      String consulta = "SELECT CD_TICKET,DS_TICKET,CD_USUARIO,DS_OBSERVACAO,SN_ATIVO,TOTAL_MINUTOS_TRABALHADOS,TOTAL_PERCENTUAL_CONCLUSAO,CD_TICKET_ASSOCIADO,SN_PRIORITARIO, NR_ORDEM_PRIORIDADE FROM CHAMADO WHERE CD_TICKET = ?";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, condicao);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno = (new Chamado(rs.getString("CD_TICKET"),
			    			                rs.getString("DS_TICKET"),
			    			                rs.getString("CD_USUARIO"),
			    			                rs.getString("DS_OBSERVACAO"),
			    			                rs.getString("SN_ATIVO"),
			    			                new BigInteger(rs.getString("TOTAL_MINUTOS_TRABALHADOS")),
			    			                new BigInteger(rs.getString("TOTAL_PERCENTUAL_CONCLUSAO")),
			    			                rs.getString("CD_TICKET_ASSOCIADO"),
			    			                rs.getString("SN_PRIORITARIO"),
			    			                rs.getString("NR_ORDEM_PRIORIDADE")

			    			));
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public List<Chamado> listar() {
		List<Chamado> retorno = new ArrayList<Chamado>();

	      String consulta = "SELECT CD_TICKET,DS_TICKET,CD_USUARIO,DS_OBSERVACAO,SN_ATIVO,TOTAL_MINUTOS_TRABALHADOS,TOTAL_PERCENTUAL_CONCLUSAO,CD_TICKET_ASSOCIADO,SN_PRIORITARIO, NR_ORDEM_PRIORIDADE FROM CHAMADO ";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno.add(new Chamado(rs.getString("CD_TICKET"),
			    			                rs.getString("DS_TICKET"),
			    			                rs.getString("CD_USUARIO"),
			    			                rs.getString("DS_OBSERVACAO"),
			    			                rs.getString("SN_ATIVO"),
			    			                new BigInteger(rs.getString("TOTAL_MINUTOS_TRABALHADOS")),
			    			                new BigInteger(rs.getString("TOTAL_PERCENTUAL_CONCLUSAO")),
			    			                rs.getString("CD_TICKET_ASSOCIADO"),
			    			                rs.getString("SN_PRIORITARIO"),
			    			                rs.getString("NR_ORDEM_PRIORIDADE")

			    			));
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public void atualizar(Chamado t) {
		String comando = "UPDATE CHAMADO SET DS_TICKET = ? ,CD_USUARIO = ?,DS_OBSERVACAO = ? ,SN_ATIVO = ? ,TOTAL_MINUTOS_TRABALHADOS = ?,TOTAL_PERCENTUAL_CONCLUSAO = ?,CD_TICKET_ASSOCIADO = ?,SN_PRIORITARIO = ?, NR_ORDEM_PRIORIDADE = ? WHERE CD_TICKET = ? ";
		 try {
	 			Connection connection = ConexaoLite.getConnection();
	 		    PreparedStatement pstmt = connection.prepareStatement(comando);
	 		    pstmt.setString(1, t.getDsTicket());
	 		    pstmt.setString(2, t.getCdUsuario());
	 		    pstmt.setString(3, t.getDsObservacao());
	 		    pstmt.setString(4, t.getSnAtivo());
	 		    pstmt.setLong(5, t.getTotalMinutosTrabalhados().longValue());
	 		    pstmt.setLong(6, t.getTotalPercentualConclusao().longValue());
	 		    pstmt.setString(7, t.getCdTicketAssociado());
	 		    pstmt.setString(8, t.getSnPrioritario());
	 		    pstmt.setString(9, t.getNrOrdemPrioridade());
	 		    pstmt.setString(10, t.getCdTicket());
	 		    pstmt.execute();
	 		} catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	}

	@Override
	public void remover(Chamado t) {
		String comando = "DELETE FROM CHAMADO WHERE CD_TICKET = ? ";
        try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, t.getCdTicket());
		    pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean validaPadrao(Chamado t) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		DaoChamado chamado = new DaoChamado();
		chamado.criarTabela();
	}

}

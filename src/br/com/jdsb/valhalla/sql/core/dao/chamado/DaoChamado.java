package br.com.jdsb.valhalla.sql.core.dao.chamado;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.core.connection.ConnectionMysql;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.texto.StringUtil;
import br.com.jdsb.valhalla.sql.objects.chamado.Chamado;

public class DaoChamado implements Dao<Chamado> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));
	StringUtil util;
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

	public DaoChamado() {
		util = new StringUtil();
	}

	@Override
	public void criarTabela() {
		String comando = "CREATE TABLE IF NOT EXISTS CHAMADO (CD_TICKET VARCHAR PRIMARY KEY, DS_TICKET VARCHAR,CD_USUARIO VARCHAR ,DS_OBSERVACAO VARCHAR, SN_ATIVO VARCHAR,TOTAL_MINUTOS_TRABALHADOS INTEGER,TOTAL_PERCENTUAL_CONCLUSAO INTEGER,CD_TICKET_ASSOCIADO VARCHAR,SN_PRIORITARIO VARCHAR, NR_ORDEM_PRIORIDADE VARCHAR)";
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
		 		                            + "TOTAL_PERCENTUAL_CONCLUSAO,"
		 		                            + "CD_TICKET_ASSOCIADO,"
		 		                            + "SN_PRIORITARIO,"
		 		                            + "NR_ORDEM_PRIORIDADE,"
		 		                            + "DT_ULTIMA_INTEVENCAO) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
         try {
 			Connection connection = ConnectionMysql.getConnection();
 		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, t.getCdTicket());
 		    pstmt.setString(2, t.getDsTicket());
 		    pstmt.setString(3, t.getCdUsuario());
 		    pstmt.setString(4, t.getDsObservacao());
 		    pstmt.setString(5, t.getSnAtivo());
 		    pstmt.setLong(6, t.getTotalMinutosTrabalhados()!=null ? t.getTotalMinutosTrabalhados().longValue() : 0);
 		    pstmt.setLong(7, t.getTotalPercentualConclusao() !=null ? t.getTotalPercentualConclusao().longValue():0);
 		    pstmt.setString(8, t.getCdTicketAssociado());
 		    pstmt.setString(9, t.getSnPrioritario());
 		    pstmt.setString(10, t.getNrOrdemPrioridade());
 		    pstmt.setTimestamp(11, new Timestamp(new Date().getTime()),cal);
 		    pstmt.execute();
 		   pstmt.close();
 		    connection.close();
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
				Connection connection = ConnectionMysql.getConnection();
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
			    connection.close();
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
				Connection connection = ConnectionMysql.getConnection();
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
		String comando = "UPDATE CHAMADO SET DS_TICKET = ? ,CD_USUARIO = ?,DS_OBSERVACAO = ? ,SN_ATIVO = ? ,TOTAL_MINUTOS_TRABALHADOS = ?,TOTAL_PERCENTUAL_CONCLUSAO = ?,CD_TICKET_ASSOCIADO = ?,SN_PRIORITARIO = ?, NR_ORDEM_PRIORIDADE = ?,DT_ULTIMA_INTEVENCAO = ? WHERE CD_TICKET = ? ";
		 try {
	 			Connection connection = ConnectionMysql.getConnection();
	 		    PreparedStatement pstmt = connection.prepareStatement(comando);
	 		    pstmt.setString(1, t.getDsTicket());
	 		    pstmt.setString(2, t.getCdUsuario());
	 		    pstmt.setString(3, t.getDsObservacao());
	 		    pstmt.setString(4, t.getSnAtivo());
	 		    pstmt.setLong(5, t.getTotalMinutosTrabalhados()!=null ? t.getTotalMinutosTrabalhados().longValue() : 0);
	 		    pstmt.setLong(6, t.getTotalPercentualConclusao() !=null ? t.getTotalPercentualConclusao().longValue():0);
	 		    pstmt.setString(7, t.getCdTicketAssociado());
	 		    pstmt.setString(8, t.getSnPrioritario());
	 		    pstmt.setString(9, t.getNrOrdemPrioridade());
	 		    pstmt.setTimestamp(10, new Timestamp(new Date().getTime()),cal);
	 		    pstmt.setString(11, t.getCdTicket());

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
			Connection connection = ConnectionMysql.getConnection();
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

	public List<Chamado> listarChamadosUsuario(String cdUsuario) {
		List<Chamado> retorno = new ArrayList<Chamado>();

	      String consulta = "SELECT CD_TICKET,DS_TICKET,CD_USUARIO,DS_OBSERVACAO,SN_ATIVO,TOTAL_MINUTOS_TRABALHADOS,TOTAL_PERCENTUAL_CONCLUSAO,CD_TICKET_ASSOCIADO,SN_PRIORITARIO, NR_ORDEM_PRIORIDADE FROM CHAMADO WHERE CD_USUARIO = ? and SN_ATIVO = 'Sim' ";
	      try {
				Connection connection = ConnectionMysql.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, cdUsuario);
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
			    pstmt.close();
			    connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	public List<Chamado> listarChamadosUsuarioPorData(String cdUsuario, Date dtChamado) {
		List<Chamado> retorno = new ArrayList<Chamado>();

	      String consulta = "SELECT CD_TICKET,DS_TICKET,CD_USUARIO,DS_OBSERVACAO,SN_ATIVO,TOTAL_MINUTOS_TRABALHADOS,TOTAL_PERCENTUAL_CONCLUSAO,CD_TICKET_ASSOCIADO,SN_PRIORITARIO, NR_ORDEM_PRIORIDADE FROM CHAMADO WHERE CD_USUARIO = ? and AND DATE_FORMAT(DT_ULTIMA_ITEVENCAO,'%d/%m/%Y') = ? ";
	      try {
				Connection connection = ConnectionMysql.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, cdUsuario);
			    pstmt.setString(2, util.converteDataParametro(dtChamado));
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
			    pstmt.close();
			    connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	public static void main(String[] args) {
		DaoChamado chamado = new DaoChamado();
		chamado.criarTabela();
	}

}

package br.com.jdsb.valhalla.sql.core.dao.ponto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import br.com.jdsb.valhalla.sql.core.connection.ConnectionMysql;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.objects.ponto.Ponto;

public class DaoPonto implements Dao<Ponto> {

	@Override
	public void criarTabela() {
		// TODO Auto-generated method stub

	}

	@Override
	public void salvar(Ponto t) {

		String comando = "INSERT INTO PONTO(DT_INIC_PONTO,DT_ALMOCO_PONTO,DT_VOLTA_PONTO,DT_FIM_PONTO,CD_USUARIO) VALUES (?,?,?,?,?)";
		Connection connection;
		try {
			connection = ConnectionMysql.getConnection();
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));
			PreparedStatement pstmt = connection.prepareStatement(comando);
			pstmt.setTimestamp(1, new Timestamp(t.getDtInicPonto().getTime()),cal);
			pstmt.setDate(2, new Date(t.getDtAlmocoPonto().getTime()),cal);
			pstmt.setDate(3, new Date( t.getDtVoltaPonto().getTime()),cal);
			pstmt.setDate(4, new Date( t.getDtFimPonto().getTime()),cal);
			pstmt.setString(5, t.getCdUsuario());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Ponto consultar(String condicao) {
		Ponto retorno = null;

	      String consulta = "SELECT CD_PONTO, CD_USUARIO, DT_INIC_PONTO, DT_ALMOCO_PONTO, DT_VOLTA_PONTO, DT_FIM_PONTO FROM PONTO WHERE CD_PONTO = ? ";
	      try {
				Connection connection = ConnectionMysql.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setInt(1, Integer.parseInt(condicao));
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno = (new Ponto(rs.getInt("CD_PONTO"),rs.getDate("DT_INIC_PONTO"),rs.getDate("DT_INIC_PONTO"),rs.getDate("DT_INIC_PONTO"),rs.getDate("DT_INIC_PONTO"),rs.getString("CD_USUARIO")));
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	public Ponto consultar(String cdUsuario,Date dtPonto) {
		Ponto retorno = null;

	      String consulta = "SELECT CD_PONTO, CD_USUARIO, DT_INIC_PONTO, DT_ALMOCO_PONTO, DT_VOLTA_PONTO, DT_FIM_PONTO FROM PONTO WHERE CD_USUARIO = ? AND TRUNC(DT_INIC_PONTO) = TRUNC(?)";
	      try {
				Connection connection = ConnectionMysql.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, cdUsuario);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno = (new Ponto(rs.getInt("CD_PONTO"),rs.getDate("DT_INIC_PONTO"),rs.getDate("DT_INIC_PONTO"),rs.getDate("DT_INIC_PONTO"),rs.getDate("DT_INIC_PONTO"),rs.getString("CD_USUARIO")));
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public List<Ponto> listar() {
		List<Ponto> retorno = new ArrayList<Ponto>();

	      String consulta = "SELECT CD_PONTO, CD_USUARIO, DT_INIC_PONTO, DT_ALMOCO_PONTO, DT_VOLTA_PONTO, DT_FIM_PONTO FROM PONTO ";
	      try {
				Connection connection = ConnectionMysql.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno.add(new Ponto(rs.getInt("CD_PONTO"),rs.getDate("DT_INIC_PONTO"),rs.getDate("DT_INIC_PONTO"),rs.getDate("DT_INIC_PONTO"),rs.getDate("DT_INIC_PONTO"),rs.getString("CD_USUARIO")));
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public void atualizar(Ponto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Ponto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validaPadrao(Ponto t) {
		// TODO Auto-generated method stub
		return false;
	}

}

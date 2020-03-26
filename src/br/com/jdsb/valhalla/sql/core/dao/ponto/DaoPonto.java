package br.com.jdsb.valhalla.sql.core.dao.ponto;

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

import br.com.jdsb.valhalla.sql.core.connection.ConnectionMysql;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.jfx.dialog.Dialogs;
import br.com.jdsb.valhalla.sql.core.texto.StringUtil;
import br.com.jdsb.valhalla.sql.objects.ponto.Ponto;
import br.com.jdsb.valhalla.sql.objects.ponto.PontoTo;

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
			if(t.getDtInicPonto()!=null){
				pstmt.setTimestamp(1, new Timestamp(t.getDtInicPonto().getTime()),cal);
			}else{
				pstmt.setTimestamp(1, null,cal);
			}
			if(t.getDtAlmocoPonto()!=null){
				pstmt.setTimestamp(2, new Timestamp(t.getDtAlmocoPonto().getTime()),cal);
			}else{
				pstmt.setTimestamp(2, null,cal);
			}
			if(t.getDtVoltaPonto()!=null){
				pstmt.setTimestamp(3, new Timestamp(t.getDtVoltaPonto().getTime()),cal);
			}else{
				pstmt.setTimestamp(3, null,cal);
			}
			if(t.getDtFimPonto()!=null){
				pstmt.setTimestamp(4, new Timestamp(t.getDtFimPonto().getTime()),cal);
			}else{
				pstmt.setTimestamp(4, null,cal);
			}
			pstmt.setString(5, t.getCdUsuario());
			pstmt.execute();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			Dialogs.AletaE("Atenção", "Ocorreu um erro ao salvar o ponto", e.getLocalizedMessage());
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

	      String consulta = "SELECT CD_PONTO, CD_USUARIO, DT_INIC_PONTO, DT_ALMOCO_PONTO, DT_VOLTA_PONTO, DT_FIM_PONTO FROM PONTO WHERE CD_USUARIO = ? AND DATE_FORMAT(DT_INIC_PONTO,'%d/%m/%Y') = ?";
	      try {
				Connection connection = ConnectionMysql.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));
			    pstmt.setString(1,cdUsuario);
			    StringUtil util =  new StringUtil();
				pstmt.setString(2, util.converteDataParametro(dtPonto));
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno = (new Ponto(rs.getInt("CD_PONTO"),rs.getTimestamp("DT_INIC_PONTO",cal),rs.getTimestamp("DT_ALMOCO_PONTO",cal),rs.getTimestamp("DT_VOLTA_PONTO",cal),rs.getTimestamp("DT_FIM_PONTO",cal),rs.getString("CD_USUARIO")));
			    }
			    pstmt.close();
			    connection.close();
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


	public List<PontoTo> listarTo(String data, String cdUsuario) {
		StringUtil utl = new StringUtil();
		List<Ponto> retorno = new ArrayList<Ponto>();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));
        List<PontoTo> listaConvertida = new ArrayList<PontoTo>();
	      String consulta = "SELECT CD_PONTO, CD_USUARIO, DT_INIC_PONTO, DT_ALMOCO_PONTO, DT_VOLTA_PONTO, DT_FIM_PONTO FROM PONTO WHERE DATE_FORMAT(DT_INIC_PONTO,'%d/%m/%Y') = ? AND CD_USUARIO = ? ";
	      try {
				Connection connection = ConnectionMysql.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, data);
			    pstmt.setString(2, cdUsuario);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno.add(new Ponto(rs.getInt("CD_PONTO"),rs.getTimestamp("DT_INIC_PONTO",cal),rs.getTimestamp("DT_ALMOCO_PONTO",cal),rs.getTimestamp("DT_VOLTA_PONTO",cal),rs.getTimestamp("DT_FIM_PONTO",cal),rs.getString("CD_USUARIO")));
			    }
			    pstmt.close();
			    connection.close();

			    for(Ponto ponto:retorno){
			    	listaConvertida.add(new PontoTo(ponto.getCdPonto(), utl.converteDataParametro(ponto.getDtInicPonto()), ponto.getCdUsuario(), utl.converteHora(ponto.getDtInicPonto()), utl.converteHora(ponto.getDtAlmocoPonto()), utl.converteHora(ponto.getDtVoltaPonto()), utl.converteHora(ponto.getDtFimPonto())));
			    }

			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return listaConvertida;
	}

	public List<PontoTo> listarTo(String data,String dataAte, String cdUsuario) {
		StringUtil utl = new StringUtil();
		List<Ponto> retorno = new ArrayList<Ponto>();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));
        List<PontoTo> listaConvertida = new ArrayList<PontoTo>();
	      String consulta = "SELECT CD_PONTO, CD_USUARIO, DT_INIC_PONTO, DT_ALMOCO_PONTO, DT_VOLTA_PONTO, DT_FIM_PONTO FROM PONTO WHERE DATE_FORMAT(DT_INIC_PONTO,'%d/%m/%Y') BETWEEN  ? AND ? AND CD_USUARIO = ? ";
	      try {
				Connection connection = ConnectionMysql.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, data);
			    pstmt.setString(2, dataAte);
			    pstmt.setString(3, cdUsuario);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno.add(new Ponto(rs.getInt("CD_PONTO"),rs.getTimestamp("DT_INIC_PONTO",cal),rs.getTimestamp("DT_ALMOCO_PONTO",cal),rs.getTimestamp("DT_VOLTA_PONTO",cal),rs.getTimestamp("DT_FIM_PONTO",cal),rs.getString("CD_USUARIO")));
			    }
			    pstmt.close();
			    connection.close();

			    for(Ponto ponto:retorno){
			    	listaConvertida.add(new PontoTo(ponto.getCdPonto(), utl.converteDataParametro(ponto.getDtInicPonto()), ponto.getCdUsuario(), utl.converteHora(ponto.getDtInicPonto()), utl.converteHora(ponto.getDtAlmocoPonto()), utl.converteHora(ponto.getDtVoltaPonto()), utl.converteHora(ponto.getDtFimPonto())));
			    }

			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return listaConvertida;
	}

	//UPDATE `PONTO` SET `DT_INIC_PONTO` = '2020-03-25 07:51:51' WHERE `PONTO`.`CD_PONTO` = 16;

	public void atualizar(PontoTo t) {
		String comando = "UPDATE PONTO SET DT_INIC_PONTO = ?, DT_ALMOCO_PONTO =  ?, DT_VOLTA_PONTO = ?, DT_FIM_PONTO = ?  WHERE CD_PONTO = ? ";
        try {
			Connection connection = ConnectionMysql.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.setString(1, retornaModelo(t.getCdDia(), t.getHrInic()));
		    pstmt.setString(2, retornaModelo(t.getCdDia(), t.getHrAlmoco()));
		    pstmt.setString(3, retornaModelo(t.getCdDia(), t.getHrVolta()));
		    pstmt.setString(4, retornaModelo(t.getCdDia(), t.getHrSaida()));
		    pstmt.setInt(5, t.getCdPonto());
		    pstmt.execute();
		    pstmt.close();
		    connection.close();
		} catch (SQLException e) {
			Dialogs.AletaE("Atenção", "Ocorreu um erro ao salvar o ponto", e.getLocalizedMessage());
			e.printStackTrace();
		}

	}

	public String retornaModelo(String data, String hora){
		   String[] tokens = data.split("/");
		   String dataFormatada = "";
		   if(tokens.length==3){
              dataFormatada = tokens[2].concat("-").concat(tokens[1]).concat("-").concat(tokens[0]).concat(" ").concat(hora).concat(":").concat("00");
		   }

		   return dataFormatada;
	}

	@Override
	public void atualizar(Ponto t) {
		String comando = "UPDATE PONTO SET DT_INIC_PONTO = ?, DT_ALMOCO_PONTO =  ?, DT_VOLTA_PONTO = ?, DT_FIM_PONTO = ?  WHERE CD_USUARIO = ? AND DATE_FORMAT(DT_INIC_PONTO,'%d/%m/%Y') = ? ";
        try {
			Connection connection = ConnectionMysql.getConnection();
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    if(t.getDtInicPonto()!=null){
				pstmt.setTimestamp(1, new Timestamp(t.getDtInicPonto().getTime()));
			}else{
				pstmt.setTimestamp(1, null,cal);
			}
			if(t.getDtAlmocoPonto()!=null){
				pstmt.setTimestamp(2, new Timestamp(t.getDtAlmocoPonto().getTime()),cal);
			}else{
				pstmt.setTimestamp(2, null,cal);
			}
			if(t.getDtVoltaPonto()!=null){
				pstmt.setTimestamp(3, new Timestamp(t.getDtVoltaPonto().getTime()),cal);
			}else{
				pstmt.setTimestamp(3, null,cal);
			}
			if(t.getDtFimPonto()!=null){
				pstmt.setTimestamp(4, new Timestamp(t.getDtFimPonto().getTime()),cal);
			}else{
				pstmt.setTimestamp(4, null,cal);
			}
			pstmt.setString(5,t.getCdUsuario());
			   StringUtil util =  new StringUtil();
				pstmt.setString(6, util.converteDataParametro(t.getDtInicPonto()));
		    pstmt.execute();
		    pstmt.close();
		    connection.close();
		} catch (SQLException e) {
			Dialogs.AletaE("Atenção", "Ocorreu um erro ao salvar o ponto", e.getLocalizedMessage());
			e.printStackTrace();
		}

	}

	public void atualizarAlmoco(Ponto t) {
		String comando = "UPDATE PONTO SET DT_ALMOCO_PONTO =  ? WHERE CD_USUARIO = ? AND DATE_FORMAT(DT_INIC_PONTO,'%d/%m/%Y') = ? ";
        try {
			Connection connection = ConnectionMysql.getConnection();
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));
		    PreparedStatement pstmt = connection.prepareStatement(comando);

			if(t.getDtAlmocoPonto()!=null){
				pstmt.setTimestamp(1, new Timestamp(t.getDtAlmocoPonto().getTime()),cal);
			}else{
				pstmt.setTimestamp(1, null,cal);
			}
			pstmt.setString(2,t.getCdUsuario());
			   StringUtil util =  new StringUtil();
				pstmt.setString(3, util.converteDataParametro(t.getDtInicPonto()));
		    pstmt.execute();
		    pstmt.close();
		    connection.close();
		} catch (SQLException e) {
			Dialogs.AletaE("Atenção", "Ocorreu um erro ao salvar o ponto", e.getLocalizedMessage());
			e.printStackTrace();
		}

	}

	public void atualizarVolta(Ponto t) {
		String comando = "UPDATE PONTO SET DT_VOLTA_PONTO =  ? WHERE CD_USUARIO = ? AND DATE_FORMAT(DT_INIC_PONTO,'%d/%m/%Y') = ? ";
        try {
			Connection connection = ConnectionMysql.getConnection();
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));
		    PreparedStatement pstmt = connection.prepareStatement(comando);

			if(t.getDtVoltaPonto()!=null){
				pstmt.setTimestamp(1, new Timestamp(t.getDtVoltaPonto().getTime()),cal);
			}else{
				pstmt.setTimestamp(1, null,cal);
			}
			pstmt.setString(2,t.getCdUsuario());
			   StringUtil util =  new StringUtil();
				pstmt.setString(3, util.converteDataParametro(t.getDtInicPonto()));
		    pstmt.execute();
		    pstmt.close();
		    connection.close();
		} catch (SQLException e) {
			Dialogs.AletaE("Atenção", "Ocorreu um erro ao salvar o ponto", e.getLocalizedMessage());
			e.printStackTrace();
		}

	}

	public void atualizarInic(Ponto t) {
		String comando = "UPDATE PONTO SET DT_INIC_PONTO =  ? WHERE CD_USUARIO = ? AND DATE_FORMAT(DT_INIC_PONTO,'%d/%m/%Y') = ? ";
        try {
			Connection connection = ConnectionMysql.getConnection();
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));
		    PreparedStatement pstmt = connection.prepareStatement(comando);

			if(t.getDtInicPonto()!=null){
				pstmt.setTimestamp(1, new Timestamp(t.getDtInicPonto().getTime()),cal);
			}else{
				pstmt.setTimestamp(1, null,cal);
			}
			pstmt.setString(2,t.getCdUsuario());
			   StringUtil util =  new StringUtil();
				pstmt.setString(3, util.converteDataParametro(t.getDtInicPonto()));
		    pstmt.execute();
		    pstmt.close();
		    connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void atualizarFim(Ponto t) {
		String comando = "UPDATE PONTO SET DT_FIM_PONTO =  ? WHERE CD_USUARIO = ? AND DATE_FORMAT(DT_INIC_PONTO,'%d/%m/%Y') = ? ";
        try {
			Connection connection = ConnectionMysql.getConnection();
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));
		    PreparedStatement pstmt = connection.prepareStatement(comando);

			if(t.getDtFimPonto()!=null){
				pstmt.setTimestamp(1, new Timestamp(t.getDtFimPonto().getTime()),cal);
			}else{
				pstmt.setTimestamp(1, null,cal);
			}
			pstmt.setString(2,t.getCdUsuario());
			   StringUtil util =  new StringUtil();
				pstmt.setString(3, util.converteDataParametro(t.getDtInicPonto()));
		    pstmt.execute();
		    pstmt.close();
		    connection.close();
		} catch (SQLException e) {
			Dialogs.AletaE("Atenção", "Ocorreu um erro ao salvar o ponto", e.getLocalizedMessage());
			e.printStackTrace();
		}

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

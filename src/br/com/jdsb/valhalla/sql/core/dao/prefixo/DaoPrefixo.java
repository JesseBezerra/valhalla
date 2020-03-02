package br.com.jdsb.valhalla.sql.core.dao.prefixo;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.objects.prefixo.Prefixo;

public class DaoPrefixo implements Dao<Prefixo> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
    private Connection con;

    public DaoPrefixo() {
    	try {
			con = ConexaoLite.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void criarTabela() {
		String comando = "CREATE TABLE IF NOT EXISTS PREFIXO(CD_PREFIXO INTEGER PRIMARY KEY AUTOINCREMENT, DS_PREFIXO VARCHAR,VL_PREFIXO VARCHAR, TP_DADO VARCHAR, TP_OBJETO VARCHAR, SN_ATIVO VARCHAR)";
        try {
        	PreparedStatement pstmt = con.prepareStatement(comando);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void salvar(Prefixo t) {
		String comando = "INSERT INTO PREFIXO(DS_PREFIXO, VL_PREFIXO, TP_DADO,TP_OBJETO, SN_ATIVO) VALUES (?,?,?,?,?)";
		try {
        	PreparedStatement pstmt = con.prepareStatement(comando);
        	pstmt.setString(1, t.getDsPrefixo());
        	pstmt.setString(2, t.getVlPrefixo());
        	pstmt.setString(3, t.getTpDado());
        	pstmt.setString(4, t.getTpObjeto());
        	pstmt.setString(5, t.getSnAtivo());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Prefixo consultar(String condicao) {
		 Prefixo retorno = null;

	      String consulta = "SELECT CD_PREFIXO ,DS_PREFIXO, VL_PREFIXO, TP_DADO,TP_OBJETO, SN_ATIVO FROM PREFIXO WHERE VL_PREFIXO = ?";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, condicao);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno = (new Prefixo(BigInteger.valueOf(rs.getInt("CD_PREFIXO")), rs.getString("DS_PREFIXO"), rs.getString("VL_PREFIXO"), rs.getString("TP_DADO"), rs.getString("TP_OBJETO"), rs.getString("SN_ATIVO")));
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	@Override
	public List<Prefixo> listar() {
		 List<Prefixo> retorno = new ArrayList<Prefixo>();

	      String consulta = "SELECT CD_PREFIXO ,DS_PREFIXO, VL_PREFIXO, TP_DADO,TP_OBJETO, SN_ATIVO FROM PREFIXO";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno.add(new Prefixo(BigInteger.valueOf(rs.getInt("CD_PREFIXO")), rs.getString("DS_PREFIXO"), rs.getString("VL_PREFIXO"), rs.getString("TP_DADO"), rs.getString("TP_OBJETO"), rs.getString("SN_ATIVO")));
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	public static void main(String[] args) {
         Dao<Prefixo> dao = new DaoPrefixo();
         dao.criarTabela();
         dao.salvar(new Prefixo(null, "CODIGO", "CD", "NUMBER", "COLUNA", "Sim"));
         for(Prefixo prefixo:dao.listar()){
        	 System.out.println(prefixo);
         }
	}

	@Override
	public void atualizar(Prefixo t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Prefixo t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validaPadrao(Prefixo t) {
		// TODO Auto-generated method stub
		return false;
	}

}

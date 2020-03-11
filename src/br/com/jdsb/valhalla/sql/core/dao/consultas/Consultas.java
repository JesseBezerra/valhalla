package br.com.jdsb.valhalla.sql.core.dao.consultas;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.core.connection.ConnectionSystem;
import br.com.jdsb.valhalla.sql.objects.chamado.Chamado;
import br.com.jdsb.valhalla.sql.objects.conexao.Conexao;
import br.com.jdsb.valhalla.sql.objects.estoque.Estoque;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;

public class Consultas {

	public List<String> listar(Conexao conexao) {
		 List<String> retorno = new ArrayList<String>();

	      String consulta = "SELECT CD_MULTI_EMPRESA || ' - ' || DS_MULTI_EMPRESA AS DS_MULTI_EMPRESA FROM DBAMV.MULTI_EMPRESAS ORDER BY CD_MULTI_EMPRESA";
	      try {
				Connection connection = ConnectionSystem.getConnection(conexao.getDsUrl(), conexao.getDsPorta(), conexao.getDsSid(), conexao.getDsUsuario(), conexao.getDsSenha(),conexao.getSnService());
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
                   retorno.add(rs.getString("DS_MULTI_EMPRESA"));
			    }
			    pstmt.close();
			    connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	public String getCdMultiEmpresa(Conexao conexao,String cdMultiEmpresa) {
		  String retorno = null;

	      String consulta = "SELECT CD_MULTI_EMPRESA || ' - ' || DS_MULTI_EMPRESA AS DS_MULTI_EMPRESA FROM DBAMV.MULTI_EMPRESAS WHERE CD_MULTI_EMPRESA = ? ORDER BY CD_MULTI_EMPRESA";
	      try {
				Connection connection = ConnectionSystem.getConnection(conexao.getDsUrl(), conexao.getDsPorta(), conexao.getDsSid(), conexao.getDsUsuario(), conexao.getDsSenha(),conexao.getSnService());
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, cdMultiEmpresa);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
                  retorno = (rs.getString("DS_MULTI_EMPRESA"));
			    }
			    pstmt.close();
			    connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	public String getCdEstoque(Conexao conexao,String cdEstoque) {
		  String retorno = null;

	      String consulta = "SELECT CD_ESTOQUE || ' - ' || DS_ESTOQUE AS DS_ESTOQUE FROM DBAMV.ESTOQUE WHERE CD_ESTOQUE = ? ORDER BY CD_ESTOQUE";
	      try {
				Connection connection = ConnectionSystem.getConnection(conexao.getDsUrl(), conexao.getDsPorta(), conexao.getDsSid(), conexao.getDsUsuario(), conexao.getDsSenha(),conexao.getSnService());
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, cdEstoque);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
                retorno = (rs.getString("DS_ESTOQUE"));
			    }
			    pstmt.close();
			    connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	public String getDsEspecie(Conexao conexao,String cdEspecie) {
		   String retorno = null;

	      String consulta = "SELECT DS_ESPECIE FROM DBAMV.ESPECIE WHERE CD_ESPECIE = ? ";
	      try {
				Connection connection = ConnectionSystem.getConnection(conexao.getDsUrl(), conexao.getDsPorta(), conexao.getDsSid(), conexao.getDsUsuario(), conexao.getDsSenha(),conexao.getSnService());
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, cdEspecie);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
                  retorno = (rs.getString("DS_ESPECIE"));
			    }
			    pstmt.close();
			    connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	public String getDsClass(Conexao conexao,String cdEspecie,String cdClasse) {
		   String retorno = null;

	      String consulta = "SELECT DS_CLASSE FROM DBAMV.CLASSE WHERE CD_ESPECIE = ? AND CD_CLASSE = ?";
	      try {
				Connection connection = ConnectionSystem.getConnection(conexao.getDsUrl(), conexao.getDsPorta(), conexao.getDsSid(), conexao.getDsUsuario(), conexao.getDsSenha(),conexao.getSnService());
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, cdEspecie);
			    pstmt.setString(2, cdClasse);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
               retorno = (rs.getString("DS_CLASSE"));
			    }
			    pstmt.close();
			    connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	public String getDsSubClass(Conexao conexao,String cdEspecie,String cdClasse,String cdSubClass) {
		   String retorno = null;

	      String consulta = "SELECT DS_SUB_CLA FROM DBAMV.SUB_CLAS WHERE CD_ESPECIE = ? AND CD_CLASSE = ? AND CD_SUB_CLA = ? ";
	      try {
				Connection connection = ConnectionSystem.getConnection(conexao.getDsUrl(), conexao.getDsPorta(), conexao.getDsSid(), conexao.getDsUsuario(), conexao.getDsSenha(),conexao.getSnService());
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, cdEspecie);
			    pstmt.setString(2, cdClasse);
			    pstmt.setString(3, cdSubClass);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
            retorno = (rs.getString("DS_SUB_CLA"));
			    }
			    pstmt.close();
			    connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	public List<String> listarEstoques(Conexao conexao,String cdMultiEmpresa) {
		 List<String> retorno = new ArrayList<String>();

	      String consulta = "SELECT CD_ESTOQUE || ' - ' || DS_ESTOQUE AS DS_ESTOQUE FROM DBAMV.ESTOQUE WHERE CD_MULTI_EMPRESA = ? ORDER BY CD_ESTOQUE";
	      try {
				Connection connection = ConnectionSystem.getConnection(conexao.getDsUrl(), conexao.getDsPorta(), conexao.getDsSid(), conexao.getDsUsuario(), conexao.getDsSenha(),conexao.getSnService());
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, cdMultiEmpresa);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
                  retorno.add(rs.getString("DS_ESTOQUE"));
			    }
			    pstmt.close();
			    connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	      return retorno;
	}

	public List<Estoque> listarEstoques(String cdConfigSupri) {
		List<Estoque> retorno = new ArrayList<Estoque>();

	      String consulta = "SELECT CD_ESTOQUE,DS_ESTOQUE,CD_CONFIG_SUPRI FROM ESTOQUE WHERE CD_CONFIG_SUPRI = ?";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, cdConfigSupri);
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

	public Usuario consultar(String condicao) {
		   Usuario retorno = null;
	      String consulta = "SELECT CD_USUARIO,NM_USUARIO,TP_USUARIO,SN_ATIVO FROM USUARIO WHERE NM_USUARIO = ?";
	      try {
				Connection connection = ConexaoLite.getConnection();
			    PreparedStatement pstmt = connection.prepareStatement(consulta);
			    pstmt.setString(1, condicao);
			    ResultSet rs = pstmt.executeQuery();
			    while(rs.next()){
			    	retorno = (new Usuario(rs.getString("CD_USUARIO"),rs.getString("NM_USUARIO"),rs.getString("TP_USUARIO"),rs.getString("SN_ATIVO")));
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

	public List<Chamado> listarChamadosUsuario(String cdUsuario) {
		List<Chamado> retorno = new ArrayList<Chamado>();

	      String consulta = "SELECT CD_TICKET,DS_TICKET,CD_USUARIO,DS_OBSERVACAO,SN_ATIVO,TOTAL_MINUTOS_TRABALHADOS,TOTAL_PERCENTUAL_CONCLUSAO,CD_TICKET_ASSOCIADO,SN_PRIORITARIO, NR_ORDEM_PRIORIDADE FROM CHAMADO WHERE CD_USUARIO = ? and SN_ATIVO = 'Sim' ";
	      try {
				Connection connection = ConexaoLite.getConnection();
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return retorno;
	}

}

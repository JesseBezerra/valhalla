package br.com.jdsb.valhalla.sql.core.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.objects.produto.Produto;

public class DaoProduto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public void criarTabelaProduto(){
          String comando = "CREATE TABLE IF NOT EXISTS PRODUTO (CD_PRODUTO INTEGER PRIMARY KEY AUTOINCREMENT,DS_PRODUTO VARCHAR, SN_ATIVO VARCHAR) ";
          try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(comando);
		    pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void inserirProduto(Produto produto){
         String comando = "INSERT INTO PRODUTO(DS_PRODUTO,SN_ATIVO) VALUES (?,?)";
         try {
 			Connection connection = ConexaoLite.getConnection();
 		    PreparedStatement pstmt = connection.prepareStatement(comando);
 		    pstmt.setString(1, produto.getDsProduto());
 		    pstmt.setString(2, produto.getSnAtivo());
 		    pstmt.execute();
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	}

	public Produto buscarProduto(String dsProduto){
      Produto retorno = null;
      String consulta = "SELECT CD_PRODUTO, DS_PRODUTO, SN_ATIVO FROM PRODUTO WHERE DS_PRODUTO = ?";
      try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(consulta);
		    pstmt.setString(1, dsProduto);
		    ResultSet rs = pstmt.executeQuery();
		    while(rs.next()){
		    	retorno =  new Produto(BigInteger.valueOf(rs.getInt("CD_PRODUTO")), rs.getString("DS_PRODUTO"), rs.getString("SN_ATIVO"));
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      return retorno;

	}

	public List<Produto> buscarProdutos(){
      List<Produto> retorno = new ArrayList<Produto>();

      String consulta = "SELECT CD_PRODUTO, DS_PRODUTO, SN_ATIVO FROM PRODUTO";
      try {
			Connection connection = ConexaoLite.getConnection();
		    PreparedStatement pstmt = connection.prepareStatement(consulta);
		    ResultSet rs = pstmt.executeQuery();
		    while(rs.next()){
		    	retorno.add(new Produto(BigInteger.valueOf(rs.getInt("CD_PRODUTO")), rs.getString("DS_PRODUTO"), rs.getString("SN_ATIVO")));
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      return retorno;
	}

	public static void main(String[] args) {
          DaoProduto daoProduto = new DaoProduto();
          daoProduto.criarTabelaProduto();
          //daoProduto.inserirProduto(new Produto(BigInteger.valueOf(0), "PEP", "S"));
          //daoProduto.inserirProduto(new Produto(BigInteger.valueOf(0), "SUPRI", "S"));
          List<Produto> lista = daoProduto.buscarProdutos();

          for(Produto produto:lista){
        	  System.out.println(produto.getDsProduto());
          }

          Produto produto = daoProduto.buscarProduto("PEP");
          System.out.println(produto);
	}







}

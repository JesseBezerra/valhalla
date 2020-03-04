package br.com.jdsb.valhalla.sql.core.dao.produtosupri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.jdsb.valhalla.sql.core.connection.ConexaoLite;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.objects.produtosupri.ProdutoSupri;

public class DaoProdutoSupri implements Dao<ProdutoSupri> {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void salvar(ProdutoSupri t) {
		// TODO Auto-generated method stub

	}

	@Override
	public ProdutoSupri consultar(String condicao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoSupri> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(ProdutoSupri t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(ProdutoSupri t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validaPadrao(ProdutoSupri t) {
		// TODO Auto-generated method stub
		return false;
	}

}

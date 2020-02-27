package br.com.jdsb.valhalla.sql.core.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T> extends Serializable {

	public void criarTabela();

	public void salvar(T t);

	public T consultar(String condicao);

	public List<T> listar();
}

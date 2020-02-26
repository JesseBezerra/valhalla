package br.com.jdsb.valhalla.sql.objects.produto;

import java.io.Serializable;

public class Produto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    private String cdProduto;
    private String dsProduto;

	public String getCdProduto() {
		return cdProduto;
	}
	public void setCdProduto(String cdProduto) {
		this.cdProduto = cdProduto;
	}
	public String getDsProduto() {
		return dsProduto;
	}
	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}




}

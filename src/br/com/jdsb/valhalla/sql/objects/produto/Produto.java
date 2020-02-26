package br.com.jdsb.valhalla.sql.objects.produto;



import java.io.Serializable;
import java.math.BigInteger;

public class Produto implements Serializable, Comparable<Produto> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger cdProduto;
	private String dsProduto;
	private String snAtivo;
	private String snPadrao;

	public BigInteger getCdProduto() {
		return cdProduto;
	}
	public void setCdProduto(BigInteger cdProduto) {
		this.cdProduto = cdProduto;
	}
	public String getDsProduto() {
		return dsProduto;
	}
	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}
	public String getSnAtivo() {
		return snAtivo;
	}
	public void setSnAtivo(String snAtivo) {
		this.snAtivo = snAtivo;
	}
	public String getSnPadrao() {
		return snPadrao;
	}
	public void setSnPadrao(String snPadrao) {
		this.snPadrao = snPadrao;
	}


	public Produto(BigInteger cdProduto, String dsProduto, String snAtivo) {
		super();
		this.cdProduto = cdProduto;
		this.dsProduto = dsProduto;
		this.snAtivo = snAtivo;
	}


	public Produto(BigInteger cdProduto, String dsProduto, String snAtivo, String snPadrao) {
		super();
		this.cdProduto = cdProduto;
		this.dsProduto = dsProduto;
		this.snAtivo = snAtivo;
		this.snPadrao = snPadrao;
	}
	public Produto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Produto o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "Produto [cdProduto=" + cdProduto + ", dsProduto=" + dsProduto + ", snAtivo=" + snAtivo + ", snPadrao="
				+ snPadrao + "]";
	}









}

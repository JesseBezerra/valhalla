package br.com.jdsb.valhalla.sql.objects.objeto;

import java.math.BigDecimal;

public class Objeto {

	private BigDecimal codigo;
	private String nome;
	private String cabecalho;
	private String corpo;
	private String tipo;
	private String produto;

	public Objeto(BigDecimal codigo, String nome, String cabecalho, String corpo, String tipo, String produto) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cabecalho = cabecalho;
		this.corpo = corpo;
		this.tipo = tipo;
		this.produto = produto;
	}

	public Objeto() {

	}

	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public BigDecimal getCodigo() {
		return codigo;
	}
	public void setCodigo(BigDecimal codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCabecalho() {
		return cabecalho;
	}
	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}





}

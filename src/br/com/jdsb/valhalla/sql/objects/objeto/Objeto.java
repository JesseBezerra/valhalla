package br.com.jdsb.valhalla.sql.objects.objeto;

import java.io.Serializable;
import java.math.BigInteger;

public class Objeto implements Serializable, Comparable<Objeto> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger cdObjeto;
	private String dsObjeto;
	private String snAtivo;
	private String snPadrao;


	public BigInteger getCdObjeto() {
		return cdObjeto;
	}
	public void setCdObjeto(BigInteger cdObjeto) {
		this.cdObjeto = cdObjeto;
	}
	public String getDsObjeto() {
		return dsObjeto;
	}
	public void setDsObjeto(String dsObjeto) {
		this.dsObjeto = dsObjeto;
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

	public Objeto() {
		// TODO Auto-generated constructor stub
	}

	public Objeto(BigInteger cdObjeto, String dsObjeto, String snAtivo, String snPadrao) {
		super();
		this.cdObjeto = cdObjeto;
		this.dsObjeto = dsObjeto;
		this.snAtivo = snAtivo;
		this.snPadrao = snPadrao;
	}

	@Override
	public String toString() {
		return "Objeto [cdObjeto=" + cdObjeto + ", dsObjeto=" + dsObjeto + ", snAtivo=" + snAtivo + ", snPadrao="
				+ snPadrao + "]";
	}
	@Override
	public int compareTo(Objeto o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdObjeto == null) ? 0 : cdObjeto.hashCode());
		result = prime * result + ((dsObjeto == null) ? 0 : dsObjeto.hashCode());
		result = prime * result + ((snAtivo == null) ? 0 : snAtivo.hashCode());
		result = prime * result + ((snPadrao == null) ? 0 : snPadrao.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Objeto other = (Objeto) obj;
		if (cdObjeto == null) {
			if (other.cdObjeto != null)
				return false;
		} else if (!cdObjeto.equals(other.cdObjeto))
			return false;
		if (dsObjeto == null) {
			if (other.dsObjeto != null)
				return false;
		} else if (!dsObjeto.equals(other.dsObjeto))
			return false;
		if (snAtivo == null) {
			if (other.snAtivo != null)
				return false;
		} else if (!snAtivo.equals(other.snAtivo))
			return false;
		if (snPadrao == null) {
			if (other.snPadrao != null)
				return false;
		} else if (!snPadrao.equals(other.snPadrao))
			return false;
		return true;
	}














}

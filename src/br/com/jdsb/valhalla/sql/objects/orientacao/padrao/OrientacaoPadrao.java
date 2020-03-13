package br.com.jdsb.valhalla.sql.objects.orientacao.padrao;

import java.io.Serializable;
import java.math.BigInteger;

public class OrientacaoPadrao implements Comparable<OrientacaoPadrao>, Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger cdOrientacaoPadrao;
	private String nmOrientacaoPadrao;
	private String dsOrientacaoPadrao;
	private String snAtivo;

	public BigInteger getCdOrientacaoPadrao() {
		return cdOrientacaoPadrao;
	}
	public void setCdOrientacaoPadrao(BigInteger cdOrientacaoPadrao) {
		this.cdOrientacaoPadrao = cdOrientacaoPadrao;
	}
	public String getNmOrientacaoPadrao() {
		return nmOrientacaoPadrao;
	}
	public void setNmOrientacaoPadrao(String nmOrientacaoPadrao) {
		this.nmOrientacaoPadrao = nmOrientacaoPadrao;
	}
	public String getDsOrientacaoPadrao() {
		return dsOrientacaoPadrao;
	}
	public void setDsOrientacaoPadrao(String dsOrientacaoPadrao) {
		this.dsOrientacaoPadrao = dsOrientacaoPadrao;
	}
	public String getSnAtivo() {
		return snAtivo;
	}
	public void setSnAtivo(String snAtivo) {
		this.snAtivo = snAtivo;
	}

	public OrientacaoPadrao() {
		// TODO Auto-generated constructor stub
	}

	public OrientacaoPadrao(BigInteger cdOrientacaoPadrao, String nmOrientacaoPadrao, String dsOrientacaoPadrao,
			String snAtivo) {
		super();
		this.cdOrientacaoPadrao = cdOrientacaoPadrao;
		this.nmOrientacaoPadrao = nmOrientacaoPadrao;
		this.dsOrientacaoPadrao = dsOrientacaoPadrao;
		this.snAtivo = snAtivo;
	}
	@Override
	public int compareTo(OrientacaoPadrao o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String toString() {
		return "OrientacaoPadrao [cdOrientacaoPadrao=" + cdOrientacaoPadrao + ", nmOrientacaoPadrao="
				+ nmOrientacaoPadrao + ", dsOrientacaoPadrao=" + dsOrientacaoPadrao + ", snAtivo=" + snAtivo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdOrientacaoPadrao == null) ? 0 : cdOrientacaoPadrao.hashCode());
		result = prime * result + ((dsOrientacaoPadrao == null) ? 0 : dsOrientacaoPadrao.hashCode());
		result = prime * result + ((nmOrientacaoPadrao == null) ? 0 : nmOrientacaoPadrao.hashCode());
		result = prime * result + ((snAtivo == null) ? 0 : snAtivo.hashCode());
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
		OrientacaoPadrao other = (OrientacaoPadrao) obj;
		if (cdOrientacaoPadrao == null) {
			if (other.cdOrientacaoPadrao != null)
				return false;
		} else if (!cdOrientacaoPadrao.equals(other.cdOrientacaoPadrao))
			return false;
		if (dsOrientacaoPadrao == null) {
			if (other.dsOrientacaoPadrao != null)
				return false;
		} else if (!dsOrientacaoPadrao.equals(other.dsOrientacaoPadrao))
			return false;
		if (nmOrientacaoPadrao == null) {
			if (other.nmOrientacaoPadrao != null)
				return false;
		} else if (!nmOrientacaoPadrao.equals(other.nmOrientacaoPadrao))
			return false;
		if (snAtivo == null) {
			if (other.snAtivo != null)
				return false;
		} else if (!snAtivo.equals(other.snAtivo))
			return false;
		return true;
	}







}

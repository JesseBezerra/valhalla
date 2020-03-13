package br.com.jdsb.valhalla.sql.objects.orientacao.ticket;

import java.io.Serializable;
import java.math.BigInteger;

public class OrientacaoTicket implements Comparable<OrientacaoTicket>, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger cdOrientacao;

	private String cdTicket;

	private String dsOrientacao;

	public BigInteger getCdOrientacao() {
		return cdOrientacao;
	}

	public void setCdOrientacao(BigInteger cdOrientacao) {
		this.cdOrientacao = cdOrientacao;
	}

	public String getCdTicket() {
		return cdTicket;
	}

	public void setCdTicket(String cdTicket) {
		this.cdTicket = cdTicket;
	}

	public String getDsOrientacao() {
		return dsOrientacao;
	}

	public void setDsOrientacao(String dsOrientacao) {
		this.dsOrientacao = dsOrientacao;
	}

	@Override
	public int compareTo(OrientacaoTicket o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public OrientacaoTicket(BigInteger cdOrientacao, String cdTicket, String dsOrientacao) {
		super();
		this.cdOrientacao = cdOrientacao;
		this.cdTicket = cdTicket;
		this.dsOrientacao = dsOrientacao;
	}

	public OrientacaoTicket() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdOrientacao == null) ? 0 : cdOrientacao.hashCode());
		result = prime * result + ((cdTicket == null) ? 0 : cdTicket.hashCode());
		result = prime * result + ((dsOrientacao == null) ? 0 : dsOrientacao.hashCode());
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
		OrientacaoTicket other = (OrientacaoTicket) obj;
		if (cdOrientacao == null) {
			if (other.cdOrientacao != null)
				return false;
		} else if (!cdOrientacao.equals(other.cdOrientacao))
			return false;
		if (cdTicket == null) {
			if (other.cdTicket != null)
				return false;
		} else if (!cdTicket.equals(other.cdTicket))
			return false;
		if (dsOrientacao == null) {
			if (other.dsOrientacao != null)
				return false;
		} else if (!dsOrientacao.equals(other.dsOrientacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrientacaoTicket [cdOrientacao=" + cdOrientacao + ", cdTicket=" + cdTicket + ", dsOrientacao="
				+ dsOrientacao + "]";
	}



}

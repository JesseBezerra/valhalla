package br.com.jdsb.valhalla.sql.objects.ticket;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable, Comparable<Ticket> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String cdTicket;

	private String dsTicket;

	private String cdModulo;

	private String dsCaminho;

	private String dsProblema;

	private String releaseNotes;

	private String snAtivo;

	public String getCdTicket() {
		return cdTicket;
	}

	public void setCdTicket(String cdTicket) {
		this.cdTicket = cdTicket;
	}


	public String getCdModulo() {
		return cdModulo;
	}

	public void setCdModulo(String cdModulo) {
		this.cdModulo = cdModulo;
	}

	public String getDsCaminho() {
		return dsCaminho;
	}

	public void setDsCaminho(String dsCaminho) {
		this.dsCaminho = dsCaminho;
	}

	public String getDsProblema() {
		return dsProblema;
	}

	public void setDsProblema(String dsProblema) {
		this.dsProblema = dsProblema;
	}

	public String getReleaseNotes() {
		return releaseNotes;
	}

	public void setReleaseNotes(String releaseNotes) {
		this.releaseNotes = releaseNotes;
	}

	@Override
	public int compareTo(Ticket arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getDsTicket() {
		return dsTicket;
	}

	public void setDsTicket(String dsTicket) {
		this.dsTicket = dsTicket;
	}

	public String getSnAtivo() {
		return snAtivo;
	}

	public void setSnAtivo(String snAtivo) {
		this.snAtivo = snAtivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdModulo == null) ? 0 : cdModulo.hashCode());
		result = prime * result + ((cdTicket == null) ? 0 : cdTicket.hashCode());
		result = prime * result + ((dsCaminho == null) ? 0 : dsCaminho.hashCode());
		result = prime * result + ((dsProblema == null) ? 0 : dsProblema.hashCode());
		result = prime * result + ((dsTicket == null) ? 0 : dsTicket.hashCode());
		result = prime * result + ((releaseNotes == null) ? 0 : releaseNotes.hashCode());
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
		Ticket other = (Ticket) obj;
		if (cdModulo == null) {
			if (other.cdModulo != null)
				return false;
		} else if (!cdModulo.equals(other.cdModulo))
			return false;
		if (cdTicket == null) {
			if (other.cdTicket != null)
				return false;
		} else if (!cdTicket.equals(other.cdTicket))
			return false;
		if (dsCaminho == null) {
			if (other.dsCaminho != null)
				return false;
		} else if (!dsCaminho.equals(other.dsCaminho))
			return false;
		if (dsProblema == null) {
			if (other.dsProblema != null)
				return false;
		} else if (!dsProblema.equals(other.dsProblema))
			return false;
		if (dsTicket == null) {
			if (other.dsTicket != null)
				return false;
		} else if (!dsTicket.equals(other.dsTicket))
			return false;
		if (releaseNotes == null) {
			if (other.releaseNotes != null)
				return false;
		} else if (!releaseNotes.equals(other.releaseNotes))
			return false;
		if (snAtivo == null) {
			if (other.snAtivo != null)
				return false;
		} else if (!snAtivo.equals(other.snAtivo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [cdTicket=" + cdTicket + ", dsTicket=" + dsTicket + ", cdModulo=" + cdModulo + ", dsCaminho="
				+ dsCaminho + ", dsProblema=" + dsProblema + ", releaseNotes=" + releaseNotes + ", snAtivo=" + snAtivo
				+ "]";
	}

	public Ticket(String cdTicket, String dsTicket, String cdModulo, String dsCaminho, String dsProblema,
			String releaseNotes, String snAtivo) {
		super();
		this.cdTicket = cdTicket;
		this.dsTicket = dsTicket;
		this.cdModulo = cdModulo;
		this.dsCaminho = dsCaminho;
		this.dsProblema = dsProblema;
		this.releaseNotes = releaseNotes;
		this.snAtivo = snAtivo;
	}





}

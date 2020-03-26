package br.com.jdsb.valhalla.sql.objects.chamado;

import java.io.Serializable;

public class Apontamento implements Serializable, Comparable<Apontamento>{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public String cdTicket;
	public String dsTitulo;
	public String cdCliente;
	public String tpSituacao;
	public String nmResponsavel;
	public String horasGastas;

	@Override
	public int compareTo(Apontamento o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getCdTicket() {
		return cdTicket;
	}

	public void setCdTicket(String cdTicket) {
		this.cdTicket = cdTicket;
	}

	public String getDsTitulo() {
		return dsTitulo;
	}

	public void setDsTitulo(String dsTitulo) {
		this.dsTitulo = dsTitulo;
	}

	public String getCdCliente() {
		return cdCliente;
	}

	public void setCdCliente(String cdCliente) {
		this.cdCliente = cdCliente;
	}

	public String getTpSituacao() {
		return tpSituacao;
	}

	public void setTpSituacao(String tpSituacao) {
		this.tpSituacao = tpSituacao;
	}

	public String getNmResponsavel() {
		return nmResponsavel;
	}

	public void setNmResponsavel(String nmResponsavel) {
		this.nmResponsavel = nmResponsavel;
	}

	public String getHorasGastas() {
		return horasGastas;
	}

	public void setHorasGastas(String horasGastas) {
		this.horasGastas = horasGastas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdCliente == null) ? 0 : cdCliente.hashCode());
		result = prime * result + ((cdTicket == null) ? 0 : cdTicket.hashCode());
		result = prime * result + ((dsTitulo == null) ? 0 : dsTitulo.hashCode());
		result = prime * result + ((horasGastas == null) ? 0 : horasGastas.hashCode());
		result = prime * result + ((nmResponsavel == null) ? 0 : nmResponsavel.hashCode());
		result = prime * result + ((tpSituacao == null) ? 0 : tpSituacao.hashCode());
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
		Apontamento other = (Apontamento) obj;
		if (cdCliente == null) {
			if (other.cdCliente != null)
				return false;
		} else if (!cdCliente.equals(other.cdCliente))
			return false;
		if (cdTicket == null) {
			if (other.cdTicket != null)
				return false;
		} else if (!cdTicket.equals(other.cdTicket))
			return false;
		if (dsTitulo == null) {
			if (other.dsTitulo != null)
				return false;
		} else if (!dsTitulo.equals(other.dsTitulo))
			return false;
		if (horasGastas == null) {
			if (other.horasGastas != null)
				return false;
		} else if (!horasGastas.equals(other.horasGastas))
			return false;
		if (nmResponsavel == null) {
			if (other.nmResponsavel != null)
				return false;
		} else if (!nmResponsavel.equals(other.nmResponsavel))
			return false;
		if (tpSituacao == null) {
			if (other.tpSituacao != null)
				return false;
		} else if (!tpSituacao.equals(other.tpSituacao))
			return false;
		return true;
	}




}

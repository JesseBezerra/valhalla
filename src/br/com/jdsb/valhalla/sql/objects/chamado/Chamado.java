package br.com.jdsb.valhalla.sql.objects.chamado;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class Chamado implements Serializable, Comparable<Chamado> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String cdTicket;

	private String dsTicket;

	private String cdUsuario;

	private String dsObservacao;

	private String snAtivo;

	private BigInteger totalMinutosTrabalhados;

	private BigInteger totalPercentualConclusao;

	private String cdTicketAssociado;

	private String snPrioritario;

	private String nrOrdemPrioridade;

	private Date dtUltimaIntervencao;

	public String getCdTicket() {
		return cdTicket;
	}

	public void setCdTicket(String cdTicket) {
		this.cdTicket = cdTicket;
	}

	public String getDsTicket() {
		return dsTicket;
	}

	public void setDsTicket(String dsTicket) {
		this.dsTicket = dsTicket;
	}

	public String getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public String getDsObservacao() {
		return dsObservacao;
	}

	public void setDsObservacao(String dsObservacao) {
		this.dsObservacao = dsObservacao;
	}

	public String getSnAtivo() {
		return snAtivo;
	}

	public void setSnAtivo(String snAtivo) {
		this.snAtivo = snAtivo;
	}

	public BigInteger getTotalMinutosTrabalhados() {
		return totalMinutosTrabalhados;
	}

	public void setTotalMinutosTrabalhados(BigInteger totalMinutosTrabalhados) {
		this.totalMinutosTrabalhados = totalMinutosTrabalhados;
	}

	public BigInteger getTotalPercentualConclusao() {
		return totalPercentualConclusao;
	}

	public void setTotalPercentualConclusao(BigInteger totalPercentualConclusao) {
		this.totalPercentualConclusao = totalPercentualConclusao;
	}

	public String getCdTicketAssociado() {
		return cdTicketAssociado;
	}

	public void setCdTicketAssociado(String cdTicketAssociado) {
		this.cdTicketAssociado = cdTicketAssociado;
	}


	public String getSnPrioritario() {
		return snPrioritario;
	}

	public void setSnPrioritario(String snPrioritario) {
		this.snPrioritario = snPrioritario;
	}

	public String getNrOrdemPrioridade() {
		return nrOrdemPrioridade;
	}

	public void setNrOrdemPrioridade(String nrOrdemPrioridade) {
		this.nrOrdemPrioridade = nrOrdemPrioridade;
	}

	@Override
	public int compareTo(Chamado arg0) {
		// TODO Auto-generated method stub
		return 0;
	}



	public Chamado(String cdTicket, String dsTicket, String cdUsuario, String dsObservacao, String snAtivo,
			BigInteger totalMinutosTrabalhados, BigInteger totalPercentualConclusao,String cdTicketAssociado) {
		super();
		this.cdTicket = cdTicket;
		this.dsTicket = dsTicket;
		this.cdUsuario = cdUsuario;
		this.dsObservacao = dsObservacao;
		this.snAtivo = snAtivo;
		this.totalMinutosTrabalhados = totalMinutosTrabalhados;
		this.totalPercentualConclusao = totalPercentualConclusao;
		this.cdTicketAssociado = cdTicketAssociado;

		/**
		 * CD_TICKET,
		 * DS_TICKET,
		 * CD_USUARIO,
		 * DS_OBSERVACAO,
		 * SN_ATIVO,
		 * TOTAL_MINUTOS_TRABALHADOS,
		 * TOTAL_PERCENTUAL_CONCLUSAO
		 * CD_TICKET_ASSOCIADO
		 * SN_PRIORITARIO,
		 * NR_ORDEM_PRIORIDADE
		 */

	}

	public Chamado() {
		// TODO Auto-generated constructor stub
	}

	public Chamado(String cdTicket, String dsTicket, String cdUsuario, String dsObservacao, String snAtivo,
			BigInteger totalMinutosTrabalhados, BigInteger totalPercentualConclusao, String cdTicketAssociado,
			String snPrioritario, String nrOrdemPrioridade) {
		super();
		this.cdTicket = cdTicket;
		this.dsTicket = dsTicket;
		this.cdUsuario = cdUsuario;
		this.dsObservacao = dsObservacao;
		this.snAtivo = snAtivo;
		this.totalMinutosTrabalhados = totalMinutosTrabalhados;
		this.totalPercentualConclusao = totalPercentualConclusao;
		this.cdTicketAssociado = cdTicketAssociado;
		this.snPrioritario = snPrioritario;
		this.nrOrdemPrioridade = nrOrdemPrioridade;
	}


	@Override
	public String toString() {
		return "Chamado [cdTicket=" + cdTicket + ", dsTicket=" + dsTicket + ", cdUsuario=" + cdUsuario
				+ ", dsObservacao=" + dsObservacao + ", snAtivo=" + snAtivo + ", totalMinutosTrabalhados="
				+ totalMinutosTrabalhados + ", totalPercentualConclusao=" + totalPercentualConclusao
				+ ", cdTicketAssociado=" + cdTicketAssociado + ", snPrioritario=" + snPrioritario
				+ ", nrOrdemPrioridade=" + nrOrdemPrioridade + ", dtUltimaIntervencao=" + dtUltimaIntervencao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdTicket == null) ? 0 : cdTicket.hashCode());
		result = prime * result + ((cdTicketAssociado == null) ? 0 : cdTicketAssociado.hashCode());
		result = prime * result + ((cdUsuario == null) ? 0 : cdUsuario.hashCode());
		result = prime * result + ((dsObservacao == null) ? 0 : dsObservacao.hashCode());
		result = prime * result + ((dsTicket == null) ? 0 : dsTicket.hashCode());
		result = prime * result + ((dtUltimaIntervencao == null) ? 0 : dtUltimaIntervencao.hashCode());
		result = prime * result + ((nrOrdemPrioridade == null) ? 0 : nrOrdemPrioridade.hashCode());
		result = prime * result + ((snAtivo == null) ? 0 : snAtivo.hashCode());
		result = prime * result + ((snPrioritario == null) ? 0 : snPrioritario.hashCode());
		result = prime * result + ((totalMinutosTrabalhados == null) ? 0 : totalMinutosTrabalhados.hashCode());
		result = prime * result + ((totalPercentualConclusao == null) ? 0 : totalPercentualConclusao.hashCode());
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
		Chamado other = (Chamado) obj;
		if (cdTicket == null) {
			if (other.cdTicket != null)
				return false;
		} else if (!cdTicket.equals(other.cdTicket))
			return false;
		if (cdTicketAssociado == null) {
			if (other.cdTicketAssociado != null)
				return false;
		} else if (!cdTicketAssociado.equals(other.cdTicketAssociado))
			return false;
		if (cdUsuario == null) {
			if (other.cdUsuario != null)
				return false;
		} else if (!cdUsuario.equals(other.cdUsuario))
			return false;
		if (dsObservacao == null) {
			if (other.dsObservacao != null)
				return false;
		} else if (!dsObservacao.equals(other.dsObservacao))
			return false;
		if (dsTicket == null) {
			if (other.dsTicket != null)
				return false;
		} else if (!dsTicket.equals(other.dsTicket))
			return false;
		if (dtUltimaIntervencao == null) {
			if (other.dtUltimaIntervencao != null)
				return false;
		} else if (!dtUltimaIntervencao.equals(other.dtUltimaIntervencao))
			return false;
		if (nrOrdemPrioridade == null) {
			if (other.nrOrdemPrioridade != null)
				return false;
		} else if (!nrOrdemPrioridade.equals(other.nrOrdemPrioridade))
			return false;
		if (snAtivo == null) {
			if (other.snAtivo != null)
				return false;
		} else if (!snAtivo.equals(other.snAtivo))
			return false;
		if (snPrioritario == null) {
			if (other.snPrioritario != null)
				return false;
		} else if (!snPrioritario.equals(other.snPrioritario))
			return false;
		if (totalMinutosTrabalhados == null) {
			if (other.totalMinutosTrabalhados != null)
				return false;
		} else if (!totalMinutosTrabalhados.equals(other.totalMinutosTrabalhados))
			return false;
		if (totalPercentualConclusao == null) {
			if (other.totalPercentualConclusao != null)
				return false;
		} else if (!totalPercentualConclusao.equals(other.totalPercentualConclusao))
			return false;
		return true;
	}

	public Date getDtUltimaIntervencao() {
		return dtUltimaIntervencao;
	}

	public void setDtUltimaIntervencao(Date dtUltimaIntervencao) {
		this.dtUltimaIntervencao = dtUltimaIntervencao;
	}









}

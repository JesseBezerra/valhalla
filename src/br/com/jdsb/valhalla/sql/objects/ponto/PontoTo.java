package br.com.jdsb.valhalla.sql.objects.ponto;

import java.io.Serializable;

public class PontoTo implements Serializable, Comparable<PontoTo> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer cdPonto;
	private String cdDia;
	private String cdUsuario;
	private String hrInic;
	private String hrAlmoco;
	private String hrVolta;
	private String hrSaida;

	public Integer getCdPonto() {
		return cdPonto;
	}

	public void setCdPonto(Integer cdPonto) {
		this.cdPonto = cdPonto;
	}

	public String getCdDia() {
		return cdDia;
	}

	public void setCdDia(String cdDia) {
		this.cdDia = cdDia;
	}

	public String getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public String getHrInic() {
		return hrInic;
	}

	public void setHrInic(String hrInic) {
		this.hrInic = hrInic;
	}

	public String getHrAlmoco() {
		return hrAlmoco;
	}

	public void setHrAlmoco(String hrAlmoco) {
		this.hrAlmoco = hrAlmoco;
	}

	public String getHrVolta() {
		return hrVolta;
	}

	public void setHrVolta(String hrVolta) {
		this.hrVolta = hrVolta;
	}

	public String getHrSaida() {
		return hrSaida;
	}

	public void setHrSaida(String hrSaida) {
		this.hrSaida = hrSaida;
	}


	public PontoTo(Integer cdPonto, String cdDia, String cdUsuario, String hrInic, String hrAlmoco, String hrVolta,
			String hrSaida) {
		super();
		this.cdPonto = cdPonto;
		this.cdDia = cdDia;
		this.cdUsuario = cdUsuario;
		this.hrInic = hrInic;
		this.hrAlmoco = hrAlmoco;
		this.hrVolta = hrVolta;
		this.hrSaida = hrSaida;
	}

	public PontoTo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(PontoTo arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdDia == null) ? 0 : cdDia.hashCode());
		result = prime * result + ((cdPonto == null) ? 0 : cdPonto.hashCode());
		result = prime * result + ((cdUsuario == null) ? 0 : cdUsuario.hashCode());
		result = prime * result + ((hrAlmoco == null) ? 0 : hrAlmoco.hashCode());
		result = prime * result + ((hrInic == null) ? 0 : hrInic.hashCode());
		result = prime * result + ((hrSaida == null) ? 0 : hrSaida.hashCode());
		result = prime * result + ((hrVolta == null) ? 0 : hrVolta.hashCode());
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
		PontoTo other = (PontoTo) obj;
		if (cdDia == null) {
			if (other.cdDia != null)
				return false;
		} else if (!cdDia.equals(other.cdDia))
			return false;
		if (cdPonto == null) {
			if (other.cdPonto != null)
				return false;
		} else if (!cdPonto.equals(other.cdPonto))
			return false;
		if (cdUsuario == null) {
			if (other.cdUsuario != null)
				return false;
		} else if (!cdUsuario.equals(other.cdUsuario))
			return false;
		if (hrAlmoco == null) {
			if (other.hrAlmoco != null)
				return false;
		} else if (!hrAlmoco.equals(other.hrAlmoco))
			return false;
		if (hrInic == null) {
			if (other.hrInic != null)
				return false;
		} else if (!hrInic.equals(other.hrInic))
			return false;
		if (hrSaida == null) {
			if (other.hrSaida != null)
				return false;
		} else if (!hrSaida.equals(other.hrSaida))
			return false;
		if (hrVolta == null) {
			if (other.hrVolta != null)
				return false;
		} else if (!hrVolta.equals(other.hrVolta))
			return false;
		return true;
	}



}

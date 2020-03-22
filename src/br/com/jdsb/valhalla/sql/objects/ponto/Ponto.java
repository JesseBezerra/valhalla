package br.com.jdsb.valhalla.sql.objects.ponto;

import java.io.Serializable;
import java.util.Date;

public class Ponto implements Serializable, Comparable<Ponto> {

	private Integer cdPonto;

	private Date dtInicPonto;

	private Date dtAlmocoPonto;

	private Date dtVoltaPonto;

	private Date dtFimPonto;

	private String cdUsuario;

	@Override
	public int compareTo(Ponto o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Integer getCdPonto() {
		return cdPonto;
	}

	public void setCdPonto(Integer cdPonto) {
		this.cdPonto = cdPonto;
	}

	public Date getDtInicPonto() {
		return dtInicPonto;
	}

	public void setDtInicPonto(Date dtInicPonto) {
		this.dtInicPonto = dtInicPonto;
	}

	public Date getDtAlmocoPonto() {
		return dtAlmocoPonto;
	}

	public void setDtAlmocoPonto(Date dtAlmocoPonto) {
		this.dtAlmocoPonto = dtAlmocoPonto;
	}

	public Date getDtVoltaPonto() {
		return dtVoltaPonto;
	}

	public void setDtVoltaPonto(Date dtVoltaPonto) {
		this.dtVoltaPonto = dtVoltaPonto;
	}

	public Date getDtFimPonto() {
		return dtFimPonto;
	}

	public void setDtFimPonto(Date dtFimPonto) {
		this.dtFimPonto = dtFimPonto;
	}

	public String getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public Ponto(Integer cdPonto, Date dtInicPonto, Date dtAlmocoPonto, Date dtVoltaPonto, Date dtFimPonto,
			String cdUsuario) {
		super();
		this.cdPonto = cdPonto;
		this.dtInicPonto = dtInicPonto;
		this.dtAlmocoPonto = dtAlmocoPonto;
		this.dtVoltaPonto = dtVoltaPonto;
		this.dtFimPonto = dtFimPonto;
		this.cdUsuario = cdUsuario;
	}

	public Ponto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdPonto == null) ? 0 : cdPonto.hashCode());
		result = prime * result + ((cdUsuario == null) ? 0 : cdUsuario.hashCode());
		result = prime * result + ((dtAlmocoPonto == null) ? 0 : dtAlmocoPonto.hashCode());
		result = prime * result + ((dtFimPonto == null) ? 0 : dtFimPonto.hashCode());
		result = prime * result + ((dtInicPonto == null) ? 0 : dtInicPonto.hashCode());
		result = prime * result + ((dtVoltaPonto == null) ? 0 : dtVoltaPonto.hashCode());
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
		Ponto other = (Ponto) obj;
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
		if (dtAlmocoPonto == null) {
			if (other.dtAlmocoPonto != null)
				return false;
		} else if (!dtAlmocoPonto.equals(other.dtAlmocoPonto))
			return false;
		if (dtFimPonto == null) {
			if (other.dtFimPonto != null)
				return false;
		} else if (!dtFimPonto.equals(other.dtFimPonto))
			return false;
		if (dtInicPonto == null) {
			if (other.dtInicPonto != null)
				return false;
		} else if (!dtInicPonto.equals(other.dtInicPonto))
			return false;
		if (dtVoltaPonto == null) {
			if (other.dtVoltaPonto != null)
				return false;
		} else if (!dtVoltaPonto.equals(other.dtVoltaPonto))
			return false;
		return true;
	}




}

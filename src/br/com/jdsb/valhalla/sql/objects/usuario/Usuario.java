package br.com.jdsb.valhalla.sql.objects.usuario;

import java.io.Serializable;

public class Usuario implements Comparable<Usuario>, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String cdUsuario;

	private String nmUsuario;

	private String tpPerfil;

	private String snAtivo;

	@Override
	public int compareTo(Usuario o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public String getTpPerfil() {
		return tpPerfil;
	}

	public void setTpPerfil(String tpPerfil) {
		this.tpPerfil = tpPerfil;
	}

	public String getSnAtivo() {
		return snAtivo;
	}

	public void setSnAtivo(String snAtivo) {
		this.snAtivo = snAtivo;
	}

	public Usuario(String cdUsuario, String nmUsuario, String tpPerfil, String snAtivo) {
		super();
		this.cdUsuario = cdUsuario;
		this.nmUsuario = nmUsuario;
		this.tpPerfil = tpPerfil;
		this.snAtivo = snAtivo;
	}

    public Usuario() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdUsuario == null) ? 0 : cdUsuario.hashCode());
		result = prime * result + ((nmUsuario == null) ? 0 : nmUsuario.hashCode());
		result = prime * result + ((snAtivo == null) ? 0 : snAtivo.hashCode());
		result = prime * result + ((tpPerfil == null) ? 0 : tpPerfil.hashCode());
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
		Usuario other = (Usuario) obj;
		if (cdUsuario == null) {
			if (other.cdUsuario != null)
				return false;
		} else if (!cdUsuario.equals(other.cdUsuario))
			return false;
		if (nmUsuario == null) {
			if (other.nmUsuario != null)
				return false;
		} else if (!nmUsuario.equals(other.nmUsuario))
			return false;
		if (snAtivo == null) {
			if (other.snAtivo != null)
				return false;
		} else if (!snAtivo.equals(other.snAtivo))
			return false;
		if (tpPerfil == null) {
			if (other.tpPerfil != null)
				return false;
		} else if (!tpPerfil.equals(other.tpPerfil))
			return false;
		return true;
	}



}

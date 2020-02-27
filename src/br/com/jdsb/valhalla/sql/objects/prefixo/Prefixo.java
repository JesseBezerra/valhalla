package br.com.jdsb.valhalla.sql.objects.prefixo;

import java.io.Serializable;
import java.math.BigInteger;

public class Prefixo implements Serializable, Comparable<Prefixo> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger cdPrefixo;

	private String dsPrefixo;

	private String vlPrefixo;

	private String tpDado;

	private String tpObjeto;

	private String snAtivo;


	public BigInteger getCdPrefixo() {
		return cdPrefixo;
	}

	public void setCdPrefixo(BigInteger cdPrefixo) {
		this.cdPrefixo = cdPrefixo;
	}

	public String getDsPrefixo() {
		return dsPrefixo;
	}

	public void setDsPrefixo(String dsPrefixo) {
		this.dsPrefixo = dsPrefixo;
	}

	public String getVlPrefixo() {
		return vlPrefixo;
	}

	public void setVlPrefixo(String vlPrefixo) {
		this.vlPrefixo = vlPrefixo;
	}

	public String getTpDado() {
		return tpDado;
	}

	public void setTpDado(String tpDado) {
		this.tpDado = tpDado;
	}

	public String getTpObjeto() {
		return tpObjeto;
	}

	public void setTpObjeto(String tpObjeto) {
		this.tpObjeto = tpObjeto;
	}

	public String getSnAtivo() {
		return snAtivo;
	}

	public void setSnAtivo(String snAtivo) {
		this.snAtivo = snAtivo;
	}

	@Override
	public int compareTo(Prefixo o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Prefixo() {
		// TODO Auto-generated constructor stub
	}


	public Prefixo(BigInteger cdPrefixo, String dsPrefixo, String vlPrefixo, String tpDado, String tpObjeto,
			String snAtivo) {
		super();
		this.cdPrefixo = cdPrefixo;
		this.dsPrefixo = dsPrefixo;
		this.vlPrefixo = vlPrefixo;
		this.tpDado = tpDado;
		this.tpObjeto = tpObjeto;
		this.snAtivo = snAtivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdPrefixo == null) ? 0 : cdPrefixo.hashCode());
		result = prime * result + ((dsPrefixo == null) ? 0 : dsPrefixo.hashCode());
		result = prime * result + ((snAtivo == null) ? 0 : snAtivo.hashCode());
		result = prime * result + ((tpDado == null) ? 0 : tpDado.hashCode());
		result = prime * result + ((tpObjeto == null) ? 0 : tpObjeto.hashCode());
		result = prime * result + ((vlPrefixo == null) ? 0 : vlPrefixo.hashCode());
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
		Prefixo other = (Prefixo) obj;
		if (cdPrefixo == null) {
			if (other.cdPrefixo != null)
				return false;
		} else if (!cdPrefixo.equals(other.cdPrefixo))
			return false;
		if (dsPrefixo == null) {
			if (other.dsPrefixo != null)
				return false;
		} else if (!dsPrefixo.equals(other.dsPrefixo))
			return false;
		if (snAtivo == null) {
			if (other.snAtivo != null)
				return false;
		} else if (!snAtivo.equals(other.snAtivo))
			return false;
		if (tpDado == null) {
			if (other.tpDado != null)
				return false;
		} else if (!tpDado.equals(other.tpDado))
			return false;
		if (tpObjeto == null) {
			if (other.tpObjeto != null)
				return false;
		} else if (!tpObjeto.equals(other.tpObjeto))
			return false;
		if (vlPrefixo == null) {
			if (other.vlPrefixo != null)
				return false;
		} else if (!vlPrefixo.equals(other.vlPrefixo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prefixo [cdPrefixo=" + cdPrefixo + ", dsPrefixo=" + dsPrefixo + ", vlPrefixo=" + vlPrefixo + ", tpDado="
				+ tpDado + ", tpObjeto=" + tpObjeto + ", snAtivo=" + snAtivo + "]";
	}















}

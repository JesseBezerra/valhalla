package br.com.jdsb.valhalla.sql.objects.estoque;

import java.io.Serializable;

public class Estoque implements Serializable, Comparable<Estoque> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compareTo(Estoque o) {
		// TODO Auto-generated method stub
		return 0;
	}

	private String cdEstoque;
	private String dsEstoque;
	private String cdConfigSupri;
	
	/**
	 * CD_ESTOQUE,
	 * DS_ESTOQUE,
	 * CD_CONFIG_SUPRI
	 * @return
	 */

	public String getCdEstoque() {
		return cdEstoque;
	}

	public void setCdEstoque(String cdEstoque) {
		this.cdEstoque = cdEstoque;
	}

	public String getDsEstoque() {
		return dsEstoque;
	}

	public void setDsEstoque(String dsEstoque) {
		this.dsEstoque = dsEstoque;
	}

	public Estoque() {
		// TODO Auto-generated constructor stub
	}

	public String getCdConfigSupri() {
		return cdConfigSupri;
	}

	public void setCdConfigSupri(String cdConfigSupri) {
		this.cdConfigSupri = cdConfigSupri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdConfigSupri == null) ? 0 : cdConfigSupri.hashCode());
		result = prime * result + ((cdEstoque == null) ? 0 : cdEstoque.hashCode());
		result = prime * result + ((dsEstoque == null) ? 0 : dsEstoque.hashCode());
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
		Estoque other = (Estoque) obj;
		if (cdConfigSupri == null) {
			if (other.cdConfigSupri != null)
				return false;
		} else if (!cdConfigSupri.equals(other.cdConfigSupri))
			return false;
		if (cdEstoque == null) {
			if (other.cdEstoque != null)
				return false;
		} else if (!cdEstoque.equals(other.cdEstoque))
			return false;
		if (dsEstoque == null) {
			if (other.dsEstoque != null)
				return false;
		} else if (!dsEstoque.equals(other.dsEstoque))
			return false;
		return true;
	}

	public Estoque(String cdEstoque, String dsEstoque, String cdConfigSupri) {
		super();
		this.cdEstoque = cdEstoque;
		this.dsEstoque = dsEstoque;
		this.cdConfigSupri = cdConfigSupri;
	}





}

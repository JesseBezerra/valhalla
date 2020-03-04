package br.com.jdsb.valhalla.sql.objects.configsupri;

import java.io.Serializable;
import java.math.BigInteger;

public class ConfigSupri implements Comparable<ConfigSupri>, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compareTo(ConfigSupri o) {
		// TODO Auto-generated method stub
		return 0;
	}

	private BigInteger cdConfigSupri;
	private BigInteger cdConexao;
	private String cdMultiEmpresa;
	private String cdEstoque;
	private String cdEspec;
	private String cdClass;
	private String cdSubClass;
	private String qtSaldoPadrao;
	private String dsUnidadePadrao;

	public BigInteger getCdConfigSupri() {
		return cdConfigSupri;
	}
	public void setCdConfigSupri(BigInteger cdConfigSupri) {
		this.cdConfigSupri = cdConfigSupri;
	}
	public BigInteger getCdConexao() {
		return cdConexao;
	}
	public void setCdConexao(BigInteger cdConexao) {
		this.cdConexao = cdConexao;
	}
	public String getCdMultiEmpresa() {
		return cdMultiEmpresa;
	}
	public void setCdMultiEmpresa(String cdMultiEmpresa) {
		this.cdMultiEmpresa = cdMultiEmpresa;
	}
	public String getCdEstoque() {
		return cdEstoque;
	}
	public void setCdEstoque(String cdEstoque) {
		this.cdEstoque = cdEstoque;
	}
	public String getCdEspec() {
		return cdEspec;
	}
	public void setCdEspec(String cdEspec) {
		this.cdEspec = cdEspec;
	}
	public String getCdClass() {
		return cdClass;
	}
	public void setCdClass(String cdClass) {
		this.cdClass = cdClass;
	}
	public String getCdSubClass() {
		return cdSubClass;
	}
	public void setCdSubClass(String cdSubClass) {
		this.cdSubClass = cdSubClass;
	}
	public String getQtSaldoPadrao() {
		return qtSaldoPadrao;
	}
	public void setQtSaldoPadrao(String qtSaldoPadrao) {
		this.qtSaldoPadrao = qtSaldoPadrao;
	}
	public String getDsUnidadePadrao() {
		return dsUnidadePadrao;
	}
	public void setDsUnidadePadrao(String dsUnidadePadrao) {
		this.dsUnidadePadrao = dsUnidadePadrao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdClass == null) ? 0 : cdClass.hashCode());
		result = prime * result + ((cdConexao == null) ? 0 : cdConexao.hashCode());
		result = prime * result + ((cdConfigSupri == null) ? 0 : cdConfigSupri.hashCode());
		result = prime * result + ((cdEspec == null) ? 0 : cdEspec.hashCode());
		result = prime * result + ((cdEstoque == null) ? 0 : cdEstoque.hashCode());
		result = prime * result + ((cdMultiEmpresa == null) ? 0 : cdMultiEmpresa.hashCode());
		result = prime * result + ((cdSubClass == null) ? 0 : cdSubClass.hashCode());
		result = prime * result + ((dsUnidadePadrao == null) ? 0 : dsUnidadePadrao.hashCode());
		result = prime * result + ((qtSaldoPadrao == null) ? 0 : qtSaldoPadrao.hashCode());
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
		ConfigSupri other = (ConfigSupri) obj;
		if (cdClass == null) {
			if (other.cdClass != null)
				return false;
		} else if (!cdClass.equals(other.cdClass))
			return false;
		if (cdConexao == null) {
			if (other.cdConexao != null)
				return false;
		} else if (!cdConexao.equals(other.cdConexao))
			return false;
		if (cdConfigSupri == null) {
			if (other.cdConfigSupri != null)
				return false;
		} else if (!cdConfigSupri.equals(other.cdConfigSupri))
			return false;
		if (cdEspec == null) {
			if (other.cdEspec != null)
				return false;
		} else if (!cdEspec.equals(other.cdEspec))
			return false;
		if (cdEstoque == null) {
			if (other.cdEstoque != null)
				return false;
		} else if (!cdEstoque.equals(other.cdEstoque))
			return false;
		if (cdMultiEmpresa == null) {
			if (other.cdMultiEmpresa != null)
				return false;
		} else if (!cdMultiEmpresa.equals(other.cdMultiEmpresa))
			return false;
		if (cdSubClass == null) {
			if (other.cdSubClass != null)
				return false;
		} else if (!cdSubClass.equals(other.cdSubClass))
			return false;
		if (dsUnidadePadrao == null) {
			if (other.dsUnidadePadrao != null)
				return false;
		} else if (!dsUnidadePadrao.equals(other.dsUnidadePadrao))
			return false;
		if (qtSaldoPadrao == null) {
			if (other.qtSaldoPadrao != null)
				return false;
		} else if (!qtSaldoPadrao.equals(other.qtSaldoPadrao))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ConfigSupri [cdConfigSupri=" + cdConfigSupri + ", cdConexao=" + cdConexao + ", cdMultiEmpresa="
				+ cdMultiEmpresa + ", cdEstoque=" + cdEstoque + ", cdEspec=" + cdEspec + ", cdClass=" + cdClass
				+ ", cdSubClass=" + cdSubClass + ", qtSaldoPadrao=" + qtSaldoPadrao + ", dsUnidadePadrao="
				+ dsUnidadePadrao + "]";
	}

	/**
	 * CD_CONFIG_SUPRI
	 * CD_CONEXAO
	 * CD_MULTI_EMPRESA
	 * CD_ESTOQUE
	 * CD_ESPEC
	 * CD_CLASS
	 * CD_SUB_CLASS
	 * QT_SALDO_PADRAO
	 * DS_UNIDADE_PADRAO
	 */









}

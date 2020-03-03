package br.com.jdsb.valhalla.sql.objects.produtosupri;

import java.io.Serializable;
import java.math.BigInteger;

public class ProdutoSupri implements Serializable, Comparable<ProdutoSupri> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compareTo(ProdutoSupri arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ProdutoSupri() {
		// TODO Auto-generated constructor stub
	}

	private BigInteger cdProduto;

	private String dsProduto;

	private String cdTickt;

	private String snLote;

	private String snValidade;

	private String snConsignado;

	private String snMestre;

	private String snKit;

	private String snEtiqueta;

	private String snPadronizado;

	private String snBloqueio;

	private String snMovimenta;

	private String snGeraEtiquetaAuto;

	private String snGeraEntradaAuto;

	private String dsUnidade;

	private BigInteger qtSaldo;

	private BigInteger cdEstoque;

	private BigInteger cdEspec;

	private BigInteger cdClass;

	private BigInteger cdSubClass;

	private String tpKit;

	private String cdKit;

	private String dsEtiquetaGerada;

	private String cdEntProGerada;

	public BigInteger getCdProduto() {
		return cdProduto;
	}

	public void setCdProduto(BigInteger cdProduto) {
		this.cdProduto = cdProduto;
	}

	public String getDsProduto() {
		return dsProduto;
	}

	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}

	public String getCdTickt() {
		return cdTickt;
	}

	public void setCdTickt(String cdTickt) {
		this.cdTickt = cdTickt;
	}

	public String getSnLote() {
		return snLote;
	}

	public void setSnLote(String snLote) {
		this.snLote = snLote;
	}

	public String getSnValidade() {
		return snValidade;
	}

	public void setSnValidade(String snValidade) {
		this.snValidade = snValidade;
	}

	public String getSnConsignado() {
		return snConsignado;
	}

	public void setSnConsignado(String snConsignado) {
		this.snConsignado = snConsignado;
	}

	public String getSnMestre() {
		return snMestre;
	}

	public void setSnMestre(String snMestre) {
		this.snMestre = snMestre;
	}

	public String getSnKit() {
		return snKit;
	}

	public void setSnKit(String snKit) {
		this.snKit = snKit;
	}

	public String getSnEtiqueta() {
		return snEtiqueta;
	}

	public void setSnEtiqueta(String snEtiqueta) {
		this.snEtiqueta = snEtiqueta;
	}

	public String getSnPadronizado() {
		return snPadronizado;
	}

	public void setSnPadronizado(String snPadronizado) {
		this.snPadronizado = snPadronizado;
	}

	public String getSnBloqueio() {
		return snBloqueio;
	}

	public void setSnBloqueio(String snBloqueio) {
		this.snBloqueio = snBloqueio;
	}

	public String getSnMovimenta() {
		return snMovimenta;
	}

	public void setSnMovimenta(String snMovimenta) {
		this.snMovimenta = snMovimenta;
	}

	public String getSnGeraEtiquetaAuto() {
		return snGeraEtiquetaAuto;
	}

	public void setSnGeraEtiquetaAuto(String snGeraEtiquetaAuto) {
		this.snGeraEtiquetaAuto = snGeraEtiquetaAuto;
	}

	public String getSnGeraEntradaAuto() {
		return snGeraEntradaAuto;
	}

	public void setSnGeraEntradaAuto(String snGeraEntradaAuto) {
		this.snGeraEntradaAuto = snGeraEntradaAuto;
	}

	public String getDsUnidade() {
		return dsUnidade;
	}

	public void setDsUnidade(String dsUnidade) {
		this.dsUnidade = dsUnidade;
	}

	public BigInteger getQtSaldo() {
		return qtSaldo;
	}

	public void setQtSaldo(BigInteger qtSaldo) {
		this.qtSaldo = qtSaldo;
	}

	public BigInteger getCdEstoque() {
		return cdEstoque;
	}

	public void setCdEstoque(BigInteger cdEstoque) {
		this.cdEstoque = cdEstoque;
	}

	public BigInteger getCdEspec() {
		return cdEspec;
	}

	public void setCdEspec(BigInteger cdEspec) {
		this.cdEspec = cdEspec;
	}

	public BigInteger getCdClass() {
		return cdClass;
	}

	public void setCdClass(BigInteger cdClass) {
		this.cdClass = cdClass;
	}

	public BigInteger getCdSubClass() {
		return cdSubClass;
	}

	public void setCdSubClass(BigInteger cdSubClass) {
		this.cdSubClass = cdSubClass;
	}

	public String getTpKit() {
		return tpKit;
	}

	public void setTpKit(String tpKit) {
		this.tpKit = tpKit;
	}

	public String getCdKit() {
		return cdKit;
	}

	public void setCdKit(String cdKit) {
		this.cdKit = cdKit;
	}

	public String getDsEtiquetaGerada() {
		return dsEtiquetaGerada;
	}

	public void setDsEtiquetaGerada(String dsEtiquetaGerada) {
		this.dsEtiquetaGerada = dsEtiquetaGerada;
	}

	public String getCdEntProGerada() {
		return cdEntProGerada;
	}

	public void setCdEntProGerada(String cdEntProGerada) {
		this.cdEntProGerada = cdEntProGerada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdClass == null) ? 0 : cdClass.hashCode());
		result = prime * result + ((cdEntProGerada == null) ? 0 : cdEntProGerada.hashCode());
		result = prime * result + ((cdEspec == null) ? 0 : cdEspec.hashCode());
		result = prime * result + ((cdEstoque == null) ? 0 : cdEstoque.hashCode());
		result = prime * result + ((cdKit == null) ? 0 : cdKit.hashCode());
		result = prime * result + ((cdProduto == null) ? 0 : cdProduto.hashCode());
		result = prime * result + ((cdSubClass == null) ? 0 : cdSubClass.hashCode());
		result = prime * result + ((cdTickt == null) ? 0 : cdTickt.hashCode());
		result = prime * result + ((dsEtiquetaGerada == null) ? 0 : dsEtiquetaGerada.hashCode());
		result = prime * result + ((dsProduto == null) ? 0 : dsProduto.hashCode());
		result = prime * result + ((dsUnidade == null) ? 0 : dsUnidade.hashCode());
		result = prime * result + ((qtSaldo == null) ? 0 : qtSaldo.hashCode());
		result = prime * result + ((snBloqueio == null) ? 0 : snBloqueio.hashCode());
		result = prime * result + ((snConsignado == null) ? 0 : snConsignado.hashCode());
		result = prime * result + ((snEtiqueta == null) ? 0 : snEtiqueta.hashCode());
		result = prime * result + ((snGeraEntradaAuto == null) ? 0 : snGeraEntradaAuto.hashCode());
		result = prime * result + ((snGeraEtiquetaAuto == null) ? 0 : snGeraEtiquetaAuto.hashCode());
		result = prime * result + ((snKit == null) ? 0 : snKit.hashCode());
		result = prime * result + ((snLote == null) ? 0 : snLote.hashCode());
		result = prime * result + ((snMestre == null) ? 0 : snMestre.hashCode());
		result = prime * result + ((snMovimenta == null) ? 0 : snMovimenta.hashCode());
		result = prime * result + ((snPadronizado == null) ? 0 : snPadronizado.hashCode());
		result = prime * result + ((snValidade == null) ? 0 : snValidade.hashCode());
		result = prime * result + ((tpKit == null) ? 0 : tpKit.hashCode());
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
		ProdutoSupri other = (ProdutoSupri) obj;
		if (cdClass == null) {
			if (other.cdClass != null)
				return false;
		} else if (!cdClass.equals(other.cdClass))
			return false;
		if (cdEntProGerada == null) {
			if (other.cdEntProGerada != null)
				return false;
		} else if (!cdEntProGerada.equals(other.cdEntProGerada))
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
		if (cdKit == null) {
			if (other.cdKit != null)
				return false;
		} else if (!cdKit.equals(other.cdKit))
			return false;
		if (cdProduto == null) {
			if (other.cdProduto != null)
				return false;
		} else if (!cdProduto.equals(other.cdProduto))
			return false;
		if (cdSubClass == null) {
			if (other.cdSubClass != null)
				return false;
		} else if (!cdSubClass.equals(other.cdSubClass))
			return false;
		if (cdTickt == null) {
			if (other.cdTickt != null)
				return false;
		} else if (!cdTickt.equals(other.cdTickt))
			return false;
		if (dsEtiquetaGerada == null) {
			if (other.dsEtiquetaGerada != null)
				return false;
		} else if (!dsEtiquetaGerada.equals(other.dsEtiquetaGerada))
			return false;
		if (dsProduto == null) {
			if (other.dsProduto != null)
				return false;
		} else if (!dsProduto.equals(other.dsProduto))
			return false;
		if (dsUnidade == null) {
			if (other.dsUnidade != null)
				return false;
		} else if (!dsUnidade.equals(other.dsUnidade))
			return false;
		if (qtSaldo == null) {
			if (other.qtSaldo != null)
				return false;
		} else if (!qtSaldo.equals(other.qtSaldo))
			return false;
		if (snBloqueio == null) {
			if (other.snBloqueio != null)
				return false;
		} else if (!snBloqueio.equals(other.snBloqueio))
			return false;
		if (snConsignado == null) {
			if (other.snConsignado != null)
				return false;
		} else if (!snConsignado.equals(other.snConsignado))
			return false;
		if (snEtiqueta == null) {
			if (other.snEtiqueta != null)
				return false;
		} else if (!snEtiqueta.equals(other.snEtiqueta))
			return false;
		if (snGeraEntradaAuto == null) {
			if (other.snGeraEntradaAuto != null)
				return false;
		} else if (!snGeraEntradaAuto.equals(other.snGeraEntradaAuto))
			return false;
		if (snGeraEtiquetaAuto == null) {
			if (other.snGeraEtiquetaAuto != null)
				return false;
		} else if (!snGeraEtiquetaAuto.equals(other.snGeraEtiquetaAuto))
			return false;
		if (snKit == null) {
			if (other.snKit != null)
				return false;
		} else if (!snKit.equals(other.snKit))
			return false;
		if (snLote == null) {
			if (other.snLote != null)
				return false;
		} else if (!snLote.equals(other.snLote))
			return false;
		if (snMestre == null) {
			if (other.snMestre != null)
				return false;
		} else if (!snMestre.equals(other.snMestre))
			return false;
		if (snMovimenta == null) {
			if (other.snMovimenta != null)
				return false;
		} else if (!snMovimenta.equals(other.snMovimenta))
			return false;
		if (snPadronizado == null) {
			if (other.snPadronizado != null)
				return false;
		} else if (!snPadronizado.equals(other.snPadronizado))
			return false;
		if (snValidade == null) {
			if (other.snValidade != null)
				return false;
		} else if (!snValidade.equals(other.snValidade))
			return false;
		if (tpKit == null) {
			if (other.tpKit != null)
				return false;
		} else if (!tpKit.equals(other.tpKit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProdutoSupri [cdProduto=" + cdProduto + ", dsProduto=" + dsProduto + ", cdTickt=" + cdTickt
				+ ", snLote=" + snLote + ", snValidade=" + snValidade + ", snConsignado=" + snConsignado + ", snMestre="
				+ snMestre + ", snKit=" + snKit + ", snEtiqueta=" + snEtiqueta + ", snPadronizado=" + snPadronizado
				+ ", snBloqueio=" + snBloqueio + ", snMovimenta=" + snMovimenta + ", snGeraEtiquetaAuto="
				+ snGeraEtiquetaAuto + ", snGeraEntradaAuto=" + snGeraEntradaAuto + ", dsUnidade=" + dsUnidade
				+ ", qtSaldo=" + qtSaldo + ", cdEstoque=" + cdEstoque + ", cdEspec=" + cdEspec + ", cdClass=" + cdClass
				+ ", cdSubClass=" + cdSubClass + ", tpKit=" + tpKit + ", cdKit=" + cdKit + ", dsEtiquetaGerada="
				+ dsEtiquetaGerada + ", cdEntProGerada=" + cdEntProGerada + "]";
	}














}

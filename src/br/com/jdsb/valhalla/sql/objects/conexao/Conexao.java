package br.com.jdsb.valhalla.sql.objects.conexao;

import java.io.Serializable;
import java.math.BigInteger;

public class Conexao implements Serializable, Comparable<Conexao> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger cdConexao;

	private String dsConexao;

    private String dsUrl;

    private String dsPorta;

    private String dsSid;

    private String tpConexao;

    private String dsUsuario;

    private String dsSenha;

    private boolean isCliente;

    private String snAtivo;

	@Override
	public int compareTo(Conexao arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public BigInteger getCdConexao() {
		return cdConexao;
	}

	public void setCdConexao(BigInteger cdConexao) {
		this.cdConexao = cdConexao;
	}

	public String getDsConexao() {
		return dsConexao;
	}

	public void setDsConexao(String dsConexao) {
		this.dsConexao = dsConexao;
	}

	public String getDsUrl() {
		return dsUrl;
	}

	public void setDsUrl(String dsUrl) {
		this.dsUrl = dsUrl;
	}

	public String getDsPorta() {
		return dsPorta;
	}

	public void setDsPorta(String dsPorta) {
		this.dsPorta = dsPorta;
	}

	public String getDsSid() {
		return dsSid;
	}

	public void setDsSid(String dsSid) {
		this.dsSid = dsSid;
	}

	public String getTpConexao() {
		return tpConexao;
	}

	public void setTpConexao(String tpConexao) {
		this.tpConexao = tpConexao;
	}

	public String getDsUsuario() {
		return dsUsuario;
	}

	public void setDsUsuario(String dsUsuario) {
		this.dsUsuario = dsUsuario;
	}

	public String getDsSenha() {
		return dsSenha;
	}

	public void setDsSenha(String dsSenha) {
		this.dsSenha = dsSenha;
	}

	public boolean isCliente() {
		return isCliente;
	}

	public void setCliente(boolean isCliente) {
		this.isCliente = isCliente;
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
		result = prime * result + ((cdConexao == null) ? 0 : cdConexao.hashCode());
		result = prime * result + ((dsConexao == null) ? 0 : dsConexao.hashCode());
		result = prime * result + ((dsPorta == null) ? 0 : dsPorta.hashCode());
		result = prime * result + ((dsSenha == null) ? 0 : dsSenha.hashCode());
		result = prime * result + ((dsSid == null) ? 0 : dsSid.hashCode());
		result = prime * result + ((dsUrl == null) ? 0 : dsUrl.hashCode());
		result = prime * result + ((dsUsuario == null) ? 0 : dsUsuario.hashCode());
		result = prime * result + (isCliente ? 1231 : 1237);
		result = prime * result + ((snAtivo == null) ? 0 : snAtivo.hashCode());
		result = prime * result + ((tpConexao == null) ? 0 : tpConexao.hashCode());
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
		Conexao other = (Conexao) obj;
		if (cdConexao == null) {
			if (other.cdConexao != null)
				return false;
		} else if (!cdConexao.equals(other.cdConexao))
			return false;
		if (dsConexao == null) {
			if (other.dsConexao != null)
				return false;
		} else if (!dsConexao.equals(other.dsConexao))
			return false;
		if (dsPorta == null) {
			if (other.dsPorta != null)
				return false;
		} else if (!dsPorta.equals(other.dsPorta))
			return false;
		if (dsSenha == null) {
			if (other.dsSenha != null)
				return false;
		} else if (!dsSenha.equals(other.dsSenha))
			return false;
		if (dsSid == null) {
			if (other.dsSid != null)
				return false;
		} else if (!dsSid.equals(other.dsSid))
			return false;
		if (dsUrl == null) {
			if (other.dsUrl != null)
				return false;
		} else if (!dsUrl.equals(other.dsUrl))
			return false;
		if (dsUsuario == null) {
			if (other.dsUsuario != null)
				return false;
		} else if (!dsUsuario.equals(other.dsUsuario))
			return false;
		if (isCliente != other.isCliente)
			return false;
		if (snAtivo == null) {
			if (other.snAtivo != null)
				return false;
		} else if (!snAtivo.equals(other.snAtivo))
			return false;
		if (tpConexao == null) {
			if (other.tpConexao != null)
				return false;
		} else if (!tpConexao.equals(other.tpConexao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conexao [cdConexao=" + cdConexao + ", dsConexao=" + dsConexao + ", dsUrl=" + dsUrl + ", dsPorta="
				+ dsPorta + ", dsSid=" + dsSid + ", tpConexao=" + tpConexao + ", dsUsuario=" + dsUsuario + ", dsSenha="
				+ dsSenha + ", isCliente=" + isCliente + ", snAtivo=" + snAtivo + "]";
	}

    public Conexao() {
		// TODO Auto-generated constructor stub
	}

	public Conexao(BigInteger cdConexao, String dsConexao, String dsUrl, String dsPorta, String dsSid, String tpConexao,
			String dsUsuario, String dsSenha, boolean isCliente, String snAtivo) {
		super();
		this.cdConexao = cdConexao;
		this.dsConexao = dsConexao;
		this.dsUrl = dsUrl;
		this.dsPorta = dsPorta;
		this.dsSid = dsSid;
		this.tpConexao = tpConexao;
		this.dsUsuario = dsUsuario;
		this.dsSenha = dsSenha;
		this.isCliente = isCliente;
		this.snAtivo = snAtivo;
	}




}
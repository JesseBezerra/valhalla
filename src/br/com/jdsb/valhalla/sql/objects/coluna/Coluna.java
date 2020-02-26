package br.com.jdsb.valhalla.sql.objects.coluna;

public class Coluna implements Comparable<Coluna>{

	private String dsOwner;
	private String dsTabela;
	private String dsColuna;
	private String tpDado;
	private String vlTamanho;
	private String vlDefault;
	private boolean isContraint;
	private boolean isNull;
	private boolean isIndice;
	private String dsConstraint;
	private String dsComentario;

	public Coluna(String dsOwner, String dsTabela, String dsColuna, String tpDado, String vlTamanho, String vlDefault,
			boolean isContraint, boolean isNull, boolean isIndice, String dsConstraint, String dsComentario) {
		super();
		this.dsOwner = dsOwner;
		this.dsTabela = dsTabela;
		this.dsColuna = dsColuna;
		this.tpDado = tpDado;
		this.vlTamanho = vlTamanho;
		this.vlDefault = vlDefault;
		this.isContraint = isContraint;
		this.isNull = isNull;
		this.isIndice = isIndice;
		this.dsConstraint = dsConstraint;
		this.dsComentario = dsComentario;
	}

	public Coluna() {
	}

	public String getDsOwner() {
		return dsOwner;
	}

	public void setDsOwner(String dsOwner) {
		this.dsOwner = dsOwner;
	}

	public String getDsTabela() {
		return dsTabela;
	}

	public void setDsTabela(String dsTabela) {
		this.dsTabela = dsTabela;
	}

	public String getDsColuna() {
		return dsColuna;
	}

	public void setDsColuna(String dsColuna) {
		this.dsColuna = dsColuna;
	}

	public String getTpDado() {
		return tpDado;
	}

	public void setTpDado(String tpDado) {
		this.tpDado = tpDado;
	}

	public String getVlTamanho() {
		return vlTamanho;
	}

	public void setVlTamanho(String vlTamanho) {
		this.vlTamanho = vlTamanho;
	}

	public String getVlDefault() {
		return vlDefault;
	}

	public void setVlDefault(String vlDefault) {
		this.vlDefault = vlDefault;
	}

	public boolean isContraint() {
		return isContraint;
	}

	public void setContraint(boolean isContraint) {
		this.isContraint = isContraint;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public boolean isIndice() {
		return isIndice;
	}

	public void setIndice(boolean isIndice) {
		this.isIndice = isIndice;
	}

	public String getDsConstraint() {
		return dsConstraint;
	}

	public void setDsConstraint(String dsConstraint) {
		this.dsConstraint = dsConstraint;
	}

	public String getDsComentario() {
		return dsComentario;
	}

	public void setDsComentario(String dsComentario) {
		this.dsComentario = dsComentario;
	}

	@Override
	public int compareTo(Coluna o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dsColuna == null) ? 0 : dsColuna.hashCode());
		result = prime * result + ((dsComentario == null) ? 0 : dsComentario.hashCode());
		result = prime * result + ((dsConstraint == null) ? 0 : dsConstraint.hashCode());
		result = prime * result + ((dsOwner == null) ? 0 : dsOwner.hashCode());
		result = prime * result + ((dsTabela == null) ? 0 : dsTabela.hashCode());
		result = prime * result + (isContraint ? 1231 : 1237);
		result = prime * result + (isIndice ? 1231 : 1237);
		result = prime * result + (isNull ? 1231 : 1237);
		result = prime * result + ((tpDado == null) ? 0 : tpDado.hashCode());
		result = prime * result + ((vlDefault == null) ? 0 : vlDefault.hashCode());
		result = prime * result + ((vlTamanho == null) ? 0 : vlTamanho.hashCode());
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
		Coluna other = (Coluna) obj;
		if (dsColuna == null) {
			if (other.dsColuna != null)
				return false;
		} else if (!dsColuna.equals(other.dsColuna))
			return false;
		if (dsComentario == null) {
			if (other.dsComentario != null)
				return false;
		} else if (!dsComentario.equals(other.dsComentario))
			return false;
		if (dsConstraint == null) {
			if (other.dsConstraint != null)
				return false;
		} else if (!dsConstraint.equals(other.dsConstraint))
			return false;
		if (dsOwner == null) {
			if (other.dsOwner != null)
				return false;
		} else if (!dsOwner.equals(other.dsOwner))
			return false;
		if (dsTabela == null) {
			if (other.dsTabela != null)
				return false;
		} else if (!dsTabela.equals(other.dsTabela))
			return false;
		if (isContraint != other.isContraint)
			return false;
		if (isIndice != other.isIndice)
			return false;
		if (isNull != other.isNull)
			return false;
		if (tpDado == null) {
			if (other.tpDado != null)
				return false;
		} else if (!tpDado.equals(other.tpDado))
			return false;
		if (vlDefault == null) {
			if (other.vlDefault != null)
				return false;
		} else if (!vlDefault.equals(other.vlDefault))
			return false;
		if (vlTamanho == null) {
			if (other.vlTamanho != null)
				return false;
		} else if (!vlTamanho.equals(other.vlTamanho))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coluna [dsOwner=" + dsOwner + ", dsTabela=" + dsTabela + ", dsColuna=" + dsColuna + ", tpDado=" + tpDado
				+ ", vlTamanho=" + vlTamanho + ", vlDefault=" + vlDefault + ", isContraint=" + isContraint + ", isNull="
				+ isNull + ", isIndice=" + isIndice + ", dsConstraint=" + dsConstraint + ", dsComentario="
				+ dsComentario + "]";
	}



}

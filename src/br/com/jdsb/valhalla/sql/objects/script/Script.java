package br.com.jdsb.valhalla.sql.objects.script;

import java.util.List;

import br.com.jdsb.valhalla.sql.objects.coluna.Coluna;

public class Script {

	private String cdScript;
	private String nmScript;
	private String cdVersao;
	private String cdMilestone;
	private String dsScript;

	private List<Coluna> colunas;

	public Script() {
	}

	public Script(String cdScript, String nmScript, String cdVersao, String cdMilestone, String dsScript,
			List<Coluna> colunas) {
		super();
		this.cdScript = cdScript;
		this.nmScript = nmScript;
		this.cdVersao = cdVersao;
		this.cdMilestone = cdMilestone;
		this.dsScript = dsScript;
		this.colunas = colunas;
	}

	public String getCdScript() {
		return cdScript;
	}

	public void setCdScript(String cdScript) {
		this.cdScript = cdScript;
	}

	public String getNmScript() {
		return nmScript;
	}

	public void setNmScript(String nmScript) {
		this.nmScript = nmScript;
	}

	public String getCdVersao() {
		return cdVersao;
	}

	public void setCdVersao(String cdVersao) {
		this.cdVersao = cdVersao;
	}

	public String getCdMilestone() {
		return cdMilestone;
	}

	public void setCdMilestone(String cdMilestone) {
		this.cdMilestone = cdMilestone;
	}

	public String getDsScript() {
		return dsScript;
	}

	public void setDsScript(String dsScript) {
		this.dsScript = dsScript;
	}

	public List<Coluna> getColunas() {
		return colunas;
	}

	public void setColunas(List<Coluna> colunas) {
		this.colunas = colunas;
	}

}

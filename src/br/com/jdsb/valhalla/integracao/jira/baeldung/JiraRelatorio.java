package br.com.jdsb.valhalla.integracao.jira.baeldung;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.Worklog;

import br.com.jdsb.valhalla.sql.core.texto.StringUtil;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;

public class JiraRelatorio {

	private JiraRestClient restClient;
	public JiraRelatorio() {
		restClient = new JiraClient("jesse.bezerra", "N@ruto2019", "https://jira.mv.com.br/").restClient;
	}

	public void getTicketsDoDia(Usuario usuario) {
		Integer retorno = 0;
		String consulta = "Produto in (SUPRI, SUPRI-COMP, SUPRIMENTOS) and worklogAuthor = %s  and worklogDate = -1d";
		SearchRestClient searchClient = restClient.getSearchClient();
		try {
			SearchResult result = searchClient
					.searchJql(String.format(consulta, usuario.getCdUsuario()), 20, 1 - 10, null).claim();
			Iterable<Issue> listaIssues = result.getIssues();
			List<Issue> isses = new ArrayList<Issue>();
			listaIssues.forEach(isses::add);
			StringUtil util = new StringUtil();
			for (Issue issue : isses) {
				issue = restClient.getIssueClient().getIssue(issue.getKey()).claim();
				System.out.println(issue.getKey());
				System.out.println(issue.getSummary());
				System.out.println(issue.getStatus().getName());
				System.out.println(issue.getFieldByName("Cliente").getValue());
				Iterable<Worklog> log = issue.getWorklogs();
				List<Worklog> resultados = new ArrayList<Worklog>();
				log.forEach(resultados::add);
				int minutos=0;
				for (Worklog worklog : resultados) {
					if (worklog.getAuthor().getName().equals(usuario.getCdUsuario()) && worklog.getStartDate()
							.toString("dd/MM/yyyy").equals(util.converteDataParametro(new Date()))) {
System.out.println(worklog.getMinutesSpent());

					}
				}
				System.out.println(minutos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		JiraRelatorio jiraRelatorio = new JiraRelatorio();
		Usuario usuario = new Usuario();
		usuario.setCdUsuario("jesse.bezerra");
		jiraRelatorio.getTicketsDoDia(usuario);
	}



}

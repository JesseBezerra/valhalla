package br.com.jdsb.valhalla.integracao.jira.baeldung;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueFieldId;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.Transition;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

import br.com.jdsb.valhalla.sql.core.texto.StringUtil;
import br.com.jdsb.valhalla.sql.objects.chamado.Chamado;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;

public class JiraClient {

	private String username;
	private String password;
	private String jiraUrl;

	public JiraRestClient restClient;

	public JiraClient(String username, String password, String jiraUrl) {
		super();
		this.username = username;
		this.password = password;
		this.jiraUrl = jiraUrl;
		this.restClient = getJiraRestClient();
	}

	private URI getJiraUri() {
		return URI.create(this.jiraUrl);
	}

	private JiraRestClient getJiraRestClient() {
		return new AsynchronousJiraRestClientFactory().createWithBasicHttpAuthentication(getJiraUri(), this.username,
				this.password);
	}

	public Issue getIssue(String issueKey) {
		return restClient.getIssueClient().getIssue(issueKey).claim();
	}

	public void addComment(Issue issue, String commentBody) {
		restClient.getIssueClient().addComment(issue.getCommentsUri(), Comment.valueOf(commentBody));
	}

	public void validarTicket(Chamado correcao) {
		Promise<Issue> promiseIssue = restClient.getIssueClient().getIssue(correcao.getCdTicket());
		Issue issue = promiseIssue.claim();
		try {
			Iterator<Transition> transitions = restClient.getIssueClient().getTransitions(issue).get().iterator();
			int transitionId = 0;
			JiraFaseController controller = new JiraFaseController(restClient);
			while (transitions.hasNext()) {
				promiseIssue = restClient.getIssueClient().getIssue(correcao.getCdTicket());
				transitions = restClient.getIssueClient().getTransitions(issue).get().iterator();
				issue = promiseIssue.claim();
				Transition transition = transitions.next();
				if (transition.getName().equals("Analisar N2")) {
					transitionId = 311;
				} else if (transition.getName().equals("Realizar suporte N2")) {
					transitionId = 321;
				} else if (transition.getName().equals("Enviar para validação")) {
					transitionId = 261;
				} else if (transition.getName().toUpperCase().equals("VALIDAR")
						&& issue.getStatus().getName().equals("EM ATENDIMENTO")) {
					transitionId = 861;
					controller.mudarFase(transitionId, issue, correcao);
					break;
				} else if (transition.getName().toUpperCase().equals("TRIAR")
						&& issue.getStatus().getName().equals("ABERTO")) {
					transitionId = 731;
					controller.mudarFase(transitionId, issue, correcao);
					transitions = restClient.getIssueClient().getTransitions(issue).get().iterator();
				} else if (transition.getName().toUpperCase().equals("Backlog atendimento".toUpperCase())
						&& issue.getStatus().getName().equals("EM TRIAGEM")) {
					transitionId = 741;
					controller.mudarFase(transitionId, issue, correcao);
				} else if (transition.getName().toUpperCase().equals("Atender".toUpperCase())
						&& issue.getStatus().getName().equals("AGUARDANDO ATENDIMENTO")) {
					transitionId = 761;
					controller.mudarFase(transitionId, issue, correcao);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizarReleaseNotes(Chamado correcao) {
		Promise<Issue> promiseIssue = restClient.getIssueClient().getIssue(correcao.getCdTicket());
		Issue issue = promiseIssue.claim();
		Iterable<Worklog> log = issue.getWorklogs();
		List<Worklog> result = new ArrayList<Worklog>();
		log.forEach(result::add);

		for (Worklog worklog : result) {
			System.out.println(worklog.getAuthor().getName());
			System.out.println(worklog.getStartDate());
			System.out.println(worklog.getMinutesSpent());

		}

		IssueRestClient irc = restClient.getIssueClient();
		Map<String, FieldInput> valMap = new HashMap<String, FieldInput>();
		String texto = "LOCALIZAÇÃO:\n\nOCORRÊNCIA:\n\nALTERAÇÃO:\n\nTICKETS RELACIONADOS:";
		valMap.put("customfield_12313", new FieldInput("customfield_12313", texto));
		// valMap.put("customfield_12313", new FieldInput("customfield_12313",
		// texto));
		// valMap.put("assignee", new FieldInput(IssueFieldId.ASSIGNEE_FIELD,
		// ComplexIssueInputFieldValue.with("name",
		// "jesse.bezerra")));
		// IssueInput ii = new IssueInput(valMap);
		//
		// irc.updateIssue(correcao.getCdTicket(), ii).claim();
		User promiseUser =  restClient.getUserClient().getUser("jesse.bezerra").claim();

	}

	public Integer getTempoApontado(Usuario usuario) {
		Integer retorno = 0;
		String consulta = "updatedDate >= startOfDay() and worklogAuthor = %s";
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
				Iterable<Worklog> log = issue.getWorklogs();
				List<Worklog> resultados = new ArrayList<Worklog>();
				log.forEach(resultados::add);
				for (Worklog worklog : resultados) {
					if (worklog.getAuthor().getName().equals(usuario.getCdUsuario()) && worklog.getStartDate()
							.toString("dd/MM/yyyy").equals(util.converteDataParametro(new Date()))) {
						retorno = retorno + worklog.getMinutesSpent();

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public void getTicketsDoDia(Usuario usuario) {
		Integer retorno = 0;
		String consulta = "updatedDate >= startOfDay() and worklogAuthor = %s";
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
				Iterable<Worklog> log = issue.getWorklogs();
				List<Worklog> resultados = new ArrayList<Worklog>();
				log.forEach(resultados::add);
				for (Worklog worklog : resultados) {
					if (worklog.getAuthor().getName().equals(usuario.getCdUsuario()) && worklog.getStartDate()
							.toString("dd/MM/yyyy").equals(util.converteDataParametro(new Date()))) {
						retorno = retorno + worklog.getMinutesSpent();

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void apontarAtividade(Chamado correcao, String comentario) {
		JiraApontamentoController apontamentoController = new JiraApontamentoController(restClient);
		apontamentoController.realizarApontamento(correcao, comentario);
	}

	public void apontarAtividade(Chamado correcao, String comentario, int minutos) {
		JiraApontamentoController apontamentoController = new JiraApontamentoController(restClient);
		apontamentoController.realizarApontamento(correcao, comentario, minutos);
	}

	public void assumirTicket(Chamado chamado) {
		IssueRestClient irc = restClient.getIssueClient();
		Map<String, FieldInput> valMap = new HashMap<String, FieldInput>();
		valMap.put("assignee", new FieldInput(IssueFieldId.ASSIGNEE_FIELD,
				ComplexIssueInputFieldValue.with("name", chamado.getCdUsuario())));
		IssueInput ii = new IssueInput(valMap);

		irc.updateIssue(chamado.getCdTicket(), ii).claim();
	}

	public static void main(String[] args) {
		JiraClient client = new JiraClient("jesse.bezerra", "N@ruto2019", "https://jira.mv.com.br/");
//		DaoChamado dao = new DaoChamado();
//		Chamado chamado = new Chamado();
//		chamado.setCdTicket("CIMP-15079");
		// dao.consultar("SUPRI-15929");
		// client.atualizarReleaseNotes(chamado);
		Usuario usuario = new Usuario();
		usuario.setCdUsuario("jesse.bezerra");
		System.out.println(client.getTempoApontado(usuario));
		int valor = 251;
		int resultado = valor % 60;
		int  divisao = (valor / 60);
		System.out.println(resultado);
		System.out.println(divisao);
	}

}

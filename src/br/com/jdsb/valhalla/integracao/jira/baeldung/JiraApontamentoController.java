package br.com.jdsb.valhalla.integracao.jira.baeldung;

import org.joda.time.DateTime;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.WorklogInput;
import com.atlassian.jira.rest.client.api.domain.input.WorklogInputBuilder;
import com.atlassian.util.concurrent.Promise;

import br.com.jdsb.valhalla.sql.objects.chamado.Chamado;

public class JiraApontamentoController {
	private JiraRestClient restClient;

	public JiraApontamentoController(JiraRestClient restClient) {
		this.restClient = restClient;
	}

	public void realizarApontamento(Chamado correcao){
		Promise<Issue> promiseIssue = restClient.getIssueClient().getIssue(correcao.getCdTicket());
		Issue issue = promiseIssue.claim();
		WorklogInput worklogInput = null;

		worklogInput = new WorklogInputBuilder(issue.getSelf()).setStartDate(new DateTime())
				.setComment(correcao.getDsObservacao()).setMinutesSpent(60).build();
		restClient.getIssueClient().addWorklog(issue.getWorklogUri(), worklogInput);
	}

	public void realizarApontamento(Chamado correcao,String comentario){
		Promise<Issue> promiseIssue = restClient.getIssueClient().getIssue(correcao.getCdTicket());
		Issue issue = promiseIssue.claim();
		WorklogInput worklogInput = null;
		String comentarioFinal = "Apontamento de atividades registrado por %s apontando %s por cento de conclusão na atividade.";
		if(comentario!=null){
			comentarioFinal = comentarioFinal.concat(".\n").concat(comentario);
		}
		comentarioFinal = String.format(comentarioFinal, correcao.getCdUsuario(), correcao.getTotalPercentualConclusao());

		worklogInput = new WorklogInputBuilder(issue.getSelf()).setStartDate(new DateTime())
				.setComment(comentarioFinal).setMinutesSpent(60).build();
		restClient.getIssueClient().addWorklog(issue.getWorklogUri(), worklogInput);
	}

	public void realizarApontamento(Chamado correcao,String comentario,int qtdMinutos){
		Promise<Issue> promiseIssue = restClient.getIssueClient().getIssue(correcao.getCdTicket());
		Issue issue = promiseIssue.claim();
		WorklogInput worklogInput = null;
		String comentarioFinal = "Apontamento de atividades registrado por %s";
		if(comentario!=null){
			comentarioFinal = comentarioFinal.concat(".\n").concat(comentario);
		}
		comentarioFinal = String.format(comentarioFinal, correcao.getCdUsuario(), correcao.getTotalPercentualConclusao());

		worklogInput = new WorklogInputBuilder(issue.getSelf()).setStartDate(new DateTime())
				.setComment(comentarioFinal).setMinutesSpent(qtdMinutos).build();
		restClient.getIssueClient().addWorklog(issue.getWorklogUri(), worklogInput);
	}
}

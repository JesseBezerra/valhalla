package br.com.jdsb.valhalla.integracao.jira.baeldung;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.atlassian.jira.rest.client.api.domain.input.TransitionInput;
import com.atlassian.jira.rest.client.api.domain.input.WorklogInput;
import com.atlassian.jira.rest.client.api.domain.input.WorklogInputBuilder;

import br.com.jdsb.valhalla.sql.objects.chamado.Chamado;

public class JiraFaseController {

	private JiraRestClient restClient;

	public JiraFaseController(JiraRestClient restClient) {
		this.restClient = restClient;
	}

	public void mudarFase(int idTransition, Issue issue,Chamado correcao) {
		TransitionInput tInput =null;
		WorklogInput worklogInput = null;
		final FieldInput fieldInput = new FieldInput("customfield_23800", ComplexIssueInputFieldValue.with("value", "Dúvida"));
		final FieldInput fieldInputClassifica = new FieldInput("customfield_17804", ComplexIssueInputFieldValue.with("value", "Requisição"));
        List<FieldInput> lista = new ArrayList<FieldInput>();
        lista.add(fieldInput);
        lista.add(fieldInputClassifica);

		switch (idTransition) {
		case 731:
			 tInput = new TransitionInput(idTransition, null, null);
			restClient.getIssueClient().transition(issue, tInput).claim();

			 worklogInput = new WorklogInputBuilder(issue.getSelf()).setStartDate(new DateTime())
					.setComment(correcao.getDsObservacao()).setMinutesSpent(1).build();
			restClient.getIssueClient().addWorklog(issue.getWorklogUri(), worklogInput);
			break;
		case 741:
			 tInput = new TransitionInput(idTransition, lista, null);
			restClient.getIssueClient().transition(issue, tInput).claim();

			worklogInput = new WorklogInputBuilder(issue.getSelf()).setStartDate(new DateTime())
					.setComment(correcao.getDsObservacao()).setMinutesSpent(1).build();
			restClient.getIssueClient().addWorklog(issue.getWorklogUri(), worklogInput);
			break;
		case 761:
			 tInput = new TransitionInput(idTransition, null, null);
			restClient.getIssueClient().transition(issue, tInput).claim();

			worklogInput = new WorklogInputBuilder(issue.getSelf()).setStartDate(new DateTime())
					.setComment(correcao.getDsObservacao()).setMinutesSpent(1).build();
			restClient.getIssueClient().addWorklog(issue.getWorklogUri(), worklogInput);
			break;
		case 861:
			 tInput = new TransitionInput(idTransition, lista, null);
				restClient.getIssueClient().transition(issue, tInput).claim();

				worklogInput = new WorklogInputBuilder(issue.getSelf()).setStartDate(new DateTime())
						.setComment("[~portal.cliente] ".concat(correcao.getDsObservacao())).setMinutesSpent(1).build();
				restClient.getIssueClient().addWorklog(issue.getWorklogUri(), worklogInput);
			break;

		default:
			break;
		}
	}

	public void mudarFaseSupri(int idTransition, Issue issue,Chamado correcao) {
		TransitionInput tInput =null;
		WorklogInput worklogInput = null;
		final FieldInput fieldInput = new FieldInput("customfield_12313", ComplexIssueInputFieldValue.with("value", "Dúvida"));
        List<FieldInput> lista = new ArrayList<FieldInput>();
        lista.add(fieldInput);

		switch (idTransition) {
		case 11:
			 tInput = new TransitionInput(idTransition, lista, null);
			restClient.getIssueClient().transition(issue, tInput).claim();
			 worklogInput = new WorklogInputBuilder(issue.getSelf()).setStartDate(new DateTime())
					.setComment(correcao.getDsObservacao()).setMinutesSpent(1).build();
			restClient.getIssueClient().addWorklog(issue.getWorklogUri(), worklogInput);
			break;
		case 21:
			 tInput = new TransitionInput(idTransition, lista, null);
			restClient.getIssueClient().transition(issue, tInput).claim();

			 worklogInput = new WorklogInputBuilder(issue.getSelf()).setStartDate(new DateTime())
					.setComment(correcao.getDsObservacao()).setMinutesSpent(1).build();
			restClient.getIssueClient().addWorklog(issue.getWorklogUri(), worklogInput);
			break;

		case 151:
			 tInput = new TransitionInput(idTransition, lista, null);
			restClient.getIssueClient().transition(issue, tInput).claim();

			 worklogInput = new WorklogInputBuilder(issue.getSelf()).setStartDate(new DateTime())
					.setComment(correcao.getDsObservacao()).setMinutesSpent(1).build();
			restClient.getIssueClient().addWorklog(issue.getWorklogUri(), worklogInput);
			break;

		default:
			break;
		}
	}

}

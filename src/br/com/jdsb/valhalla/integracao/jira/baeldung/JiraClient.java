package br.com.jdsb.valhalla.integracao.jira.baeldung;

import java.net.URI;
import java.util.Iterator;

import org.joda.time.DateTime;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Transition;
import com.atlassian.jira.rest.client.api.domain.input.TransitionInput;
import com.atlassian.jira.rest.client.api.domain.input.WorklogInput;
import com.atlassian.jira.rest.client.api.domain.input.WorklogInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

import br.com.jdsb.valhalla.sql.core.dao.chamado.DaoChamado;
import br.com.jdsb.valhalla.sql.objects.chamado.Chamado;

public class JiraClient {

	private String username;
	private String password;
	private String jiraUrl;

	private JiraRestClient restClient;

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
	    return new AsynchronousJiraRestClientFactory()
	      .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
	}

	public Issue getIssue(String issueKey) {
	    return restClient.getIssueClient()
	      .getIssue(issueKey)
	      .claim();
	}

	public void addComment(Issue issue, String commentBody) {
	    restClient.getIssueClient()
	      .addComment(issue.getCommentsUri(), Comment.valueOf(commentBody));
	}

	public void validarTicket(Chamado correcao){
		Promise<Issue> promiseIssue = restClient.getIssueClient().getIssue(correcao.getCdTicket());
		Issue issue = promiseIssue.claim();
		try{
	     Iterator<Transition> transitions =
	    		 restClient.getIssueClient().getTransitions(issue).get().iterator();
	        int transitionId = 0;
	        while (transitions.hasNext()) {
	            Transition transition = transitions.next();
	            if(transition.getName().equals("Analisar N2")){
	            	transitionId = 311;
	            }else if (transition.getName().equals("Realizar suporte N2")){
	            	transitionId = 321;
	            }else if (transition.getName().equals("Enviar para validação")){
	            	transitionId = 261;
	            }else if(transition.getName().toUpperCase().equals("VALIDAR")){
	            	transitionId = 861;
	            	break;
	            }

	        }

	        if (transitionId != 0 && transitionId!=261 && transitionId!=861) {
	        	TransitionInput tInput =
    	                new TransitionInput(transitionId, null, null);
	        	restClient.getIssueClient().transition(issue, tInput).claim();


    	    	 final WorklogInput worklogInput = new WorklogInputBuilder(issue.getSelf())
    	    				.setStartDate(new DateTime())
    	    				.setComment(correcao.getDsObservacao())
    	    				.setMinutesSpent(1)
    	    				.build();
    	    	 restClient.getIssueClient().addWorklog(issue.getWorklogUri(),worklogInput);
	        }else if (transitionId==261) {
	        	TransitionInput tInput =
    	                new TransitionInput(transitionId, null, Comment.valueOf("[~portal.cliente] ".concat(correcao.getDsObservacao())));
	        	restClient.getIssueClient().transition(issue, tInput).claim();


    	    	 final WorklogInput worklogInput = new WorklogInputBuilder(issue.getSelf())
    	    				.setStartDate(new DateTime())
    	    				.setComment(correcao.getDsObservacao())
    	    				.setMinutesSpent(60)
    	    				.build();
    	    	 restClient.getIssueClient().addWorklog(issue.getWorklogUri(),worklogInput);
	        }else if(transitionId==861){
	        	TransitionInput tInput =
    	                new TransitionInput(transitionId, null, Comment.valueOf("[~portal.cliente] ".concat(correcao.getDsObservacao())));
	        	restClient.getIssueClient().transition(issue, tInput).claim();


    	    	 final WorklogInput worklogInput = new WorklogInputBuilder(issue.getSelf())
    	    				.setStartDate(new DateTime())
    	    				.setComment(correcao.getDsObservacao())
    	    				.setMinutesSpent(60)
    	    				.build();
    	    	 restClient.getIssueClient().addWorklog(issue.getWorklogUri(),worklogInput);
	        }



	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public static void main(String[] args) {
		JiraClient client = new JiraClient("jesse.bezerra", "N@ruto2019", "https://jira.mv.com.br/");
		DaoChamado dao = new DaoChamado();
		Chamado chamado = dao.consultar("CIMP-14539");
		client.validarTicket(chamado);
	}


}

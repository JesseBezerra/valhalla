package br.com.jdsb.valhalla.integracao.jira.baeldung;

import java.net.URI;
import java.util.Iterator;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Transition;
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
	        JiraFaseController controller = new JiraFaseController(restClient);
	        while (transitions.hasNext()) {
	        	promiseIssue = restClient.getIssueClient().getIssue(correcao.getCdTicket());
	        	transitions =
	   	    		 restClient.getIssueClient().getTransitions(issue).get().iterator();
	        	issue = promiseIssue.claim();
	            Transition transition = transitions.next();
	            if(transition.getName().equals("Analisar N2")){
	            	transitionId = 311;
	            }else if (transition.getName().equals("Realizar suporte N2")){
	            	transitionId = 321;
	            }else if (transition.getName().equals("Enviar para validação")){
	            	transitionId = 261;
	            }
	            else if(transition.getName().toUpperCase().equals("VALIDAR")  && issue.getStatus().getName().equals("EM ATENDIMENTO")){
	            	transitionId = 861;
	            	controller.mudarFase(transitionId, issue, correcao);
	            	break;
	            }else if(transition.getName().toUpperCase().equals("TRIAR") && issue.getStatus().getName().equals("ABERTO")){
	            	transitionId = 731;
	            	controller.mudarFase(transitionId, issue, correcao);
	            	transitions =
	   	   	    		 restClient.getIssueClient().getTransitions(issue).get().iterator();
	            }else if(transition.getName().toUpperCase().equals("Backlog atendimento".toUpperCase()) && issue.getStatus().getName().equals("EM TRIAGEM")){
	            	transitionId = 741;
	    	        controller.mudarFase(transitionId, issue, correcao);
	            }else if(transition.getName().toUpperCase().equals("Atender".toUpperCase()) && issue.getStatus().getName().equals("AGUARDANDO ATENDIMENTO")){
	            	transitionId = 761;
	    	        controller.mudarFase(transitionId, issue, correcao);
	            }
	        }


	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void apontarAtividade(Chamado correcao,String comentario){
		JiraApontamentoController apontamentoController = new JiraApontamentoController(restClient);
		apontamentoController.realizarApontamento(correcao, comentario);

	}

	public static void main(String[] args) {
		JiraClient client = new JiraClient("jesse.bezerra", "N@ruto2019", "https://jira.mv.com.br/");
		DaoChamado dao = new DaoChamado();
		Chamado chamado = dao.consultar("CIMP-14725");
		client.validarTicket(chamado);
	}


}

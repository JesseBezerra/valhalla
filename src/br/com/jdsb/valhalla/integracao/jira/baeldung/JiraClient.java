package br.com.jdsb.valhalla.integracao.jira.baeldung;

import java.net.URI;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

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

	public static void main(String[] args) {
		JiraClient client = new JiraClient("jesse.bezerra", "N@ruto2019", "https://jira.mv.com.br/");
		Issue issue = client.getIssue("SUP-195009");
		System.out.println(issue.getDescription());
		System.out.println(issue.getSummary());
		System.out.println(issue.getStatus().getName());
		System.out.println(issue.getPriority().getName());
	}


}

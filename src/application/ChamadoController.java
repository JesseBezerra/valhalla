package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.atlassian.jira.rest.client.api.domain.Issue;

import br.com.jdsb.valhalla.integracao.jira.baeldung.JiraClient;
import br.com.jdsb.valhalla.sql.core.texto.StringUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChamadoController implements Initializable {

	@FXML
	private TextField cdTicket;

	@FXML
	private TextField dsTitulo;

	@FXML
	private TextArea dsObservacao;

	@FXML
	private TextArea dsTicket;

	@FXML
	private TextField dsPrioridade;

	@FXML
	private ChoiceBox<String> dsUsuarioAtribui;

	@FXML
	private TextField dsSituacao;

	private Issue issue;

	private JiraClient client;

	private StringUtil util;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		client = new JiraClient("jesse.bezerra", "N@ruto2019", "https://jira.mv.com.br/");
		util = new StringUtil();

		this.cdTicket.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					try {
						issue = client.getIssue(cdTicket.getText());
						dsTitulo.setText(issue.getSummary());
						dsTicket.setText(util.quebrarTexto(issue.getDescription()));
						dsSituacao.setText(issue.getStatus().getName());
						dsPrioridade.setText(issue.getPriority().getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	@FXML
	void limpar(ActionEvent event) {

	}

	@FXML
	void remover(ActionEvent event) {

	}

	@FXML
	void salvar(ActionEvent event) {

	}

}

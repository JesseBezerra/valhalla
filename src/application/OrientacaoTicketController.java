package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.atlassian.jira.rest.client.api.domain.Issue;

import br.com.jdsb.valhalla.integracao.jira.baeldung.JiraClient;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.orientacao.padrao.DaoOrientacaoPadrao;
import br.com.jdsb.valhalla.sql.core.dao.orientacao.ticket.DaoOrientacaoTicket;
import br.com.jdsb.valhalla.sql.objects.orientacao.padrao.OrientacaoPadrao;
import br.com.jdsb.valhalla.sql.objects.orientacao.ticket.OrientacaoTicket;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OrientacaoTicketController implements Initializable {

	Dao<OrientacaoTicket> daoOTicket;
	Dao<OrientacaoPadrao> daoOPadrao;
	ObservableList<String> optionsOrientacaoPadrao = FXCollections.observableArrayList();
    private Issue issue;
    private JiraClient client;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		daoOTicket = new DaoOrientacaoTicket();
		daoOPadrao = new DaoOrientacaoPadrao();
		client = new JiraClient("jesse.bezerra", "N@ruto2019", "https://jira.mv.com.br/");
        carregaOptionObjetos();

		this.tpOrientacaoPadrao.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			// if the item of the list is changed
			public void changed(ObservableValue ov, Number value, Number new_value) {

				String nmUsuario = optionsOrientacaoPadrao.get(new_value.intValue());
				OrientacaoPadrao orientacaoPadrao = daoOPadrao.consultar(nmUsuario);
				String orientacaoAnterior = dsOrientacaoPadrao.getText();
				if(orientacaoAnterior!=null && !orientacaoAnterior.isEmpty() && orientacaoAnterior.length()>2){
					dsOrientacaoPadrao.setText(orientacaoAnterior.concat("\n").concat(orientacaoPadrao.getDsOrientacaoPadrao()));
				}else{
					dsOrientacaoPadrao.setText(orientacaoPadrao.getDsOrientacaoPadrao());
				}

			}
		});


		this.cdTicket.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					try {
						if (cdTicket != null && !cdTicket.getText().isEmpty()) {
							issue = client.getIssue(cdTicket.getText());
							dsTitulo.setText(issue.getSummary());

						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	@FXML
	private TextField cdTicket;

	@FXML
	private TextField dsTitulo;

	@FXML
	private ChoiceBox<String> tpOrientacaoPadrao;

	@FXML
	private TextArea dsOrientacaoPadrao;

	@FXML
	void limpar(ActionEvent event) {
          limpar();
	}

	@FXML
	void salvar(ActionEvent event) {
        OrientacaoTicket orientacaoTicket = new OrientacaoTicket();
        orientacaoTicket.setCdTicket(cdTicket.getText());
        orientacaoTicket.setDsOrientacao(dsOrientacaoPadrao.getText());
        client.addComment(issue, orientacaoTicket.getDsOrientacao());
		daoOTicket.salvar(orientacaoTicket);
		limpar();
	}

	public void carregaOptionObjetos() {
		for (OrientacaoPadrao usuario : daoOPadrao.listar()) {
			optionsOrientacaoPadrao.add(usuario.getNmOrientacaoPadrao());
		}
		tpOrientacaoPadrao.setItems(optionsOrientacaoPadrao);
	}

	public void limpar(){
		cdTicket.clear();
		dsTitulo.clear();
		tpOrientacaoPadrao.setValue("");
		dsOrientacaoPadrao.clear();
	}

}

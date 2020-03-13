package application;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.integracao.cmd.chrome.CMD;
import br.com.jdsb.valhalla.integracao.jira.baeldung.JiraClient;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.chamado.DaoChamado;
import br.com.jdsb.valhalla.sql.core.dao.consultas.Consultas;
import br.com.jdsb.valhalla.sql.objects.chamado.Chamado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class ApontamentoController implements Initializable {

	@FXML
	private ChoiceBox<String> cdTicket;

	@FXML
	private ChoiceBox<String> vlPercConclusao;

	@FXML
	private TextArea dsComentario;

	ObservableList<String> optionsTicket = FXCollections.observableArrayList();
	private Consultas consultas;
	private Dao<Chamado> dao;
	private Chamado chamado;
	private JiraClient jiraClient;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		consultas = new Consultas();
		dao = new DaoChamado();
		carregarOtinsTicket();
		chamado = consultas.getChamadoPrincipal("jesse.bezerra");
		if(chamado!=null){
		  cdTicket.setValue(chamado.getCdTicket());
		  vlPercConclusao.setValue(String.valueOf(chamado.getTotalPercentualConclusao()));
		}
		jiraClient = new JiraClient("jesse.bezerra", "N@ruto2019", "https://jira.mv.com.br");
	}

	public void chrome(){
		CMD.chamaChromeDoes(String.format("https://jira.mv.com.br/browse/%s", cdTicket.getValue()));
	}

	public void apontar() {
		chamado = dao.consultar(cdTicket.getValue());
		chamado.setTotalPercentualConclusao(new BigInteger(vlPercConclusao.getValue()));
		if(chamado.getTotalPercentualConclusao().equals(new BigInteger("100"))){
           chamado.setSnPrioritario("Não");
           chamado.setSnAtivo("Não");
		}
        if(dsComentario!=null && !dsComentario.getText().isEmpty()){
        	   jiraClient.apontarAtividade(chamado, dsComentario.getText());
         }else{
        	   jiraClient.apontarAtividade(chamado, null);
         }
		dao.atualizar(chamado);
	}

	public void carregarOtinsTicket() {
         for(Chamado chamado:consultas.listarChamadosUsuario("jesse.bezerra")){
        	 optionsTicket.add(chamado.getCdTicket());
         }
         cdTicket.setItems(optionsTicket);
	}

}

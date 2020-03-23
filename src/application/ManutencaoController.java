package application;

import java.math.BigInteger;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.atlassian.jira.rest.client.api.domain.Issue;

import br.com.jdsb.valhalla.integracao.cmd.chrome.CMD;
import br.com.jdsb.valhalla.integracao.jira.baeldung.JiraApontamentoController;
import br.com.jdsb.valhalla.integracao.jira.baeldung.JiraClient;
import br.com.jdsb.valhalla.sql.core.dao.chamado.DaoChamado;
import br.com.jdsb.valhalla.sql.core.dao.ponto.DaoPonto;
import br.com.jdsb.valhalla.sql.core.jfx.dialog.Dialogs;
import br.com.jdsb.valhalla.sql.core.texto.StringUtil;
import br.com.jdsb.valhalla.sql.objects.chamado.Chamado;
import br.com.jdsb.valhalla.sql.objects.ponto.Ponto;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class ManutencaoController implements Initializable {

	@FXML
	private Label lbPonto;

	@FXML
	private TextField dtPontoInic;
	@FXML
	private TextField dtPontoAlmoco;
	@FXML
	private TextField dtPontoVolta;
	@FXML
	private TextField dtPontoFinaliza;
	@FXML
	private TextField cdTicket;
	@FXML
	private TextField dsTitulo;
	@FXML
	private TextField dsSituacao;
	@FXML
	private TextArea dsObservacao;
	@FXML
	private TextArea dsTicket;
	@FXML
	private ChoiceBox<String> cdSistema;
	@FXML
	private ChoiceBox<String> vlTempo;
	@FXML
	private TextField qtdTempo;
	@FXML
	private ChoiceBox<String> cdVercao;
	@FXML
	private ChoiceBox<String> cdModulo;
	ObservableList<String> optionsTime = FXCollections.observableArrayList("Minutos", "Horas","");

	private Issue issue;
	private JiraClient client;
    private Chamado chamado;
    private Usuario usuario;

	private DaoPonto dao;
	private DaoChamado daoChamado;

	public void iniciar() {
		Ponto ponto = // new Ponto();
				dao.consultar("jesse.bezerra", new Date());
		if (ponto == null) {
			ponto = new Ponto();
			ponto.setDtInicPonto(new Date());
			ponto.setCdUsuario(usuario.getCdUsuario());
			dao.salvar(ponto);
			dtPontoInic.setText(formatador.format(ponto.getDtInicPonto()));
		} else {
			ponto.setDtInicPonto(new Date());
			ponto.setCdUsuario(usuario.getCdUsuario());
			dao.atualizarInic(ponto);
			dtPontoInic.setText(formatador.format(ponto.getDtInicPonto()));
		}

	}

	@FXML
	private TableView<Chamado> grdChamado;

	@FXML
	private TableColumn<Chamado, String> tbcCdTicket;

	@FXML
	private TableColumn<Chamado, String> tbcDsTitulo;



	public void volta() {
		Ponto ponto = // new Ponto();
				dao.consultar(usuario.getCdUsuario(), new Date());
		ponto.setDtVoltaPonto(new Date());
		dao.atualizarVolta(ponto);
		dtPontoVolta.setText(formatador.format(ponto.getDtVoltaPonto()));
	}

	public void almoco() {
		Ponto ponto = // new Ponto();
				dao.consultar(usuario.getCdUsuario(), new Date());
		ponto.setDtAlmocoPonto(new Date());
		dao.atualizarAlmoco(ponto);
		dtPontoAlmoco.setText(formatador.format(ponto.getDtAlmocoPonto()));
	}

	public void finalizar() {
		Ponto ponto = dao.consultar(usuario.getCdUsuario(), new Date());
		ponto.setDtFimPonto(new Date());
		dao.atualizarFim(ponto);
		dtPontoFinaliza.setText(formatador.format(ponto.getDtFimPonto()));
	}

	private SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
    private StringUtil util;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		util = new StringUtil();
		client = Estatica.jiraClient;
		usuario = Estatica.usuario;
		daoChamado = new DaoChamado();
		dtPontoAlmoco.setDisable(true);
		dtPontoInic.setDisable(true);
		dtPontoVolta.setDisable(true);
		dtPontoFinaliza.setDisable(true);

		KeyFrame frame = new KeyFrame(Duration.millis(1000), e -> atualizaHoras());
		Timeline timeline = new Timeline(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		this.cdTicket.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					try {
						if (cdTicket != null && !cdTicket.getText().isEmpty()) {
							chamado = daoChamado.consultar(cdTicket.getText());
							if(chamado==null){
								chamado = new Chamado();
								chamado.setCdTicket(cdTicket.getText());
								chamado.setCdUsuario("sem.dono");
								daoChamado.salvar(chamado);
								chamado = daoChamado.consultar(cdTicket.getText());
							}
							issue = client.getIssue(cdTicket.getText());
							dsTitulo.setText(issue.getSummary());
							dsTicket.setText(util.quebrarTexto(issue.getDescription()));
							dsSituacao.setText(issue.getStatus().getName());
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		grdChamado.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				chamado = grdChamado.getSelectionModel().getSelectedItem();
				dsObservacao.setText(chamado.getDsObservacao());
				cdTicket.setText(chamado.getCdTicket());
				cdTicketValidate();
			}
		});

		dao = new DaoPonto();
		System.setProperty("user.timezone", "America/Recife");

		Ponto ponto = // new Ponto();
				dao.consultar(usuario.getCdUsuario(), new Date());
		carregarPonto(ponto);
		vlTempo.setItems(optionsTime);
		vlTempo.setValue("Minutos");
		carregarDados();
	}

	public void carregarPonto(Ponto ponto) {
		if (ponto != null) {
			if (ponto.getDtInicPonto() != null) {
				dtPontoInic.setText(formatador.format(ponto.getDtInicPonto()));
			}

			if (ponto.getDtAlmocoPonto() != null) {
				dtPontoAlmoco.setText(formatador.format(ponto.getDtAlmocoPonto()));
			}

			if (ponto.getDtVoltaPonto() != null) {
				dtPontoVolta.setText(formatador.format(ponto.getDtVoltaPonto()));
			}

			if (ponto.getDtFimPonto() != null) {
				dtPontoFinaliza.setText(formatador.format(ponto.getDtFimPonto()));
			}

		}
	}

	private void atualizaHoras() {
		Date agora = new Date();
		lbPonto.setText("Ponto " + formatador.format(agora));

	}

	public void assumir(){
      chamado.setDsTicket(dsTitulo.getText());
      chamado.setDsObservacao(dsObservacao.getText());
      chamado.setSnAtivo("Sim");
      chamado.setSnPrioritario("Sim");
      chamado.setCdUsuario(usuario.getCdUsuario());
      daoChamado.atualizar(chamado);
	  client.assumirTicket(chamado);
	  carregarDados();
	}
	private ObservableList<Chamado> populateTable = FXCollections.observableArrayList();

	private void carregarDados() {
		populateTable = FXCollections.observableArrayList();
		for (Chamado cliente : daoChamado.listarChamadosUsuario(usuario.getCdUsuario())) {
			populateTable.add(cliente);
		}

		tbcCdTicket.setCellValueFactory(new PropertyValueFactory<Chamado, String>("cdTicket"));
		tbcDsTitulo.setCellValueFactory(new PropertyValueFactory<Chamado, String>("dsTicket"));

		grdChamado.setItems(populateTable);
		;
	}

	private void cdTicketValidate() {
		if (cdTicket != null && !cdTicket.getText().isEmpty()) {
			issue = client.getIssue(cdTicket.getText());
			dsTitulo.setText(issue.getSummary());
			dsTicket.setText(util.quebrarTexto(issue.getDescription()));
			dsSituacao.setText(issue.getStatus().getName());
		}

	}

	public void chrome(){
		CMD.chamaChromeDoes(String.format("https://jira.mv.com.br/browse/%s", cdTicket.getText()));
	}

	public void limparTela(){

	}

	public void apontar(){

		if(chamado==null){
			Dialogs.AletaW("Atenção", "Não foi informando nenhum ticket valido","O ticket informado não é valido");
		}

		String valor = vlTempo.getValue();
		int qtdMinutos = 0;
		if(valor.toUpperCase().equals("MINUTOS")){
			qtdMinutos =  tratarTempo(qtdTempo.getText());
		}else if(valor.toUpperCase().equals("HORAS")){
			qtdMinutos =  tratarTempo(qtdTempo.getText());
			qtdMinutos = qtdMinutos * 60;
		}
		try {
			 client.apontarAtividade(chamado, "Apontameno manual", qtdMinutos);
			 Dialogs.AletaI("Atenção", "Ponto Resgistado com sucesso!","Ponto Resgistado com sucesso!");
		} catch (Exception e) {
			Dialogs.AletaE("Atenção", "Ocorreu um erro ao realiar o apontamento", e.getLocalizedMessage());
		}

	}

	public void limparApontamento(){
		vlTempo.setValue("Minutos");
		qtdTempo.clear();
	}

	public int tratarTempo(String tempo){
		Integer retorno = 0;
		try {
			retorno = Integer.parseInt(tempo);
		} catch (Exception e) {
			Dialogs.AletaE("Atenção", "Formato informado é inválido", e.getLocalizedMessage());
		}
		return retorno;
	}

}

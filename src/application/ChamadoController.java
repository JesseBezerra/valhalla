package application;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import com.atlassian.jira.rest.client.api.domain.Issue;

import br.com.jdsb.valhalla.integracao.jira.baeldung.JiraClient;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.chamado.DaoChamado;
import br.com.jdsb.valhalla.sql.core.dao.consultas.Consultas;
import br.com.jdsb.valhalla.sql.core.dao.usuario.DaoUsuario;
import br.com.jdsb.valhalla.sql.core.texto.StringUtil;
import br.com.jdsb.valhalla.sql.objects.chamado.Chamado;
import br.com.jdsb.valhalla.sql.objects.conexao.Conexao;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
	private ChoiceBox<String> snPrioritario;

	@FXML
	private TextField dsSituacao;

	private Issue issue;

	private JiraClient client;

	private StringUtil util;

	private Consultas consultas;

	ObservableList<String> optionsSnAtivo = FXCollections.observableArrayList("Sim","N�o");
	ObservableList<String> optionsUsuario = FXCollections.observableArrayList();

	private Dao<Usuario> dao;

	private Dao<Chamado> daoChamado;

	private Usuario usuario;

	@FXML
	private TableView<Chamado> grdChamado;

	@FXML
	private TableColumn<Chamado, String> tbcCdTicket;

	@FXML
	private TableColumn<Chamado, String> tbcDsTitulo;

	@FXML
	private TableColumn<Chamado, String> tbcSnPrioritario;

	@FXML
	private TableColumn<Chamado, BigInteger> tbcVlPerConclusao;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = new DaoUsuario();
		daoChamado = new DaoChamado();

		consultas = new Consultas();
		client = new JiraClient("jesse.bezerra", "N@ruto2019", "https://jira.mv.com.br/");
		util = new StringUtil();
        snPrioritario.setItems(optionsSnAtivo);
        snPrioritario.setValue("N�o");
        carregaOptionObjetos();

        grdChamado.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	Chamado chamado = new Chamado();
		    	chamado = grdChamado.getSelectionModel().getSelectedItem();
		    	dsObservacao.setText(chamado.getDsObservacao());
		    	cdTicket.setText(chamado.getCdTicket());
		    }
		});

		this.cdTicket.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					try {
						if(cdTicket!=null && !cdTicket.getText().isEmpty()){
								issue = client.getIssue(cdTicket.getText());
								dsTitulo.setText(issue.getSummary());
								dsTicket.setText(util.quebrarTexto(issue.getDescription()));
								dsSituacao.setText(issue.getStatus().getName());
								dsPrioridade.setText(issue.getPriority().getName());
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		this.dsUsuarioAtribui.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {

                String nmUsuario = optionsUsuario.get(new_value.intValue());
                usuario = consultas.consultar(nmUsuario);
                carregarDados();
            }
        });
	}

	@FXML
	void limpar(ActionEvent event) {

	}

	@FXML
	void remover(ActionEvent event) {
		Chamado chamado = new Chamado();
		chamado.setCdTicket(cdTicket.getText());
		chamado.setCdUsuario(usuario.getCdUsuario());
		chamado.setDsObservacao(dsObservacao.getText());
		chamado.setSnAtivo("Sim");
		chamado.setDsTicket(dsTitulo.getText());
		chamado.setSnPrioritario(snPrioritario.getValue());
		chamado.setTotalPercentualConclusao(new BigInteger("0"));
		chamado.setTotalMinutosTrabalhados(new BigInteger("0"));
		daoChamado.remover(chamado);
        carregarDados();
	}

	@FXML
	void salvar(ActionEvent event) {

		Chamado chamado = new Chamado();
		chamado.setCdTicket(cdTicket.getText());
		chamado.setCdUsuario(usuario.getCdUsuario());
		chamado.setDsObservacao(dsObservacao.getText());
		chamado.setSnAtivo("Sim");
		chamado.setDsTicket(dsTitulo.getText());
		chamado.setSnPrioritario(snPrioritario.getValue());
		chamado.setTotalPercentualConclusao(new BigInteger("0"));
		chamado.setTotalMinutosTrabalhados(new BigInteger("0"));
		Chamado aux = daoChamado.consultar(cdTicket.getText());
		if(aux==null){
		daoChamado.salvar(chamado);
		}else{
			daoChamado.atualizar(chamado);
		}
		client.addComment(issue, dsObservacao.getText());
        carregarDados();


	}

	 public void carregaOptionObjetos(){
	       for(Usuario usuario:dao.listar()){
	    	   optionsUsuario.add(usuario.getNmUsuario());
	       }
	       dsUsuarioAtribui.setItems(optionsUsuario);
	    }

	 private ObservableList<Chamado> populateTable = FXCollections.observableArrayList();

	 private void carregarDados() {
			populateTable = FXCollections.observableArrayList();
			for(Chamado cliente:consultas.listarChamadosUsuario(usuario.getCdUsuario())) {
				populateTable.add(cliente);
			}

			tbcCdTicket.setCellValueFactory(new PropertyValueFactory<Chamado,String>("cdTicket"));
			tbcDsTitulo.setCellValueFactory(new PropertyValueFactory<Chamado,String>("dsTicket"));
			tbcSnPrioritario.setCellValueFactory(new PropertyValueFactory<Chamado,String>("snPrioritario"));
			tbcVlPerConclusao.setCellValueFactory(new PropertyValueFactory<Chamado,BigInteger>("totalPercentualConclusao"));

			grdChamado.setItems(populateTable);;
		}

}

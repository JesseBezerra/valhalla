package application;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.conexao.DaoConexao;
import br.com.jdsb.valhalla.sql.objects.conexao.Conexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConexaoController implements Initializable {

	ObservableList<String> optionsConexao = FXCollections.observableArrayList("Extrair","Executar","Consultar");
	ObservableList<String> optionSnAtivo = FXCollections.observableArrayList("Sim","Não");
    private Dao<Conexao> dao;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dao = new DaoConexao();

		tpConexao.setItems(optionsConexao);
		tpConexao.setValue("Executar");

		snAtivo.setItems(optionSnAtivo);
		snAtivo.setValue("Sim");

		snCliente.setItems(optionSnAtivo);
		snCliente.setValue("Não");

		carregarDados();

		grdConexao.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	Conexao conexao = new Conexao();
		    	conexao = grdConexao.getSelectionModel().getSelectedItem();
		    	cdConexao.setText(String.valueOf(conexao.getCdConexao()));
		    	dsConexao.setText(conexao.getDsConexao());
		    	snAtivo.setValue(conexao.getSnAtivo());
		    	dsPorta.setText(conexao.getDsPorta());
		    	dsSenha.setText(conexao.getDsSenha());
		    	dsSid.setText(conexao.getDsSid());
		    	tpConexao.setValue(conexao.getTpConexao());
		    	dsUrl.setText(conexao.getDsUrl());
		    	dsUsuario.setText(conexao.getDsUsuario());
		    }
		});
	}


	private ObservableList<Conexao> populateTable = FXCollections.observableArrayList();

	private void carregarDados() {
		populateTable = FXCollections.observableArrayList();
		for(Conexao cliente:dao.listar()) {
			populateTable.add(cliente);
		}

		tbcCdConexao.setCellValueFactory(new PropertyValueFactory<Conexao,BigInteger>("cdConexao"));
		tbcDsConexao.setCellValueFactory(new PropertyValueFactory<Conexao,String>("dsConexao"));
		tbcSnCliente.setCellValueFactory(new PropertyValueFactory<Conexao,String>("snCliente"));
		tbcTpUtilizacao.setCellValueFactory(new PropertyValueFactory<Conexao,String>("tpConexao"));

		grdConexao.setItems(populateTable);;
	}

	@FXML
	private TableView<Conexao> grdConexao;


	@FXML
    private TextField cdConexao;

    @FXML
    private TextField dsConexao;

    @FXML
    private TextField dsUrl;

    @FXML
    private TextField dsPorta;

    @FXML
    private TextField dsSid;

    @FXML
    private ChoiceBox<String> tpConexao;

    @FXML
    private ChoiceBox<String> snCliente;

    @FXML
    private TextField dsUsuario;

    @FXML
    private TextField dsSenha;


    @FXML
    private TableColumn<Conexao, BigInteger> tbcCdConexao;

    @FXML
    private TableColumn<Conexao, String> tbcDsConexao;

    @FXML
    private TableColumn<Conexao, String> tbcTpUtilizacao;

    @FXML
    private TableColumn<Conexao, String> tbcSnCliente;

    @FXML
    private Button btnRemover;

    @FXML
    private ChoiceBox<String> snAtivo;

    @FXML
    void limpar(ActionEvent event) {
    	dsConexao.clear();
    	dsSenha.clear();
    	dsPorta.clear();
    	cdConexao.clear();
    	dsSid.clear();
    	dsUrl.clear();
    	dsUsuario.clear();
    	snAtivo.setValue("Sim");
    	snCliente.setValue("Não");
    	tpConexao.setValue("Executar");
    }

    @FXML
    void remover(ActionEvent event) {
    	Conexao conexao = new Conexao();
        conexao.setSnCliente(snCliente.getValue());
        conexao.setDsConexao(dsConexao.getText());
        conexao.setDsPorta(dsPorta.getText());
        conexao.setDsSenha(dsSenha.getText());
        conexao.setDsSid(dsSid.getText());
        conexao.setDsUrl(dsUrl.getText());
        conexao.setDsUsuario(dsUsuario.getText());
        conexao.setSnAtivo(snAtivo.getValue());
        conexao.setTpConexao(tpConexao.getValue());
        if(cdConexao.getText()==null || cdConexao.getText().isEmpty()){
       	 limpar(event);
        }else{
       	 conexao.setCdConexao(BigInteger.valueOf(Long.parseLong(cdConexao.getText())));
       	 dao.remover(conexao);
        }

        limpar(event);
        carregarDados();
    }

    @FXML
    void salvar(ActionEvent event) {
     Conexao conexao = new Conexao();
     conexao.setSnCliente(snCliente.getValue());
     conexao.setDsConexao(dsConexao.getText());
     conexao.setDsPorta(dsPorta.getText());
     conexao.setDsSenha(dsSenha.getText());
     conexao.setDsSid(dsSid.getText());
     conexao.setDsUrl(dsUrl.getText());
     conexao.setDsUsuario(dsUsuario.getText());
     conexao.setSnAtivo(snAtivo.getValue());
     conexao.setTpConexao(tpConexao.getValue());
     if(cdConexao.getText()==null || cdConexao.getText().isEmpty()){
    	 dao.salvar(conexao);
     }else{
    	 conexao.setCdConexao(BigInteger.valueOf(Long.parseLong(cdConexao.getText())));
    	 dao.atualizar(conexao);
     }

     limpar(event);
     carregarDados();

    }


}

package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class ProdutoSupriController implements Initializable {

	ObservableList<String> options = FXCollections.observableArrayList("Sim","Não");
	ObservableList<String> optionsConsig = FXCollections.observableArrayList("Consignado","Reprocessado","Normal");
	String valueSim = "Sim";
	String valueNao = "Não";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
       snLote.setValue(valueSim);
       snLote.setItems(options);

       snValidade.setValue(valueSim);
       snValidade.setItems(options);
	}



	@FXML
	private TextField cdTicket;

	@FXML
	private ChoiceBox<String> snLote;

	@FXML
	private ChoiceBox<String> snValidade;

	@FXML
	private ChoiceBox<String> snConigando;

	@FXML
	private ChoiceBox<String> snMestre;

	@FXML
	private ChoiceBox<String> snKIt;

	@FXML
	private ChoiceBox<String> snEtiqueta;

	@FXML
	private ChoiceBox<String> snPadro;

	@FXML
	private ChoiceBox<String> snBloqueia;

	@FXML
	private ChoiceBox<String> snMovimenta;

	@FXML
	private CheckBox snGeraEtiq;

	@FXML
	private CheckBox snGeraEntrada;

	@FXML
	private TextField qtSaldo;

	@FXML
	private TextField cdEstoque;

	@FXML
	private TextField cdProduto;

	@FXML
	private TextField dsProduto;

	@FXML
	private TextField cdEstiq;

	@FXML
	private TextField cdEntPro;

	@FXML
	private ChoiceBox<String> cdKit;

	@FXML
	private ChoiceBox<String> tpConexao;

	@FXML
	private TableColumn<?, String> tbcCdProduto;

	@FXML
	private TableColumn<?, String> tbcSnLote;

	@FXML
	private TableColumn<?, String> tbcSnValidade;

	@FXML
	private TableColumn<?, String> tbcSnConsig;

	@FXML
	private TableColumn<?, String> tbcCdEspec;

	@FXML
	private TableColumn<?, String> tbcCdClass;

	@FXML
	private TableColumn<?, String> tbcCdSunClass;

	@FXML
	private TableColumn<?, String> tbcCdEstoque;

	@FXML
	private TableColumn<?, String> tbcQtSaldo;

	@FXML
	private TextField cdEspec;

	@FXML
	private TextField cdClass;

	@FXML
	private TextField cdSubClass;

	@FXML
	private TextField dsUnid;

	@FXML
	private ChoiceBox<String> tpKit;

	@FXML
	private CheckBox snComponente;

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

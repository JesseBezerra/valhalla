package application;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class ProdutoSupriController implements Initializable {

	ObservableList<String> options = FXCollections.observableArrayList("Sim","Não");
	ObservableList<String> optionsKit = FXCollections.observableArrayList("Formula","Produção");

	ObservableList<String> optionsConexao = FXCollections.observableArrayList();

	ObservableList<String> optionsConsig = FXCollections.observableArrayList("Consignado","Reprocessado","Normal");
	String valueSim = "Sim";
	String valueNao = "Não";

	Dao<Conexao> dao;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	   dao = new DaoConexao();

	   snLote.setValue(valueSim);
       snLote.setItems(options);

       snValidade.setValue(valueSim);
       snValidade.setItems(options);

       snConigando.setValue("Normal");
       snConigando.setItems(optionsConsig);

       snMestre.setValue(valueNao);
       snMestre.setItems(options);

       snKIt.setValue(valueNao);
       snKIt.setItems(options);

       snEtiqueta.setValue(valueSim);
       snEtiqueta.setItems(options);

       snPadro.setValue(valueSim);
       snPadro.setItems(options);

       snMovimenta.setValue(valueSim);
       snMovimenta.setItems(options);

       snBloqueia.setValue(valueNao);
       snBloqueia.setItems(options);

       snGeraEtiq.setSelected(true);

       snGeraEntrada.setSelected(true);

       cdEspec.setText("1");
       cdClass.setText("1");
       cdSubClass.setText("1");

       tpKit.setItems(optionsKit);
       tpKit.setValue("Formula");

       dsUnid.setText("AMP");

       carregarConexoes();

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

	public void carregarConexoes(){
		for(Conexao conexao:dao.listar()){
			optionsConexao.add(conexao.getDsConexao());
		}
		tpConexao.setItems(optionsConexao);
	}

}

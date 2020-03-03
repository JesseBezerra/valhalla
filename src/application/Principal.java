package application;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.objects.coluna.Coluna;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Principal extends Menu implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		isNull.setSelected(true);
	}

	@FXML
	private TextField cdScript;
	@FXML
	private TextField nmScript;
	@FXML
	private TextField dsTabela;
	@FXML
	private TextField dsColuna;
	@FXML
	private TextField dsOwner;
	@FXML
	private ChoiceBox cdVersao;
	@FXML
	private ChoiceBox cdMilestone;
	@FXML
	private TextArea dsScript;

	@FXML
	private TextField vlDefault;

	@FXML
	private TextArea dsConstraint;

	@FXML
	private TextArea dsComentario;

	@FXML
	private ChoiceBox tpDado;
	@FXML
	private TextField vlTamanho;

	@FXML
	private CheckBox isNull;

	@FXML
	private CheckBox isConstraint;

	@FXML
	private CheckBox isIndice;

	@FXML
	private TableView<Coluna> grdColunas;

	@FXML
	private TableColumn<Coluna, String> tbcDsColuna;

	@FXML
	private TableColumn<Coluna, String> tbcTpDado;

	@FXML
	private TableColumn<Coluna, String> tbcVlTamanho;

	@FXML
	private TableColumn<Coluna, String> tbcIsNull;

	public void save(){

	}


}

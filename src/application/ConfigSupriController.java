package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ConfigSupriController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private ChoiceBox<String> tpConexao;

	@FXML
	private ChoiceBox<String> cdMultiEmpresa;

	@FXML
	private ChoiceBox<String> cdEstoque;

	@FXML
	private TableView<?> grdEstoque;

	@FXML
	private TableColumn<?, ?> tbcCdEstoque;

	@FXML
	private TableColumn<?, ?> tbcDsEstoque;

	@FXML
	private TextField cdEspec;

	@FXML
	private TextField cdClass;

	@FXML
	private TextField cdSubClass;

	@FXML
	private TextField qtSaldoPadrao;

	@FXML
	private TextField dsUnidadePadrao;

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

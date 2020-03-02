package application;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.objeto.DaoObjeto;
import br.com.jdsb.valhalla.sql.objects.objeto.Objeto;
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

public class ObjetoController implements Initializable {

	Dao<Objeto> dao = null;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dao = new DaoObjeto();
		snAtivo.setItems(options);
		snAtivo.setValue("Sim");
		carregarDados();
	}

	ObservableList<String> options = FXCollections.observableArrayList("Sim","Não");

	@FXML
	private TextField cdObjeto;

	@FXML
	private TextField dsObjeto;

	@FXML
	private ChoiceBox<String> snPadrao;

	@FXML
	private TableView<Objeto> grdObjetos;

	@FXML
	private TableColumn<Objeto, BigInteger> tbcCdObjeto;

	@FXML
	private TableColumn<Objeto, String> tbcDsObjeto;

	@FXML
	private TableColumn<Objeto, String> tbcSnAtivo;

	@FXML
	private TableColumn<Objeto, String> tbcSnPadrao;

	private ObservableList<Objeto> populateTable = FXCollections.observableArrayList();

    private void carregarDados() {
		populateTable = FXCollections.observableArrayList();
		for(Objeto cliente:dao.listar()) {
			populateTable.add(cliente);
		}

		tbcCdObjeto.setCellValueFactory(new PropertyValueFactory<Objeto,BigInteger>("cdObjeto"));
		tbcDsObjeto.setCellValueFactory(new PropertyValueFactory<Objeto,String>("dsObjeto"));
		tbcSnAtivo.setCellValueFactory(new PropertyValueFactory<Objeto,String>("snAtivo"));
		tbcSnPadrao.setCellValueFactory(new PropertyValueFactory<Objeto,String>("snPadrao"));

		grdObjetos.setItems(populateTable);;
	}

	@FXML
	private Button btn_limpar;

	@FXML
	private Button btn_remover;

	@FXML
	private Button btn_salvar;

	@FXML
	private ChoiceBox<String> snAtivo;

	@FXML
	void limpar(ActionEvent event) {
           limpar();
	}

	@FXML
	void remover(ActionEvent event) {

	}

	@FXML
	void salvar(ActionEvent event) {
        Objeto objeto = new Objeto();
		objeto.setDsObjeto(dsObjeto.getText());
		objeto.setSnAtivo(snAtivo.getValue().toString());
		objeto.setSnPadrao(snPadrao.getValue().toString());

		dao.salvar(objeto);

        objeto = null;
        limpar();
        carregarDados();
	}

	private void limpar(){
		dsObjeto.setText("");
		snAtivo.setValue("Sim");
		snPadrao.setValue("Não");
	}

}

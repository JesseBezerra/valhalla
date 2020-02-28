package application;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.prefixo.DaoPrefixo;
import br.com.jdsb.valhalla.sql.objects.prefixo.Prefixo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrefixoController implements Initializable {

	Dao<Prefixo> dao;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dao = new DaoPrefixo();
	}

	@FXML
    private TextField cdPrefixo;

    @FXML
    private TextField dsPrefixo;

    @FXML
    private TextField vlPrefixo;

    @FXML
    private ChoiceBox<String> tpObjeto;

    @FXML
    private ChoiceBox<String> snAtivo;

    @FXML
    private ChoiceBox<String> tpDado;

    @FXML
    private TableView<Prefixo> grdPrefixo;

    @FXML
    private TableColumn<Prefixo, BigInteger> tbcCdPrefixo;

    @FXML
    private TableColumn<Prefixo, String> tbcDsPrefixo;

    @FXML
    private TableColumn<Prefixo, String> tbcVlPrefixo;

    @FXML
    private TableColumn<Prefixo, String> tbcTpObjeto;

    @FXML
    private TableColumn<Prefixo, String> tbcSnAtivo;

    private ObservableList<Prefixo> populateTable = FXCollections.observableArrayList();

    private void carregarDados() {
		populateTable = FXCollections.observableArrayList();
		for(Prefixo cliente:dao.listar()) {
			populateTable.add(cliente);
		}
		tbcCdPrefixo.setCellValueFactory(new PropertyValueFactory<Prefixo,BigInteger>("cdPrefixo"));
		tbcDsPrefixo.setCellValueFactory(new PropertyValueFactory<Prefixo,String>("dsPrefixo"));

		tbEmailCliente.setCellValueFactory(new PropertyValueFactory<Cliente,String>("eMail"));

		tbSnLTS.setCellValueFactory(new PropertyValueFactory<Cliente,String>("snLts"));
		tbSnAtivo.setCellValueFactory(new PropertyValueFactory<Cliente,String>("snAtivo"));

		grdCliente.setItems(populateTable);;
	}

    public void salvar(){

    }

    public void remover(){

    }

    public void limpar(){

    }




}

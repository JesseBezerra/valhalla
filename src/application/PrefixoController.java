package application;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.objects.prefixo.Prefixo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PrefixoController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

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

    public void salvar(){

    }

    public void remover(){

    }

    public void limpar(){

    }




}

package application;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.objects.produto.Produto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProdutoController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	private TextField cdProduto;

	@FXML
	private TextField dsProduto;

	@FXML
	private ChoiceBox snAtivo;

	@FXML
	private ChoiceBox snPadrao;

	@FXML
	private TableView<Produto> grdProduto;

	@FXML
	private TableColumn<Produto, BigInteger> tbcCdProduto;

	@FXML
	private TableColumn<Produto, String> tbcDsProduto;

	@FXML
	private TableColumn<Produto, String> tbcSnAtivo;

	@FXML
	private TableColumn<Produto, String> tbcSnPadrao;

	public void salvar(){

	}

    public void limpar(){

	}

    public void remover(){

	}



}

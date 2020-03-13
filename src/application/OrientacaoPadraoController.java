package application;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.orientacao.padrao.DaoOrientacaoPadrao;
import br.com.jdsb.valhalla.sql.objects.orientacao.padrao.OrientacaoPadrao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrientacaoPadraoController implements Initializable {
	ObservableList<String> optionsSnAtivo = FXCollections.observableArrayList("Sim", "Não");
	Dao<OrientacaoPadrao> dao;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        snAtivo.setItems(optionsSnAtivo);
		snAtivo.setValue("Sim");
		dao = new DaoOrientacaoPadrao();
		carregarDados();
	}

	@FXML
	private TextField cdOrientacaoPadrao;

	@FXML
	private TextField nmOrientacaoPadrao;

	@FXML
	private Label txtSnValidar;

	@FXML
	private ChoiceBox<String> snAtivo;

	@FXML
	private TextArea dsOrientacaoPadrao;

	@FXML
	private TableView<OrientacaoPadrao> grdOrientacaoPadrao;

	@FXML
	private TableColumn<OrientacaoPadrao, BigInteger> tbcCdOrientacaoPadrao;

	@FXML
	private TableColumn<OrientacaoPadrao, String> tbcNmOrientacaoPadrao;

	@FXML
	private TableColumn<OrientacaoPadrao, String> tbcSnAtivo;

	@FXML
	void limpar(ActionEvent event) {

	}

	private ObservableList<OrientacaoPadrao> populateTable = FXCollections.observableArrayList();

	private void carregarDados() {
		populateTable = FXCollections.observableArrayList();
		for(OrientacaoPadrao cliente:dao.listar()) {
			populateTable.add(cliente);
		}

		tbcCdOrientacaoPadrao.setCellValueFactory(new PropertyValueFactory<OrientacaoPadrao,BigInteger>("cdOrientacaoPadrao"));
		tbcNmOrientacaoPadrao.setCellValueFactory(new PropertyValueFactory<OrientacaoPadrao,String>("nmOrientacaoPadrao"));
		tbcSnAtivo.setCellValueFactory(new PropertyValueFactory<OrientacaoPadrao,String>("snAtivo"));

		grdOrientacaoPadrao.setItems(populateTable);;
	}

	@FXML
	void remover(ActionEvent event) {
		OrientacaoPadrao orientacaoPadrao = new OrientacaoPadrao();
        orientacaoPadrao.setDsOrientacaoPadrao(dsOrientacaoPadrao.getText());
        orientacaoPadrao.setNmOrientacaoPadrao(nmOrientacaoPadrao.getText());
        orientacaoPadrao.setSnAtivo(snAtivo.getValue());
		if(cdOrientacaoPadrao==null || cdOrientacaoPadrao.getText().isEmpty()){
              limpar();
       }else{
    	      orientacaoPadrao.setCdOrientacaoPadrao(new BigInteger(cdOrientacaoPadrao.getText()));
    	      dao.remover(orientacaoPadrao);
       }
        limpar();
        carregarDados();
	}

	public void limpar(){
		cdOrientacaoPadrao.clear();
		dsOrientacaoPadrao.clear();
		nmOrientacaoPadrao.clear();
		snAtivo.setValue("Sim");
	}

	@FXML
	void salvar(ActionEvent event) {
        OrientacaoPadrao orientacaoPadrao = new OrientacaoPadrao();
        orientacaoPadrao.setDsOrientacaoPadrao(dsOrientacaoPadrao.getText());
        orientacaoPadrao.setNmOrientacaoPadrao(nmOrientacaoPadrao.getText());
        orientacaoPadrao.setSnAtivo(snAtivo.getValue());
		if(cdOrientacaoPadrao==null || cdOrientacaoPadrao.getText().isEmpty()){
              dao.salvar(orientacaoPadrao);
       }else{
    	      orientacaoPadrao.setCdOrientacaoPadrao(new BigInteger(cdOrientacaoPadrao.getText()));
    	      dao.atualizar(orientacaoPadrao);
       }
        limpar();
        carregarDados();

	}

}

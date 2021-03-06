package application;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.objeto.DaoObjeto;
import br.com.jdsb.valhalla.sql.core.jfx.dialog.Dialogs;
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

		grdObjetos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	Objeto objeto = new Objeto();
		    	objeto = grdObjetos.getSelectionModel().getSelectedItem();
		    	cdObjeto.setText(String.valueOf(objeto.getCdObjeto()));
		    	dsObjeto.setText(objeto.getDsObjeto());
		    	snAtivo.setValue(objeto.getSnAtivo());
		    	snPadrao.setValue(objeto.getSnPadrao());
		    }
		});
	}

	ObservableList<String> options = FXCollections.observableArrayList("Sim","N�o");

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
		Objeto objeto = new Objeto();
		objeto.setDsObjeto(dsObjeto.getText());
		objeto.setSnAtivo(snAtivo.getValue().toString());
		objeto.setSnPadrao(snPadrao.getValue().toString());
        if(cdObjeto.getText()==null || cdObjeto.getText().isEmpty()){
        	limpar();
        }else{
        	objeto.setCdObjeto(BigInteger.valueOf(Long.parseLong(cdObjeto.getText())));
        	dao.remover(objeto);
        }



        objeto = null;
        limpar();
        carregarDados();
	}


	@FXML
	void salvar(ActionEvent event) {
        Objeto objeto = new Objeto();
		objeto.setDsObjeto(dsObjeto.getText());
		objeto.setSnAtivo(snAtivo.getValue().toString());
		objeto.setSnPadrao(snPadrao.getValue().toString());
        if(cdObjeto.getText()==null || cdObjeto.getText().isEmpty()){
        	if(snPadrao.getValue().equals("Sim") && dao.validaPadrao(objeto)){
        		Dialogs.AletaW("Aten��o", "J� existe um objeto padr�o definido", "Configure o objeto para n�o padr�o");
        		System.out.println("ja existe um objeto padrao");
        	}else{
        	  dao.salvar(objeto);
        	}
        }else{
        	objeto.setCdObjeto(BigInteger.valueOf(Long.parseLong(cdObjeto.getText())));
        	 if(snPadrao.getValue().equals("Sim") &&  dao.validaPadrao(objeto)){
        		 Dialogs.AletaW("Aten��o", "J� existe um objeto padr�o definido", "Configure o objeto para n�o padr�o");
             	}else{
             		dao.atualizar(objeto);
             	}
        }



        objeto = null;
        limpar();
        carregarDados();
	}

	private void limpar(){
		cdObjeto.setText("");
		dsObjeto.setText("");
		snAtivo.setValue("Sim");
		snPadrao.setValue("N�o");
	}

}

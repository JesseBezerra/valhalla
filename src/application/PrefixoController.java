package application;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.objeto.DaoObjeto;
import br.com.jdsb.valhalla.sql.core.dao.prefixo.DaoPrefixo;
import br.com.jdsb.valhalla.sql.objects.objeto.Objeto;
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
	Dao<Objeto> daoObject;

	ObservableList<String> optionsTipes = FXCollections.observableArrayList("VARCHAR2","DATE","NUMBER");
	ObservableList<String> optionsObjetos = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dao = new DaoPrefixo();
		daoObject = new DaoObjeto();

		carregarDados();
		carregaOptionObjetos();
		tpDado.setItems(optionsTipes);
		tpObjeto.setItems(optionsObjetos);

		grdPrefixo.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	Prefixo prefixo = new Prefixo();
		    	prefixo = grdPrefixo.getSelectionModel().getSelectedItem();
		    	cdPrefixo.setText(String.valueOf(prefixo.getCdPrefixo()));
		    	dsPrefixo.setText(prefixo.getDsPrefixo());
		    	snAtivo.setValue(prefixo.getSnAtivo());
		    	tpDado.setValue(prefixo.getTpDado());
		    	tpObjeto.setValue(prefixo.getTpObjeto());
		    	vlPrefixo.setText(prefixo.getVlPrefixo());
		    }
		});

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
		tbcVlPrefixo.setCellValueFactory(new PropertyValueFactory<Prefixo,String>("vlPrefixo"));
		tbcTpObjeto.setCellValueFactory(new PropertyValueFactory<Prefixo,String>("tpObjeto"));
		tbcSnAtivo.setCellValueFactory(new PropertyValueFactory<Prefixo,String>("snAtivo"));

		grdPrefixo.setItems(populateTable);;
	}

    public void salvar(){
            Prefixo prefixo = new Prefixo();
            prefixo.setDsPrefixo(dsPrefixo.getText());
            prefixo.setVlPrefixo(vlPrefixo.getText());
            prefixo.setTpObjeto(tpObjeto.getValue());
            prefixo.setSnAtivo(snAtivo.getValue());
            prefixo.setTpDado(tpDado.getValue());
            if(cdPrefixo.getText()==null || cdPrefixo.getText().isEmpty()){
               dao.salvar(prefixo);
            }else{
            	prefixo.setCdPrefixo(new BigInteger(cdPrefixo.getText()));
            	dao.atualizar(prefixo);
            }

            limpar();
            carregarDados();
    }

    public void remover(){
    	Prefixo prefixo = new Prefixo();
        prefixo.setDsPrefixo(dsPrefixo.getText());
        prefixo.setVlPrefixo(vlPrefixo.getText());
        prefixo.setTpObjeto(tpObjeto.getValue());
        prefixo.setSnAtivo(snAtivo.getValue());
        if(cdPrefixo.getText()==null || cdPrefixo.getText().isEmpty()){
            limpar();
         }else{
         	prefixo.setCdPrefixo(new BigInteger(cdPrefixo.getText()));
         	dao.remover(prefixo);
         }
        limpar();
        carregarDados();
    }

    public void limpar(){
    	dsPrefixo.clear();
    	cdPrefixo.clear();
    	vlPrefixo.clear();
    	tpDado.setValue("");
    	tpObjeto.setValue("");
    	snAtivo.setValue("Sim");
    }


    public void carregaOptionObjetos(){
       for(Objeto objeto:daoObject.listar()){
    	   optionsObjetos.add(objeto.getDsObjeto());
       }
    }




}

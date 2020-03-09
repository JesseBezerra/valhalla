package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.conexao.DaoConexao;
import br.com.jdsb.valhalla.sql.core.dao.configsupri.DaoConfigSupri;
import br.com.jdsb.valhalla.sql.core.dao.consultas.Consultas;
import br.com.jdsb.valhalla.sql.core.dao.estoque.DaoEstoque;
import br.com.jdsb.valhalla.sql.objects.conexao.Conexao;
import br.com.jdsb.valhalla.sql.objects.configsupri.ConfigSupri;
import br.com.jdsb.valhalla.sql.objects.estoque.Estoque;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConfigSupriController implements Initializable {

	ObservableList<String> optionsConexao = FXCollections.observableArrayList();
	ObservableList<String> optionsEmpresa = FXCollections.observableArrayList();
	ObservableList<String> optionsEstoques = FXCollections.observableArrayList();
	Dao<Conexao> dao;
	Dao<Estoque> daoEstoque;
	Consultas consultas ;
	Dao<ConfigSupri> daoSupri;
	private Conexao conexao;
	private Estoque estoque;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		cdMultiEmpresa.setDisable(false);

		dao = new DaoConexao();
		daoSupri = new DaoConfigSupri();
		daoEstoque = new DaoEstoque();
		consultas = new Consultas();

		carregarConexoes();

		this.tpConexao.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

	            // if the item of the list is changed
	            public void changed(ObservableValue ov, Number value, Number new_value)
	            {

	                String dsConexao = optionsConexao.get(new_value.intValue());
	                         conexao = dao.consultar(dsConexao);
	                  carregarEmpresas(consultas.listar(conexao));
	                  carregarConfigSuprii(conexao);
	            }
	        });

		this.cdMultiEmpresa.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {

                String dsMultiEmpresa = optionsEmpresa.get(new_value.intValue());
                String[] cdMultiEmpresa = dsMultiEmpresa.split("-");
                carregarEstoques(consultas.listarEstoques(conexao,cdMultiEmpresa[0]));
            }
        });

		this.cdEstoque.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {

                String dsEstoque = optionsEstoques.get(new_value.intValue());
                String[] campoEstoque = dsEstoque.split("-");
                estoque = new Estoque();
                estoque.setCdEstoque(campoEstoque[0]);
                estoque.setDsEstoque(campoEstoque[1]);
            }
        });

		this.cdEspec.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(!newValue) {
		        	try {
						dsEspec.setText(consultas.getDsEspecie(conexao, cdEspec.getText()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    }
		});

		this.cdClass.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(!newValue) {
		        	try {
						dsClasse.setText(consultas.getDsClass(conexao, cdEspec.getText(),cdClass.getText()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    }
		});

		this.cdSubClass.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(!newValue) {
		        	try {
						dsSubClasse.setText(consultas.getDsSubClass(conexao, cdEspec.getText(), cdClass.getText(),cdSubClass.getText()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    }
		});
	}

	@FXML
	private ChoiceBox<String> tpConexao;

	@FXML
	private ChoiceBox<String> cdMultiEmpresa;

	@FXML
	private ChoiceBox<String> cdEstoque;

	@FXML
	private TableView<Estoque> grdEstoque;

	@FXML
	private TableColumn<Estoque, String> tbcCdEstoque;

	@FXML
	private TableColumn<Estoque, String> tbcDsEstoque;

	@FXML
	private TextField cdConfigSupri;

	@FXML
	private TextField cdEspec;

	@FXML
	private TextField dsEspec;

	@FXML
	private TextField cdClass;

	@FXML
	private TextField dsClasse;

	@FXML
	private TextField cdSubClass;

	@FXML
	private TextField dsSubClasse;

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
        ConfigSupri configSupri = new ConfigSupri();
        configSupri.setCdMultiEmpresa(cdMultiEmpresa.getValue().split("-")[0]);
        configSupri.setCdEstoque(cdEstoque.getValue().split("-")[0]);
        configSupri.setCdConexao(conexao.getCdConexao());
        configSupri.setQtSaldoPadrao(qtSaldoPadrao.getText());
        configSupri.setCdEspec(cdEspec.getText());
        configSupri.setCdClass(cdClass.getText());
        configSupri.setCdSubClass(cdSubClass.getText());
        configSupri.setDsUnidadePadrao(dsUnidadePadrao.getText());
        if(cdConfigSupri!=null && !cdConfigSupri.getText().isEmpty()){
        	daoSupri.atualizar(configSupri);;
        }else{
        	daoSupri.salvar(configSupri);
        }

	}

	public void addEstoque(){
		if(cdConfigSupri!=null && !cdConfigSupri.getText().isEmpty()){
			estoque.setCdConfigSupri(cdConfigSupri.getText());
			daoEstoque.salvar(estoque);
			carregarDadosEstoque();
		}else{
			ConfigSupri configSupri = new ConfigSupri();
	        configSupri.setCdMultiEmpresa(cdMultiEmpresa.getValue().split("-")[0]);
	        configSupri.setCdEstoque(cdEstoque.getValue().split("-")[0]);
	        configSupri.setCdConexao(conexao.getCdConexao());
	        daoSupri.salvar(configSupri);
	        configSupri = daoSupri.consultar(conexao.getCdConexao().toString());
			cdConfigSupri.setText(configSupri.getCdConfigSupri().toString());
	        estoque.setCdConfigSupri(cdConfigSupri.getText());
			daoEstoque.salvar(estoque);
			carregarDadosEstoque();
		}

	}

	public void rmEstoque(){
	  if(estoque!=null){
	      daoEstoque.remover(estoque);
	      carregarDadosEstoque();
	  }
	}

	public void carregarConexoes(){
		for(Conexao conexao:dao.listar()){
			optionsConexao.add(conexao.getDsConexao());
		}
		tpConexao.setItems(optionsConexao);
	}

	public void carregarEmpresas(List<String> empresas){
		for(String string:empresas){
			optionsEmpresa.add(string);
		}
		cdMultiEmpresa.setItems(optionsEmpresa);
	}

	public void carregarEstoques(List<String> estoques){
		for(String string:estoques){
			optionsEstoques.add(string);
		}
		cdEstoque.setItems(optionsEstoques);
	}

	private ObservableList<Estoque> populateTable = FXCollections.observableArrayList();

	private void carregarDadosEstoque() {
		populateTable = FXCollections.observableArrayList();
		for(Estoque cliente:consultas.listarEstoques(cdConfigSupri.getText())) {
			populateTable.add(cliente);
		}

		tbcCdEstoque.setCellValueFactory(new PropertyValueFactory<Estoque,String>("cdEstoque"));
		tbcDsEstoque.setCellValueFactory(new PropertyValueFactory<Estoque,String>("dsEstoque"));

		grdEstoque.setItems(populateTable);;
	}

	private void carregarConfigSuprii(Conexao conexao){
		ConfigSupri configSupri = daoSupri.consultar(this.conexao.getCdConexao().toString());
		if(configSupri!=null)
		{
			cdConfigSupri.setText(configSupri.getCdConfigSupri().toString());
			cdMultiEmpresa.setValue(consultas.getCdMultiEmpresa(conexao, configSupri.getCdMultiEmpresa()));
			carregarDadosEstoque();
			carregarEstoques(consultas.listarEstoques(conexao,configSupri.getCdMultiEmpresa()));
			cdEstoque.setValue(consultas.getCdEstoque(conexao, configSupri.getCdEstoque()));
			cdMultiEmpresa.setDisable(true);
			cdEspec.setText(configSupri.getCdEspec());
			if(cdEspec!=null && !cdEspec.getText().isEmpty()){
				dsEspec.setText(consultas.getDsEspecie(conexao, cdEspec.getText()));
			}
			cdClass.setText(configSupri.getCdClass());
			if(cdClass!=null && !cdClass.getText().isEmpty()){
				dsClasse.setText(consultas.getDsClass(conexao, cdEspec.getText(),cdClass.getText()));
			}
			cdSubClass.setText(configSupri.getCdSubClass());
			if(cdSubClass.getText()!=null && !cdSubClass.getText().isEmpty()){
				dsSubClasse.setText(consultas.getDsSubClass(conexao, cdEspec.getText(), cdClass.getText(),cdSubClass.getText()));
			}
			qtSaldoPadrao.setText(configSupri.getQtSaldoPadrao());
			dsUnidadePadrao.setText(configSupri.getDsUnidadePadrao());

		}
	}

}

package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.conexao.DaoConexao;
import br.com.jdsb.valhalla.sql.core.dao.consultas.Consultas;
import br.com.jdsb.valhalla.sql.objects.conexao.Conexao;
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

public class ConfigSupriController implements Initializable {

	ObservableList<String> optionsConexao = FXCollections.observableArrayList();
	ObservableList<String> optionsEmpresa = FXCollections.observableArrayList();
	ObservableList<String> optionsEstoques = FXCollections.observableArrayList();
	Dao<Conexao> dao;
	private Conexao conexao;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dao = new DaoConexao();
		carregarConexoes();

		this.tpConexao.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

	            // if the item of the list is changed
	            public void changed(ObservableValue ov, Number value, Number new_value)
	            {

	                String dsConexao = optionsConexao.get(new_value.intValue());
	                         conexao = dao.consultar(dsConexao);
	                Consultas consultas = new Consultas();
	                carregarEmpresas(consultas.listar(conexao));
	            }
	        });

		this.cdMultiEmpresa.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {

                String dsMultiEmpresa = optionsEmpresa.get(new_value.intValue());
                String[] cdMultiEmpresa = dsMultiEmpresa.split("-");
                Consultas consultas = new Consultas();
                carregarEstoques(consultas.listarEstoques(conexao,cdMultiEmpresa[1]));
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

}

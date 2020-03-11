package application;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.usuario.DaoUsuario;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;
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

public class UsuarioController implements Initializable {

	ObservableList<String> optionsPerfil = FXCollections.observableArrayList("Adm","User");
	ObservableList<String> optionsSnAtivo = FXCollections.observableArrayList("Sim","Não");
    Dao<Usuario> dao;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
       tpPerfil.setItems(optionsPerfil);
       tpPerfil.setValue("User");
       snAtivo.setItems(optionsSnAtivo);
       snAtivo.setValue("Sim");
       dao = new DaoUsuario();
       carregarDados();
	}

	@FXML
	private TextField nmUsuario;

	@FXML
	private TextField cdUsuario;

	@FXML
	private ChoiceBox<String> snAtivo;

	@FXML
	private ChoiceBox<String> tpPerfil;

	@FXML
	private TableView<Usuario> grdUsuario;

	@FXML
	private TableColumn<Usuario, String> tbcNmUsuario;

	@FXML
	private TableColumn<Usuario, String> tbcCdUsuario;

	@FXML
	private TableColumn<Usuario, String> tbcTpPerfil;

	@FXML
	private TableColumn<Usuario, String> tbcSnAtivo;

	@FXML
	void limpar(ActionEvent event) {
         limpar();
	}

	@FXML
	void remover(ActionEvent event) {
		Usuario usuario = new Usuario();
	    usuario.setCdUsuario(cdUsuario.getText());
	    usuario.setNmUsuario(nmUsuario.getText());
	    usuario.setTpPerfil(tpPerfil.getValue());
	    usuario.setSnAtivo(snAtivo.getValue());
	    dao.remover(usuario);
		limpar();
	}

	@FXML
	void salvar(ActionEvent event) {
       Usuario usuario = new Usuario();
       usuario.setCdUsuario(cdUsuario.getText());
       usuario.setNmUsuario(nmUsuario.getText());
       usuario.setTpPerfil(tpPerfil.getValue());
       usuario.setSnAtivo(snAtivo.getValue());

       Usuario validar = dao.consultar(nmUsuario.getText());
       if(validar!=null){
    	   dao.atualizar(usuario);
       }else{
    	   dao.salvar(usuario);
       }

       limpar();
       carregarDados();
	}

	public void limpar(){
		cdUsuario.clear();
		nmUsuario.clear();
		tpPerfil.setValue("User");
		snAtivo.setValue("Sim");
	}

	private ObservableList<Usuario> populateTable = FXCollections.observableArrayList();

	private void carregarDados() {
		populateTable = FXCollections.observableArrayList();
		for(Usuario cliente:dao.listar()) {
			populateTable.add(cliente);
		}

		tbcNmUsuario.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nmUsuario"));
		tbcCdUsuario.setCellValueFactory(new PropertyValueFactory<Usuario,String>("cdUsuario"));
		tbcTpPerfil.setCellValueFactory(new PropertyValueFactory<Usuario,String>("tpPerfil"));
		tbcSnAtivo.setCellValueFactory(new PropertyValueFactory<Usuario,String>("snAtivo"));

		grdUsuario.setItems(populateTable);;
	}

}

package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.atlassian.jira.rest.client.api.domain.Issue;

import br.com.jdsb.valhalla.integracao.jira.baeldung.JiraClient;
import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.usuario.DaoUsuario;
import br.com.jdsb.valhalla.sql.core.jfx.dialog.Dialogs;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
    private TextField cdUsuario;

    @FXML
    private ImageView dsImagem;

    @FXML
    private PasswordField cdSenha;

    @FXML
    private Label dsNumUsuario;

    Dao<Usuario> dao;
    Usuario usuario;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dsImagem.setImage(new Image("https://images.vexels.com/media/users/3/150034/isolated/preview/29e322a8fe068f39d666137f7c1fc8f8-nave-espacial-crian--as-ilustra----o-by-vexels.png"));
	    dao = new DaoUsuario();
	}

	public void logar(){
		usuario = dao.consultar(cdUsuario.getText());
		if(cdSenha==null || cdSenha.getText().isEmpty()){
			Dialogs.AletaW("Atenção", "O campo senha não foi informado", "Informe a senha!");
		}
		if(cdUsuario==null || cdUsuario.getText().isEmpty()){
			Dialogs.AletaW("Atenção", "O campo usuário não foi informado", "Informe o usuário!");
		}

		if(usuario==null){
			cdUsuario.clear();
			cdSenha.clear();
			Dialogs.AletaW("Atenção", "Usuário ou senha inválidos", "Verifique o cadastro do usuário");
		}
		try {

		JiraClient client = new JiraClient(usuario.getCdUsuario(), cdSenha.getText(), "https://jira.mv.com.br/");
		Issue issue = client.getIssue("CIMP-14942");
		if(issue!=null){
            Estatica.usuario = usuario;
            Estatica.jiraClient = client;
            chamaForm();
            cdUsuario.getScene().getWindow().hide();
		}

		} catch (Exception e) {
			Dialogs.AletaE("Atenção", "Ocorreu um erro ao se antenticar no jira", e.getLocalizedMessage());
		}


	}

	public void chamaForm() {
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_manutencao.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();
	        Stage stage = new Stage();
	        stage.setTitle("Manutenção ");
	        stage.setResizable(false);
	        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
	        stage.setScene(new Scene(root1));
	        stage.initModality(Modality.WINDOW_MODAL);
	        stage.show();
	        } catch(Exception e) {
	           e.printStackTrace();
	        }
	}





}

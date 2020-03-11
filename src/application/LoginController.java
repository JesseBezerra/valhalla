package application;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.usuario.DaoUsuario;
import br.com.jdsb.valhalla.sql.core.jfx.dialog.Dialogs;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

	}





}

package application;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.integracao.quartiz.apontamento.AgendarApontamento;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Menu implements Initializable {

	private Usuario usuario;

	public boolean isVisible(){
        boolean retorno = false;
		if(usuario!=null){
			retorno = usuario.getTpPerfil().toUpperCase().equals("ADM");
        }
		return retorno;
	}
	public void goObjetos() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_objeto.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.setTitle("Objetos de Dados ");
			stage.setResizable(false);
			// stage.getIcons().add(new
			// Image(this.getClass().getResourceAsStream("nave.png")));
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goPrefixos() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_prefixo.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.setTitle("Prefixos de Dados ");
			stage.setResizable(false);
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goConexao() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_conexao.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.setTitle("Conexão de Dados ");
			stage.setResizable(false);
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goProdutos() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_produto.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.setTitle("Produtos");
			stage.setResizable(false);
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goProdutoSupri() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_produto_supri.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.setTitle("Produtos Suprimentos");
			stage.setResizable(false);
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goConfigSupri() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_config_supri.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.setTitle("Config. Suprimentos");
			stage.setResizable(false);
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goChamado() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_chamado.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.setTitle("Empacotador v2.0 (Cad. Agenda) - Beta");
			stage.setResizable(false);
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goUsuarios() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_usuario.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.setTitle("Empacotador v2.0 (Cad. Usuários) - Beta");
			stage.setResizable(false);
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goOrientacaoTicket() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_orientacao_ticket.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.setTitle("Empacotador v2.0 (Cad. Usuários) - Beta");
			stage.setResizable(false);
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goApontamento() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_apontamento.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			Platform.setImplicitExit(false);
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					stage.hide();

				}
			});
			AgendarApontamento agendarApontamento = new AgendarApontamento();
			agendarApontamento.schedule(stage);

			stage.setTitle("Empacotador v2.0 (Cad. Usuários) - Beta");
			stage.setResizable(false);
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goOrientacaoPadrao() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_orientacao_padrao.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.setTitle("Empacotador v2.0 (Cad. Usuários) - Beta");
			stage.setResizable(false);
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goManutencao() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_manutencao.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.setTitle("Empacotador v2.0 (Cad. Usuários) - Beta");
			stage.setResizable(false);
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
      this.usuario = Estatica.usuario;
	}

}

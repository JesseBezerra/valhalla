package application;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.integracao.quartiz.apontamento.AgendarApontamento;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Menu implements Initializable {

	private Usuario usuario;

	@FXML
	MenuItem mRApontamento;

	public boolean isVisible(){
        boolean retorno = true;
		if(usuario!=null){
			retorno = usuario.getTpPerfil().toUpperCase().equals("ADM");
			mRApontamento.setVisible(retorno);
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
			stage.setTitle("Conex�o de Dados ");
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
			stage.setTitle("Empacotador v2.0 (Cad. Usu�rios) - Beta");
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
			stage.setTitle("Empacotador v2.0 (Cad. Usu�rios) - Beta");
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

			stage.setTitle("Empacotador v2.0 (Cad. Usu�rios) - Beta");
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
			stage.setTitle("Empacotador v2.0 (Cad. Usu�rios) - Beta");
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
			stage.setTitle("Empacotador v2.0 (Cad. Usu�rios) - Beta");
			stage.setResizable(false);
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goRApontamento() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("r_apontamento.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.setTitle("Empacotador v2.0 (Cad. Usu�rios) - Beta");
			stage.setResizable(false);
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goPonto() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_ponto.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			Stage stage = new Stage();
			stage.setTitle("Empacotador v2.0 (Ajustes de Ponto) - Beta");
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
      if(usuario!=null){
		boolean	retorno = usuario.getTpPerfil().toUpperCase().equals("ADM");
		mChamado.setVisible(retorno);
		mApontamento.setVisible(retorno);
		mObjetos.setVisible(retorno);
		mProdutos.setVisible(retorno);
		mVercoes.setVisible(retorno);
		mOrientacaoPadrao.setVisible(retorno);
		mOrientacao.setVisible(retorno);
		mPrefixos.setVisible(retorno);
		mConexao.setVisible(retorno);
		mConfigSupri.setVisible(retorno);
		mUsuarios.setVisible(retorno);
      }
	}

	@FXML
	MenuItem mChamado;

	@FXML
	MenuItem mApontamento;

	@FXML
	MenuItem mObjetos;

	@FXML
	MenuItem mProdutos;

	@FXML
	MenuItem mVercoes;

	@FXML
	MenuItem mConexao;

	@FXML
	MenuItem mPrefixos;

	@FXML
	MenuItem mOrientacaoPadrao;

	@FXML
	MenuItem mOrientacao;

	@FXML
	MenuItem mConfigSupri;

	@FXML
	MenuItem mUsuarios;



}

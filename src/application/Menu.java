package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Menu implements Initializable{

	public void goObjetos(){
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_objeto.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();

	        Stage stage = new Stage();
	        stage.setTitle("Objetos de Dados ");
	        stage.setResizable(false);
	       // stage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
	        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("valhalla.jpg")));
	        stage.setScene(new Scene(root1));
	        stage.show();
	        } catch(Exception e) {
	           e.printStackTrace();
	          }
	}

	public void goPrefixos(){
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_prefixo.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();

	        Stage stage = new Stage();
	        stage.setTitle("Prefixos de Dados ");
	        stage.setResizable(false);
	        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("valhalla.jpg")));
	        stage.setScene(new Scene(root1));
	        stage.show();
	        } catch(Exception e) {
	           e.printStackTrace();
	          }
	}

	public void goConexao(){
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_conexao.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();

	        Stage stage = new Stage();
	        stage.setTitle("Conexão de Dados ");
	        stage.setResizable(false);
	        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("valhalla.jpg")));
	        stage.setScene(new Scene(root1));
	        stage.show();
	        } catch(Exception e) {
	           e.printStackTrace();
	          }
	}

	public void goProdutos(){
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("m_produto.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();

	        Stage stage = new Stage();
	        stage.setTitle("Produtos");
	        stage.setResizable(false);
	        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("valhalla.jpg")));
	        stage.setScene(new Scene(root1));
	        stage.show();
	        } catch(Exception e) {
	           e.printStackTrace();
	          }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}


}

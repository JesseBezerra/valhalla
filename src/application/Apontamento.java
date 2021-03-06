package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Apontamento extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Platform.runLater(new Runnable(){
				   @Override
				public void run() {
						try {
							AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("m_apontamento.fxml"));
							Scene scene = new Scene(root);
							scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							primaryStage.setScene(scene);
							primaryStage.setResizable(false);
							primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
							primaryStage.setWidth(252);
							primaryStage.setHeight(400);
							primaryStage.sizeToScene();
							primaryStage.setResizable(false);
							primaryStage.setTitle("Empacotador v2.0 (Agendador) - Beta");
							primaryStage.show();
						} catch (Exception e) {
							e.printStackTrace();
						}
				   }
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch("");
	}



}

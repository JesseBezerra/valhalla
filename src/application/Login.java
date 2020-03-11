package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Login extends Application {
			@Override
			public void start(Stage primaryStage) {
				try {
					try {
						AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("m_login.fxml"));
						Scene scene = new Scene(root);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.setResizable(false);
						primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("nave.png")));
						primaryStage.setResizable(false);
						primaryStage.setTitle("Empacotador v2.0 (Agendador) - Beta");
						primaryStage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			public static void main(String[] args) {
				launch(args);
			}
}

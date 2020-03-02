package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			try {
				VBox root = (VBox)FXMLLoader.load(getClass().getResource("m_template.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("valhalla.jpg")));
				primaryStage.setResizable(false);
				primaryStage.setTitle("Validador v2.0 (Valhalla) - Beta");
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

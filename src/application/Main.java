package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Platform.setImplicitExit(false);
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		            @Override
		            public void handle(WindowEvent t) {
		            	primaryStage.hide();

		            }
		        });
			try {
//				AgendarApontamento agendarApontamento = new AgendarApontamento();
//				agendarApontamento.schedule(primaryStage);
				VBox root = (VBox)FXMLLoader.load(getClass().getResource("m_ponto.fxml"));
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

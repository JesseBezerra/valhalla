package application;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class ManutencaoController implements Initializable {

	@FXML
	private Label lbPonto;

	@FXML
	private TextField dtPontoInic;
	@FXML
	private TextField dtPontoAlmoco;
	@FXML
	private TextField dtPontoVolta;
	@FXML
	private TextField dtPontoFinaliza;


	private SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//lbPonto.setEffect(new DropShadow(10, Color.RED));

		// agora ligamos um loop infinito que roda a cada segundo e atualiza nosso label chamando atualizaHoras.
		KeyFrame frame = new KeyFrame(Duration.millis(1000), e -> atualizaHoras());
		Timeline timeline = new Timeline(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

	}

	private void atualizaHoras() {
		Date agora = new Date();
		lbPonto.setText("Ponto "+formatador.format(agora) );


	}

}

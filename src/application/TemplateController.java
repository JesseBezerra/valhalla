package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.HTMLEditor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class TemplateController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	private HTMLEditor editor;

	public void salvar(){
		Document doc = Jsoup.parse(editor.getHtmlText());
        Element  element = doc.select("html").first();
		System.out.println(element.text());
	}

}

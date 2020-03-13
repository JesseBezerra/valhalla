package application;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.jdsb.valhalla.sql.core.dao.Dao;
import br.com.jdsb.valhalla.sql.core.dao.conexao.DaoConexao;
import br.com.jdsb.valhalla.sql.core.dao.orientacao.padrao.DaoOrientacaoPadrao;
import br.com.jdsb.valhalla.sql.core.texto.StringUtil;
import br.com.jdsb.valhalla.sql.objects.chamado.Chamado;
import br.com.jdsb.valhalla.sql.objects.conexao.Conexao;
import br.com.jdsb.valhalla.sql.objects.orientacao.padrao.OrientacaoPadrao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrientacaoPadraoController implements Initializable {
	ObservableList<String> optionsSnAtivo = FXCollections.observableArrayList("Sim", "Não");
	ObservableList<String> optionsConexao = FXCollections.observableArrayList();

	Dao<OrientacaoPadrao> dao;
	Dao<Conexao> daoCon;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		snAtivo.setItems(optionsSnAtivo);
		snAtivo.setValue("Sim");
		dao = new DaoOrientacaoPadrao();
		daoCon = new DaoConexao();
		carregarDados();
		carregarConexoes();

		grdOrientacaoPadrao.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldSelection, newSelection) -> {
					if (newSelection != null) {
						OrientacaoPadrao orientacao = new OrientacaoPadrao();
						orientacao = grdOrientacaoPadrao.getSelectionModel().getSelectedItem();
						cdOrientacaoPadrao.setText(orientacao.getCdOrientacaoPadrao().toString());
						dsOrientacaoPadrao.setText(orientacao.getDsOrientacaoPadrao());
						nmOrientacaoPadrao.setText(orientacao.getNmOrientacaoPadrao());
						snAtivo.setValue(orientacao.getSnAtivo());
					}
				});

		this.tpConexao.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			// if the item of the list is changed
			public void changed(ObservableValue ov, Number value, Number new_value) {

				String dsConexao = optionsConexao.get(new_value.intValue());
				Conexao conexao = daoCon.consultar(dsConexao);
				StringUtil util = new StringUtil();
				dsOrientacaoPadrao.setText(String.format(util.CONSTANTE_CONEXAO, conexao.getDsConexao(),
						conexao.getDsUrl(), conexao.getDsPorta(), conexao.getDsSenha(), conexao.getDsUsuario(),
						conexao.getDsSid(), conexao.getDsUrl(), conexao.getDsPorta()));

			}
		});

	}

	public void carregarConexoes() {
		for (Conexao conexao : daoCon.listar()) {
			optionsConexao.add(conexao.getDsConexao());
		}
		tpConexao.setItems(optionsConexao);
	}

	@FXML
	private TextField cdOrientacaoPadrao;

	@FXML
	private TextField nmOrientacaoPadrao;

	@FXML
	private Label txtSnValidar;

	@FXML
	private ChoiceBox<String> snAtivo;

	@FXML
	private TextArea dsOrientacaoPadrao;

	@FXML
	private TableView<OrientacaoPadrao> grdOrientacaoPadrao;

	@FXML
	private TableColumn<OrientacaoPadrao, BigInteger> tbcCdOrientacaoPadrao;

	@FXML
	private TableColumn<OrientacaoPadrao, String> tbcNmOrientacaoPadrao;

	@FXML
	private TableColumn<OrientacaoPadrao, String> tbcSnAtivo;

	@FXML
	private ChoiceBox<String> tpConexao;

	@FXML
	void limpar(ActionEvent event) {
		limpar();
	}

	private ObservableList<OrientacaoPadrao> populateTable = FXCollections.observableArrayList();

	private void carregarDados() {
		populateTable = FXCollections.observableArrayList();
		for (OrientacaoPadrao cliente : dao.listar()) {
			populateTable.add(cliente);
		}

		tbcCdOrientacaoPadrao
				.setCellValueFactory(new PropertyValueFactory<OrientacaoPadrao, BigInteger>("cdOrientacaoPadrao"));
		tbcNmOrientacaoPadrao
				.setCellValueFactory(new PropertyValueFactory<OrientacaoPadrao, String>("nmOrientacaoPadrao"));
		tbcSnAtivo.setCellValueFactory(new PropertyValueFactory<OrientacaoPadrao, String>("snAtivo"));

		grdOrientacaoPadrao.setItems(populateTable);
		;
	}

	@FXML
	void remover(ActionEvent event) {
		OrientacaoPadrao orientacaoPadrao = new OrientacaoPadrao();
		orientacaoPadrao.setDsOrientacaoPadrao(dsOrientacaoPadrao.getText());
		orientacaoPadrao.setNmOrientacaoPadrao(nmOrientacaoPadrao.getText());
		orientacaoPadrao.setSnAtivo(snAtivo.getValue());
		if (cdOrientacaoPadrao == null || cdOrientacaoPadrao.getText().isEmpty()) {
			limpar();
		} else {
			orientacaoPadrao.setCdOrientacaoPadrao(new BigInteger(cdOrientacaoPadrao.getText()));
			dao.remover(orientacaoPadrao);
		}
		limpar();
		carregarDados();
	}

	public void limpar() {
		cdOrientacaoPadrao.clear();
		dsOrientacaoPadrao.clear();
		nmOrientacaoPadrao.clear();
		snAtivo.setValue("Sim");
	}

	@FXML
	void salvar(ActionEvent event) {
		OrientacaoPadrao orientacaoPadrao = new OrientacaoPadrao();
		orientacaoPadrao.setDsOrientacaoPadrao(dsOrientacaoPadrao.getText());
		orientacaoPadrao.setNmOrientacaoPadrao(nmOrientacaoPadrao.getText());
		orientacaoPadrao.setSnAtivo(snAtivo.getValue());
		if (cdOrientacaoPadrao == null || cdOrientacaoPadrao.getText().isEmpty()) {
			dao.salvar(orientacaoPadrao);
		} else {
			orientacaoPadrao.setCdOrientacaoPadrao(new BigInteger(cdOrientacaoPadrao.getText()));
			dao.atualizar(orientacaoPadrao);
		}
		limpar();
		carregarDados();

	}

}

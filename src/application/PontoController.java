package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

import br.com.jdsb.valhalla.sql.core.dao.ponto.DaoPonto;
import br.com.jdsb.valhalla.sql.core.dao.usuario.DaoUsuario;
import br.com.jdsb.valhalla.sql.core.data.LocalDateTimeToDate;
import br.com.jdsb.valhalla.sql.core.jfx.dialog.Dialogs;
import br.com.jdsb.valhalla.sql.objects.ponto.PontoTo;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PontoController implements Initializable {

	@FXML
	private ChoiceBox<String> cdUsuario;

	@FXML
	private DatePicker dtPonto;

	@FXML
	private DatePicker dtPontoAte;

	@FXML
	private TableView<PontoTo> grdPonto;

	@FXML
	private TableColumn<PontoTo, String> tbcDia;

	@FXML
	private TableColumn<PontoTo, String> tbcInic;

	@FXML
	private TableColumn<PontoTo, String> tbcAlmoco;

	@FXML
	private TableColumn<PontoTo, String> tbcVolta;

	@FXML
	private TableColumn<PontoTo, String> tbcSaida;

	@FXML
	private TextField dtDiaPonto;

	@FXML
	private TextField hrInic;

	@FXML
	private TextField hrAlmoco;

	@FXML
	private TextField hrVolta;

	@FXML
	private TextField hrSaida;

	@FXML
	void atualizar(ActionEvent event) {
		if(this.pontoTo!=null){
				boolean horarioInicValido = true;
				boolean horarioAlmocoValido = true;
				boolean horarioVoltaValido = true;
				boolean horarioSaidaValido = true;

				if (!hrInic.getText().equals("00:00")) {
					horarioInicValido = validate(hrInic.getText());
				}
				if (!hrAlmoco.getText().equals("00:00")) {
					horarioAlmocoValido = validate(hrAlmoco.getText());
				}
				if (!hrVolta.getText().equals("00:00")) {
					horarioVoltaValido = validate(hrVolta.getText());
				}
				if (!hrSaida.getText().equals("00:00")) {
					horarioSaidaValido = validate(hrSaida.getText());
				}

				if(horarioInicValido && horarioAlmocoValido && horarioVoltaValido && horarioSaidaValido){
		             daoPonto.atualizar(pontoTo);
		             Dialogs.AletaI("Atenção", "O ponto atualizado com sucesso!", "Ponto Atualizado!");
		             limpar();
				}else{
					Dialogs.AletaW("Atenção", "O ponto não foi atualizado", "Verifique os horarios informados");
				}
		}else{
			Dialogs.AletaW("Atenção","Não foi informado nenhum ponto", "Verifique os dados informados");
		}

	}

	ObservableList<String> optionsUsuario = FXCollections.observableArrayList();
	DaoUsuario dao;
	DaoPonto daoPonto;
	private PontoTo pontoTo;
	private Pattern timePattern;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		Calendar calendar = Calendar.getInstance();
		LocalDate localDate = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId())
				.toLocalDate();
		dtPonto.setValue(localDate);
		dtPontoAte.setValue(localDate);

		timePattern = Pattern.compile("\\d\\d:\\d\\d");

		dao = new DaoUsuario();
		daoPonto = new DaoPonto();
		carregaOptionObjetos();

		grdPonto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				pontoTo = grdPonto.getSelectionModel().getSelectedItem();
				dtDiaPonto.setText(pontoTo.getCdDia());
				hrInic.setText(pontoTo.getHrInic());
				hrAlmoco.setText(pontoTo.getHrAlmoco());
				hrVolta.setText(pontoTo.getHrVolta());
				hrSaida.setText(pontoTo.getHrSaida());
			}
		});

		this.hrInic.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					try {
						if (hrInic != null && !hrInic.getText().isEmpty()) {
							boolean horarioValido = validate(hrInic.getText());
							if (!horarioValido) {
								Dialogs.AletaW("Atenção", "O horario informado não é valido", "Verifique o Horario");
							}else{
								pontoTo.setHrInic(hrInic.getText());
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		this.hrAlmoco.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					try {
						if (hrAlmoco != null && !hrAlmoco.getText().isEmpty()) {
							boolean horarioValido = validate(hrAlmoco.getText());
							if (!horarioValido) {
								Dialogs.AletaW("Atenção", "O horario informado não é valido", "Verifique o Horario");
							}else{
								pontoTo.setHrAlmoco(hrAlmoco.getText());
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		this.hrVolta.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					try {
						if (hrVolta != null && !hrVolta.getText().isEmpty()) {
							boolean horarioValido = validate(hrVolta.getText());
							if (!horarioValido) {
								Dialogs.AletaW("Atenção", "O horario informado não é valido", "Verifique o Horario");
							}else{
								pontoTo.setHrVolta(hrVolta.getText());
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		this.hrSaida.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					try {
						if (hrSaida != null && !hrSaida.getText().isEmpty()) {
							boolean horarioValido = validate(hrSaida.getText());
							if (!horarioValido) {
								Dialogs.AletaW("Atenção", "O horario informado não é valido", "Verifique o Horario");
							}else{
								pontoTo.setHrSaida(hrSaida.getText());
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

	}

	public void carregaOptionObjetos() {
		if (Estatica.usuario == null || (Estatica.usuario != null
				&& Estatica.usuario.getTpPerfil().toUpperCase().equals("adm".toUpperCase()))) {
			for (Usuario usuario : dao.listar()) {
				if (!usuario.getCdUsuario().toUpperCase().equals("sem.dono".toUpperCase())) {
					optionsUsuario.add(usuario.getNmUsuario());
				}
			}
			optionsUsuario.add("Todos");
			cdUsuario.setItems(optionsUsuario);
			cdUsuario.setValue("Todos");
		} else {
			optionsUsuario.add(Estatica.usuario.getNmUsuario());
			cdUsuario.setItems(optionsUsuario);
			;
			cdUsuario.setValue(Estatica.usuario.getNmUsuario());
		}
	}

	private ObservableList<PontoTo> populateTable = FXCollections.observableArrayList();

	private void carregarDados(List<PontoTo> lista) {
		populateTable = FXCollections.observableArrayList();
		for (PontoTo cliente : lista) {
			populateTable.add(cliente);
		}

		tbcDia.setCellValueFactory(new PropertyValueFactory<PontoTo, String>("cdDia"));
		tbcInic.setCellValueFactory(new PropertyValueFactory<PontoTo, String>("hrInic"));
		tbcAlmoco.setCellValueFactory(new PropertyValueFactory<PontoTo, String>("hrAlmoco"));
		tbcVolta.setCellValueFactory(new PropertyValueFactory<PontoTo, String>("hrVolta"));
		tbcSaida.setCellValueFactory(new PropertyValueFactory<PontoTo, String>("hrSaida"));

		grdPonto.setItems(populateTable);
		;
	}

	public void limpar(){
		dtDiaPonto.clear();
		hrAlmoco.clear();
		hrInic.clear();
		hrVolta.clear();
		hrSaida.clear();
		pontoTo = null;
		populateTable.clear();
		grdPonto.setItems(populateTable);
		recarregar();
	}

	public void limparPonto(){
		dtDiaPonto.clear();
		hrAlmoco.clear();
		hrInic.clear();
		hrVolta.clear();
		hrSaida.clear();
		pontoTo = null;
	}

	public void recarregar(){
		Usuario usuario = dao.consultarPorNome(cdUsuario.getValue());
		LocalDate dataInic = dtPonto.getValue();
		Date dataInicD = LocalDateTimeToDate.localDateToDate(dataInic);
		DateTime daInic = new DateTime(dataInicD);

		LocalDate dataFim = dtPontoAte.getValue();
		Date dataFimD = LocalDateTimeToDate.localDateToDate(dataFim);
		DateTime dataF = new DateTime(dataFimD);

		List<PontoTo> lista = daoPonto.listarTo(daInic.toString("dd/MM/yyyy"),dataF.toString("dd/MM/yyyy"), usuario.getCdUsuario());
		carregarDados(lista);
	}

	@FXML
	void consultar(ActionEvent event) {
		Usuario usuario = dao.consultarPorNome(cdUsuario.getValue());
		LocalDate dataInic = dtPonto.getValue();
		Date dataInicD = LocalDateTimeToDate.localDateToDate(dataInic);
		DateTime daInic = new DateTime(dataInicD);

		LocalDate dataFim = dtPontoAte.getValue();
		Date dataFimD = LocalDateTimeToDate.localDateToDate(dataFim);
		DateTime dataF = new DateTime(dataFimD);

		List<PontoTo> lista = daoPonto.listarTo(daInic.toString("dd/MM/yyyy"),dataF.toString("dd/MM/yyyy"), usuario.getCdUsuario());
		carregarDados(lista);
		limparPonto();
	}

	private boolean validate(String time) {
		if (!timePattern.matcher(time).matches()) {
			return false;
		}
		String[] tokens = time.split(":");
		assert tokens.length == 2;
		try {
			int hours = Integer.parseInt(tokens[0]);
			int mins = Integer.parseInt(tokens[1]);
			if (hours < 0 || hours > 23) {
				return false;
			}
			if (mins < 0 || mins > 59) {
				return false;
			}
			return true;
		} catch (NumberFormatException nfe) {
			// regex matching should assure we never reach this catch block
			assert false;
			return false;
		}
	}

}

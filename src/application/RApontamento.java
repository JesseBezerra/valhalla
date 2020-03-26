package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.joda.time.DateTime;
import org.joda.time.Period;

import br.com.jdsb.valhalla.integracao.excel.GerarExcel;
import br.com.jdsb.valhalla.sql.core.dao.usuario.DaoUsuario;
import br.com.jdsb.valhalla.sql.core.data.LocalDateTimeToDate;
import br.com.jdsb.valhalla.sql.objects.chamado.Apontamento;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RApontamento implements Initializable {

	ObservableList<String> optionsUsuario = FXCollections.observableArrayList();
	DaoUsuario dao;
	private List<Apontamento> apontamentos;
	private GerarExcel gerarExcel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		LocalDate localDate = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId())
				.toLocalDate();
		dtInic.setValue(localDate);
		dao = new DaoUsuario();
		carregaOptionObjetos();
		if (Estatica.jiraClient == null) {
			gerarExcel = new GerarExcel();
		} else {
			gerarExcel = new GerarExcel(Estatica.jiraClient);
		}
	}

	@FXML
	private TableView<Apontamento> grdApontamento;

	@FXML
	private TableColumn<Apontamento, String> tbcCdTicket;

	@FXML
	private TableColumn<Apontamento, String> tbcDsTitulo;

	@FXML
	private TableColumn<Apontamento, String> tbcTpSituacao;

	@FXML
	private TableColumn<Apontamento, String> tbcCdCliente;

	@FXML
	private TableColumn<Apontamento, String> tbcCdResponsavel;

	@FXML
	private TableColumn<Apontamento, String> tbcHorasGastas;

	@FXML
	private ChoiceBox<String> cdUsuario;

	@FXML
	private DatePicker dtInic;

	@FXML
	void consultar(ActionEvent event) {
		LocalDate dataInic = dtInic.getValue();
		Date dataInicD = LocalDateTimeToDate.localDateToDate(dataInic);
		DateTime daInic = new DateTime(dataInicD);
		DateTime daFim = new DateTime(new Date());
		Period period = new Period(daInic, daFim);
		Usuario usuario = dao.consultarPorNome(cdUsuario.getValue());
		System.out.println(cdUsuario.getValue());
		if (usuario != null) {
			apontamentos = gerarExcel.listarTicketsDodia(daInic.toString("dd/MM/yyyy"), period.getDays(), usuario);
			carregarDados(apontamentos);
		} else {
			apontamentos = gerarExcel.listarTicketsDodia(daInic.toString("dd/MM/yyyy"), period.getDays());
			carregarDados(apontamentos);
		}
	}

	@FXML
	void imprimir(ActionEvent event) {
		gerarExcel.gerarPlanilha(apontamentos);
	}

	private ObservableList<Apontamento> populateTable = FXCollections.observableArrayList();

	private void carregarDados(List<Apontamento> lista) {
		populateTable = FXCollections.observableArrayList();
		for (Apontamento cliente : lista) {
			populateTable.add(cliente);
		}

		tbcCdTicket.setCellValueFactory(new PropertyValueFactory<Apontamento, String>("cdTicket"));
		tbcDsTitulo.setCellValueFactory(new PropertyValueFactory<Apontamento, String>("dsTitulo"));
		tbcCdCliente.setCellValueFactory(new PropertyValueFactory<Apontamento, String>("cdCliente"));
		tbcCdResponsavel.setCellValueFactory(new PropertyValueFactory<Apontamento, String>("nmResponsavel"));
		tbcHorasGastas.setCellValueFactory(new PropertyValueFactory<Apontamento, String>("horasGastas"));
		tbcTpSituacao.setCellValueFactory(new PropertyValueFactory<Apontamento, String>("tpSituacao"));

		grdApontamento.setItems(populateTable);
		;
	}

	public void carregaOptionObjetos() {
		if(Estatica.usuario==null || (Estatica.usuario!=null && Estatica.usuario.getTpPerfil().toUpperCase().equals("adm".toUpperCase()))){
		for (Usuario usuario : dao.listar()) {
			if (!usuario.getCdUsuario().toUpperCase().equals("sem.dono".toUpperCase())) {
				optionsUsuario.add(usuario.getNmUsuario());
			}
		}
		optionsUsuario.add("Todos");
		cdUsuario.setItems(optionsUsuario);
		cdUsuario.setValue("Todos");
	   }else{
		   optionsUsuario.add(Estatica.usuario.getNmUsuario());
		   cdUsuario.setItems(optionsUsuario);;
		   cdUsuario.setValue(Estatica.usuario.getNmUsuario());
	   }
	}

}

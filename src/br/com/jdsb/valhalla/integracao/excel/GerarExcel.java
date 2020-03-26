package br.com.jdsb.valhalla.integracao.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.Worklog;

import br.com.jdsb.valhalla.integracao.jira.baeldung.JiraClient;
import br.com.jdsb.valhalla.sql.core.dao.usuario.DaoUsuario;
import br.com.jdsb.valhalla.sql.core.texto.StringUtil;
import br.com.jdsb.valhalla.sql.objects.chamado.Apontamento;
import br.com.jdsb.valhalla.sql.objects.usuario.Usuario;

public class GerarExcel {
	int TICKET = 0;
	int TITULO = 0;
	int STATUS = 0;
	int CLIENTE = 0;
	int RESPONSAVEL = 0;

	private StringUtil stringUtil;
	private JiraRestClient restClient;

	public GerarExcel() {
		stringUtil = new StringUtil();
		restClient = new JiraClient("jesse.bezerra", "N@ruto2019", "https://jira.mv.com.br/").restClient;
	}

	public GerarExcel(JiraClient client) {
		stringUtil = new StringUtil();
		restClient = client.restClient;
	}



	public static void main(String[] args) {
		GerarExcel excel = new GerarExcel();
		excel.gerarPlanilha();
		// DaoChamado daoChamado = new DaoChamado();
		// List<Chamado> lista =
		// daoChamado.listarChamadosUsuario("jesse.bezerra");
		// for(Chamado chamado:lista){
		// System.out.println(chamado.getCdTicket());
		// }
	}

	public void gerarPlanilha() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		gerarTicketDoDia(workbook);
	}

	public void gerarPlanilha(List<Apontamento> apontamentos) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		gerarTicketDoDia(workbook,apontamentos);
	}

	public List<Issue> getTicketsDoDia(Usuario usuario) {
		List<Issue> retorno = new ArrayList<Issue>();
		String consulta = //"Produto in (SUPRI, SUPRI-COMP, SUPRIMENTOS) and worklogAuthor = %s  and worklogDate = -1d";
				" worklogAuthor = %s  and worklogDate = startOfDay()";
		SearchRestClient searchClient = restClient.getSearchClient();
		try {
			SearchResult result = searchClient
					.searchJql(String.format(consulta, usuario.getCdUsuario()), 20, 1 - 10, null).claim();
			Iterable<Issue> listaIssues = result.getIssues();
			listaIssues.forEach(retorno::add);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public List<Issue> getTicketsDoDia(Usuario usuario,int dias) {
		List<Issue> retorno = new ArrayList<Issue>();
		String consulta ="worklogAuthor = %s  and worklogDate = -1d";
		if(dias==0){
			consulta = " worklogAuthor = %s  and worklogDate = startOfDay()";
		}
		SearchRestClient searchClient = restClient.getSearchClient();
		try {
			SearchResult result = searchClient
					.searchJql(String.format(consulta, usuario.getCdUsuario()), 20, 1 - 10, null).claim();
			Iterable<Issue> listaIssues = result.getIssues();
			listaIssues.forEach(retorno::add);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public String converteMinutoHora(Integer qtdMinutos) {
		String retorno = "";
		int minutos = qtdMinutos % 60;
		int horas = (qtdMinutos / 60);
		retorno = (horas + ":" + minutos);
		return retorno;

	}

	public List<Apontamento> listarTicketsDodia(String dataFormatada,int qtdDias){
		List<Apontamento> retorno = new ArrayList<Apontamento>();
		DaoUsuario dao = new DaoUsuario();
		List<Usuario> listaUsuario = dao.listar();
		for (Usuario usuario : listaUsuario) {
			List<Issue> lista = getTicketsDoDia(usuario,qtdDias);
			for (Issue issue : lista) {
				Apontamento apontamento = new Apontamento();

				apontamento.cdTicket=  issue.getKey();
                apontamento.dsTitulo = issue.getSummary();
                apontamento.tpSituacao = issue.getStatus().getName();
                apontamento.nmResponsavel=usuario.getNmUsuario();
                apontamento.cdCliente = issue.getFieldByName("Cliente").getValue().toString().replace("\"", "")
						.replace("[", "").replace("]", "");


				issue = restClient.getIssueClient().getIssue(issue.getKey()).claim();
				Iterable<Worklog> log = issue.getWorklogs();
				List<Worklog> resultados = new ArrayList<Worklog>();
				log.forEach(resultados::add);
				int minutos=0;
				for (Worklog worklog : resultados) {
					if (worklog.getAuthor().getName().equals(usuario.getCdUsuario()) && (worklog.getStartDate()
							.toString("dd/MM/yyyy").equals(dataFormatada))) {
                      minutos = minutos + worklog.getMinutesSpent();
					}
				}
				apontamento.horasGastas = converteMinutoHora(minutos);
				retorno.add(apontamento);
			}
		}
        return retorno;
	}

	public List<Apontamento> listarTicketsDodia(String dataFormatada,int qtdDias,Usuario usuario){
		List<Apontamento> retorno = new ArrayList<Apontamento>();
		DaoUsuario dao = new DaoUsuario();
			List<Issue> lista = getTicketsDoDia(usuario,qtdDias);
			for (Issue issue : lista) {
				Apontamento apontamento = new Apontamento();

				apontamento.cdTicket=  issue.getKey();
                apontamento.dsTitulo = issue.getSummary();
                apontamento.tpSituacao = issue.getStatus().getName();
                apontamento.nmResponsavel=usuario.getNmUsuario();
                apontamento.cdCliente = issue.getFieldByName("Cliente").getValue().toString().replace("\"", "")
						.replace("[", "").replace("]", "");


				issue = restClient.getIssueClient().getIssue(issue.getKey()).claim();
				Iterable<Worklog> log = issue.getWorklogs();
				List<Worklog> resultados = new ArrayList<Worklog>();
				log.forEach(resultados::add);
				int minutos=0;
				for (Worklog worklog : resultados) {
					if (worklog.getAuthor().getName().equals(usuario.getCdUsuario()) && (worklog.getStartDate()
							.toString("dd/MM/yyyy").equals(dataFormatada))) {
                      minutos = minutos + worklog.getMinutesSpent();
					}
				}
				apontamento.horasGastas = converteMinutoHora(minutos);
				retorno.add(apontamento);
		}
        return retorno;
	}

	public void gerarTicketDoDia(HSSFWorkbook workbook) {

		String titulo = "Ticket do dia %s";
		HSSFSheet sheetUsers = workbook
				.createSheet(String.format(titulo, stringUtil.converteDataParametroD(new Date())));
		Row row = sheetUsers.createRow(TICKET);
		Cell cellTicket = row.createCell(0);
		cellTicket.setCellValue("Ticket");

		Cell cellTitulo = row.createCell(1);
		cellTitulo.setCellValue("Titulo");

		Cell cellStatus = row.createCell(2);
		cellStatus.setCellValue("Status");

		Cell cellCliente = row.createCell(3);
		cellCliente.setCellValue("Cliente");

		Cell cellResponsalvel = row.createCell(4);
		cellResponsalvel.setCellValue("Resposavel");

		Cell cellTempo = row.createCell(5);
		cellTempo.setCellValue("Tempo Gasto");


		DaoUsuario dao = new DaoUsuario();
		List<Usuario> listaUsuario = dao.listar();



		int contador = 1;
		for (Usuario usuario : listaUsuario) {
			List<Issue> lista = getTicketsDoDia(usuario);
			for (Issue issue : lista) {
				row = sheetUsers.createRow(contador);
				cellTicket = row.createCell(0);
				cellTicket.setCellValue(issue.getKey());

				cellTitulo = row.createCell(1);
				cellTitulo.setCellValue(issue.getSummary());

				cellStatus = row.createCell(2);
				cellStatus.setCellValue(issue.getStatus().getName());

				cellCliente = row.createCell(3);
				cellCliente.setCellValue(issue.getFieldByName("Cliente").getValue().toString().replace("\"", "")
						.replace("[", "").replace("]", ""));

				cellResponsalvel = row.createCell(4);
				cellResponsalvel.setCellValue(usuario.getNmUsuario());

				issue = restClient.getIssueClient().getIssue(issue.getKey()).claim();
				Iterable<Worklog> log = issue.getWorklogs();
				List<Worklog> resultados = new ArrayList<Worklog>();
				log.forEach(resultados::add);
				int minutos=0;
				for (Worklog worklog : resultados) {
					if (worklog.getAuthor().getName().equals(usuario.getCdUsuario()) && worklog.getStartDate()
							.toString("dd/MM/yyyy").equals("25/03/2020")) {
                      minutos = minutos + worklog.getMinutesSpent();
					}
				}
				cellTempo = row.createCell(5);
				cellTempo.setCellValue(converteMinutoHora(minutos));


				contador++;
			}
		}

		gerarArquivo(workbook);

	}


	public void gerarTicketDoDia(HSSFWorkbook workbook,List<Apontamento> apontamentos) {

		String titulo = "Ticket do dia %s";
		HSSFSheet sheetUsers = workbook
				.createSheet(String.format(titulo, stringUtil.converteDataParametroD(new Date())));
		Row row = sheetUsers.createRow(TICKET);
		Cell cellTicket = row.createCell(0);
		cellTicket.setCellValue("Ticket");

		Cell cellTitulo = row.createCell(1);
		cellTitulo.setCellValue("Titulo");

		Cell cellStatus = row.createCell(2);
		cellStatus.setCellValue("Status");

		Cell cellCliente = row.createCell(3);
		cellCliente.setCellValue("Cliente");

		Cell cellResponsalvel = row.createCell(4);
		cellResponsalvel.setCellValue("Resposavel");

		Cell cellTempo = row.createCell(5);
		cellTempo.setCellValue("Tempo Gasto");

		int contador = 1;
			for (Apontamento apontamento : apontamentos) {
				row = sheetUsers.createRow(contador);
				cellTicket = row.createCell(0);
				cellTicket.setCellValue(apontamento.getCdTicket());

				cellTitulo = row.createCell(1);
				cellTitulo.setCellValue(apontamento.getDsTitulo());

				cellStatus = row.createCell(2);
				cellStatus.setCellValue(apontamento.getTpSituacao());

				cellCliente = row.createCell(3);
				cellCliente.setCellValue(apontamento.getCdCliente());

				cellResponsalvel = row.createCell(4);
				cellResponsalvel.setCellValue(apontamento.getNmResponsavel());

					cellTempo = row.createCell(5);
				cellTempo.setCellValue(apontamento.getHorasGastas());

				contador++;
			}

		gerarArquivo(workbook);

	}

	public void criarLinha(Issue issue) {

	}

	public void gerarArquivo(HSSFWorkbook workbook) {
		try {
			String nmArquivo =stringUtil.converteDataParametroD2(new Date());
			FileOutputStream out = new FileOutputStream(new File("C:\\empacotador\\R_APONTAMENTO_"+nmArquivo+".xls"));

			workbook.write(out);
			out.close();
			System.out.println("Arquivo Excel criado com sucesso!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo não encontrado!");
		} catch (IOException e) {
			System.out.println("Erro na edição do arquivo!");
		}
	}
}

package br.com.jdsb.valhalla.integracao.quartiz.apontamento;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import application.Apontamento;
import javafx.stage.Stage;

public class GerenciaApontamento implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Stage stage = (Stage) arg0.getJobDetail().getJobDataMap().get("stage");
         Apontamento apontamento = new Apontamento();
         apontamento.start(stage);


	}

}

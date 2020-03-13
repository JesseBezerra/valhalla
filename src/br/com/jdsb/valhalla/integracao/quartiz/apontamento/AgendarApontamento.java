package br.com.jdsb.valhalla.integracao.quartiz.apontamento;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import javafx.stage.Stage;

public class AgendarApontamento {
	public void schedule(Stage primaryStage) {

        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();

            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("stage", primaryStage);

            JobDetail job = JobBuilder.newJob(GerenciaApontamento.class)
            		.setJobData(jobDataMap)
                    .withIdentity("GerenciaApontamento").build();

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("GerenciaApontamento")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/30 * * * ?")).build();

            sched.scheduleJob(job, trigger);

            sched.start();
        } catch (org.quartz.ObjectAlreadyExistsException e) {
        	/**
        	 *Não considero a exceção tendo em vista que o job já foi criado
        	 */
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}

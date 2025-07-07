package dz.eadn.readerpro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableBatchProcessing
public class ReaderProApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReaderProApplication.class, args);
    }

    @Component
    public class JobRunner implements CommandLineRunner {

        private static final Logger logger = LoggerFactory.getLogger(JobRunner.class);

        private final JobLauncher jobLauncher;
        private final Job chequeJob;

        public JobRunner(JobLauncher jobLauncher, Job chequeJob) {
            this.jobLauncher = jobLauncher;
            this.chequeJob = chequeJob;
        }

        @Override
        public void run(String... args) throws Exception {
            logger.info("Starting cheque job execution...");

            try {
                JobParameters params = new JobParametersBuilder()
                        .addLong("timestamp", System.currentTimeMillis())
                        .toJobParameters();

                JobExecution execution = jobLauncher.run(chequeJob, params);

                logger.info("Job execution completed with status: {}", execution.getStatus());
                logger.info("Job execution summary: {}", execution.getExitStatus().getExitDescription());

            } catch (Exception e) {
                logger.error("Job execution failed: ", e);
                throw e;
            }
        }
    }
}

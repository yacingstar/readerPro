package dz.eadn.readerpro;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import dz.eadn.readerpro.model.Cheque;

@Configuration
public class ChequeJobConfig {

    @Bean
    public JdbcCursorItemReader<Cheque> reader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Cheque>()
                .name("chequeReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM CHEQUES")
                .rowMapper(new BeanPropertyRowMapper<>(Cheque.class))
                .build();
    }

    @Bean
    public FlatFileItemWriter<Cheque> writer() {
        return new FlatFileItemWriterBuilder<Cheque>()
                .name("chequeWriter")
                .resource(new FileSystemResource("cheque.txt"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }

    @Bean
    public Step onlyStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, JdbcCursorItemReader<Cheque> reader, FlatFileItemWriter<Cheque> writer) {
        return new StepBuilder("onlyStep", jobRepository)
                .<Cheque, Cheque>chunk(2, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Job chequeJob(JobRepository jobRepository, Step onlyStep) {
        return new JobBuilder("chequeJob", jobRepository)
                .start(onlyStep)
                .build();
    }

}

package dz.eadn.readerpro;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import dz.eadn.readerpro.model.Cheque;

@Configuration
public class OperationJobConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Cheque> sentChequeReader() {
        return new JdbcCursorItemReaderBuilder<Cheque>()
                .name("sentChequeReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM CHEQUE")
                .rowMapper(new BeanPropertyRowMapper<>(Cheque.class))
                .build();
    }

    @Bean
    public FlatFileItemWriter<Cheque> sentChequeWriter() {
        return new FlatFileItemWriterBuilder<Cheque>()
                .name("sentChequeWriter")
                .resource(new FileSystemResource("sent_cheques.txt"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }
}

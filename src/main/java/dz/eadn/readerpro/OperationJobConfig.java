package dz.eadn.readerpro;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.context.annotation.Bean;

import dz.eadn.readerpro.model.Cheque;

@Configuration
public class OperationJobConfig {
    // File to DB (Received from Other banks) =============================
    @Bean
    public FlatFileItemReader<Cheque> receivedChequeFileReader() {
        return new FlatFileItemWriterBuilder<Cheque>()
        .name("receivedChequeFileReader")
        .resource(new ClassPathResource("lot/001.000.005.030.000.LOT"))
        .
    }
}

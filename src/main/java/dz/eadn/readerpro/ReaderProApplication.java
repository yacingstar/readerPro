package dz.eadn.readerpro;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ReaderProApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReaderProApplication.class, args);
    }

}

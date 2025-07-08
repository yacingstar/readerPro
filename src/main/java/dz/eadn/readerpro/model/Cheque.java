package dz.eadn.readerpro.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Cheque {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String chequeNumber;
    private Integer bankCode;
    private String accountNumber;
    private BigDecimal amount;
    private String currency;
    private String status;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return String.format("Cheque{id=%d, chequeNumber='%s', bankCode=%d, accountNumber='%s', amount=%s, currency='%s', status='%s', createdAt=%s}",
                id, chequeNumber, bankCode, accountNumber, amount, currency, status, createdAt);
    }
}

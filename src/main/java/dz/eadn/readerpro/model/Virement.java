package dz.eadn.readerpro.model;

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
public class Virement {

    // Setters
    // Getters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long amount;
    private short currency;
    private long sender_RIP;
    private long receiver_RIP;
    private LocalDateTime created_At;

}

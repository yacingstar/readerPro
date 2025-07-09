package dz.eadn.readerpro.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String type;                // 031
    private String reference;           // random reference
    private String receiverRIB;         // receiver RIB
    private String receiverAgence;      // receiver agency
    private String senderRIP;           // sender RIP
    private String chequeNumber;        // cheque number
    private String senderBankCode;      // sender bank code
    private String senderAgencyCode;    // sender agency code
    private BigDecimal montant;         // amount
    private LocalDateTime date;         // date

    @Lob
    private byte[] chequeImage;         // Binary image data
}

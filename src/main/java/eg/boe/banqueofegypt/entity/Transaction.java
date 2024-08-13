package eg.boe.banqueofegypt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payer_id")
    private Account payer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payee_id")
    private Account payee;
    private String amount;
    private Status status;
    private Date date;

    public enum Status {
        PENDING(0), SUCCESS(200),
        INSUFFICIENT_FUNDS(80090), INVALID_DATA(99993),
        DEST_TIMEOUT(80091), SRC_TIMEOUT(80092);

        public final static Map<Integer, String> codeMessageMapper = Map.of(
                PENDING.code, "Pending",
                SUCCESS.code, "Success",
                INSUFFICIENT_FUNDS.code, "Insufficient funds",
                INVALID_DATA.code, "Invalid data",
                DEST_TIMEOUT.code, "Destination timeout",
                SRC_TIMEOUT.code, "Source timeout"
        );

        public final Integer code;

        Status(Integer code) {
            this.code = code;
        }
    }
}

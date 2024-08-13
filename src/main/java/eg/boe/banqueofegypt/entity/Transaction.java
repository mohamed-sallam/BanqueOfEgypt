package eg.boe.banqueofegypt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "^[0-9]*$", message = "Id must be a number")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payer_id")
    @NotNull(message = "Payer is required")
    private Account payer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payee_id")
    @NotNull(message = "Payee is required")
    private Account payee;

    @Pattern(regexp = "^[0-9]*$", message = "Amount must be a number")
    @Positive(message = "Amount must be not negative or zero")
    @Size(min = 4, max = 9, message = "amounts must be from $10,000 to $1000,000,000")
    private String amount;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date must be in the past or present")
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

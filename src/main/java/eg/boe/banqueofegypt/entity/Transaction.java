package eg.boe.banqueofegypt.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "transaction")
public record Transaction(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "payer_id")
        Account payer,
        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "payee_id")
        Account payee,
        String amount,
        Status status,
        Date date
) {
    public Transaction() {
        this(null, null, null, null, null, null);
    }

    public enum Status {
        PENDING, SUCCESS, FAIL
    }
}

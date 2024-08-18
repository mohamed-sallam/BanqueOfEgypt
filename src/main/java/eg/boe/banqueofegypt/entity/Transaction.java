package eg.boe.banqueofegypt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Pattern(regexp = "^[0-9]*$", message = "Id must be a number")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payer_id")
//    @NotNull(message = "Payer is required")
    private Account payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
//    @NotNull(message = "Payee is required")
    private Account payee;

//    @Pattern(regexp = "^[0-9]*$", message = "Amount must be a number")
//    @Positive(message = "Amount must be not negative or zero")
//    @Size(min = 4, max = 9, message = "amounts must be from $10,000 to $1000,000,000")
    private String amount;

//    @NotNull(message = "Status is required")
//    @Enumerated(EnumType.STRING)
    private Long status;

//    @NotNull(message = "Date is required")
//    @PastOrPresent(message = "Date must be in the past or present")
    private Date date;


}

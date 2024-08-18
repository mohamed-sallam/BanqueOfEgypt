package eg.boe.banqueofegypt.model.dto;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionRetrieval {
    private Long id;
    private AccountPayload payer;
    private AccountPayload payee;
    private String amount;
    private Integer status;
    private Date date;
}

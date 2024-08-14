package eg.boe.banqueofegypt.model.dto;

import eg.boe.banqueofegypt.entity.Status;
import eg.boe.banqueofegypt.entity.Transaction;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionRetrievalDto {
    private Long id;
    private AccountDto payer;
    private AccountDto payee;
    private String amount;
    private Integer status;
    private Date date;
}

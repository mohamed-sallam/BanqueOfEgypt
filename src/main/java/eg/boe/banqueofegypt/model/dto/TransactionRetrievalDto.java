package eg.boe.banqueofegypt.model.dto;

import eg.boe.banqueofegypt.entity.Status;
import eg.boe.banqueofegypt.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRetrievalDto {
    private Long id;
    private AccountDto payer;
    private AccountDto payee;
    private String amount;
//    private Status status;
    private Date date;
}

package eg.boe.banqueofegypt.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class TransactionRetrievalDto {
    private Long id;
    private AccountDto payer;
    private AccountDto payee;
    private String amount;
    private Integer status;
    private Date date;
}

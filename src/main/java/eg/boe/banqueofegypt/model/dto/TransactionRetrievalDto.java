package eg.boe.banqueofegypt.model.dto;

import eg.boe.banqueofegypt.util.PresentationUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Data
public class TransactionRetrievalDto {
    private Long id;
    private AccountDto payer;
    private AccountDto payee;
    private String amount;
    private Integer status;
    private Date date;

    public TransactionRetrievalDto(Long id, AccountDto payer, AccountDto payee, String amount, Integer status, Date date) {
        this.id = id;
        this.payer = payer;
        this.payee = payee;
        setAmount(amount);
        this.status = status;
        this.date = date;
    }

    public void setAmount(String amount) {
        this.amount = PresentationUtil.formatWithPostfix(Long.parseLong(amount));
    }
}

package eg.boe.banqueofegypt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPreservationDto {
    private AccountDto payer;
    private AccountDto payee;
    private String amount;
}

package eg.boe.banqueofegypt.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionMappingDto {
    private String payer;
    private String payee;
    private String amount;
}

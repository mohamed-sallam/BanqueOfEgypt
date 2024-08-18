package eg.boe.banqueofegypt.model.dto;

import lombok.Data;

@Data
public class TransactionPreservationDto {
    private Long payerId;
    private Long payeeId;
    private String amount;
}

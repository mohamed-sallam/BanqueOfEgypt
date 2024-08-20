package eg.boe.banqueofegypt.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TransactionPreservationDto {
    private Long payerId;
    private Long payeeId;
    private String amount;
}

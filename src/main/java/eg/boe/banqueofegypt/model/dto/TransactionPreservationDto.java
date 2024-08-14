package eg.boe.banqueofegypt.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionPreservationDto {
    private Long payerId;
    private Long payeeId;
    private String amount;
}

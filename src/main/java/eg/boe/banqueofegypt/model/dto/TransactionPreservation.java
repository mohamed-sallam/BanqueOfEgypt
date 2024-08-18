package eg.boe.banqueofegypt.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionPreservation {
    private Long payerId;
    private Long payeeId;
    private String amount;
}

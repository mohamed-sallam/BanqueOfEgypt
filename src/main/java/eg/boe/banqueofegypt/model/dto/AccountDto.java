package eg.boe.banqueofegypt.model.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String name;
    private String address;
    private String swiftCode;
    private String balance;
    //todo private List<TransactionDto> transactionDtoList;
}

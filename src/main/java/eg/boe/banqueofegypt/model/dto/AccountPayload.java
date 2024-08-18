package eg.boe.banqueofegypt.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountPayload {
    private Long id;
    private String name;
    private String address;
    private String swiftCode;
    private String balance;
    private String url;

    public static AccountPayload getAccountById(String swiftCode, List<AccountPayload> list){
        return list.stream().filter(a -> a.getSwiftCode().equals(swiftCode)).findFirst().orElse(null);  // null if no account found
    }
    //todo private List<TransactionDto> transactionDtoList;
}

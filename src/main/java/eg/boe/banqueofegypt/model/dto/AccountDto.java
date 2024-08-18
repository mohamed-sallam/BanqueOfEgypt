package eg.boe.banqueofegypt.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountDto {
    private Long id;
    private String name;
    private String address;
    private String swiftCode;
    private String balance;
    private String url;

    public static AccountDto getAccountById(String swiftCode, List<AccountDto> list){
        return list.stream().filter(a -> a.getSwiftCode().equals(swiftCode)).findFirst().orElse(null);  // null if no account found
    }
    //todo private List<TransactionDto> transactionDtoList;
}

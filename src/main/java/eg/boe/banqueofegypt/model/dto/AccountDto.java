package eg.boe.banqueofegypt.model.dto;

import lombok.Data;

@Data
public class AccountDto {
    private Long id;
    private String name;
    private String address;
    private String swiftCode;
    private String balance;
    private String url;
}

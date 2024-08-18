package eg.boe.banqueofegypt.model.dto;

import eg.boe.banqueofegypt.util.PresentationUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AccountDto {
    private Long id;
    private String name;
    private String address;
    private String swiftCode;
    private String balance;
    private String url;

    public AccountDto(Long id, String name, String address, String swiftCode, String balance, String url) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.swiftCode = swiftCode;
        setBalance(balance);
        this.url = url;
    }

    public void setBalance(String balance) {
        try {
            this.balance = PresentationUtil.formatWithPostfix(Long.parseLong(balance));
        } catch (NumberFormatException e) {
            this.balance = balance;
        }
    }
}

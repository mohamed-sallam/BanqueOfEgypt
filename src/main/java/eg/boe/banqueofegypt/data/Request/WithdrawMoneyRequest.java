package eg.boe.banqueofegypt.data.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawMoneyRequest {
    private String token;
    private  String amount;
}

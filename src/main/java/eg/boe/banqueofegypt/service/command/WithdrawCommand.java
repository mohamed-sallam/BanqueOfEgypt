package eg.boe.banqueofegypt.service.command;

import eg.boe.banqueofegypt.data.dto.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.dto.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.exception.BusinessException;
import eg.boe.banqueofegypt.service.ClientRepository;
import eg.boe.banqueofegypt.util.Command;
import eg.boe.banqueofegypt.util.Response;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;

@RequiredArgsConstructor
public class WithdrawCommand implements Command {
    private final WithdrawMoneyRequest withdrawMoneyRequest;
    private final String url;
    private final ClientRepository clientRepository;

    @Override
    public void execute() {
        Response<Void> response;
        try {
            response = clientRepository.withdrawMoney(withdrawMoneyRequest, url);
        } catch (Exception e) {
            throw new BusinessException(408, "Withdraw failed");
        }

        if (response.getCode() != 200)
            throw new BusinessException(response.getCode(), response.getMessage());
    }

    @Override
    public void undo() {
        clientRepository.depositMoney(new DepositMoneyRequest(withdrawMoneyRequest.getToken(), withdrawMoneyRequest.getAmount()), url);
    }
}

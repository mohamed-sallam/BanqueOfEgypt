package eg.boe.banqueofegypt.service.command;

import eg.boe.banqueofegypt.model.request.DepositMoneyRequest;
import eg.boe.banqueofegypt.model.request.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.exception.BusinessException;
import eg.boe.banqueofegypt.service.ClientRepository;
import eg.boe.banqueofegypt.util.Command;
import eg.boe.banqueofegypt.model.response.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DepositCommand implements Command {
    private final DepositMoneyRequest depositMoneyRequest;
    private final String url;
    private final ClientRepository clientRepository;

    @Override
    public void execute() {
        Response<Void> response;
        try {
            response = clientRepository.depositMoney(depositMoneyRequest, url);
        } catch (Exception e) {
            throw new BusinessException(408, "Deposit money failed");
        }

        if (response.getCode() != 200)
            throw new BusinessException(response.getCode(), response.getMessage());
    }

    @Override
    public void undo() {
        clientRepository.withdrawMoney(new WithdrawMoneyRequest(depositMoneyRequest.getAmount()), url);
    }
}

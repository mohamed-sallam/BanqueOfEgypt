package eg.boe.banqueofegypt.service.command;

import eg.boe.banqueofegypt.data.dto.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.dto.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.service.ClientRepository;
import eg.boe.banqueofegypt.util.Command;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DepositCommand implements Command {
    private final DepositMoneyRequest depositMoneyRequest;
    private final String url;
    private final ClientRepository clientRepository;

    @Override
    public void execute() {
        clientRepository.depositMoney(depositMoneyRequest, url);
    }

    @Override
    public void undo() {
        clientRepository.withdrawMoney(new WithdrawMoneyRequest(depositMoneyRequest.getToken(), depositMoneyRequest.getAmount()), url);
    }
}

package eg.boe.banqueofegypt.service.command;

import eg.boe.banqueofegypt.data.dto.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.dto.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.service.ClientRepository;
import eg.boe.banqueofegypt.util.Command;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WithdrawCommand implements Command {
    private final WithdrawMoneyRequest withdrawMoneyRequest;
    private final String url;
    private final ClientRepository clientRepository;

    @Override
    public void execute() {
        clientRepository.withdrawMoney(withdrawMoneyRequest, url);
    }

    @Override
    public void undo() {
        clientRepository.depositMoney(new DepositMoneyRequest(withdrawMoneyRequest.getToken(), withdrawMoneyRequest.getAmount()), url);
    }
}

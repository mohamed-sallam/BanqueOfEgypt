package eg.boe.banqueofegypt.service.command;

import eg.boe.banqueofegypt.data.Request.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.Request.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.service.ClientRepository;
import eg.boe.banqueofegypt.util.Command;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WithdrawCommand implements Command {
    private final WithdrawMoneyRequest withdrawMoneyRequest;
    private final String url;
    private final ClientRepository clientRepository;

    public WithdrawCommand(ClientRepository repo, WithdrawMoneyRequest request, String url) {
        this.clientRepository = repo;
        this.url = url;
        this.withdrawMoneyRequest = request;
    }

    @Override
    public void execute() {
        clientRepository.withdrawMoney(withdrawMoneyRequest, url);
    }

    @Override
    public void undo() {
        clientRepository.depositMoney(new DepositMoneyRequest(withdrawMoneyRequest.getToken(), withdrawMoneyRequest.getAmount()), url);
    }
}

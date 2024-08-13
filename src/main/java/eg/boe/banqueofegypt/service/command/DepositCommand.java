package eg.boe.banqueofegypt.service.command;

import eg.boe.banqueofegypt.data.Request.CheckBalanceRequest;
import eg.boe.banqueofegypt.data.Request.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.Request.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.service.ClientRepository;
import eg.boe.banqueofegypt.util.Command;

public class DepositCommand implements Command {
    private final DepositMoneyRequest depositMoneyRequest;
    private final String url;
    private final ClientRepository clientRepository;

    public DepositCommand(ClientRepository repo, DepositMoneyRequest request, String url) {
        this.clientRepository = repo;
        this.url = url;
        this.depositMoneyRequest = request;
    }

    @Override
    public void execute() {
        clientRepository.depositMoney(depositMoneyRequest, url);
    }

    @Override
    public void undo() {
        clientRepository.withdrawMoney(new WithdrawMoneyRequest(depositMoneyRequest.getToken(), depositMoneyRequest.getAmount()), url);
    }
}

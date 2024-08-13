package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.ClientService;
import eg.boe.banqueofegypt.data.Request.CheckBalanceRequest;
import eg.boe.banqueofegypt.data.Request.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.Request.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.data.Response.BalanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public void deposit(DepositMoneyRequest request, String url) {
        clientRepository.depositMoney(request, url);
    }

    @Override
    public void withdraw(WithdrawMoneyRequest request, String url) {
        clientRepository.withdrawMoney(request, url);
    }

    @Override
    public BalanceResponse getBalance(CheckBalanceRequest request, String url) {
        return (BalanceResponse) (clientRepository.checkBalance(request, url).getData());
    }
}

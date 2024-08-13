package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.ClientService;
import eg.boe.banqueofegypt.data.dto.CheckBalanceRequest;
import eg.boe.banqueofegypt.data.dto.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.dto.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.data.dto.BalanceResponse;
import eg.boe.banqueofegypt.util.Response;
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
        Response<Void> response = clientRepository.withdrawMoney(request, url);
    }

    @Override
    public BalanceResponse getBalance(CheckBalanceRequest request, String url) {
        return (BalanceResponse) (clientRepository.checkBalance(request, url).getData());
    }
}

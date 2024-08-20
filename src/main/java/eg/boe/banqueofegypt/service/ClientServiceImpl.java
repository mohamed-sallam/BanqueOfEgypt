package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.ClientService;
import eg.boe.banqueofegypt.model.response.BalanceResponse;
import eg.boe.banqueofegypt.model.request.DepositMoneyRequest;
import eg.boe.banqueofegypt.model.request.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.exception.BusinessException;
import eg.boe.banqueofegypt.service.command.DepositCommand;
import eg.boe.banqueofegypt.service.command.WithdrawCommand;
import eg.boe.banqueofegypt.util.Command;
import eg.boe.banqueofegypt.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Command deposit(DepositMoneyRequest request, String url) {
        return new DepositCommand(request, url, clientRepository);
    }

    @Override
    public Command withdraw(WithdrawMoneyRequest request, String url) {
            return new WithdrawCommand(request, url, clientRepository);
    }

    @Override
    public BalanceResponse getBalance(String url)  {
        try {
            Response<BalanceResponse> response = clientRepository.checkBalance(url);
            if (response.getCode() != 200) throw new BusinessException(response.getCode(), response.getMessage());
            return response.getData();
        }catch (Exception e) {
            throw new BusinessException(500, "Failed to get balance");
        }
    }
}

package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.exception.BusinessException;
import eg.boe.banqueofegypt.model.response.BalanceResponse;
import eg.boe.banqueofegypt.model.request.DepositMoneyRequest;
import eg.boe.banqueofegypt.model.request.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.util.Command;
import org.aspectj.weaver.BCException;

public interface ClientService {
    Command deposit(DepositMoneyRequest request, String url);

    Command withdraw(WithdrawMoneyRequest request, String url);

    BalanceResponse getBalance(String url);
}

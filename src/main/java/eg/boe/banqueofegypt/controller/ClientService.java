package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.request.BalanceRequest;
import eg.boe.banqueofegypt.model.request.DepositMoneyRequest;
import eg.boe.banqueofegypt.model.request.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.model.response.BalanceResponse;
import eg.boe.banqueofegypt.util.Command;

public interface ClientService {
    Command deposit(DepositMoneyRequest request, String url);
    Command withdraw(WithdrawMoneyRequest request, String url);
    BalanceResponse getBalance(BalanceRequest request, String url);
}

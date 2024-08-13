package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.data.dto.CheckBalanceRequest;
import eg.boe.banqueofegypt.data.dto.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.dto.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.data.dto.BalanceResponse;

public interface ClientService {
    void deposit(DepositMoneyRequest request, String url);
    void withdraw(WithdrawMoneyRequest request, String url);
    BalanceResponse getBalance(CheckBalanceRequest request, String url);
}

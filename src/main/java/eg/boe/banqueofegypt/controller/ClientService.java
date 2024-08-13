package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.data.Request.CheckBalanceRequest;
import eg.boe.banqueofegypt.data.Request.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.Request.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.data.Response.BalanceResponse;

public interface ClientService {
    void deposit(DepositMoneyRequest request, String url);
    void withdraw(WithdrawMoneyRequest request, String url);
    BalanceResponse getBalance(CheckBalanceRequest request, String url);
}

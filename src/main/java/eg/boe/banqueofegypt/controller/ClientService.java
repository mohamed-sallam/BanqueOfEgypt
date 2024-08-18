package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.data.dto.BalanceResponse;
import eg.boe.banqueofegypt.data.dto.CheckBalanceRequest;
import eg.boe.banqueofegypt.data.dto.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.dto.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.util.Command;

public interface ClientService {
    Command deposit(DepositMoneyRequest request, String url);
    Command withdraw(WithdrawMoneyRequest request, String url);
    BalanceResponse getBalance(CheckBalanceRequest request, String url);
}

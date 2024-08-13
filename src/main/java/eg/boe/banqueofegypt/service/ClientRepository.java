package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.data.Request.CheckBalanceRequest;
import eg.boe.banqueofegypt.data.Request.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.Request.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.util.Response;

public interface ClientRepository {
    Response<?> checkBalance(CheckBalanceRequest request, String url);

    Response<?> depositMoney(DepositMoneyRequest request, String url);

    Response<?> withdrawMoney(WithdrawMoneyRequest request, String url);
}

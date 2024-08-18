package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.model.response.BalanceResponse;
import eg.boe.banqueofegypt.model.request.DepositMoneyRequest;
import eg.boe.banqueofegypt.model.request.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.model.response.Response;

public interface ClientRepository {
    Response<BalanceResponse> checkBalance(String url);

    Response<Void> depositMoney(DepositMoneyRequest request, String url);

    Response<Void> withdrawMoney(WithdrawMoneyRequest request, String url);
}

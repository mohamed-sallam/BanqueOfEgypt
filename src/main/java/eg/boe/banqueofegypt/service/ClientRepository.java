package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.data.dto.BalanceResponse;
import eg.boe.banqueofegypt.data.dto.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.dto.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.util.Response;

public interface ClientRepository {
    Response<BalanceResponse> checkBalance(String url);

    Response<Void> depositMoney(DepositMoneyRequest request, String url);

    Response<Void> withdrawMoney(WithdrawMoneyRequest request, String url);
}

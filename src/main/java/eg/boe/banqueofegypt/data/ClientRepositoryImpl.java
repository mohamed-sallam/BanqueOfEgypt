package eg.boe.banqueofegypt.data;

import eg.boe.banqueofegypt.data.Request.CheckBalanceRequest;
import eg.boe.banqueofegypt.data.Request.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.Request.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.util.Response;
import eg.boe.banqueofegypt.service.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@AllArgsConstructor
@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private final RestClient restClient = RestClient.create();

    public Response<?> checkBalance(CheckBalanceRequest request, String url) {
        return restClient
                .post()
                .uri(url + "/balance")
                .body(request)
                .retrieve()
                .body(Response.class);
    }

    public Response<?> depositMoney(DepositMoneyRequest request, String url) {
        return restClient
                .post()
                .uri(url + "/deposit")
                .body(request)
                .retrieve()
                .body(Response.class);
    }

    public Response<?> withdrawMoney(WithdrawMoneyRequest request, String url) {
        return restClient
                .post()
                .uri(url + "/withdraw")
                .body(request)
                .retrieve()
                .body(Response.class);
    }
}

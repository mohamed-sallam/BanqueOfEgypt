package eg.boe.banqueofegypt.data;

import eg.boe.banqueofegypt.data.Request.CheckBalanceRequest;
import eg.boe.banqueofegypt.data.Response.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestClient;

@AllArgsConstructor
public class ClientRepositoryImpl {
    RestClient restClient = RestClient.create();

    Response<?> checkBalance(CheckBalanceRequest request, String url) {
        return restClient
                .post()
                .uri(url + "/balance")
                .body(request)
                .retrieve()
                .body(Response.class);
    }

    Response<?> depositMoney(CheckBalanceRequest request, String url) {
        return restClient
                .post()
                .uri(url + "/deposit")
                .body(request)
                .retrieve()
                .body(Response.class);
    }

    Response<?> withdrawMoney(CheckBalanceRequest request, String url) {
        return restClient
                .post()
                .uri(url + "/withdraw")
                .body(request)
                .retrieve()
                .body(Response.class);
    }
}

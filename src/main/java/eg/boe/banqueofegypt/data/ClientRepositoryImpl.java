package eg.boe.banqueofegypt.data;

import eg.boe.banqueofegypt.model.request.BalanceRequest;
import eg.boe.banqueofegypt.model.request.DepositMoneyRequest;
import eg.boe.banqueofegypt.model.request.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.model.response.BalanceResponse;
import eg.boe.banqueofegypt.util.Response;
import eg.boe.banqueofegypt.service.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Response<BalanceResponse> checkBalance(BalanceRequest request, String url) {
        HttpEntity<BalanceRequest> entity = new HttpEntity<>(request);
        return restTemplate.exchange(
                url + "/balance",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Response<BalanceResponse>>() {}
        ).getBody();
    }

    @Override
    public Response<Void> depositMoney(DepositMoneyRequest request, String url) {
        HttpEntity<DepositMoneyRequest> entity = new HttpEntity<>(request);
        return restTemplate.exchange(
                url + "/deposit",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Response<Void>>() {}
        ).getBody();
    }

    @Override
    public Response<Void> withdrawMoney(WithdrawMoneyRequest request, String url) {
        HttpEntity<WithdrawMoneyRequest> entity = new HttpEntity<>(request);
        return restTemplate.exchange(
                url + "/withdraw",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Response<Void>>() {}
        ).getBody();
    }
}

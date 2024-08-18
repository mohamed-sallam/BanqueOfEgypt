package eg.boe.banqueofegypt.data;

import eg.boe.banqueofegypt.config.HttpClientConfig;
import eg.boe.banqueofegypt.data.dto.BalanceResponse;
import eg.boe.banqueofegypt.data.dto.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.dto.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.service.ClientRepository;
import eg.boe.banqueofegypt.util.Response;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static eg.boe.banqueofegypt.service.TransactionServiceImpl.TOKEN;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private final RestTemplate restTemplate;

    public ClientRepositoryImpl(HttpClientConfig httpClientConfig) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(httpClientConfig.connectTimeout());
        requestFactory.setReadTimeout(httpClientConfig.readTimeout());
        this.restTemplate = new RestTemplate(requestFactory);
    }

    @Override
    public Response<BalanceResponse> checkBalance( String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", TOKEN);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                url + "/balance",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Response<BalanceResponse>>() {
                }
        ).getBody();
    }

    @Override
    public Response<Void> depositMoney(DepositMoneyRequest request, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", TOKEN);
        HttpEntity<DepositMoneyRequest> entity = new HttpEntity<>(request);
        return restTemplate.exchange(
                url + "/deposit",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Response<Void>>() {
                }
        ).getBody();
    }

    @Override
    public Response<Void> withdrawMoney(WithdrawMoneyRequest request, String url) {
        HttpEntity<WithdrawMoneyRequest> entity = new HttpEntity<>(request);
        return restTemplate.exchange(
                url + "/withdraw",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Response<Void>>() {
                }
        ).getBody();
    }
}

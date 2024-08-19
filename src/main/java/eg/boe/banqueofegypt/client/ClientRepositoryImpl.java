package eg.boe.banqueofegypt.client;

import eg.boe.banqueofegypt.config.HttpClientConfig;
import eg.boe.banqueofegypt.exception.BusinessException;
import eg.boe.banqueofegypt.model.response.BalanceResponse;
import eg.boe.banqueofegypt.model.request.DepositMoneyRequest;
import eg.boe.banqueofegypt.model.request.WithdrawMoneyRequest;
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
    public Response<BalanceResponse> checkBalance(String url)  {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("token", TOKEN);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            return restTemplate.exchange(
                    url + "/balance",
                    HttpMethod.POST,
                    entity,
                    new ParameterizedTypeReference<Response<BalanceResponse>>() {
                    }
            ).getBody();
        }catch (Exception e) {
            throw new BusinessException(500, "Failed to check balance");
        }
    }

    @Override
    public Response<Void> depositMoney(DepositMoneyRequest request, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", TOKEN);
        HttpEntity<DepositMoneyRequest> entity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(
                url + "/deposit",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Response<Void>>() {
                }
        ).getBody();
    }

    @Override
    public Response<Void> withdrawMoney(WithdrawMoneyRequest request, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", TOKEN);
        HttpEntity<WithdrawMoneyRequest> entity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(
                url + "/withdraw",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Response<Void>>() {
                }
        ).getBody();
    }
}

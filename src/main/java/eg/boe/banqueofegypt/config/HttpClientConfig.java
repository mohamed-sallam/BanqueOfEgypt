package eg.boe.banqueofegypt.config;

public record HttpClientConfig(
        int readTimeout,
        int connectTimeout
) {
}

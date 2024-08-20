package eg.boe.banqueofegypt.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public HttpClientConfig httpClientConfig() {
        return new HttpClientConfig(3000, 2000);
    }
}

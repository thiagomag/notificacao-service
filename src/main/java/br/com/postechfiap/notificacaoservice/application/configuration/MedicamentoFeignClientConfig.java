package br.com.postechfiap.notificacaoservice.application.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicamentoFeignClientConfig {

    @Value("${client.medicamentos-service.api-key}")
    private String medicamentosServiceApiKey;

    @Bean
    public RequestInterceptor medicamentosServiceApiKeyInterceptor() {
        return requestTemplate -> requestTemplate.header("X-API-Key", medicamentosServiceApiKey);
    }
}

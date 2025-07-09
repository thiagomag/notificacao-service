package br.com.postechfiap.notificacaoservice.application.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastroUsuacriosFeignClientConfig {

    @Value("${client.cadastro-usuarios-service.api-key}")
    private String cadastroUsuariosServiceApiKey;

    @Bean
    public RequestInterceptor cadastroUsuariosServiceApiKeyInterceptor() {
        return requestTemplate -> requestTemplate.header("X-API-Key", cadastroUsuariosServiceApiKey);
    }
}

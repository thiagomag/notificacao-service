package br.com.postechfiap.notificacaoservice.infraestructure.client;

import br.com.postechfiap.notificacaoservice.application.configuration.CadastroUsuacriosFeignClientConfig;
import br.com.postechfiap.notificacaoservice.infraestructure.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "${client.cadastro-usuarios-service.name}",
        url = "${client.cadastro-usuarios-service.url}",
        configuration = CadastroUsuacriosFeignClientConfig.class
)
public interface CadastroUsuarioServiceClient {

    @GetMapping("/users/{userId}")
    UserResponse buscarUsuarioPorId(@PathVariable("userId") Long userId);
}

package br.com.postechfiap.notificacaoservice.infraestructure.client;

import br.com.postechfiap.notificacaoservice.application.configuration.FeignClientConfig;
import br.com.postechfiap.notificacaoservice.infraestructure.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "${client.cadastro-usuarios-service.name}", // Usando o nome do config
        url = "${client.cadastro-usuarios-service.url}", // Já estava assim
        configuration = FeignClientConfig.class
)
public interface CadastroUsuarioServiceClient {

    @GetMapping("/users/{id}")
    UserResponse buscarUsuarioPorId(Long userId);
}

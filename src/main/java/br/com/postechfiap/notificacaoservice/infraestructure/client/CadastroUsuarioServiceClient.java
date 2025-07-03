package br.com.postechfiap.notificacaoservice.infraestructure.client;

import br.com.postechfiap.notificacaoservice.infraestructure.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "CadastroUsuariosServiceClient", url = "${client.cadastro-usuarios-service.url}")
public interface CadastroUsuarioServiceClient {

    @GetMapping("/users/{id}")
    UserResponse buscarUsuarioPorId(Long userId);
}

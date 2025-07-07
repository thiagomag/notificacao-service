package br.com.postechfiap.notificacaoservice.infraestructure.controller.adapters;

import br.com.postechfiap.notificacaoservice.domain.entity.NotificacaoUsuarios;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoUsuariosDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificacaoUsuariosAdapeter {

    public List<NotificacaoUsuarios> adapt(NotificacaoUsuariosDto notificacaoUsuariosDto) {
        return notificacaoUsuariosDto.getIdUsuarios()
                .stream()
                .map(idUsuario -> NotificacaoUsuarios.builder()
                        .idUsuario(idUsuario)
                        .idNotificacao(notificacaoUsuariosDto.getIdNotificacao())
                        .build())
                .toList();
    }
}

package br.com.postechfiap.notificacaoservice.infraestructure.gateways.adapters;

import br.com.postechfiap.notificacaoservice.domain.entity.NotificacaoUsuarios;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoUsuariosResponse;
import br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities.NotificacaoEntity;
import br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities.NotificacaoUsuariosEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificacaoUsuariosEntityAdapter {

    public NotificacaoUsuariosEntity toEntity(Long idUsuario, NotificacaoEntity notificacao) {
        return NotificacaoUsuariosEntity.builder()
                .idUsuario(idUsuario)
                .notificacao(notificacao)
                .build();
    }

    public NotificacaoUsuariosEntity toEntity(NotificacaoUsuarios notificacaoUsuarios, NotificacaoEntity notificacao) {
        return NotificacaoUsuariosEntity.builder()
                .idUsuario(notificacaoUsuarios.getIdUsuario())
                .notificacao(notificacao)
                .build();
    }

    public List<NotificacaoUsuariosResponse> toResponseList(List<NotificacaoUsuariosEntity> notificacaoUsuariosEntity) {
        return notificacaoUsuariosEntity
                .stream()
                .map(entity -> NotificacaoUsuariosResponse.builder()
                        .idUsuario(entity.getIdUsuario())
                        .idNotificacao(entity.getNotificacao().getId())
                        .build())
                .toList();
    }
}

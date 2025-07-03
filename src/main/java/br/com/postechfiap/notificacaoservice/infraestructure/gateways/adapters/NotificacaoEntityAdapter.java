package br.com.postechfiap.notificacaoservice.infraestructure.gateways.adapters;

import br.com.postechfiap.notificacaoservice.domain.entity.Notificacao;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoResponse;
import br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities.NotificacaoEntity;
import br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities.NotificacaoUsuariosEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificacaoEntityAdapter {

    private final NotificacaoUsuariosEntityAdapter notificacaoUsuariosEntityAdapter;

    public NotificacaoEntity toNotificacaoEntity(Notificacao notificacao) {
        return NotificacaoEntity.builder()
                .nome(notificacao.getNome())
                .descricao(notificacao.getDescricao())
                .tipo(notificacao.getTipo())
                .build();
    }

    public NotificacaoResponse toNotificacaoResponse(NotificacaoEntity notificacaoEntity, List<NotificacaoUsuariosEntity> notificacaoUsuariosList) {
        return NotificacaoResponse.builder()
                .id(notificacaoEntity.getId())
                .nome(notificacaoEntity.getNome())
                .descricao(notificacaoEntity.getDescricao())
                .tipo(notificacaoEntity.getTipo())
                .usuariosNotificacao(notificacaoUsuariosEntityAdapter.toResponseList(notificacaoUsuariosList))
                .build();
    }
}

package br.com.postechfiap.notificacaoservice.application.gateways;

import br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities.NotificacaoUsuariosEntity;

import java.util.List;

public interface NotificacaoUsuariosGateway extends BaseGateway<NotificacaoUsuariosEntity, Long> {

    List<NotificacaoUsuariosEntity> findByNotificacaoId(Long notificacaoId);
}

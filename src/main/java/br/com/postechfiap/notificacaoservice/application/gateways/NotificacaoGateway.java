package br.com.postechfiap.notificacaoservice.application.gateways;

import br.com.postechfiap.notificacaoservice.domain.enums.TipoNotificacaoEnum;
import br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities.NotificacaoEntity;

import java.util.Optional;

public interface NotificacaoGateway extends BaseGateway<NotificacaoEntity, Long> {

    Optional<NotificacaoEntity> findByTipo(TipoNotificacaoEnum tipo);

}

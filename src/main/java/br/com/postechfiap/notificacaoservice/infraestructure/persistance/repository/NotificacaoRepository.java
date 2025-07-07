package br.com.postechfiap.notificacaoservice.infraestructure.persistance.repository;

import br.com.postechfiap.notificacaoservice.domain.enums.TipoNotificacaoEnum;
import br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities.NotificacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificacaoRepository extends JpaRepository<NotificacaoEntity, Long> {

    Optional<NotificacaoEntity> findByTipoAndDeletedTmspIsNull(TipoNotificacaoEnum tipo);
}

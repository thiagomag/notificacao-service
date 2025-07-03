package br.com.postechfiap.notificacaoservice.infraestructure.persistance.repository;

import br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities.NotificacaoUsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoUsuariosRepository extends JpaRepository<NotificacaoUsuariosEntity, Long> {

    List<NotificacaoUsuariosEntity> findByNotificacao_IdAndDeletedTmspIsNull(Long notificacaoId);
}

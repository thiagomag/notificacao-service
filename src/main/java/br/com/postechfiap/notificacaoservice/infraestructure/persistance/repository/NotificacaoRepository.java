package br.com.postechfiap.notificacaoservice.infraestructure.persistance.repository;

import br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities.NotificacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<NotificacaoEntity, Long> {
}

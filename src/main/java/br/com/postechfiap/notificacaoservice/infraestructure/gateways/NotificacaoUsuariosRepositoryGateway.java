package br.com.postechfiap.notificacaoservice.infraestructure.gateways;

import br.com.postechfiap.notificacaoservice.application.gateways.NotificacaoUsuariosGateway;
import br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities.BaseEntity;
import br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities.NotificacaoUsuariosEntity;
import br.com.postechfiap.notificacaoservice.infraestructure.persistance.repository.NotificacaoUsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NotificacaoUsuariosRepositoryGateway implements NotificacaoUsuariosGateway {

    private final NotificacaoUsuariosRepository notificacaoUsuariosRepository;

    @Override
    public NotificacaoUsuariosEntity save(NotificacaoUsuariosEntity entity) {
        return notificacaoUsuariosRepository.save(entity);
    }

    @Override
    public List<NotificacaoUsuariosEntity> saveAll(List<NotificacaoUsuariosEntity> entities) {
        return notificacaoUsuariosRepository.saveAll(entities);
    }

    @Override
    public List<NotificacaoUsuariosEntity> findAll() {
        return notificacaoUsuariosRepository.findAll();
    }

    @Override
    public Optional<NotificacaoUsuariosEntity> findById(Long id) {
        return notificacaoUsuariosRepository.findById(id);
    }

    @Override
    public void delete(NotificacaoUsuariosEntity entity) {
        entity.delete();
        notificacaoUsuariosRepository.save(entity);
    }

    @Override
    public void deleteAll(List<NotificacaoUsuariosEntity> entities) {
        entities.forEach(BaseEntity::delete);
        notificacaoUsuariosRepository.saveAll(entities);
    }

    @Override
    public boolean existsById(Long id) {
        return notificacaoUsuariosRepository.existsById(id);
    }

    @Override
    public NotificacaoUsuariosEntity update(NotificacaoUsuariosEntity entity) {
        entity.setUpdatedAt(LocalDateTime.now());
        return notificacaoUsuariosRepository.save(entity);
    }

    @Override
    public List<NotificacaoUsuariosEntity> findByNotificacaoId(Long notificacaoId) {
        return notificacaoUsuariosRepository.findByNotificacao_IdAndDeletedTmspIsNull(notificacaoId);
    }
}

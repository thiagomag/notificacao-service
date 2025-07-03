package br.com.postechfiap.notificacaoservice.infraestructure.gateways;

import br.com.postechfiap.notificacaoservice.application.gatways.NotificacaoGateway;
import br.com.postechfiap.notificacaoservice.domain.enums.TipoNotificacaoEnum;
import br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities.NotificacaoEntity;
import br.com.postechfiap.notificacaoservice.infraestructure.persistance.repository.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NotificacaoRepositoryGateway implements NotificacaoGateway {

    private final NotificacaoRepository notificacaoRepository;

    @Override
    public NotificacaoEntity save(NotificacaoEntity entity) {
        return notificacaoRepository.save(entity);
    }

    @Override
    public List<NotificacaoEntity> findAll() {
        return notificacaoRepository.findAll();
    }

    @Override
    public Optional<NotificacaoEntity> findById(Long id) {
        return notificacaoRepository.findById(id);
    }

    @Override
    public void delete(NotificacaoEntity entity) {
        entity.delete();
        notificacaoRepository.save(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return notificacaoRepository.existsById(id);
    }

    @Override
    public NotificacaoEntity update(NotificacaoEntity entity) {
        entity.setUpdatedAt(LocalDateTime.now());
        return notificacaoRepository.save(entity);
    }

    @Override
    public Optional<NotificacaoEntity> findByTipo(TipoNotificacaoEnum tipo) {
        return notificacaoRepository.findByTipoAndDescricaoIsNull(tipo);
    }
}

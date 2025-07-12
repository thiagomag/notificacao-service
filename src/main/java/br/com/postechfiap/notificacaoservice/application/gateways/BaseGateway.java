package br.com.postechfiap.notificacaoservice.application.gateways;

import br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseGateway<T extends BaseEntity<ID>, ID> {

    T save(T entity);

    List<T> saveAll(List<T> entities);

    List<T> findAll();

    Optional<T> findById(Long id);

    void delete(T entity);

    void deleteAll(List<T> entities);

    boolean existsById(Long id);

    T update(T entity);
}

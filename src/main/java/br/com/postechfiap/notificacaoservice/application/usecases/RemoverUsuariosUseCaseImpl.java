package br.com.postechfiap.notificacaoservice.application.usecases;

import br.com.postechfiap.notificacaoservice.application.gateways.NotificacaoGateway;
import br.com.postechfiap.notificacaoservice.application.gateways.NotificacaoUsuariosGateway;
import br.com.postechfiap.notificacaoservice.application.interfaces.RemoverUsuariosUseCase;
import br.com.postechfiap.notificacaoservice.domain.entity.NotificacaoUsuarios;
import br.com.postechfiap.notificacaoservice.infraestructure.gateways.adapters.NotificacaoUsuariosEntityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RemoverUsuariosUseCaseImpl implements RemoverUsuariosUseCase {

    private final NotificacaoGateway notificacaoGateway;
    private final NotificacaoUsuariosGateway notificacaoUsuariosGateway;
    private final NotificacaoUsuariosEntityAdapter notificacaoUsuariosAdapeter;

    @Override
    public String execute(List<NotificacaoUsuarios> notificacaoUsuarios) {
        final var notificacaoEntity = notificacaoGateway.findById(notificacaoUsuarios.getFirst().getIdNotificacao())
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
        final var notificacaoUsuariosEntities = notificacaoUsuarios.stream()
                .map(notificacaoUsuarios1 -> notificacaoUsuariosAdapeter.toEntity(notificacaoUsuarios1, notificacaoEntity))
                .toList();
        notificacaoUsuariosGateway.deleteAll(notificacaoUsuariosEntities);
        return "Usuários removidos com sucesso da notificação: " + notificacaoEntity.getId();
    }
}

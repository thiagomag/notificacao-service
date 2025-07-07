package br.com.postechfiap.notificacaoservice.application.usecases;

import br.com.postechfiap.notificacaoservice.application.gatways.NotificacaoGateway;
import br.com.postechfiap.notificacaoservice.application.gatways.NotificacaoUsuariosGateway;
import br.com.postechfiap.notificacaoservice.application.interfaces.AdicionarUsuariosUseCase;
import br.com.postechfiap.notificacaoservice.domain.entity.NotificacaoUsuarios;
import br.com.postechfiap.notificacaoservice.infraestructure.gateways.adapters.NotificacaoUsuariosEntityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdicionarUsuariosUseCaseImpl implements AdicionarUsuariosUseCase {

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
        notificacaoUsuariosGateway.saveAll(notificacaoUsuariosEntities);
        return "Usuarios adicionados com sucesso à notificação: " + notificacaoEntity.getId();
    }
}

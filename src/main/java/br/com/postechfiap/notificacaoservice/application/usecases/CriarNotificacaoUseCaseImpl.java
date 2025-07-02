package br.com.postechfiap.notificacaoservice.application.usecases;

import br.com.postechfiap.notificacaoservice.application.gatways.NotificacaoGateway;
import br.com.postechfiap.notificacaoservice.application.gatways.NotificacaoUsuariosGateway;
import br.com.postechfiap.notificacaoservice.application.interfaces.CriarNotificacaoUseCase;
import br.com.postechfiap.notificacaoservice.domain.entity.Notificacao;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoResponse;
import br.com.postechfiap.notificacaoservice.infraestructure.gateways.adapters.NotificacaoEntityAdapter;
import br.com.postechfiap.notificacaoservice.infraestructure.gateways.adapters.NotificacaoUsuariosEntityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarNotificacaoUseCaseImpl implements CriarNotificacaoUseCase {

    private final NotificacaoGateway notificacaoGateway;
    private final NotificacaoUsuariosGateway notificacaoUsuariosGateway;
    private final NotificacaoEntityAdapter notificacaoEntityAdapter;
    private final NotificacaoUsuariosEntityAdapter notificacaoUsuariosAdapter;

    @Override
    public NotificacaoResponse execute(Notificacao notificacao) {
        final var notificacaoEntity = notificacaoEntityAdapter.toNotificacaoEntity(notificacao);
        final var savedNotificacao = notificacaoGateway.save(notificacaoEntity);
        final var notificacaoUsuariosList = notificacao.getIdUsuarios().stream().map(id -> {
            final var notificacaoUsuariosEntity = notificacaoUsuariosAdapter.toEntity(id, savedNotificacao);
            return notificacaoUsuariosGateway.save(notificacaoUsuariosEntity);
        }).toList();
        return notificacaoEntityAdapter.toNotificacaoResponse(savedNotificacao, notificacaoUsuariosList);
    }
}

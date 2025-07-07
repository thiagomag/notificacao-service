package br.com.postechfiap.notificacaoservice.application.usecases;

import br.com.postechfiap.notificacaoservice.application.gatways.NotificacaoGateway;
import br.com.postechfiap.notificacaoservice.application.gatways.NotificacaoUsuariosGateway;
import br.com.postechfiap.notificacaoservice.application.interfaces.BuscarNotificacaoPorIdUseCase;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoResponse;
import br.com.postechfiap.notificacaoservice.infraestructure.gateways.adapters.NotificacaoEntityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarNotificacaoPorIdUseCaseImpl implements BuscarNotificacaoPorIdUseCase {

    private final NotificacaoGateway notificacaoGateway;
    private final NotificacaoUsuariosGateway notificacaoUsuariosGateway;
    private final NotificacaoEntityAdapter notificacaoEntityAdapter;

    @Override
    public NotificacaoResponse execute(Long notificacaoId) {
        final var notificacaoEntity = notificacaoGateway.findById(notificacaoId)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
        final var notificacaoUsuariosEntities = notificacaoUsuariosGateway.findByNotificacaoId(notificacaoId);
        return notificacaoEntityAdapter.toNotificacaoResponse(notificacaoEntity, notificacaoUsuariosEntities);
    }
}

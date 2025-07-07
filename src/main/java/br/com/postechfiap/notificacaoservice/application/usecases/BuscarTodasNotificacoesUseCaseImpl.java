package br.com.postechfiap.notificacaoservice.application.usecases;

import br.com.postechfiap.notificacaoservice.application.gatways.NotificacaoGateway;
import br.com.postechfiap.notificacaoservice.application.gatways.NotificacaoUsuariosGateway;
import br.com.postechfiap.notificacaoservice.application.interfaces.BuscarTodasNotificacoesUseCase;
import br.com.postechfiap.notificacaoservice.application.usecases.dto.NotificacaoQueryParams;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoResponse;
import br.com.postechfiap.notificacaoservice.infraestructure.gateways.adapters.NotificacaoEntityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarTodasNotificacoesUseCaseImpl implements BuscarTodasNotificacoesUseCase {

    private final NotificacaoGateway notificacaoGateway;
    private final NotificacaoUsuariosGateway notificacaoUsuariosGateway;
    private final NotificacaoEntityAdapter notificacaoEntityAdapter;

    @Override
    public List<NotificacaoResponse> execute(NotificacaoQueryParams notificacaoQueryParams) {
        return notificacaoGateway.findAll()
                .stream()
                .map(notificacaoEntity -> {
                    final var notificacaoUsuarios = notificacaoUsuariosGateway.findByNotificacaoId(notificacaoEntity.getId());
                    return notificacaoEntityAdapter.toNotificacaoResponse(notificacaoEntity, notificacaoUsuarios);
                })
                .toList();
    }
}

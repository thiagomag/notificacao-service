package br.com.postechfiap.notificacaoservice.application.interfaces;

import br.com.postechfiap.notificacaoservice.application.usecases.dto.NotificacaoQueryParams;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoResponse;

import java.util.List;

public interface BuscarTodasNotificacoesUseCase extends UseCase<NotificacaoQueryParams, List<NotificacaoResponse>> {
}

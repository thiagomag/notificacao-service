package br.com.postechfiap.notificacaoservice.application.interfaces;

import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoResponse;

public interface BuscarNotificacaoPorIdUseCase extends UseCase<Long, NotificacaoResponse> {
}

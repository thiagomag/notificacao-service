package br.com.postechfiap.notificacaoservice.application.interfaces;

import br.com.postechfiap.notificacaoservice.domain.entity.Notificacao;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoResponse;

public interface CriarNotificacaoUseCase extends UseCase<Notificacao, NotificacaoResponse>{
}

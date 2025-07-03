package br.com.postechfiap.notificacaoservice.application.usecases.dto;

import br.com.postechfiap.notificacaoservice.infraestructure.listener.dto.NotificacaoEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EnviarNotificacaoContext {

    private NotificacaoEvent notificacaoEvent;
}

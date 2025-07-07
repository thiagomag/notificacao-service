package br.com.postechfiap.notificacaoservice.infraestructure.listener;

import br.com.postechfiap.notificacaoservice.application.interfaces.EnviarNotificacaoUseCase;
import br.com.postechfiap.notificacaoservice.application.usecases.dto.EnviarNotificacaoContext;
import br.com.postechfiap.notificacaoservice.infraestructure.listener.dto.EstoqueAlertaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(
        topics = "medicamento-fim-topic",
        groupId = "notificacao-service",
        id = "string-listener-test"
)
public class NotificacaoListener {

    private final EnviarNotificacaoUseCase enviarNotificacaoUseCase;

    @KafkaHandler
    public void onPedidoCreated(@Payload EstoqueAlertaDTO estoqueAlertaDTO) {
        try {
            log.info("Mensagem desserializada recebida: {}", estoqueAlertaDTO.toString());

            final var context = EnviarNotificacaoContext.builder()
                    .estoqueAlertaDTO(estoqueAlertaDTO)
                    .build();
            enviarNotificacaoUseCase.execute(context);

            log.info("✅ Notificação processada com sucesso para o SKU: {}", estoqueAlertaDTO.getSku());

        } catch (Exception e) {
            log.error("❌ Erro ao executar o caso de uso para a notificação: {}", e.getMessage());
        }
    }
}

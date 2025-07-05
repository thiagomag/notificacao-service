package br.com.postechfiap.notificacaoservice.infraestructure.listener;

import br.com.postechfiap.notificacaoservice.application.interfaces.EnviarNotificacaoUseCase;
import br.com.postechfiap.notificacaoservice.application.usecases.dto.EnviarNotificacaoContext;
import br.com.postechfiap.notificacaoservice.infraestructure.listener.dto.EstoqueAlertaDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @KafkaHandler
    public void onPedidoCreated(@Payload String messageAsJson) {
    try {
        System.out.println("Mensagem JSON bruta recebida do Kafka: " + messageAsJson);
        String realJson = objectMapper.readValue(messageAsJson, String.class);
        JsonNode rootNode = objectMapper.readTree(realJson);
        System.out.println("JSON NODE   " + rootNode);

        JsonNode jsonNode = rootNode.get("notificacoes");
        EstoqueAlertaDTO estoqueAlertaDTO = objectMapper.treeToValue(jsonNode, EstoqueAlertaDTO.class);

        final var context = EnviarNotificacaoContext.builder()
                .estoqueAlertaDTO(estoqueAlertaDTO)
                .build();
        enviarNotificacaoUseCase.execute(context);
    }
    catch (Exception e) {
            log.info(" Erro ao desserializar: " + e.getMessage());
        }
    }
}

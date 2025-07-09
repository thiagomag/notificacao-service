package br.com.postechfiap.notificacaoservice.application.usecases;

import br.com.postechfiap.notificacaoservice.application.interfaces.EnviarNotificacaoUseCase;
import br.com.postechfiap.notificacaoservice.application.usecases.dto.EnviarNotificacaoContext;
import br.com.postechfiap.notificacaoservice.domain.enums.TipoNotificacaoEnum;
import br.com.postechfiap.notificacaoservice.infraestructure.client.CadastroUsuarioServiceClient;
import br.com.postechfiap.notificacaoservice.infraestructure.client.MedicamentosServiceClient;
import br.com.postechfiap.notificacaoservice.infraestructure.gateways.NotificacaoRepositoryGateway;
import br.com.postechfiap.notificacaoservice.infraestructure.gateways.NotificacaoUsuariosRepositoryGateway;
import br.com.postechfiap.notificacaoservice.infraestructure.listener.dto.EstoqueAlertaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnviarNotificacaoUseCaseImpl implements EnviarNotificacaoUseCase {

    private final NotificacaoRepositoryGateway notificacaoRepositoryGateway;
    private final NotificacaoUsuariosRepositoryGateway notificacaoUsuariosRepositoryGateway;
    private final CadastroUsuarioServiceClient cadastroUsuarioServiceClient;
    private final MedicamentosServiceClient medicamentosServiceClient;
    private final JavaMailSender mailSender;

    @Override
    public Void execute(EnviarNotificacaoContext enviarNotificacaoContext) {
        final var estoqueAlertaDTO = enviarNotificacaoContext.getEstoqueAlertaDTO();
        final var tipoNotificacao = estoqueAlertaDTO.getTipoNotificacao();
        log.info("Tipo de notificacao: {}", tipoNotificacao);
        final var notificacao = notificacaoRepositoryGateway.findByTipo(TipoNotificacaoEnum.valueOf(tipoNotificacao))
                .orElseThrow(() -> new IllegalArgumentException("Notificação não encontrada para o tipo: " + tipoNotificacao));
        log.info("Notificação encontrada: {}", notificacao);
        notificacaoUsuariosRepositoryGateway.findByNotificacaoId(notificacao.getId())
                .forEach(notificacaoUsuario -> {
                    log.info("Enviando notificação para o usuário: {}", notificacaoUsuario.getIdUsuario());
                    final var usuario = cadastroUsuarioServiceClient.buscarUsuarioPorId(notificacaoUsuario.getIdUsuario());
                    enviarNotificacao(usuario.getEmail(), estoqueAlertaDTO);
                    log.info("Notificação {} enviada para o usuário: " + usuario.getEmail(), tipoNotificacao);
                });
        return medicamentosServiceClient.atualizarReposicaoPendente(estoqueAlertaDTO.getSku());
    }

    public void enviarNotificacao(String email, EstoqueAlertaDTO estoqueAlertaDTO) {
        log.info("Enviando notificação para o email: " + email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Notificação Sistema de Medicamentos SUS");
        message.setText(buildMessagem(estoqueAlertaDTO));
        mailSender.send(message);
        log.info("Notificação enviada para " + email);
    }

    private String buildMessagem(EstoqueAlertaDTO estoqueAlertaDTO) {
        return """
               Email de notificação do Sistema de Medicamentos SUS
               
                Olá, este é um email automático do Sistema de Medicamentos SUS.
                Informamos que o medicamento com o SKU: %s está com estoque baixo.
                Por favor, verifique o estoque e tome as medidas necessárias.
                Detalhes do Alerta:
                - SKU: %s
                - Nome do Medicamento: %s
                - Laboratório: %s
                - Quantidade Atual: %d
               """
                .formatted(
                        estoqueAlertaDTO.getSku(),
                        estoqueAlertaDTO.getSku(),
                        estoqueAlertaDTO.getNomeProduto(),
                        estoqueAlertaDTO.getLaboratorio(),
                        estoqueAlertaDTO.getQuantidade()
                );

    }
}

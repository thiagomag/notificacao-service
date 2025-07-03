package br.com.postechfiap.notificacaoservice.application.usecases;

import br.com.postechfiap.notificacaoservice.application.interfaces.EnviarNotificacaoUseCase;
import br.com.postechfiap.notificacaoservice.application.usecases.dto.EnviarNotificacaoContext;
import br.com.postechfiap.notificacaoservice.domain.enums.TipoNotificacaoEnum;
import br.com.postechfiap.notificacaoservice.infraestructure.client.CadastroUsuarioServiceClient;
import br.com.postechfiap.notificacaoservice.infraestructure.gateways.NotificacaoRepositoryGateway;
import br.com.postechfiap.notificacaoservice.infraestructure.gateways.NotificacaoUsuariosRepositoryGateway;
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
    private final JavaMailSender mailSender;

    @Override
    public Void execute(EnviarNotificacaoContext enviarNotificacaoContext) {
        final var tipoNotificacao = enviarNotificacaoContext.getNotificacaoEvent().getTipoNotificacao();
        final var notificacao = notificacaoRepositoryGateway.findByTipo(TipoNotificacaoEnum.valueOf(tipoNotificacao))
                .orElseThrow(() -> new IllegalArgumentException("Notificação não encontrada para o tipo: " + tipoNotificacao));
        notificacaoUsuariosRepositoryGateway.findByNotificacaoId(notificacao.getId())
                .forEach(notificacaoUsuario -> {
                    final var usuario = cadastroUsuarioServiceClient.buscarUsuarioPorId(notificacaoUsuario.getIdUsuario());
                    enviarNotificacao(usuario.getEmail(), "Medicamento no final");
                    log.info("Notificação {} enviada para o usuário: " + usuario.getEmail(), tipoNotificacao);
                });
        return null;
    }

    public void enviarNotificacao(String email, String mensagem) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Notificação de Estacionamento");
        message.setText(mensagem);
        mailSender.send(message);
        log.info("Notificação enviada para " + email);
    }
}

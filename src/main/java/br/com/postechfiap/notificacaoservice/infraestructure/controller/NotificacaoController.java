package br.com.postechfiap.notificacaoservice.infraestructure.controller;

import br.com.postechfiap.notificacaoservice.application.interfaces.CriarNotificacaoUseCase;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.adapters.NotificacaoAdapter;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoDto;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private final CriarNotificacaoUseCase criarNotificacaoUseCase;
    private final NotificacaoAdapter notificacaoAdapter;

    @PostMapping
    public ResponseEntity<NotificacaoResponse> criarNotificacao(@RequestBody NotificacaoDto notificacaoDto) {
        final var notificacao = notificacaoAdapter.toNotificacao(notificacaoDto);
        return new ResponseEntity<>(criarNotificacaoUseCase.execute(notificacao), HttpStatus.CREATED);
    }
}

package br.com.postechfiap.notificacaoservice.infraestructure.controller;

import br.com.postechfiap.notificacaoservice.application.interfaces.*;
import br.com.postechfiap.notificacaoservice.application.usecases.dto.NotificacaoQueryParams;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.adapters.NotificacaoAdapter;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.adapters.NotificacaoUsuariosAdapeter;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoDto;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoResponse;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoUsuariosDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private final CriarNotificacaoUseCase criarNotificacaoUseCase;
    private final AdicionarUsuariosUseCase adicionarUsuariosUseCase;
    private final RemoverUsuariosUseCase removerUsuariosUseCase;
    private final BuscarTodasNotificacoesUseCase buscarTodasNotificacoesUseCase;
    private final BuscarNotificacaoPorIdUseCase buscarNotificacaoPorIdUseCase;
    private final NotificacaoUsuariosAdapeter notificacaoUsuariosAdapeter;
    private final NotificacaoAdapter notificacaoAdapter;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<NotificacaoResponse> criarNotificacao(@RequestBody NotificacaoDto notificacaoDto) {
        final var notificacao = notificacaoAdapter.toNotificacao(notificacaoDto);
        return new ResponseEntity<>(criarNotificacaoUseCase.execute(notificacao), HttpStatus.CREATED);
    }

    @PostMapping("/adicionar-usuarios/{notificacaoId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<String> addUsuario(@PathVariable Long notificacaoId, @RequestBody NotificacaoUsuariosDto notificacaoUsuariosDto) {
        notificacaoUsuariosDto.setIdNotificacao(notificacaoId);
        final var notificacaoUsuarios = notificacaoUsuariosAdapeter.adapt(notificacaoUsuariosDto);
        return new ResponseEntity<>(adicionarUsuariosUseCase.execute(notificacaoUsuarios), HttpStatus.OK);
    }

    @PostMapping("/remover-usuarios/{notificacaoId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<String> removerUsuario(@PathVariable Long notificacaoId, @RequestBody NotificacaoUsuariosDto notificacaoUsuariosDto) {
        notificacaoUsuariosDto.setIdNotificacao(notificacaoId);
        final var notificacaoUsuarios = notificacaoUsuariosAdapeter.adapt(notificacaoUsuariosDto);
        return new ResponseEntity<>(removerUsuariosUseCase.execute(notificacaoUsuarios), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<NotificacaoResponse> getNotificacao(@PathVariable Long id) {
        return new ResponseEntity<>(buscarNotificacaoPorIdUseCase.execute(id), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<List<NotificacaoResponse>> getTodasNotificacoes() {
        return new ResponseEntity<>(buscarTodasNotificacoesUseCase.execute(NotificacaoQueryParams.builder().build()), HttpStatus.OK);
    }
}

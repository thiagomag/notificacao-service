package br.com.postechfiap.notificacaoservice.domain.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificacaoUsuarios {

    private Long idUsuario;
    private Long idNotificacao;
}

package br.com.postechfiap.notificacaoservice.domain.entity;

import br.com.postechfiap.notificacaoservice.domain.enums.TipoNotificacaoEnum;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Notificacao {

    private String nome;
    private String descricao;
    private TipoNotificacaoEnum tipo;
    private List<Long> idUsuarios;
}

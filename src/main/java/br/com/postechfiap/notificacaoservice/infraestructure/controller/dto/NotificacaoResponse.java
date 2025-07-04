package br.com.postechfiap.notificacaoservice.infraestructure.controller.dto;

import br.com.postechfiap.notificacaoservice.domain.enums.TipoNotificacaoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NotificacaoResponse {

    private Long id;
    private String nome;
    private String descricao;
    private TipoNotificacaoEnum tipo;
    private List<NotificacaoUsuariosResponse> usuariosNotificacao;
}

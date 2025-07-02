package br.com.postechfiap.notificacaoservice.domain.entity;

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
    private List<Long> idUsuarios;
}

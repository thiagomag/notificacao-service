package br.com.postechfiap.notificacaoservice.infraestructure.controller.adapters;

import br.com.postechfiap.notificacaoservice.domain.entity.Notificacao;
import br.com.postechfiap.notificacaoservice.infraestructure.controller.dto.NotificacaoDto;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoAdapter {

    public Notificacao toNotificacao(NotificacaoDto notificacaoDto) {
       return Notificacao.builder()
               .nome(notificacaoDto.getNome())
               .descricao(notificacaoDto.getDescricao())
               .tipo(notificacaoDto.getTipo())
               .idUsuarios(notificacaoDto.getIdUsuarios())
               .build();
    }

}

package br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities;

import br.com.postechfiap.notificacaoservice.domain.enums.TipoNotificacaoEnum;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="notificacao")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NotificacaoEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoNotificacaoEnum tipo;
    private String descricao;
}

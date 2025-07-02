package br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="notificacao_usuario")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NotificacaoUsuariosEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    @OneToOne
    @JoinColumn(name = "id_notificacao", referencedColumnName = "id")
    private NotificacaoEntity notificacao;
}

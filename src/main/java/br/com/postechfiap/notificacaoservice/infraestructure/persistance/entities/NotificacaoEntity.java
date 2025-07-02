package br.com.postechfiap.notificacaoservice.infraestructure.persistance.entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String descricao;
}

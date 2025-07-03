package br.com.postechfiap.notificacaoservice.infraestructure.client.dto;


import br.com.postechfiap.notificacaoservice.infraestructure.client.dto.enums.RoleNameEnum;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

    private RoleNameEnum name;
}
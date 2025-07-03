package br.com.postechfiap.notificacaoservice.infraestructure.client.dto;


import br.com.postechfiap.notificacaoservice.infraestructure.client.dto.enums.EmployeeTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private EmployeeTypeEnum employeeTypeEnum;
    private String councilNumber;
    private String councilState;
    private String specialty;
    private Role role;
}

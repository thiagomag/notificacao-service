package br.com.postechfiap.notificacaoservice.infraestructure.listener.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EstoqueAlertaDTO {
    private String nomeProduto;
    private String sku;
    private String laboratorio;
    private Integer quantidade;
    private LocalDateTime dataAnalise;
    private String tipoNotificacao;


    @Override
    public String toString() {
        return "{" +
                "nomeProduto: '" + nomeProduto + '\'' +
                ", sku: '" + sku + '\'' +
                ", laboratorio: '" + laboratorio + '\'' +
                ", quantidade: " + quantidade +
                ", dataAnalise: " + dataAnalise +
                ", tipoNotificacao: '" + tipoNotificacao + '\'' +
                '}';
    }
}

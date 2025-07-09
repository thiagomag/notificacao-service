package br.com.postechfiap.notificacaoservice.infraestructure.client;

import br.com.postechfiap.notificacaoservice.application.configuration.MedicamentoFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(
        name = "${client.medicamentos-service.name}",
        url = "${client.medicamentos-service.url}",
        configuration = MedicamentoFeignClientConfig.class
)
public interface MedicamentosServiceClient {

    @PutMapping("/estoque/reposicao-pendente/{sku}")
    Void atualizarReposicaoPendente(@PathVariable("sku") String sku);
}

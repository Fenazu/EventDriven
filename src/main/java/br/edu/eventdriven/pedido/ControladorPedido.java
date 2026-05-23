package br.edu.eventdriven.pedido;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.eventdriven.configuracao.ConfiguracaoRabbitMQ;

@RestController
@RequestMapping({"/pedidos"})
public class ControladorPedido {

    private final RabbitTemplate templateRabbit;

    public ControladorPedido(RabbitTemplate templateRabbit) {
        this.templateRabbit = templateRabbit;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> criarPedido(@RequestBody SolicitacaoPedido solicitacao) {
        String idPedido = UUID.randomUUID().toString();
        EventoPedidoCriado evento = new EventoPedidoCriado(
                idPedido,
                solicitacao.idCliente(),
                solicitacao.idProduto(),
                solicitacao.quantidade(),
                Instant.now()
        );

        templateRabbit.convertAndSend(ConfiguracaoRabbitMQ.EXCHANGE_PEDIDOS, "", evento);

        return ResponseEntity.accepted().body(Map.of(
                "idPedido", idPedido,
                "orderId", idPedido,
                "status", "EVENTO_PUBLICADO",
                "message", "EventoPedidoCriado publicado com sucesso",
                "mensagem", "EventoPedidoCriado publicado com sucesso"
        ));
    }

    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Map<String, String>> cancelarPedido(
            @PathVariable String idPedido,
            @RequestParam(defaultValue = "Cancelado pelo cliente") String motivo) {

        EventoPedidoCancelado evento = new EventoPedidoCancelado(idPedido, motivo, Instant.now());

        templateRabbit.convertAndSend(ConfiguracaoRabbitMQ.EXCHANGE_PEDIDOS_CANCELADOS, "", evento);

        return ResponseEntity.accepted().body(Map.of(
                "idPedido", idPedido,
                "status", "CANCELAMENTO_PUBLICADO",
                "mensagem", "EventoPedidoCancelado publicado com sucesso"
        ));
    }
}

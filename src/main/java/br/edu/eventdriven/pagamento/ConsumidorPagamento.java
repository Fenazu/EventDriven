package br.edu.eventdriven.pagamento;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import br.edu.eventdriven.configuracao.ConfiguracaoRabbitMQ;
import br.edu.eventdriven.pedido.EventoPedidoCriado;

@Component
public class ConsumidorPagamento {

    private static final Logger log = LoggerFactory.getLogger(ConsumidorPagamento.class);

    private final RabbitTemplate templateRabbit;

    public ConsumidorPagamento(RabbitTemplate templateRabbit) {
        this.templateRabbit = templateRabbit;
    }

    @RabbitListener(queues = ConfiguracaoRabbitMQ.FILA_PAGAMENTO)
    public void processar(EventoPedidoCriado evento) {
        log.info("Pagamento: processando cobrança do pedido {} para o cliente {}",
                evento.idPedido(), evento.idCliente());

        EventoPagamentoAprovado aprovado = new EventoPagamentoAprovado(
                evento.idPedido(),
                evento.idCliente(),
                Instant.now()
        );

        templateRabbit.convertAndSend(ConfiguracaoRabbitMQ.EXCHANGE_PAGAMENTOS, "", aprovado);
        log.info("Pagamento: EventoPagamentoAprovado publicado para o pedido {}", evento.idPedido());
    }
}

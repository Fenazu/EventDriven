package br.edu.eventdriven.estoque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.edu.eventdriven.configuracao.ConfiguracaoRabbitMQ;
import br.edu.eventdriven.pedido.EventoPedidoCriado;

@Component
public class ConsumidorEstoque {

    private static final Logger log = LoggerFactory.getLogger(ConsumidorEstoque.class);

    @RabbitListener(queues = ConfiguracaoRabbitMQ.FILA_ESTOQUE)
    public void processar(EventoPedidoCriado evento) {
        if (evento.quantidade() > 5) {
            log.warn("Estoque: reserva RECUSADA — quantidade {} excede o limite de 5 para o pedido {}",
                    evento.quantidade(), evento.idProduto(), evento.idPedido());
        } else {
            log.info("Estoque: reservando {} unidade(s) do produto {} para o pedido {}",
                    evento.quantidade(), evento.idProduto(), evento.idPedido());
        }
    }
}

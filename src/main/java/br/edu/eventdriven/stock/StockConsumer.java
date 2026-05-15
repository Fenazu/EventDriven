package br.edu.eventdriven.stock;

import br.edu.eventdriven.config.RabbitMQConfig;
import br.edu.eventdriven.order.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class StockConsumer {

    private static final Logger log = LoggerFactory.getLogger(StockConsumer.class);

    @RabbitListener(queues = RabbitMQConfig.STOCK_QUEUE)
    public void handle(OrderCreatedEvent event) {
        log.info("Estoque: reservando {} unidade(s) do produto {} para o pedido {}",
                event.quantity(), event.productId(), event.orderId());

        // TODO didático: rejeitar reservas quando quantity > 5.
    }
}

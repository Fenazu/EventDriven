package br.edu.eventdriven.notification;

import br.edu.eventdriven.config.RabbitMQConfig;
import br.edu.eventdriven.order.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
    public void handle(OrderCreatedEvent event) {
        log.info("Notificação: enviando confirmação do pedido {} para o cliente {}",
                event.orderId(), event.customerId());

        // TODO didático: criar um novo consumidor para outro canal de notificação.
    }
}

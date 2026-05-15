package br.edu.eventdriven.payment;

import br.edu.eventdriven.config.RabbitMQConfig;
import br.edu.eventdriven.order.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    private static final Logger log = LoggerFactory.getLogger(PaymentConsumer.class);

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_QUEUE)
    public void handle(OrderCreatedEvent event) {
        log.info("Pagamento: processando cobrança do pedido {} para o cliente {}",
                event.orderId(), event.customerId());

        // TODO didático: criar e publicar um PaymentApprovedEvent após aprovar o pagamento.
    }
}

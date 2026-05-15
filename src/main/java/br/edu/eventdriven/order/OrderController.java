package br.edu.eventdriven.order;

import br.edu.eventdriven.config.RabbitMQConfig;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final RabbitTemplate rabbitTemplate;

    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody CreateOrderRequest request) {
        String orderId = UUID.randomUUID().toString();
        OrderCreatedEvent event = new OrderCreatedEvent(
                orderId,
                request.customerId(),
                request.productId(),
                request.quantity(),
                Instant.now()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDERS_EXCHANGE, "", event);

        // TODO didático: publicar outros tipos de evento quando o fluxo crescer.
        return ResponseEntity.accepted().body(Map.of(
                "orderId", orderId,
                "status", "EVENT_PUBLISHED",
                "message", "OrderCreatedEvent publicado com sucesso"
        ));
    }
}

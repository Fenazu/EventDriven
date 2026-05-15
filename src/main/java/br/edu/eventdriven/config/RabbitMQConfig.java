package br.edu.eventdriven.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ORDERS_EXCHANGE = "orders.exchange";
    public static final String STOCK_QUEUE = "stock.queue";
    public static final String PAYMENT_QUEUE = "payment.queue";
    public static final String NOTIFICATION_QUEUE = "notification.queue";

    @Bean
    FanoutExchange ordersExchange() {
        return new FanoutExchange(ORDERS_EXCHANGE);
    }

    @Bean
    Queue stockQueue() {
        return new Queue(STOCK_QUEUE, true);
    }

    @Bean
    Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE, true);
    }

    @Bean
    Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE, true);
    }

    @Bean
    Binding stockBinding(Queue stockQueue, FanoutExchange ordersExchange) {
        return BindingBuilder.bind(stockQueue).to(ordersExchange);
    }

    @Bean
    Binding paymentBinding(Queue paymentQueue, FanoutExchange ordersExchange) {
        return BindingBuilder.bind(paymentQueue).to(ordersExchange);
    }

    @Bean
    Binding notificationBinding(Queue notificationQueue, FanoutExchange ordersExchange) {
        return BindingBuilder.bind(notificationQueue).to(ordersExchange);
    }

    @Bean
    MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }
}

package br.edu.eventdriven.notificacao;

import br.edu.eventdriven.configuracao.ConfiguracaoRabbitMQ;
import br.edu.eventdriven.pedido.EventoPedidoCriado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorNotificacaoEmail {

    private static final Logger log = LoggerFactory.getLogger(ConsumidorNotificacaoEmail.class);

    @RabbitListener(queues = ConfiguracaoRabbitMQ.FILA_NOTIFICACAO_EMAIL)
    public void processar(EventoPedidoCriado evento) {
        log.info("Notificação e-mail: enviando e-mail de confirmação do pedido {} para o cliente {}",
                evento.idPedido(), evento.idCliente());
    }
}

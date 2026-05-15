# 01 — Conceitos

## Evento

Um evento representa algo que **já aconteceu** no sistema. No domínio desta atividade, `OrderCreatedEvent` significa que um pedido foi criado.

## Produtor

O produtor é quem publica o evento. Aqui, o `OrderController` recebe a requisição HTTP e publica o evento no RabbitMQ.

## Consumidor

O consumidor reage a um evento recebido. Nesta atividade:

- `StockConsumer` simula reserva de estoque;
- `PaymentConsumer` simula processamento de pagamento;
- `NotificationConsumer` simula envio de notificação.

## Broker

O broker é o intermediário responsável por receber, rotear e entregar mensagens. O broker usado aqui é o **RabbitMQ**.

## Exchange

A exchange recebe mensagens publicadas pelos produtores e decide para onde enviá-las. A aplicação usa a exchange `orders.exchange`.

## Fila

A fila armazena mensagens até que um consumidor as processe. Nesta atividade existem:

- `stock.queue`;
- `payment.queue`;
- `notification.queue`.

## Binding

Um binding conecta uma exchange a uma fila. É ele que define quais filas recebem as mensagens enviadas para uma exchange.

## Fanout exchange

Uma exchange do tipo `fanout` envia cada mensagem para **todas** as filas ligadas a ela. Assim, um único pedido criado chega ao estoque, ao pagamento e à notificação.

## Desacoplamento

O `OrderController` não precisa conhecer os consumidores. Ele apenas publica um evento. Isso permite:

- adicionar novos consumidores sem alterar o produtor;
- evoluir cada parte do sistema separadamente;
- reduzir dependências diretas entre componentes.

Em sistemas maiores, esse desacoplamento melhora a flexibilidade, mas também traz novos desafios de observabilidade, consistência e tratamento de falhas.

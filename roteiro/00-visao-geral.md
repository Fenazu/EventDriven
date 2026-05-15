# 00 — Visão geral

## Proposta

Nesta atividade, você irá explorar os fundamentos de uma arquitetura orientada a eventos por meio de uma aplicação simples de criação de pedidos. Em vez de um componente chamar diretamente todos os outros, o sistema publica um evento e deixa que diferentes consumidores reajam a ele de forma independente.

## Duração estimada

De **90 a 120 minutos**.

## Pré-requisitos

- noções básicas de Java;
- noções básicas de HTTP;
- noções básicas de Spring Boot são desejáveis, mas não obrigatórias.

## Domínio usado

O domínio é o de **criação de pedidos**. Quando um pedido é recebido:

1. a aplicação cria um evento `OrderCreatedEvent`;
2. publica esse evento no RabbitMQ;
3. três consumidores reagem de forma independente:
   - estoque;
   - pagamento;
   - notificação.

Esse domínio é pequeno o bastante para caber em uma primeira prática, mas próximo o bastante de sistemas reais para tornar os conceitos concretos.

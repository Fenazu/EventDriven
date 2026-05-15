# Atividade prática: arquitetura event-driven com Java, RabbitMQ e GitHub Codespaces

Este repositório contém uma atividade prática para alunos de graduação explorarem os fundamentos de arquitetura orientada a eventos em um cenário simples de pedidos. A aplicação usa Java 21, Spring Boot, Spring AMQP, RabbitMQ e GitHub Codespaces para que todo o ambiente já esteja pronto ao abrir o projeto.

## Objetivos de aprendizagem

Ao final da atividade, espera-se que o aluno consiga:

- entender o papel de produtores de eventos;
- entender o papel de consumidores de eventos;
- identificar exchanges e filas no RabbitMQ;
- explicar o desacoplamento entre componentes;
- observar múltiplos consumidores reagindo ao mesmo evento.

## Como abrir no Codespaces

1. Acesse o repositório no GitHub.
2. Clique em **Code**.
3. Abra a aba **Codespaces**.
4. Clique em **Create codespace on main**.
5. Aguarde o ambiente subir e o comando de pós-criação validar o projeto.

O Codespace inicia automaticamente:

- um container Java para desenvolvimento;
- um container RabbitMQ com interface de administração.

## Como executar

No terminal do Codespace:

```bash
mvn spring-boot:run
```

## Como testar

Use o arquivo `requests.http` ou execute:

```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": "C001",
    "productId": "P001",
    "quantity": 2
  }'
```

Resposta esperada:

```json
{
  "orderId": "uuid-gerado",
  "status": "EVENT_PUBLISHED",
  "message": "OrderCreatedEvent publicado com sucesso"
}
```

## Como acessar o RabbitMQ Management

Abra a porta encaminhada **15672** no Codespaces.

- usuário: `guest`
- senha: `guest`

Na interface, observe:

- a exchange `orders.exchange`;
- as filas `stock.queue`, `payment.queue` e `notification.queue`;
- os bindings entre a exchange e as filas;
- o número de mensagens publicadas e consumidas.

## Fluxo da aplicação

```text
POST /orders
   |
   v
OrderController
   |
   v
orders.exchange
   |
   +--> stock.queue        -> StockConsumer
   +--> payment.queue      -> PaymentConsumer
   +--> notification.queue -> NotificationConsumer
```

O produtor publica um único `OrderCreatedEvent`. A exchange do tipo `fanout` replica esse evento para três filas independentes.

## Roteiro resumido da atividade

1. Entender o domínio de pedidos.
2. Executar a aplicação.
3. Enviar um pedido.
4. Observar os consumidores nos logs.
5. Observar exchange, filas e bindings no RabbitMQ.
6. Modificar ou criar um novo evento.

## Critérios de avaliação

- A aplicação executa no Codespaces.
- O aluno consegue publicar um evento.
- O aluno identifica exchange, filas e consumidores.
- O aluno implementa ou altera um consumidor.
- O aluno explica o desacoplamento obtido.

## Material da atividade

O roteiro completo está na pasta [`roteiro/`](roteiro/):

- `00-visao-geral.md`
- `01-conceitos.md`
- `02-ambiente.md`
- `03-execucao.md`
- `04-tarefas.md`
- `05-desafios.md`
- `06-discussao.md`

## Observações didáticas

O projeto inicial funciona sem modificações. Alguns `TODOs` foram deixados no código para ampliar o fluxo durante a prática, como publicar novos eventos, criar novos consumidores e substituir a exchange `fanout` por uma `topic` em desafios posteriores.

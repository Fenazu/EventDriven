# 03 — Execução

## Executar a aplicação

No terminal:

```bash
mvn spring-boot:run
```

## Testar com `requests.http`

Abra o arquivo `requests.http` e execute a requisição `POST /orders` usando a extensão REST Client do VS Code.

## Testar com curl

```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": "C001",
    "productId": "P001",
    "quantity": 2
  }'
```

## Acessar RabbitMQ Management

Abra a porta encaminhada `15672` no Codespaces e faça login com:

- usuário: `guest`
- senha: `guest`

## O que observar

Na interface:

- a exchange `orders.exchange`;
- as filas `stock.queue`, `payment.queue` e `notification.queue`;
- os bindings entre a exchange e as filas;
- o número de mensagens publicadas e consumidas.

Nos logs da aplicação, observe como os três consumidores reagem ao mesmo evento.

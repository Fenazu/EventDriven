# 05 — Desafios

## Desafio 1 — Criar uma nova fila de auditoria

- Criar `audit.queue`;
- criar `AuditConsumer`;
- ligar a fila à exchange;
- registrar todo `OrderCreatedEvent`.

## Desafio 2 — Criar evento de pagamento aprovado

- alterar `PaymentConsumer`;
- publicar `PaymentApprovedEvent`;
- criar nova exchange ou reutilizar uma exchange;
- criar consumidor para notificação de pagamento aprovado.

## Desafio 3 — Trocar fanout exchange por topic exchange

- criar routing keys:
  - `order.created`;
  - `payment.approved`;
  - `payment.rejected`;
- explicar a diferença entre `fanout` e `topic`.

## Desafio 4 — Simular falhas

- lançar exceção em um consumidor;
- observar o comportamento;
- discutir conceitualmente:
  - retry;
  - dead-letter queue;
  - idempotência.

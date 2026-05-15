# 04 — Tarefas

## Tarefa 1 — Executar o projeto

**Duração:** 10 minutos  
Abra o Codespace e execute a aplicação.

## Tarefa 2 — Enviar primeiro pedido

**Duração:** 10 minutos  
Envie `POST /orders` e observe os logs da aplicação.

## Tarefa 3 — Localizar produtor de eventos

**Duração:** 10 minutos  
Identifique onde o `OrderCreatedEvent` é criado e publicado.

## Tarefa 4 — Localizar consumidores

**Duração:** 15 minutos  
Encontre `StockConsumer`, `PaymentConsumer` e `NotificationConsumer`.

## Tarefa 5 — Modificar um consumidor

**Duração:** 15 a 20 minutos  
Altere o `StockConsumer` para rejeitar a reserva quando `quantity > 5`, apenas imprimindo um log. Não é necessário persistir estado.

## Tarefa 6 — Criar ou completar um novo evento

**Duração:** 20 a 25 minutos  
Use `PaymentApprovedEvent` como base. Modifique o `PaymentConsumer` para simular a aprovação do pagamento.

Se a implementação completa exceder o tempo da turma, trate esta tarefa como desafio guiado: discuta onde o novo evento seria criado, em qual exchange seria publicado e quem poderia consumi-lo.

## Tarefa 7 — Discussão

**Duração:** 10 a 15 minutos

- O `OrderController` conhece os consumidores?
- O que acontece se adicionarmos um novo consumidor?
- O que acontece se um consumidor falhar?
- Quais vantagens e riscos existem nesse modelo?

# 02 — Ambiente

## Abrindo o Codespace

1. Abra o repositório no GitHub.
2. Clique em **Code**.
3. Abra a aba **Codespaces**.
4. Crie um novo Codespace.
5. Aguarde o ambiente terminar de iniciar.

## Containers iniciados

O ambiente usa dois containers:

### `app`

Container de desenvolvimento com Java 21 e Maven. É nele que você edita o código e executa a aplicação Spring Boot.

### `rabbitmq`

Container com RabbitMQ e a interface web de administração.

## Portas usadas

- `8080`: aplicação Spring Boot;
- `5672`: comunicação AMQP entre a aplicação e o RabbitMQ;
- `15672`: interface web RabbitMQ Management.

O Codespaces encaminha as portas necessárias para que a aplicação e a interface de administração possam ser acessadas no navegador.

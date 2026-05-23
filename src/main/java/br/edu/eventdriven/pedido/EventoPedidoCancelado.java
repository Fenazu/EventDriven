package br.edu.eventdriven.pedido;

import java.time.Instant;

public record EventoPedidoCancelado(
        String idPedido,
        String motivoCancelamento,
        Instant canceladoEm
) {
}

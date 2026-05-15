package br.edu.eventdriven.order;

public record CreateOrderRequest(
        String customerId,
        String productId,
        int quantity
) {
}

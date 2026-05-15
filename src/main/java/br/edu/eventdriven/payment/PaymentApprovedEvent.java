package br.edu.eventdriven.payment;

import java.time.Instant;

public record PaymentApprovedEvent(
        String orderId,
        String customerId,
        Instant approvedAt
) {
}

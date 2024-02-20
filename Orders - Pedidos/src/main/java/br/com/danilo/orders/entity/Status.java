package br.com.danilo.orders.entity;

public enum Status {
    ACCOMPLISHED,               // Realizado
    CANCELED,                   // Cancelado
    PAID_OUT,                   // Pago
    NOT_AUTHORIZED,             // Não Autorizado
    CONFIRMED,                  // Confirmado
    READY,                      // Pronto
    OUT_FOR_DELIVERY,           // Saiu para a Entrega
    DELIVERED;                  // Entregue
}

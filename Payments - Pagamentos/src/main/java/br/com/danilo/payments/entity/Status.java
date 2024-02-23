package br.com.danilo.payments.entity;

public enum Status {

    CREATED,                        // Criado
    CONFIRMED,                      // Confirmado
    CONFIRMED_WITHOUT_INTEGRATION,  // Confirmado sem integração
    CANCELED,                       // Cancelado
}

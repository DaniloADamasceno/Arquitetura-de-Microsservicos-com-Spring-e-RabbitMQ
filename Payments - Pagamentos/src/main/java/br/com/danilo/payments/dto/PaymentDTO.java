package br.com.danilo.payments.dto;

import br.com.danilo.payments.entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {

    private Long id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expiration;
    private String cvv;
    private Status status;
    private Long orderID;
    private Long formPayment;
}

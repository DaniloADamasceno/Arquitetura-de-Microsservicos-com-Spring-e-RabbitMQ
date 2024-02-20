package br.com.danilo.payments.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payments {

    @Id
    @GeneratedValue(strategy = IDENTITY)        // Gerar um valor de ID automaticamente
    private Long id;

    @NotNull                                    // Não pode ser nulo
    @Positive                                   // Deve ser um valor positivo
    private BigDecimal value;

    @NotBlank                                   // Não pode ser vazio
    @Size(min = 3, max = 100)                   // Deve ter entre 3 e 100 caracteres
    private String name;

    @NotBlank                                   // Não pode ser vazio
    @Size(min = 16, max = 19)                   // Deve ter entre 16 e 19 caracteres
    private String number;

    @NotBlank                                   // Não pode ser vazio
    @Size(max = 7)                             // Deve ter 7 caracteres
    private String expiration;

    @NotBlank                                   // Não pode ser vazio
    @Size(min = 3, max = 3)                     // Deve ser de 3 caracteres
    private String cvv;

    @NotNull                                    // Não pode ser nulo
    @Enumerated(EnumType.STRING)                // Deve ser um valor do Enum
    private Status status;

    @NotNull                                    // Não pode ser nulo
    private Long orderID;
    @NotNull                                    // Não pode ser nulo
    private Long formPayment;
}

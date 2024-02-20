package br.com.danilo.payments.controller;

import br.com.danilo.payments.dto.PaymentDTO;
import br.com.danilo.payments.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    //! ----------------------------------------  Dependency Injection  ------------------------------------------------

    @Autowired
    private PaymentService paymentServiceController;

    //! -----------------------------------------------  Methods  ------------------------------------------------------

    //%% Buscar todos os Pagamentos
    @GetMapping
    public Page<PaymentDTO> listAllPaymentsController(@PageableDefault(size = 10) Pageable pageable) {
        return paymentServiceController.findAllPayments(pageable);
    }

    //%% Buscar Pagamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> findPaymentByIdController(@PathVariable @NotNull Long id) {
        PaymentDTO paymentDtoController = paymentServiceController.findPaymentById(id);

        return ResponseEntity.ok(paymentDtoController);
    }

    //%% Cadastrar Pagamento
    @PostMapping
    public ResponseEntity<PaymentDTO>registerPaymentController(@RequestBody @Valid PaymentDTO paymentDTO, UriComponentsBuilder uriBuilder) {
        PaymentDTO paymentDtoController = paymentServiceController.createPayment(paymentDTO);
        URI uriAddress = uriBuilder.path("/payments/{id}").buildAndExpand(paymentDtoController.getId()).toUri();

        return ResponseEntity.created(uriAddress).body(paymentDtoController);
    }

    //%% Atualizar Pagamento
    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePaymentController(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDTO paymentDTO) {
        PaymentDTO paymentDtoController = paymentServiceController.updatePayment(id, paymentDTO);

        return ResponseEntity.ok(paymentDtoController);
    }

    //%% Deletar Pagamento
    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentDTO> deletePaymentController(@PathVariable @NotNull Long id) {
        paymentServiceController.deletePayment(id);

        return ResponseEntity.noContent().build();
    }


}

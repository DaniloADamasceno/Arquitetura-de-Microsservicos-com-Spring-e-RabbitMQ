package br.com.danilo.payments.service;

import br.com.danilo.payments.dto.PaymentDTO;
import br.com.danilo.payments.entity.Payments;
import br.com.danilo.payments.entity.Status;
import br.com.danilo.payments.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    //! ----------------------------------------  Dependency Injection  ------------------------------------------------

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    //! -----------------------------------------------  Methods  ------------------------------------------------------

    //%% Buscar todos os Pagamentos
    public Page<PaymentDTO> findAllPayments(Pageable pageable) {
        return paymentRepository
                .findAll(pageable)
                .map(mapPayments -> modelMapper.map(mapPayments, PaymentDTO.class));
    }

    //%% Buscar Pagamento por ID
    public PaymentDTO findPaymentById(Long id) {
        Payments payments = paymentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado ID:| Payment not found ID: " + id));

        return modelMapper.map(payments, PaymentDTO.class);
    }

    //%% Criar Pagamento
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payments payments = modelMapper.map(paymentDTO, Payments.class);
        payments.setStatus(Status.CREATED);

        return modelMapper.map(payments, PaymentDTO.class);
    }

    //%% Atualizar Pagamento
    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Payments payments = paymentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado ID:| Payment not found ID: " + id));

        payments.setValue(paymentDTO.getValue());
        payments.setName(paymentDTO.getName());
        payments.setNumber(paymentDTO.getNumber());
        payments.setExpiration(paymentDTO.getExpiration());
        payments.setCvv(paymentDTO.getCvv());
        payments.setStatus(paymentDTO.getStatus());
        payments.setOrderID(paymentDTO.getOrderID());
        payments.setFormPayment(paymentDTO.getFormPayment());

        return modelMapper.map(payments, PaymentDTO.class);
    }

    //%% Deletar Pagamento
    public void deletePayment(Long id) {
        paymentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado ID:| Payment not found ID: " + id));

        paymentRepository.deleteById(id);
    }
}

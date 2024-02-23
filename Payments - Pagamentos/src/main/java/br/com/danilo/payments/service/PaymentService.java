package br.com.danilo.payments.service;

import br.com.danilo.payments.dto.PaymentDTO;
import br.com.danilo.payments.entity.Payments;
import br.com.danilo.payments.entity.Status;
import br.com.danilo.payments.http.OrderClient;
import br.com.danilo.payments.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PaymentService {

    //! ----------------------------------------  Dependency Injection  ------------------------------------------------

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderClient orderClient;

    //! -----------------------------------------------  Methods  ------------------------------------------------------

    //%% Buscar todos os Pagamentos
    public Page<PaymentDTO> findAllPayments(Pageable pageable) {
        return paymentRepository
                .findAll(pageable)
                .map(mapPayments -> modelMapper.map(mapPayments, PaymentDTO.class));
    }

    //%% Buscar Pagamento por ID
    public PaymentDTO findPaymentById(Long id) {
        Payments paymentFindId = paymentRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado ID:| Payment not found ID: " + id));

        return modelMapper.map(paymentFindId, PaymentDTO.class);
    }

    //%% Criar Pagamento
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payments paymentCreated = modelMapper.map(paymentDTO, Payments.class);
        paymentCreated.setStatus(Status.CREATED);
        paymentCreated = paymentRepository.save(paymentCreated);                       // Salvar no banco de dados

        return modelMapper.map(paymentCreated, PaymentDTO.class);
    }

    //%% Atualizar Pagamento
    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Payments paymentsUpdate = modelMapper.map(paymentDTO, Payments.class);
        paymentsUpdate.setId(id);                                                       // Setar o ID
        paymentsUpdate = paymentRepository.save(paymentsUpdate);                  // Salvar no banco de dados

        return modelMapper.map(paymentsUpdate, PaymentDTO.class);
    }

    //%% Deletar Pagamento
    public void deletePayment(Long id) {
        paymentRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado ID:| Payment not found ID: " + id));

        paymentRepository.deleteById(id);
    }

    //%% Confirmar Pagamento
    public void approvePayment(Long id) {
        Optional<Payments> paymentOptional = paymentRepository.findById(id);

        if (paymentOptional.isEmpty()) {
            throw new EntityNotFoundException("Pagamento não encontrado ID:| Payment not found ID: " + id);
        }

        paymentOptional.get().setStatus(Status.CONFIRMED);
        paymentRepository.save(paymentOptional.get());
        orderClient.updateOrderStatusToPaidOut(paymentOptional.get().getOrderID());
    }

    //%% FALLBACK --> Confirmar Pagamento (Circuit Breaker)
    public void changeStatus(Long id) {
        Optional<Payments> paymentOptional = paymentRepository.findById(id);

        if (paymentOptional.isEmpty()) {
            throw new EntityNotFoundException("Pagamento não encontrado ID:| Payment not found ID: " + id);
        }

        paymentOptional.get().setStatus(Status.CONFIRMED_WITHOUT_INTEGRATION);
    }
}

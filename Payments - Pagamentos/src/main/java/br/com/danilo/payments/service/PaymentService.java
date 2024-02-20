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
        Payments paymentFindId = paymentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado ID:| Payment not found ID: " + id));

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
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado ID:| Payment not found ID: " + id));

        paymentRepository.deleteById(id);
    }
}

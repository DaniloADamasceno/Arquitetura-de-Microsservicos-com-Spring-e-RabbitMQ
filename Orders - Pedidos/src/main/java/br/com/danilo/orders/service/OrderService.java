package br.com.danilo.orders.service;

import br.com.danilo.orders.dto.OrderDto;
import br.com.danilo.orders.dto.StatusDto;
import br.com.danilo.orders.entity.Order;
import br.com.danilo.orders.entity.Status;
import br.com.danilo.orders.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    //! ----------------------------------------  Dependency Injection  ------------------------------------------------
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private final ModelMapper modelMapper;

    //! -----------------------------------------------  Methods  ------------------------------------------------------
    //%% Listar todos os Pedidos
    public List<OrderDto> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(p -> modelMapper.map(p, OrderDto.class))
                .collect(Collectors.toList());
    }

    //%% Buscar Pedido por ID
    public OrderDto findOrderById(Long id) {
        Order orderID = orderRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(orderID, OrderDto.class);
    }

    //%% Criar Pedido
    public OrderDto createOrder(OrderDto dto) {
        Order orderCreated = modelMapper.map(dto, Order.class);

        orderCreated.setDateTime(LocalDateTime.now());
        orderCreated.setStatus(Status.ACCOMPLISHED);
        orderCreated.getItems().forEach(item -> item.setOrder(orderCreated));
        Order orderSaved = orderRepository.save(orderCreated);

        return modelMapper.map(orderCreated, OrderDto.class);
    }

    //%% Atualizar Status do Pedido
    public OrderDto updateStatusOrder(Long id, StatusDto dto) {

        Order orderStatus = orderRepository.idWithItems(id);

        if (orderStatus == null) {
            throw new EntityNotFoundException();
        }

        orderStatus.setStatus(dto.getStatusDto());
        orderRepository.updateStatusOrder(dto.getStatusDto(), orderStatus);
        return modelMapper.map(orderStatus, OrderDto.class);
    }

    //%% Aprovar Pagamento do Pedido
    public void approvePaymentOrder(Long id) {

        Order approveOrder = orderRepository.idWithItems(id);

        if (approveOrder == null) {
            throw new EntityNotFoundException();
        }

        approveOrder.setStatus(Status.PAID_OUT);
        orderRepository.updateStatusOrder(Status.PAID_OUT, approveOrder);
    }
}

package br.com.danilo.orders.controller;

import br.com.danilo.orders.dto.OrderDto;
import br.com.danilo.orders.dto.StatusDto;
import br.com.danilo.orders.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    //! ----------------------------------------  Dependency Injection  ------------------------------------------------
    @Autowired
    private OrderService orderService;

    //! -----------------------------------------------  Methods  ------------------------------------------------------
    //%% Buscar todos os Pedidos
    @GetMapping()
    public List<OrderDto> listAllOrdersController() {
        return orderService.findAllOrders();
    }

    //%% Buscar Pedidos por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findOrdersByIdController(@PathVariable @NotNull Long id) {
        OrderDto orderDtoController = orderService.findOrderById(id);

        return ResponseEntity.ok(orderDtoController);
    }

    //%% Realizar Pedido
    @PostMapping()
    public ResponseEntity<OrderDto> registerOrderController(@RequestBody @Valid OrderDto orderDto, UriComponentsBuilder uriBuilder) {
        OrderDto orderDtoController = orderService.createOrder(orderDto);
        URI uriAddressOrder = uriBuilder.path("/orders/{id}").buildAndExpand(orderDtoController.getIdDto()).toUri();

        return ResponseEntity.created(uriAddressOrder).body(orderDtoController);

    }

    //%% Atualizar Status do Pedido
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDto> updateStatusOrderController(@PathVariable Long id, @RequestBody StatusDto status) {
        OrderDto orderDtoUpdateStatus = orderService.updateStatusOrder(id, status);

        return ResponseEntity.ok(orderDtoUpdateStatus);
    }

    //%% Aprovar Pagamento
    @PutMapping("/{id}/pago")
    public ResponseEntity<Void> approvePayment(@PathVariable @NotNull Long id) {
        orderService.approvePaymentOrder(id);

        return ResponseEntity.ok().build();
    }
}

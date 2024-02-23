package br.com.danilo.orders.controller;

import br.com.danilo.orders.dto.OrderDto;
import br.com.danilo.orders.dto.StatusDto;
import br.com.danilo.orders.service.OrderService;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
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
    @Operation(summary = "Buscar todos os pedidos")
    public List<OrderDto> listAllOrdersController() {
        return orderService.findAllOrders();
    }

    //%% Buscar Pedidos por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID")
    public ResponseEntity<OrderDto> findOrdersByIdController(@PathVariable @NotNull Long id) {
        OrderDto orderDtoController = orderService.findOrderById(id);

        return ResponseEntity.ok(orderDtoController);
    }

    //%% Realizar Pedido
    @PostMapping()
    @Operation(summary = "Realizar pedido")
    public ResponseEntity<OrderDto> registerOrderController(@RequestBody @Valid OrderDto orderDto, UriComponentsBuilder uriBuilder) {
        OrderDto orderDtoController = orderService.createOrder(orderDto);
        URI uriAddressOrder = uriBuilder.path("/orders/{id}").buildAndExpand(orderDtoController.getIdDto()).toUri();

        return ResponseEntity.created(uriAddressOrder).body(orderDtoController);

    }

    //%% Atualizar Status do Pedido
    @PutMapping("/{id}/status")
    @Operation(summary = "Atualizar status do pedido")
    public ResponseEntity<OrderDto> updateStatusOrderController(@PathVariable Long id, @RequestBody StatusDto status) {
        OrderDto orderDtoUpdateStatus = orderService.updateStatusOrder(id, status);

        return ResponseEntity.ok(orderDtoUpdateStatus);
    }

    //%% Aprovar Pagamento
    @PutMapping("/{id}/paidOut")
    @Operation(summary = "Aprovar pagamento")
    public ResponseEntity<Void> approvePayment(@PathVariable @NotNull Long id) {
        orderService.approvePaymentOrder(id);

        return ResponseEntity.ok().build();
    }

    //%% Retornar a porta do serviço
    @GetMapping("/port")
    @Operation(summary = "Retornar porta")
    public String ReturnPort(@Value("${local.server.port}") String port) {
        return String.format("Request Responded to the Door:" +
                             "| Requisição Respondida na Porta: %s", port);
    }
}

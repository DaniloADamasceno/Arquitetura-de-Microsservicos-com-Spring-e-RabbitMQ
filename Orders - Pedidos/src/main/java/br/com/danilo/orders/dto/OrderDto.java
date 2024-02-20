package br.com.danilo.orders.dto;

import br.com.danilo.orders.entity.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long idDto;
    private LocalDateTime dateTimeDto;
    private Status statusDto;
    private List<OrderItemDto> itemsDto = new ArrayList<>();



}

package br.com.danilo.payments.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("payment-pagamento")
public interface OrderClient {
    @RequestMapping(method = RequestMethod.PUT, value = "/orders/{id}/paidOut")
    void updateOrderStatusToPaidOut(@PathVariable Long id);
}

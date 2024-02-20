package br.com.danilo.orders.repository;

import br.com.danilo.orders.entity.Order;
import br.com.danilo.orders.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Order p set p.status = :status where p = :order")
    void updateStatusOrder(Status status, Order order);

    @Query(value = "SELECT p from Order p LEFT JOIN FETCH p.items where p.id = :id")
    Order idWithItems(Long id);


}

package br.com.microservices.core.inventory.adapter.out.repository;

import br.com.microservices.core.inventory.domain.model.OrderInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderInventoryRepository extends JpaRepository<OrderInventory, Integer> {

    List<OrderInventory> findByOrderIdAndTransactionId(String orderId, String transactionId);

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);
}

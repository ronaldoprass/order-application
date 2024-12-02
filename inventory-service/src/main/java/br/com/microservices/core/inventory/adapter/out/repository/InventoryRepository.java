package br.com.microservices.core.inventory.adapter.out.repository;

import br.com.microservices.core.inventory.domain.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    Optional<Inventory> findByProductCode(String productCode);
}

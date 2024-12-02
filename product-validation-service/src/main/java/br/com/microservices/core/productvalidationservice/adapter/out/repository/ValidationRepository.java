package br.com.microservices.core.productvalidationservice.adapter.out.repository;

import br.com.microservices.core.productvalidationservice.domain.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValidationRepository extends JpaRepository<Validation, Integer> {

	Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

	Optional<Validation> findByOrderIdAndTransactionId(String orderId, String transactionId);
}

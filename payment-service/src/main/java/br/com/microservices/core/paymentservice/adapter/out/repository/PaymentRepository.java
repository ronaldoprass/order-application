package br.com.microservices.core.paymentservice.adapter.out.repository;

import br.com.microservices.core.paymentservice.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

	Optional<Payment> findByOrderIdAndTransactionId(String orderId, String transactionId);
}

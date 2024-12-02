package br.com.microservices.core.orderservice.adapter.out.repository;

import br.com.microservices.core.orderservice.domain.document.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}

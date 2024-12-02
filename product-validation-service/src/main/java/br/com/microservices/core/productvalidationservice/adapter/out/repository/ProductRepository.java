package br.com.microservices.core.productvalidationservice.adapter.out.repository;

import br.com.microservices.core.productvalidationservice.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Boolean existsByCode(String code);
}

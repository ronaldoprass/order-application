package br.com.microservices.core.orderservice.domain.dto;

import br.com.microservices.core.orderservice.domain.document.OrderProducts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

	private List<OrderProducts> products;
}

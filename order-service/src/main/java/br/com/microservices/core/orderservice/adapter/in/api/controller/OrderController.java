package br.com.microservices.core.orderservice.adapter.in.api.controller;

import br.com.microservices.core.orderservice.app.service.OrderService;
import br.com.microservices.core.orderservice.domain.document.Order;
import br.com.microservices.core.orderservice.domain.dto.OrderRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

	private OrderService orderService;

	@PostMapping
	public Order create(@RequestBody OrderRequest order) {
		return orderService.createOrder(order);
	}
}

package br.com.microservices.core.orderservice.app.service;

import br.com.microservices.core.orderservice.adapter.out.producer.SagaProducer;
import br.com.microservices.core.orderservice.adapter.out.repository.OrderRepository;
import br.com.microservices.core.orderservice.domain.document.Event;
import br.com.microservices.core.orderservice.domain.document.Order;
import br.com.microservices.core.orderservice.domain.dto.OrderRequest;
import br.com.microservices.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

	private static final String TRANSACTION_ID_PATTERN = "%s_%s";

	private final EventService eventService;
	private final SagaProducer producer;
	private final JsonUtil jsonUtil;
	private final OrderRepository repository;

	public Order createOrder(OrderRequest orderRequest) {
		var order = Order
				.builder()
				.products(orderRequest.getProducts())
				.createdAt(LocalDateTime.now())
				.transactionId(
						String.format(TRANSACTION_ID_PATTERN, Instant.now().toEpochMilli(), UUID.randomUUID()))
				.build();
		repository.save(order);
		producer.sendEvent(jsonUtil.toJson(createPayload(order)));
		return order;
	}

	private Event createPayload(Order order) {
		var event = Event
				.builder()
				.orderId(order.getId())
				.transactionId(order.getTransactionId())
				.payload(order)
				.createdAt(LocalDateTime.now())
				.build();
		eventService.save(event);
		return event;
	}
}

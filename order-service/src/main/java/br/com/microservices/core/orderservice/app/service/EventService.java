package br.com.microservices.core.orderservice.app.service;

import br.com.microservices.core.orderservice.adapter.out.repository.EventRepository;
import br.com.microservices.core.orderservice.domain.document.Event;
import br.com.microservices.core.orderservice.domain.dto.EventFilters;
import br.com.microservices.core.orderservice.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Service
@AllArgsConstructor
public class EventService {

	private final EventRepository repository;

	public void notifyEnding(Event event) {
		event.setOrderId(event.getPayload().getId());
		event.setCreatedAt(LocalDateTime.now());
		save(event);
		log.info("Order {} with saga notified! TransactionId: {}", event.getOrderId(), event.getTransactionId());
	}

	public List<Event> findAll() {
		return repository.findAllByOrderByCreatedAtDesc();
	}

	public Event findByFilters(EventFilters filters) {
		validateEmptyFilters(filters);
		if (!isEmpty(filters.getOrderId())) {
			return findByOrderId(filters.getOrderId());
		} else {
			return findByTransactionId(filters.getTransactionId());
		}
	}

	private void validateEmptyFilters(EventFilters filters) {
		if (isEmpty(filters.getOrderId()) && isEmpty(filters.getTransactionId())) {
			throw new ValidationException("OrderID or TransactionID must be informed.");
		}
	}

	private Event findByTransactionId(String transactionId) {
		return repository
				.findTop1ByTransactionIdOrderByCreatedAtDesc(transactionId)
				.orElseThrow(() -> new ValidationException("Event not found by transactionId."));
	}

	private Event findByOrderId(String orderId) {
		return repository
				.findTop1ByOrderIdOrderByCreatedAtDesc(orderId)
				.orElseThrow(() -> new ValidationException("Event not found by orderID."));
	}

	public Event save(Event event) {
		return repository.save(event);
	}
}

package com.nikolic.order.service;

import com.nikolic.order.dto.OrderDTO;
import com.nikolic.order.event.model.OrderCreatedEvent;
import com.nikolic.order.event.model.OrderedBook;
import com.nikolic.order.event.producer.OrderEventProducer;
import com.nikolic.order.mapper.OrderMapper;
import com.nikolic.order.model.Order;
import com.nikolic.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;
    private final OrderEventProducer producer;

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toOrderDTO);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toOrder(orderDTO);
        Order savedOrder = orderRepository.save(order);

        var orderCreatedEvent = new OrderCreatedEvent();
        orderCreatedEvent.setOrderId(savedOrder.getId().toString());
        orderCreatedEvent.setUserId(savedOrder.getUserId().toString());
        orderCreatedEvent.setBooks(
                List.of(new OrderedBook(orderDTO.getBookId().toString(), 1)) // Example data
        );

        producer.sendNewOrderEvent(orderCreatedEvent);
        
        return orderMapper.toOrderDTO(savedOrder);
    }

    public Optional<OrderDTO> updateOrder(Long id, OrderDTO orderDetails) {
        return orderRepository.findById(id).map(order -> {
            order.setOrderStatus(orderDetails.getOrderStatus());
            Order updatedOrder = orderRepository.save(order);
            return orderMapper.toOrderDTO(updatedOrder);
        });
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

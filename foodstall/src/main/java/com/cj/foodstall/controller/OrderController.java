package com.cj.foodstall.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cj.foodstall.dto.OrderDto;
import com.cj.foodstall.model.Item;
import com.cj.foodstall.model.Order;
import com.cj.foodstall.model.User;
import com.cj.foodstall.repositry.ItemRepositry;
import com.cj.foodstall.repositry.OrderRepositry;
import com.cj.foodstall.repositry.UserRepositry;

@RestController
public class OrderController {

	@Autowired
	private OrderRepositry orderRepositry;

	@Autowired
	private ItemRepositry itemRepositry;
	
	@Autowired
	private UserRepositry userRepositry;

	@GetMapping(value = "/order")
	public List<Order> getOrders() {
		return orderRepositry.findAll();
	}

	@GetMapping(value = "/order/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId) {
		Optional<Order> order = orderRepositry.findById(orderId);

		if (order.isPresent()) {
			return ResponseEntity.ok(order.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto) {

		Optional<Item> item = itemRepositry.findById(orderDto.getItemId());
		Optional<User> user = userRepositry.findById(orderDto.getUserId());

		if (item.isPresent() && user.isPresent()) {

			Order order = new Order(orderDto.getQuantity(), item.get(), user.get());

			Order savedOrder = orderRepositry.save(order);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{orderId}")
					.buildAndExpand(savedOrder.getOrderId()).toUri();
			return ResponseEntity.created(location).build();

		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}

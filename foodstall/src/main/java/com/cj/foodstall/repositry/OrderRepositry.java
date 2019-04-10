package com.cj.foodstall.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cj.foodstall.model.Order;
import com.cj.foodstall.model.User;

public interface OrderRepositry extends JpaRepository<Order, Integer> {

	public List<Order> findByUser(User User);

}

package com.cj.foodstall.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cj.foodstall.model.Order;

public interface OrderRepositry extends JpaRepository<Order, Integer> {

}

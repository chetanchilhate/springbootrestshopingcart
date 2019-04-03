package com.cj.foodstall.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cj.foodstall.model.User;

@Repository
public interface UserRepositry extends JpaRepository<User, Integer> {

}

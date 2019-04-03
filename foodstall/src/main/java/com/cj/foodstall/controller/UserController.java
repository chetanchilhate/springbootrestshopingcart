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

import com.cj.foodstall.model.User;
import com.cj.foodstall.repositry.UserRepositry;

@RestController
public class UserController {

	@Autowired
	private UserRepositry userRepositry;

	@GetMapping(value = "/user")
	public List<User> getAllUser() {
		return userRepositry.findAll();
	}

	@GetMapping(value = "/user/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
		Optional<User> user = userRepositry.findById(userId);

		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping(value = "/user")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User savedUser = userRepositry.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
				.buildAndExpand(savedUser.getUserId()).toUri();
		return ResponseEntity.created(location).build();
	}

}

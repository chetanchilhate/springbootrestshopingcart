package com.cj.foodstall.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cj.foodstall.model.Item;
import com.cj.foodstall.repositry.ItemRepositry;

@RestController
public class ItemController {

	@Autowired
	private ItemRepositry itemRepositry;

	@GetMapping(value = "/item/category", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getItemCategory() {
		return itemRepositry.findDistinctItemCategory();
	}

	@GetMapping(value = "/item")
	public List<Item> getItems(@RequestParam(required = false) String itemCategory) {

		if (Objects.isNull(itemCategory) || itemCategory.isEmpty())
			return itemRepositry.findAll();

		return itemRepositry.findByItemCategory(itemCategory);
	}

}

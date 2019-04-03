package com.cj.foodstall.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cj.foodstall.model.Item;

@Repository
public interface ItemRepositry extends JpaRepository<Item, Integer> {

	@Query(value = "SELECT DISTINCT itemCategory FROM Item ")
	public List<String> findDistinctItemCategory();

	public List<Item> findByItemCategory(String itemCategory);
}

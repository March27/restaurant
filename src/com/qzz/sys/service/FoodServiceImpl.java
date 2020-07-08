package com.qzz.sys.service;

import java.util.List;

import com.qzz.sys.bean.Food;

public interface FoodServiceImpl {

	List<Food> find(String keyword, String foodTypeId);

	Food findById(int id);

	void update(Food food);

	Food findByFoodName(String foodName);

	void save(Food food);

}

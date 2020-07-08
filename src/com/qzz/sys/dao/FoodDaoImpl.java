package com.qzz.sys.dao;

import java.util.List;

import com.qzz.sys.bean.Food;

public interface FoodDaoImpl {

	List<Food> find(String keyword, String foodTypeId);

	Food findById(int id);

	void update(Food food);

	Food findByFoodName(String foodName);

	void save(Food food);

}

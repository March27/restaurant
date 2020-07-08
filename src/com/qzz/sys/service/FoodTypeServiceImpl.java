package com.qzz.sys.service;

import java.util.List;

import com.qzz.sys.bean.FoodType;

public interface FoodTypeServiceImpl {

	List<FoodType> find(String keyword, String disabled);

	FoodType findByFoodName(String foodTypeName);

	void save(FoodType foodType2);

	FoodType findById(int id);

	void update(FoodType foodType2);

}

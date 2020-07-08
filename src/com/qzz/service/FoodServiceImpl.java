package com.qzz.service;

import java.util.List;

import com.qzz.bean.Food;

public interface FoodServiceImpl {

	List<Food> findByFoodTypeId(Integer foodTypeId);

	Food findByFoodId(Integer foodid);

	int getFoodCount(int parseInt);

}

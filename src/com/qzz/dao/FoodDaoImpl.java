package com.qzz.dao;

import java.util.List;

import com.qzz.bean.Food;

public interface FoodDaoImpl {

	List<Food> findByFoodTypeId(Integer foodTypeId);

	Food findByFoodId(Integer foodid);

	int getFoodCount(int foodid);

}

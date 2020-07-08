package com.qzz.sys.dao;

import java.util.List;

import com.qzz.sys.bean.Food;
import com.qzz.sys.bean.FoodType;

public interface FoodTypeDaoImpl {

	List<FoodType> find(String keyword, String disabled);

	FoodType findByFoodName(String foodTypeName);

	void save(FoodType foodType2);

	FoodType findById(int id);

	Object update(FoodType foodType2);

}

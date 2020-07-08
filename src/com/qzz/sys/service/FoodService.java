package com.qzz.sys.service;

import java.util.List;

import com.qzz.sys.bean.Food;
import com.qzz.sys.dao.FoodDao;
import com.qzz.sys.dao.FoodDaoImpl;

public class FoodService implements FoodServiceImpl {
	
	FoodDaoImpl  foodDao = new FoodDao();
	
	
	@Override
	public List<Food> find(String keyword, String foodTypeId) {
		
		return foodDao.find(keyword,foodTypeId);
	}


	@Override
	public Food findById(int id) {
		// TODO 自动生成的方法存根
		return foodDao.findById(id);
	}


	@Override
	public void update(Food food) {
		foodDao.update(food);
		
	}


	@Override
	public Food findByFoodName(String foodName) {
		// TODO 自动生成的方法存根
		return foodDao.findByFoodName(foodName);
	}


	@Override
	public void save(Food food) {
		// TODO 自动生成的方法存根
		foodDao.save(food);
	}

}

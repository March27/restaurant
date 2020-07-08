package com.qzz.sys.service;

import java.util.List;

import com.qzz.sys.bean.FoodType;
import com.qzz.sys.dao.FoodTypeDao;
import com.qzz.sys.dao.FoodTypeDaoImpl;

public class FoodTypeService implements FoodTypeServiceImpl {
	FoodTypeDaoImpl foodTypeDao = new FoodTypeDao();
	@Override
	public List<FoodType> find(String keyword, String disabled) {
		// TODO 自动生成的方法存根
		return foodTypeDao.find(keyword, disabled);
	}
	@Override
	public FoodType findByFoodName(String foodTypeName) {
		// TODO 自动生成的方法存根
		return foodTypeDao.findByFoodName(foodTypeName);
	}
	@Override
	public void save(FoodType foodType2) {
		// TODO 自动生成的方法存根
		foodTypeDao.save(foodType2);
	}
	@Override
	public FoodType findById(int id) {
		// TODO 自动生成的方法存根
		return foodTypeDao.findById(id);
	}
	@Override
	public void update(FoodType foodType2) {
		// TODO 自动生成的方法存根
		 foodTypeDao.update(foodType2);
	}

}

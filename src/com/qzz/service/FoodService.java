package com.qzz.service;

import java.util.List;

import com.qzz.bean.Food;
import com.qzz.dao.FoodDao;
import com.qzz.dao.FoodDaoImpl;

public class FoodService implements FoodServiceImpl {
	
	private FoodDaoImpl FoodDao = new FoodDao();
	@Override
	public List<Food> findByFoodTypeId(Integer foodTypeId) {
		// TODO �Զ����ɵķ������
		return FoodDao.findByFoodTypeId(foodTypeId);
	}
	@Override
	public Food findByFoodId(Integer foodid) {
		// TODO �Զ����ɵķ������
		return FoodDao.findByFoodId(foodid);
	}
	@Override
	public int getFoodCount(int foodid) {
		// TODO �Զ����ɵķ������
		return FoodDao.getFoodCount(foodid);
	}

}

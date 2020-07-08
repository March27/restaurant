package com.qzz.service;

import java.util.List;

import com.qzz.bean.FoodType;
import com.qzz.dao.FoodTypeDao;
import com.qzz.dao.FoodTypeDaoImpl;

public class FoodTypeService implements FoodTypeServiceImpl {
	
	//service层调用dao层
	private FoodTypeDaoImpl foodTypeDao = new FoodTypeDao();
	@Override
	public List<FoodType> findAll() {
		// TODO 自动生成的方法存根
		return foodTypeDao.findAll();
	}

}

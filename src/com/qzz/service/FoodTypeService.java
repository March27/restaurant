package com.qzz.service;

import java.util.List;

import com.qzz.bean.FoodType;
import com.qzz.dao.FoodTypeDao;
import com.qzz.dao.FoodTypeDaoImpl;

public class FoodTypeService implements FoodTypeServiceImpl {
	
	//service�����dao��
	private FoodTypeDaoImpl foodTypeDao = new FoodTypeDao();
	@Override
	public List<FoodType> findAll() {
		// TODO �Զ����ɵķ������
		return foodTypeDao.findAll();
	}

}

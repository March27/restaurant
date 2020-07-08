package com.qzz.sys.service;

import com.qzz.sys.bean.User;
import com.qzz.sys.dao.UserDao;
import com.qzz.sys.dao.UserDaoImpl;

public class UserService implements UserServiceImpl {
	
	UserDaoImpl UserDao = new UserDao();
	@Override
	public User findByLoginNameAndPass(String name, String password) {
		
		return UserDao.findByLoginNameAndPass(name,password);
	}

}

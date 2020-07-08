package com.qzz.service;

import com.qzz.bean.User;
import com.qzz.dao.UserDao;
import com.qzz.dao.UserDaoImpl;

public class UserService implements UserServiceImpl {
	UserDaoImpl userDao = new UserDao();
	public User findByLoginNameAndPass(String name, String password) {
		// TODO 自动生成的方法存根
		return userDao.findByLoginNameAndPass(name,password);
	}

}

package com.qzz.dao;

import com.qzz.bean.User;

public interface UserDaoImpl {

	User findByLoginNameAndPass(String name, String password);

}

package com.qzz.sys.dao;

import com.qzz.sys.bean.User;

public interface UserDaoImpl {

	User findByLoginNameAndPass(String name, String password);

	

}

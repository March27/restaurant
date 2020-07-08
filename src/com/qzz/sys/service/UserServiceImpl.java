package com.qzz.sys.service;

import com.qzz.sys.bean.User;

public interface UserServiceImpl {

	User findByLoginNameAndPass(String name, String password);

}

package com.qzz.service;

import com.qzz.bean.User;

public interface UserServiceImpl {

	User findByLoginNameAndPass(String name, String password);

}

package com.qzz.dao;

import java.util.List;

import com.qzz.bean.Comment;

public interface CommentDaoImpl {

	List<Comment> findByFoodId(int foodid);

	void comment(int userId, int foodId, String content);

}

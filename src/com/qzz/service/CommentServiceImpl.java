package com.qzz.service;

import java.util.List;

import com.qzz.bean.Comment;

public interface CommentServiceImpl {

	List<Comment> findByFoodId(int parseInt);

	void comment(int parseInt, int parseInt2, String content);

}

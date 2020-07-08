package com.qzz.service;

import java.util.List;

import com.qzz.bean.Comment;
import com.qzz.dao.CommentDao;
import com.qzz.dao.CommentDaoImpl;

public class CommentService implements CommentServiceImpl {
	
	
	CommentDaoImpl commentDao = new CommentDao();
	@Override
	public List<Comment> findByFoodId(int foodid) {
		// TODO �Զ����ɵķ������
		return commentDao.findByFoodId(foodid);
	}
	@Override
	public void comment(int userId, int foodId, String content) {
		// TODO �Զ����ɵķ������
		commentDao.comment(userId,foodId,content);
	}

}

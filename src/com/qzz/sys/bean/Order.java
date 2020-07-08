package com.qzz.sys.bean;

import java.util.Date;
import java.util.List;

public class Order {
	private Integer id;
	private String orderCode;
	private Double totalPrice;
	private Integer status;
	private Date orderTime;
	private String payTime;
	private Date updateTime;
	private Integer disabled;
	private Integer userId;
	
	private OrderDetail orderDetail;
	private Food food;
	private User user;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String string) {
		this.payTime = string;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderCode=" + orderCode + ", totalPrice=" + totalPrice + ", status=" + status
				+ ", orderTime=" + orderTime + ", payTime=" + payTime + ", updateTime=" + updateTime + ", disabled="
				+ disabled + ", userId=" + userId + ", orderDetail=" + orderDetail + ", food=" + food + ", user=" + user
				+ "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public Food getFood() {
		return food;
	}
}

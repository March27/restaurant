package com.qzz.sys.bean;

import java.util.Date;

public class Food {
	private int id;
	private String foodName;
	private int foodTypeId;
	private double price;
	private String remark;
	private String img;
	private Date createDate;
	private Date updateDate;
	private Integer disabled;
	
	private FoodType foodType;
	private Integer buyNum;
	
	public Food() {
		
	}
	
	public Food(String foodName, int foodTypeId, double price, String remark, String img, Integer disabled) {
		super();
		this.foodName = foodName;
		this.foodTypeId = foodTypeId;
		this.price = price;
		this.remark = remark;
		this.img = img;
		this.disabled = disabled;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	@Override
	public String toString() {
		return "Food [id=" + id + ", foodName=" + foodName + ", foodTypeId=" + foodTypeId + ", price=" + price
				+ ", remark=" + remark + ", img=" + img + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", disabled=" + disabled + ", foodType=" + foodType + ", buyNum=" + buyNum + "]";
	}
	public Integer getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	public FoodType getFoodType() {
		return foodType;
	}
	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}
}

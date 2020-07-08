package com.qzz.sys.bean;

import java.util.Date;

public class FoodType {
	private int id;
	private String typeName;
	private Date createDate;
	private Date updateDate;
	private Integer disabled;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
		return "FoodType [id=" + id + ", typeName=" + typeName + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", disabled=" + disabled + "]";
	}

}

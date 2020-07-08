package com.qzz.bean;

import java.io.Serializable;
import java.util.List;

public class PageBean<E> implements Serializable{

    private List<E> list;     // 某对象(比如说商城商品)当前页的数据
    private Integer currPage; // 当前所在的页数,如第1页,第2页
    private Integer pageSize; // 每页所包含的商品的数量
    @SuppressWarnings("unused")
    private Integer totalPage;  // 总页数
    private Integer totalCount; // 总商品数

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return  (int) Math.ceil(totalCount * 1.0 / pageSize);
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public PageBean() {
        super();
    }

    public PageBean(List<E> list, Integer currPage, Integer pageSize, Integer totalCount) {
        super();
        this.list = list;
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }
}




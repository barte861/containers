package com.olczak.model;

import java.util.List;

public class CategoryList {
	private List<Category> list;
	public CategoryList(List<Category> list){
		this.list = list;
	}

	public List<Category> getList() {
		return list;
	}

	public void setList(List<Category> list) {
		this.list = list;
	}
}

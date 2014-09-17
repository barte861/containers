package com.olczak.model;

import java.util.List;

public class PartList {

	private List<Part> list;
	public PartList(List<Part> list){
		this.list = list;
	}

	public List<Part> getList() {
		return list;
	}

	public void setList(List<Part> list) {
		this.list = list;
	}
}

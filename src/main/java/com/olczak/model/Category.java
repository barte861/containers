package com.olczak.model;

import java.io.Serializable;

public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	public Category(){
		super();
	}
	
	public Category(int id, String name){
		super();
		this.setId(id);
		this.setName(name);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name;
		
	}
	

}

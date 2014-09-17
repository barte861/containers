package com.olczak.model;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;


public class Part implements Serializable{
	@Override
	public String toString() {
		return "Part [id=" + id + ", name=" + name + ", details=" + details
				+ ", cost=" + cost + ", category=" + category + "]";
	}
	public Part() {
		super();
	}
	public Part(int id, String name, String details, String cost,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.details = details;
		this.cost = cost;
		this.category = category;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String details;
	private String cost;
	@Autowired
	private Category category;
	
	public void setCategory(Category category) {
		this.category = category;
	}
	public Category getCategory() {
		return category;
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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}

	
}

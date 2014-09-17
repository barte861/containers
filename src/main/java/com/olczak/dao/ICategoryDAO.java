package com.olczak.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.olczak.model.Category;
import com.olczak.model.CategoryList;

public interface ICategoryDAO {
	 public abstract void addCategory(Category c) throws IOException, SQLException;
		
		public abstract void removeCategory(int id) throws SQLException; 
		
		public abstract void modifyCategory(Category c, int id);
		
		public abstract Category getCategory(int id);
		
		public abstract CategoryList getAllCategories();
}

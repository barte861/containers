package com.olczak.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.olczak.model.Category;
import com.olczak.model.CategoryList;
import com.olczak.model.Part;
import com.olczak.model.PartList;

public class CategoryDao implements ICategoryDAO{

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RowMapper<Category> rowMapper2;
	
	@Override
	public void addCategory(Category c) throws IOException, SQLException {

	}

	@Override
	public void removeCategory(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyCategory(Category c, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category getCategory(int id) {
		
		Category categoryRet = this.jdbcTemplate.queryForObject("SELECT * FROM Category WHERE id=?", new Object[]{id}, rowMapper2);
		return categoryRet;
	}

	@Override
	public CategoryList getAllCategories() {
		List<Category> list = jdbcTemplate.query("SELECT * FROM Category", rowMapper2);
		
		return new CategoryList(list);
	}

}

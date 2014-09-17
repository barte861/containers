package com.olczak.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.olczak.model.Part;
import com.olczak.model.PartList;

public class PartsDao implements IPartsDAO{
		
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RowMapper<Part> rowMapper;

	public void addPart(Part p) throws IOException {
		if (p != null){
			jdbcTemplate.update("INSERT INTO Parts VALUES (1,?,?,?,?)",p.getName(),p.getDetails(),p.getCost(),p.getCategory().getId());
		}
		
	}

	public Part getPart(int id) {
		

		Part partRet = this.jdbcTemplate.queryForObject("SELECT * FROM Parts WHERE id=?;", new Object[]{id}, rowMapper);
		
		return partRet;
	}

	public void removePart(int id) throws IOException {
		
			jdbcTemplate.update("DELETE FROM Parts WHERE ID=?; commit;",id);
		
	}
	public void removePartName(String name, String details, String cost)  throws IOException{
		
			jdbcTemplate.update("DELETE FROM Parts WHERE name=? AND details=? AND cost=?; commit;",name,details,cost);
		
	}

	public void modifyPart(int id, Part p) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public PartList getAllParts() {
		List<Part> list = jdbcTemplate.query("SELECT * FROM Parts;", rowMapper);
		return new PartList(list);
	}
	
	public int dodawanie(int a,int b){
		return a+b;
	}

}

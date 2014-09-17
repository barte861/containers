package com.olczak.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.olczak.dao.CategoryDao;
import com.olczak.model.Category;
import com.olczak.model.Part;

/**
 * 
 * @author bartek Nie działa @Value bez statycznego beana i @PropertySource
 */
@Configurable
@PropertySource("classpath:database.properties")
public class DatabaseConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value("${db.driver}")
	private String driverClass;

	@Value("${db.username}")
	private String username;

	@Value("${db.password}")
	private String password;

	@Value("${db.url}")
	private String url;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(this.driverClass);
		dataSource.setUrl(this.url);
		dataSource.setUsername(this.username);
		dataSource.setPassword(this.password);
		//System.out.println(driverClass + username + password + url);
		return dataSource;
	}

	@Bean
	@Autowired
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		System.out.println("dataSource " +dataSource);
		jdbcTemplate.setDataSource(dataSource);
		System.out.println("DZIAAAAAAAAAAAAALAAAAAAAAA ");
		System.out.println(jdbcTemplate);
		return jdbcTemplate;
	}
	
	@Autowired
	CategoryDao categoryDao;
	
	@Bean
	@Scope("prototype")
	public RowMapper<Part> rowMapper() {
		RowMapper<Part> rowMapper = new RowMapper<Part>() {
			public Part mapRow(ResultSet row, int arg1) throws SQLException {
				Part part = new Part();
				part.setId(row.getInt("id"));
				part.setName(row.getString("name"));
				part.setDetails(row.getString("details"));
				part.setCost(row.getString("cost"));
				//System.out.println("nazwa: "+part.getName());
				part.setCategory(categoryDao.getCategory(row.getInt("category")));
				
				

				return part;
			}
		};
		return rowMapper;
	}

	@Bean
	@Scope("prototype")
	public RowMapper<Category> rowMapper2() {
		RowMapper<Category> rowMapper2 = new RowMapper<Category>() {
			public Category mapRow(ResultSet row, int arg1) throws SQLException {
				Category category = new Category();
				category.setId(row.getInt("id"));
				category.setName(row.getString("name"));
				return category;
			}
		};
		return rowMapper2;
	}

}

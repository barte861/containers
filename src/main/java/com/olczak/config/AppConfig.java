package com.olczak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.olczak.control.LanguageController;
import com.olczak.dao.CategoryDao;
import com.olczak.dao.PartsDao;
import com.olczak.gui.ViewConfig;
import com.olczak.model.LanguageModel;

@Configuration
@EnableAspectJAutoProxy
@Import({ DatabaseConfig.class, ViewConfig.class })
public class AppConfig {

	@Bean
	LanguageModel languageModel() {
		return new LanguageModel();
	}

	@Bean
	LanguageController languageController() {
		return new LanguageController(languageModel());
	}

	@Bean
	PartsDao partDao() {
		return new PartsDao();
	}
	@Bean
	CategoryDao categoryDao(){
		return new CategoryDao();
	}

//	@Bean
//	ObservableList<Table> partData() {
//		return FXCollections.observableArrayList();
//	}



}

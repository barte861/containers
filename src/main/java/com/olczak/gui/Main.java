package com.olczak.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.olczak.config.AppConfig;
import com.olczak.model.LanguageModel;

@Service
public class Main extends Application {

	private static Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		//this.stage=stage;
		logger.info("Starting application");

		Platform.setImplicitExit(true);

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		ViewConfig screens = context.getBean(ViewConfig.class);
		LanguageModel lang = context.getBean(LanguageModel.class);
		
		screens.setLangModel(lang);
		screens.setPrimaryStage(stage);
		screens.showMainScreen();
		screens.loadFirst();
		//((AnnotationConfigApplicationContext)context).close();
	}
}

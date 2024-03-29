package com.olczak.gui;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.olczak.model.LanguageModel;

@Configuration
@Lazy
public class ViewConfig implements Observer{
	private static Logger logger = LogManager.getLogger(ViewConfig.class);
	
	public static final int WIDTH = 650;
	public static final int HEIGHT = 400;	
	public static final String STYLE_FILE = "main.css";
	
	private Stage stage;
	private Scene scene;
	private LanguageModel lang;
	private StackPane root;	

	public void setPrimaryStage(Stage primaryStage) {
		this.stage = primaryStage;
	}

	public void setLangModel(LanguageModel lang) {
		if (this.lang!=null){
			this.lang.deleteObserver(this);
		}
		lang.addObserver(this);
		this.lang = lang;
	}

	public ResourceBundle getBundle() {
		return lang.getBundle();
	}

	public void showMainScreen() {
		root = new StackPane();
		root.getStylesheets().add(STYLE_FILE);
		root.getStyleClass().add("main-window");
		stage.setTitle("VW T3");
		scene = new Scene(root, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.setResizable(false);

		stage.setOnHiding(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				System.exit(0);
				// TODO are you sure you want to exit dialog
			}
		});

		stage.show();
	}

	private void setNode(Node node) {
		root.getChildren().setAll(node);
	}

	private void setNodeOnTop(Node node) {
		root.getChildren().add(node);
	}

	public void removeNode(Node node) {
		root.getChildren().remove(node);
	}

	void loadFirst() {
		setNode(getNode(firstView(), getClass().getResource("/view/FirstView.fxml")));
	}
	
	void loadSecond() {
		setNode(getNode(popupView(), getClass().getResource("/view/Popup.fxml")));
	}
//	
//	void loadPopup() {
//		ModalDialog modal = new ModalDialog(popupPresentation(), getClass().getResource("Popup.fxml"), stage.getOwner(), lang.getBundle());
//		modal.setTitle( lang.getBundle().getString("popup.title") );
//		modal.getStyleSheets().add(STYLE_FILE);
//		modal.show(); 
//	}

	@Bean
	@Scope("prototype")
	FirstView firstView() {
		return new FirstView(this);
	}
	
	@Bean
	@Scope("prototype")
	PopupView popupView() {
		return new PopupView(this);
	}
//	
//	@Bean
//	@Scope("prototype")
//	PopupPresentation popupPresentation() {
//		return new PopupPresentation(this);
//	}

	private Node getNode(final AbstractView control, URL location) {
		FXMLLoader loader = new FXMLLoader(location, lang.getBundle());
		loader.setControllerFactory(new Callback<Class<?>, Object>() {
			public Object call(Class<?> aClass) {
				return control;
			}
		});
				
		try {
			return (Node) loader.load();
		} catch (Exception e) {
			logger.error("Error casting node", e);
			return null;
		}
	}

	public Stage getStage() {
		return stage;
	}

	public void update(Observable o, Object arg) {
		loadFirst();		
	}

}

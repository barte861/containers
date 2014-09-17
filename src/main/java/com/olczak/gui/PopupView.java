package com.olczak.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;

import com.olczak.dao.CategoryDao;
import com.olczak.dao.PartsDao;
import com.olczak.model.Category;
import com.olczak.model.Part;

public class PopupView extends AbstractView {

	@FXML
	ChoiceBox<Category> categoryChoice;

	@FXML
	TextField  partName;
	@FXML
	TextField  partCost;
	@FXML
	TextArea partDetails;
	
	

	@Autowired
	PartsDao partDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	
	private boolean okClicked = false;

	@FXML
	void initialize() {
		for(Category c:categoryDao.getAllCategories().getList()){
			categoryChoice.getItems().add(c);
		}
		
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public PopupView(ViewConfig config) {
		super(config);
	}



	@FXML
	private void handleOk(ActionEvent event) {
		try {
			if(categoryChoice.getSelectionModel().getSelectedIndex()==-1){
				System.out.println("nie wybrano");
			}else{
				partDao.addPart(new Part(1, partName.getText(), partDetails.getText(), partCost.getText(), categoryChoice.getValue()));
				config.loadFirst();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//}
	}
	
	@FXML
    private void handleCancel(ActionEvent event) {
		config.loadFirst();
		
    }
}

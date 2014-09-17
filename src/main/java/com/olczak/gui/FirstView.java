package com.olczak.gui;

import java.io.IOException;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import org.springframework.beans.factory.annotation.Autowired;

import com.olczak.control.LanguageController;
import com.olczak.dao.CategoryDao;
import com.olczak.dao.PartsDao;
import com.olczak.model.LanguageModel.Language;
import com.olczak.model.Part;
import com.olczak.model.Table;

public class FirstView extends AbstractView {

	public FirstView(ViewConfig config) {
		super(config);
	}

	private ObservableList<Table> partData = FXCollections
			.observableArrayList();

	public ObservableList<Table> getPartData() {
		return partData;
	}

	@FXML
	RadioButton enRadio, plRadio;
	@FXML
	ToggleGroup langGroup;

	@FXML
	private TableView<Table> mainTable3;
	@FXML
	private TableColumn<Table, String> categoryColumn;
	@FXML
	private TableColumn<Table, String> nameColumn;
	@FXML
	private TableColumn<Table, String> detailsColumn;
	@FXML
	private TableColumn<Table, String> costColumn;

	@Autowired
	private LanguageController langCtr;

	@Autowired
	PartsDao partDao;

	@Autowired
	CategoryDao categoryDao;

	// @FXML
	// void nextView(ActionEvent event){
	// config.loadSecond();
	// }

	@FXML
	void initialize() {
		System.out.println("inicialize firstView");
		if (Language.PL.equals(langCtr.getLanguage())) {
			enRadio.setSelected(false);
			plRadio.setSelected(true);
		}

		langGroup.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {
					public void changed(
							ObservableValue<? extends Toggle> observable,
							Toggle oldValue, Toggle newValue) {
						changeLanguage();
					}
				});

		categoryColumn.setCellValueFactory(cellData -> cellData.getValue()
				.categoryProperty());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.nameProperty());
		detailsColumn.setCellValueFactory(cellData -> cellData.getValue()
				.detailsProperty());
		costColumn.setCellValueFactory(cellData -> cellData.getValue()
				.costProperty());

		List<Part> listTmp = partDao.getAllParts().getList();
		for (Part p : listTmp) {
			partData.add(new Table(p.getCategory(), p));
		}

		mainTable3.setItems(partData);

	}

	private void changeLanguage() {
		if (enRadio.isSelected()) {
			langCtr.toEnglish();

		} else {
			langCtr.toPolish();

		}

	}

	@FXML
	private void addClick(ActionEvent event) {
		config.loadSecond();
	}

	@FXML
	private void deleteClick(ActionEvent event) {
		if (mainTable3.getSelectionModel().getSelectedIndex() == 0) {

		} else {
			int selectedIndex = mainTable3.getSelectionModel()
					.getSelectedIndex();
			try {
				partDao.removePartName(partData.get(selectedIndex)
						.getNameProperty(), partData.get(selectedIndex)
						.getDetailsProperty(), partData.get(selectedIndex)
						.getCostProperty());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mainTable3.getItems().remove(selectedIndex);
		}

	}

}

package com.olczak.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Table {
	private final StringProperty categoryProperty;
	private final StringProperty nameProperty;
	private final StringProperty detailsProperty;
	private final StringProperty costProperty;
	
	public Table(Category category, Part part){
		categoryProperty = new SimpleStringProperty(category.getName());
		nameProperty = new SimpleStringProperty(part.getName());
		detailsProperty = new SimpleStringProperty(part.getDetails());
		costProperty = new SimpleStringProperty(part.getCost());
		
	}
	public String getCategoryProperty() {
		return categoryProperty.get();
	}
	public void setCategoryProperty(String categoryProperty) {
		this.categoryProperty.set(categoryProperty);
	}
	public StringProperty categoryProperty() {
        return categoryProperty;
    }
	public String getNameProperty() {
		return nameProperty.get();
	}
	public void setNameProperty(String nameProperty) {
		this.nameProperty.set(nameProperty);
	}
	public StringProperty nameProperty() {
        return nameProperty;
    }
	public String getDetailsProperty() {
		return detailsProperty.get();
	}
	public void setDetailsProperty(String detailsProperty) {
		this.detailsProperty.set(detailsProperty);
	}
	public StringProperty detailsProperty() {
        return detailsProperty;
    }
	public String getCostProperty() {
		return costProperty.get();
	}
	public void setCostProperty(String costProperty) {
		this.costProperty.set(costProperty);
	}
	public StringProperty costProperty() {
        return costProperty;
    }
}

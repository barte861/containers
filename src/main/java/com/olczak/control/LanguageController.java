package com.olczak.control;

import com.olczak.model.LanguageModel;
import com.olczak.model.LanguageModel.Language;

public class LanguageController {

	private LanguageModel model;
	
	public LanguageController(LanguageModel model){
		this.model = model;
		toEnglish();
	}
	
	public void toEnglish(){
		model.setBundle(Language.EN);
	}
	
	public void toPolish(){
		model.setBundle(Language.PL);
	}

	public Language getLanguage() {
		return model.getLanguage();
	}
	
}

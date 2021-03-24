package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLanguages;

    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtErrors;

    @FXML
    private TextField txtNumberOfErrors;

    @FXML
    private Button btnClear;

    @FXML
    private TextField txtCompletionTime;

    @FXML
    void doClearText(ActionEvent event) {
    	txtErrors.clear();
    	txtInput.clear();
    	txtCompletionTime.clear();
    	txtNumberOfErrors.clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	long init = System.nanoTime();
    	String language = boxLanguages.getValue();
    	
    	if(language == null) {
    		txtErrors.setText("Choose a languange: English or Italian \nScegliere una lingua: inglese o italiano");
    		return;
    	}
    			
    	model.loadDictionary(language);    	
    	String sInput = txtInput.getText().toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	List<RichWord> result = model.spellCheckText(model.stringToListString(sInput));
    	
    	txtCompletionTime.setText("Spell check completed in " + ((System.nanoTime() - init) / 1000000000.0) + " seconds");
    	
    	txtErrors.setText(model.toString(result));
    	
    	txtNumberOfErrors.setText("Text contains " + result.size() + " errors");
    }

    @FXML
    void initialize() {
        assert boxLanguages != null : "fx:id=\"boxLanguages\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumberOfErrors != null : "fx:id=\"txtNumberOfErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCompletionTime != null : "fx:id=\"txtCompletionTime\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Dictionary model) {
    	this.model = model;
    	boxLanguages.getItems().addAll("English", "Italian");
    }
}

package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	String word;
	boolean corretta;
	
	public RichWord(String word, boolean corretta) {
		
		this.word = word;
		this.corretta = corretta;
	}

	public boolean isCorretta() {
		return corretta;
	}

	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}

	public String getWord() {
		return word;
	}
	
	public String toString() {
		return word;
	}
	
	
	
	

}

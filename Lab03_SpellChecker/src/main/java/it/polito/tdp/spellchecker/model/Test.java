package it.polito.tdp.spellchecker.model;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		//System.out.print(1/2);
		//System.out.println("cane".compareTo("gatto"));
		//System.out.println("cane".compareTo("Gatto"));
		//System.out.println("there".compareTo("ther"));
		
		Dictionary d = new Dictionary();
		
		d.loadDictionary("English");
		
		String input = "there";
		List<String> list = d.stringToListString(input);
		
		List<RichWord> result = d.spellCheckText(list);
		System.out.println("Errori:" + d.toString(result));
		

	}

}

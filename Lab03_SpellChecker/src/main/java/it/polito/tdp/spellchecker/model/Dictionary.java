package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	Set<String> languages;
	List<String> dizionario;
	
	
	public Dictionary() {
		languages = new HashSet<String>();
		dizionario = new ArrayList<String>(); // con LinkedList molto più lento il get(index)
	}


	public void loadDictionary(String language) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + language + ".txt"));
			String line;
			while((line = br.readLine()) != null) {
				dizionario.add(line);
			}
			
			Collections.sort(dizionario); // INDISPENSABILE (es. there'd prima di there -> non funziona BS)
			br.close();
		} catch(IOException e) {
			System.out.println("Errore lettura file!");
		}
	}
	
	public List<RichWord> spellCheckText (List<String> inputTextList) {
		List<RichWord> check = new LinkedList<RichWord>();
		for(String word : inputTextList)  
			/*if(!linearSearch(word))*/ if(!binarySearch(word))
				check.add(new RichWord(word, false));

		return check;
	}
	
	// scorro tutto il dizionario per cercare la corrispondenza
	public boolean linearSearch(String word) {
		for(String s : dizionario)
			if(s.equals(word))
				return true;
		return false;
	}
	
	public boolean binarySearch(String word) {
		int inizio = 0;
		int fine = dizionario.size() -1; // ultimo indice possibile
		int centro; 
		
		while(fine > inizio) {  // esce quando gli estremi dell'intervallo coincidono (-> la parola non esiste)
			
			centro = (fine + inizio ) / 2;
			
			if(word.equals(dizionario.get(centro))) // trovata
				return true;
			
			if(word.compareTo(dizionario.get(centro)) < 0)   	 // devo cercare nell'intervallo inferiore
				fine = centro - 1;								 // [ inizio , centro-1 ]
			else if(word.compareTo(dizionario.get(centro)) > 0)  // devo cercare nell'intervallo superiore
				inizio = centro + 1;							 // [ centro+1 , fine ]
		}
		
		return false;
	}
		
	// il metodo spellCheck prende in input una lista di stringhe
	public List<String> stringToListString(String s) {
		List<String> listString = new LinkedList<String>();
		for(String ss : s.split(" "))
			listString.add(ss);
		return listString;
	}
	
	public String toString(List<RichWord> list) {
		String s = "";
		for(RichWord rw : list)
			s += rw.toString() + "\n";
		return s;
	}

}

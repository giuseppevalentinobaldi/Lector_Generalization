package it.uniroma3.stopword;
import java.util.ArrayList;
import java.util.List;

import it.uniroma3.krovetz.KrovetzStemmer;

public class SoftStopping {
	
	private List<String> stopWordSet = new ArrayList<String>();
	private KrovetzStemmer stemmer;
	
	public SoftStopping(){
		this.stopWordSet.add("for");
		this.stopWordSet.add("the");
		this.stopWordSet.add("to");
		this.stopWordSet.add("in");
		this.stopWordSet.add("a");
		this.stopWordSet.add("an");
		this.stopWordSet.add("at");
		this.stemmer = new KrovetzStemmer();
	}
	
	public boolean isStopword(String word) {
		if(stopWordSet.contains(word)){ return true;}
		else return false;
	}
	
	public String removeStopWords(String string) {
		String result = "";
		String[] words = string.split("\\s+");
		int i;
		int l= words.length;
			if(l>1)
				for(i=0; i<l; i++) {
					if(words[i].isEmpty()) continue;
					if(isStopword(words[i]) && ((l-i)<3)) continue; //remove stopwords
					result += (this.stemmer.stem(words[i])+" ");
				}
		return result;
	}

}

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
		this.stopWordSet.add("on");
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
		if(words[0].equals("is") | words[0].equals("are") | words[0].equals("was") | words[0].equals("were"))
			result += "BE ";
		else
			result += (this.stemmer.stem(words[0])+" ");
			for(i=1; i<l; i++) {
				if(words[i].isEmpty()) continue;
				if(isStopword(words[i]) && ((l-i)<3)) continue; //remove stopwords
				
				if(words[i].equals("is") | words[i].equals("are") | words[i].equals("was") | words[i].equals("were"))
					result += "BE ";
				else
					result += (this.stemmer.stem(words[i])+" ");				}
		return result;
	}

}

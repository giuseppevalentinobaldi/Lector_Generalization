package it.uniroma3.stopword;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	
	public String removeStopWords(String string) throws IOException{
		String result = "";
		String[] words = string.split("\\s+");
		int i;
		int l= words.length;

		
		BufferedReader br = new BufferedReader(new FileReader("sports.txt"));
		String line;
		List<String> list = new ArrayList<String>();

	    while ((line=br.readLine()) != null) {
	        list.add(line);
	    }
	    br.close();
	         
		
			if(words[0].equals("is") | words[0].equals("are") | words[0].equals("was") | words[0].equals("were"))
				result += "BE ";
			else if(list.contains(words[0]))
				result += "SPORT ";
			else
				result += (this.stemmer.stem(words[0])+" ");
			for(i=1; i<l; i++) {
				if(words[i].isEmpty()) continue;
				if(isStopword(words[i]) && ((l-i)<3)) continue; //remove stopwords
				
					if(words[i].equals("is") | words[i].equals("are") | words[i].equals("was") | words[i].equals("were"))
						result += "BE ";
					else if(list.contains(words[i]))
						result += "SPORT ";
					else
						result += (this.stemmer.stem(words[i])+" ");				}
		return result;
	}

}

package it.uniroma3.tsv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import it.uniroma3.stopword.SoftStopping;

public class TsvSingleGenerator {
	public static void main(String[]args){
		 	
		TsvReader tr= new TsvReader();  
		TsvPrinter tp = new TsvPrinter();
		SoftStopping ss= new SoftStopping();
		
		/*genera il file new_phrases_count_single.tsv*/
		List<String[]> allRows = tr.init("phrases_count_single.tsv");
		tp.init("new_phrases_count_single.tsv");
		ListIterator<String[]> li=allRows.listIterator();
		Map<String, String[]> comulative = new HashMap<String, String[]>();
		
		String[] temp;
		String [] current;
				
			while(li.hasNext()){
				temp=li.next();
				try {
					temp[1]=ss.removeStopWords(temp[1]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					if(comulative.containsKey(temp[1])){
						current=comulative.get(temp[1]);
						current[0]=(Integer.parseInt(temp[0])+Integer.parseInt(current[0]))+"";
					}
					else
						comulative.put(temp[1], temp);
			}
						
		List<String[]> collezione = new ArrayList<String[]>(comulative.values());
		Comparator<String[]> c = new MyComparatorSingle();
		Collections.sort(collezione, c);
		li=collezione.listIterator();
				
			while(li.hasNext()){
				tp.writeRow(li.next());
			}
		tp.close();
		/*end*/
	}

}
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

public class TsvWitnessGenerator {
	
	public static void main(String[]args) {
		
		TsvReader tr= new TsvReader();    
		TsvPrinter tp = new TsvPrinter();
		SoftStopping ss= new SoftStopping();
		
		String[] temp=null;
		String [] current;
		
		/*genera il file new_phrases_count_witness.tsv*/
		List<String[]> allRows = tr.init("phrases_count_witness.tsv");
		tp.init("new_phrases_count_witness.tsv");
		ListIterator<String[]> li=allRows.listIterator();
		Map<String, String[]> comulative = new HashMap<String, String[]>();
			while(li.hasNext()){
					temp = li.next();
					try {
						temp[1]=ss.removeStopWords(temp[1]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
						if(comulative.containsKey(temp[0]+temp[1])){
							current=comulative.get(temp[0]+temp[1]);
							current[2]= "" + (Integer.parseInt(current[2]) + Integer.parseInt(temp[2]) );
							current[3]= "" + (Integer.parseInt(current[3]) + Integer.parseInt(temp[3]) );
							current[4]= "" + (Integer.parseInt(current[4]) + Integer.parseInt(temp[4]) );
						}
						else
							comulative.put(temp[0]+temp[1], temp);
			}
			
		List<String[]> collezione = new ArrayList<String[]>(comulative.values());
		Comparator<String[]> c = new MyComparatorWitness();
		Collections.sort(collezione, c);
		li=collezione.listIterator();		
			while(li.hasNext()){
				tp.writeRow(li.next());
			}
		tp.close();
		/*end*/
	}

}

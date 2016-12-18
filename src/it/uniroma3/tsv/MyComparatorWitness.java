package it.uniroma3.tsv;
import java.util.Comparator;

public class MyComparatorWitness implements Comparator<String[]> {

	@Override
	public int compare(String[] arg0, String[] arg1) {
		return arg0[0].compareTo(arg1[0]);
	}

	
}
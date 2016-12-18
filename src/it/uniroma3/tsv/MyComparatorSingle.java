package it.uniroma3.tsv;
import java.util.Comparator;

public class MyComparator implements Comparator<String[]> {

	@Override
	public int compare(String[] arg0, String[] arg1) {
		return (Integer.valueOf(arg1[0])).compareTo(Integer.valueOf(arg0[0]));
	}

	
}

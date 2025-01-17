package it.uniroma3.tsv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

public class TsvReader {
	private TsvParserSettings settings;
	private TsvParser parser;
	
	public TsvReader(){
		this.settings = new TsvParserSettings(); //inizializzo l'oggetto setting del parser
		settings.getFormat().setLineSeparator("\n"); //gli dico che ogni linea è separata da \n  
		this.setParser(new TsvParser(settings)); //inizializzo l'oggetto parser
		
	}
	
	public List<String[]> init(String fileName){
		try {
			return this.getParser().parseAll(new FileReader(fileName)); //prende il file tsv e lo trasforma in una list<String[]>
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public TsvParser getParser() {
		return parser;
	}

	public void setParser(TsvParser parser) {
		this.parser = parser;
	}

}

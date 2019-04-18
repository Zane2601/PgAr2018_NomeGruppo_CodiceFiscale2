package unibs.fp.it.codicefiscale;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Controllore {
	private static final String SCHEMA_CODICE_FISCALE = "[a-zA-Z]{6}\\d\\d(?:[A-EHLMPR-Ta-ehlmpr-t](?:[04][1-9]|[1256]\\d)|[DHPSdhps][37]0|[ACELMRTacelmrt][37][01])[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	Dati codiceFiscale = new Dati ();
	
	
	/**
	 * 
	 * @param SCHEMA_CODICE_FISCALE: schema con tutte le caratteristiche che deve avere un codice fiscale
	 * @param codiceDaControllare: codice fiscale da passere al metodo e che deve essere controllato
	 * @return ritorna true se il codice fiscale è valido, false altrimenti
	 */
	public boolean controllaFormato (String codiceDaControllare) {    //VEDETE COME PASSARE IL CODICE FISCALE AL METODO CHE IO NON LO SO
		if(Pattern.matches(SCHEMA_CODICE_FISCALE, codiceDaControllare)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
}

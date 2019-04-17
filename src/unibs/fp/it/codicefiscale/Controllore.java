package unibs.fp.it.codicefiscale;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Controllore {
	private static final String SCHEMA_CODICE_FISCALE = "/^(?:(?:[B-DF-HJ-NP-TV-Z]|[AEIOU])[AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[1256LMRS][\\dLMNP-V])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$/i";
	Dati codiceFiscale = new Dati ();
	
	
	/**
	 * 
	 * @param SCHEMA_CODICE_FISCALE: schema con tutte le caratteristiche che deve avere un codice fiscale
	 * @param codiceDaControllare: codice fiscale da passere al metodo e che deve essere controllato
	 * @return ritorna true se il codice fiscale è valido, false altrimenti
	 */
	public boolean controllaFormato (String SCHEMA_CODICE_FISCALE, String codiceDaControllare) {    //VEDETE COME PASSARE IL CODICE FISCALE AL METODO CHE IO NON LO SO
		if(Pattern.matches(SCHEMA_CODICE_FISCALE, codiceDaControllare)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
}

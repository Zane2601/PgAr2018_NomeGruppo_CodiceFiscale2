package unibs.fp.it.codicefiscale;

import java.util.Iterator;

public class Creazione {
	public String creaCodice(Persona persona) {
		String codice = new String(new char[16]);
		
		String codiceCognome = creaCodiceCognome(persona.getCognome());
		String codiceNome = creaCodiceNome(persona.getNome());
		
		/*
		 * 	DOPO AVER CREATO TUTTI I METODI PER CREARE LE STRINGHE / CODICI, SCOMMENTARE IL COMMENTO QUA SOTTO!!
		 */

		//codice = codiceCognome + codiceNome + codiceAnno + codiceMese + codiceGiorno + codiceComune + codiceControllo;
		return codice;
	}
	
	
//////////////////////////////////////////////////////////////////////////////
	//crea il codice iniziale di 3 caratteri dal cognome
	private String creaCodiceCognome(String cognomePersona) {
		int i = 0;
		char[] codiceCognomePersona = new char[3];
	
		codiceCognomePersona = estraiPrimeTreConsonanti(cognomePersona);
		
		//se vengono trovate 3 consonanti viene ritornato il codice composto da 3 consonanti
		if (codiceCognomePersona[2] != 0) return codiceCognomePersona.toString();
			else {
				if (codiceCognomePersona[0] == 0) i = 0;
				else
					if (codiceCognomePersona[1] == 0) i = 1;
					else if (codiceCognomePersona[2] == 0) i = 2;
				}
		
		//se non vengono trovate 3 consonanti si passa a questo for, che cerca le vocali e riempie gli eventuali spazi svuoti
		for (int j = 0; j < cognomePersona.length(); j++) {
					
			if (isConsonant(cognomePersona.charAt(j)) == false) {
				codiceCognomePersona[i] = cognomePersona.charAt(j);
				i++;
			}
			if (i == 3) return codiceCognomePersona.toString();
		}
		
		//se, anche dopo aver controllato le vocali, restano spazi vuoti (dei 3 totali) si riempiono gli spazi con questo metodo
		return riempiSpaziX(i, codiceCognomePersona).toString();
	}
	
////////////////////////////////////////////////////////////////////////////
	private String creaCodiceNome(String nomePersona) {
		int i = 0;
		char[] codiceNomePersona = new char[3];
		if (contaConsonanti(nomePersona) >= 4) {
			
			
		} else {
			codiceNomePersona = estraiPrimeTreConsonanti(nomePersona);
		}
		
		
		return codiceNomePersona.toString();
	}


/////////////////////////////////////////////////////////////////////////////
//crea una sottostringa di due numeri da un int preso in ingresso e la ritorna
	public static String creaCodiceAnno(int annoPersona) {	
	return String.valueOf(annoPersona).substring(2);
	}
	
	
	private char[] riempiSpaziX(int k, char[] codiceConSpaziVuoti) {
		char[] codiceDaRiempire = codiceConSpaziVuoti;
		//se anche le vocali sono insufficienti si riempiono gli spazi rimasti con delle 'X'
		do {
			codiceDaRiempire[k] = 'X';
			k++;
		} while (k < 3);
		return codiceDaRiempire;
	}
	
	
	public static char[] estraiPrimeTreConsonanti(String datoPersona) {
		char[] codicePrimeTreConsonanti = new char[3];
		int k = 0;
		//scorre il cognome (o nome) della persona preso in ingresso
		for (int j = 0; j < datoPersona.length() ; j++) {
			if (isConsonant(datoPersona.charAt(j))) {
				codicePrimeTreConsonanti[k] = datoPersona.charAt(j);
				k++;
			}
			
			//se si arriva già a 3 caratteri estratti non serve controllare anche le altre lettere
			if (k == 3) return codicePrimeTreConsonanti;
			
		}
		return codicePrimeTreConsonanti;
	}
	
	//conta le consonanti contenute in una parola (usato per il metodo creaCodiceNome)
		public static int contaConsonanti(String parola) {
			int numConsonanti = 0;
			//si scorre la parola per controllare ogni lettera
			for (int i = 0; i < parola.length(); i++) {
				if (isConsonant(parola.charAt(i))) numConsonanti++;		
			}
			return numConsonanti;
		}
		
		
		
		
		//controlla che un carattere dato in ingresso sia una consonante
		public static boolean isConsonant(char carattere) {
			if (carattere != 'a' || carattere == 'e' || carattere == 'i' || carattere == 'o' || carattere == 'u' || carattere == 'A' || carattere == 'E' || carattere == 'I' || carattere == 'O' || carattere == 'U') return false;
				else return true;
		}
		
		
		public void stampaCodice(char[] codiceDaStampare) {
			System.out.println(codiceDaStampare.toString());
		}
}

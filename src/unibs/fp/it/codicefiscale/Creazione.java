package unibs.fp.it.codicefiscale;

import java.util.Iterator;


public class Creazione {
	public static String creaCodice(Persona persona) {
		String codice = new String(new char[16]);
		
		String codiceCognome = creaCodiceCognome(persona.getCognome());
		String codiceNome = creaCodiceNome(persona.getNome());
		String data = persona.getData();
		String anno = data.substring(0, 3);
		String mese = data.substring(5, 7);
		String giorno = data.substring(9);
		String codiceAnno = creaCodiceAnno(anno);
		String codiceMese = creaCodiceMese(mese);
		String codiceGiorno = creaCodiceGiorno(giorno, persona.getSesso());
		
		/*
		 * 	DOPO AVER CREATO TUTTI I METODI PER CREARE LE STRINGHE / CODICI, SCOMMENTARE IL COMMENTO QUA SOTTO!!
		 */

		codice = codiceCognome + codiceNome + codiceAnno + codiceMese + codiceGiorno; // + codiceComune + codiceControllo;
		return codice;
	}
	
	
///////////////////////////////////////////////////////////////////////////////
	//crea il codice iniziale di 3 caratteri dal cognome
	public static String creaCodiceCognome(String cognomePersona) {
		String codiceCognomePersona = "";
	
		codiceCognomePersona = estraiPrimeTreConsonanti(cognomePersona, "cognome");
		
		//se vengono trovate 3 consonanti viene ritornato il codice composto da 3 consonanti
		if (codiceCognomePersona.length() == 3) return codiceCognomePersona;
		else {
			//se non vengono trovate 3 consonanti si passa a questo for, che cerca le vocali e riempie gli eventuali spazi svuoti
			for (int j = 0; j < cognomePersona.length(); j++) {
					
				if (isConsonant(cognomePersona.charAt(j)) == false) {
					codiceCognomePersona += cognomePersona.charAt(j);
				}
				if (codiceCognomePersona.length() == 3) return codiceCognomePersona.toString();
			}
		
			//se, anche dopo aver controllato le vocali, restano spazi vuoti (dei 3 totali) si riempiono gli spazi con questo metodo
			return riempiSpaziX(codiceCognomePersona).toString();
			}
	}
	
////////////////////////////////////////////////////////////////////////////
	public static String creaCodiceNome(String nomePersona) {
		int i = 0;
		String codiceNomePersona = "";
		
		codiceNomePersona = estraiPrimeTreConsonanti(nomePersona, "nome");
		
		
		return codiceNomePersona;
	}


/////////////////////////////////////////////////////////////////////////////
//crea il codice per l'anno di nascita, una sottostringa di due numeri da un int preso in ingresso e la ritorna
	public static String creaCodiceAnno(String annoPersona) {	
	return String.valueOf(annoPersona).substring(2);
	}
	
	
/////////////////////////////////////////////////////////////////////////////
	//in base al mese di nascita preso come stringa (int non accetta 08, 09) ritorna la lettera corrispondente
	public static String creaCodiceMese(String mesePersona) {
		String meseCodice = "";
		switch (mesePersona) {
		case "01":
			meseCodice = "A";
			break;
		case "02":
			meseCodice = "B";
			break;
		case "03":
			meseCodice = "C";
			break;
		case "04":
			meseCodice = "D";
			break;
		case "05":
			meseCodice = "E";
			break;
		case "06":
			meseCodice = "H";
			break;
		case "07":
			meseCodice = "L";
			break;
		case "08":
			meseCodice = "M";
			break;
		case "09":
			meseCodice = "P";
			break;
		case "10":
			meseCodice = "R";
			break;
		case "11":
			meseCodice = "S";
			break;
		case "12":
			meseCodice = "T";
			break;

		default:
			break;
		}
		
		return meseCodice;
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////
	//prendo in ingresso il giorno di nascita e il sesso; torno il codice del giorno (se F aggiungo +40)
	public static String creaCodiceGiorno(String giornoPersona, String sessoPersona) {

		//se il sesso è F
		if (sessoPersona.equals("F")) {
			//converto la stringa del giorno in int
			int giornoPersonaInt = Integer.parseInt(giornoPersona);
			giornoPersonaInt += 40;
			//riconverto da int a string 
			giornoPersona = Integer.toString(giornoPersonaInt);
		}

		return giornoPersona;
	}
	
////////////////////////////////////////////////////////////////////////
	public static String creaCodiceComune(String comunePersona) {
		String codiceComunePersona = Dati.leggiInputComuni(comunePersona);
		return codiceComunePersona;
	}
	
	
	
	public static String riempiSpaziX(String codiceConSpaziDaRiempire) {
		//se anche le vocali sono insufficienti si riempiono gli spazi rimasti con delle 'X'
		do {
			codiceConSpaziDaRiempire += "X";
		} while (codiceConSpaziDaRiempire.length() < 3);
		return codiceConSpaziDaRiempire;
	}
	
	
	public static String estraiPrimeTreConsonanti(String datoPersona, String nomeCognome) {
		String codiceTreConsonanti = "";
		
		if ((nomeCognome.equals("nome") && (contaConsonanti(datoPersona) <= 3) || nomeCognome.equals("cognome"))) {
		//scorre il cognome (o nome) della persona preso in ingresso
			for (int j = 0; j < datoPersona.length() ; j++) {
				if (isConsonant(datoPersona.charAt(j))) {
					codiceTreConsonanti += datoPersona.charAt(j);
				}
			
				//se si arriva già a 3 caratteri estratti non serve controllare anche le altre lettere
				if (codiceTreConsonanti.length() == 3) return codiceTreConsonanti;
				}
			}
		
		else {
			String codiceConsonanti = "";
			//crea con prima, terza  e quarta consonante
			for (int j = 0; j < datoPersona.length() ; j++) {
				if (isConsonant(datoPersona.charAt(j))) {
					codiceConsonanti += datoPersona.charAt(j);
				}
			}
			codiceTreConsonanti = codiceConsonanti.substring(0, 1) + codiceConsonanti.substring(2, 4); 
		}
		return codiceTreConsonanti;
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
			if (carattere == 'a' || carattere == 'e' || carattere == 'i' || carattere == 'o' || carattere == 'u' || carattere == 'A' || carattere == 'E' || carattere == 'I' || carattere == 'O' || carattere == 'U') return false;
				else return true;
		}
		
		
		public void stampaCodice(char[] codiceDaStampare) {
			System.out.println(codiceDaStampare.toString());
		}
}